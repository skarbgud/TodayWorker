<template>
  <div>
    <b-container>
      <div style="justify-content-md-center" class="mt-4">
        <swiper-bar></swiper-bar>
        <b-row
          align-h="center"
          class="justify-content-center"
          cols="1"
          cols-sm="1"
          cols-md="2"
          cols-lg="2"
        >
          <b-col class="pr-0 pl-0" v-for="(title, index) in post" :key="index">
            <card-list
              :post="post[index]"
              @click="goDetailRouter(index)"
            ></card-list>
          </b-col>
          <infinite-loading
            @infinite="infiniteHandler"
            spinner="waveDots"
          ></infinite-loading>
        </b-row>
        <loading-bar :variant="'info'" :loading="loading"></loading-bar>
      </div>
    </b-container>
  </div>
</template>

<script>
import CardList from '@/views/components/cards/CardList';
import SwiperBar from '@/views/components/swiper/SwiperBar';
import boardApi from '@/api/board/index';
import LoadingBar from '../../components/loading/LoadingBar.vue';
import InfiniteLoading from 'vue-infinite-loading';

export default {
  name: 'BoardList',
  components: { CardList, SwiperBar, LoadingBar, InfiniteLoading },
  created() {
    this.getBoardListApi();
  },
  data() {
    return {
      loading: false,
      boardPath: '',
      post: [],
    };
  },
  computed: {
    setParams() {
      const params = {
        fromIndex: 0,
        pageSize: 10,
      };
      return params;
    },
  },
  methods: {
    // getBoardListApi() {
    //   this.loading = true;
    //   boardApi
    //     .getBoardList(this.setParams)
    //     .then((response) => {
    //       if (response.data.success) {
    //         this.post = response.data.data;
    //       } else {
    //         console.log('데이터 불러오기 실패');
    //       }
    //     })
    //     .catch(function(error) {
    //       console.log(error);
    //     })
    //     .finally(() => {
    //       this.loading = false;
    //     });
    // },
    infiniteHandler($state) {
      boardApi
        .getBoardList(this.setParams)
        .then((response) => {
          if (response.data.success) {
            this.post = this.post.concat(response.data.data);
            $state.loaded();
            this.setParams.fromIndex += 10;
            if (response.data.data.length == 0) {
              $state.complete();
            }
          } else {
            $state.complete();
            console.log('무한스크롤 실패');
          }
        })
        .catch(function(error) {
          console.log(error);
        })
        .finally(() => {});
    },
    goDetailRouter() {},
  },
};
</script>

<style></style>
