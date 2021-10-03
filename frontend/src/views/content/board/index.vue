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
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import CardList from '@/views/components/cards/CardList';
import SwiperBar from '@/views/components/swiper/SwiperBar';
import { axiosService } from '@/api/index';

export default {
  name: 'BoardList',
  components: { CardList, SwiperBar },
  created() {
    this.getBoardListApi();
  },
  data() {
    return {
      boardPath: '',
      post: [],
    };
  },
  methods: {
    getBoardListApi() {
      axiosService.post(`/board/get-board-list.do`)
        .then((response) => {
          if(response.data.success)
          {
            this.post = response.data.data;
          }
          else {
            console.log('데이터 불러오기 실패');
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
