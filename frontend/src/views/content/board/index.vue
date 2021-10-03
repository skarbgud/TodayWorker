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
      post: [
        {
          index: 1,
          title: '강원도여행 가려는데',
          content:
            'asdfdf  lkadsjflkdjflkdf ㅣㅏ어라ㅣㅓㅏㅣ어라ㅣ어라어러아ㅣ러  ㅣㅏㅁㅇ렁나렁ㅁㄴ러ㅣㅇ너리ㅏㅁㅇ너라ㅣㅓㅇㅁ나ㅣ렁나ㅣㅓ이ㅏ링너라ㅣ  ㅣㄴㅁㅇ렁나러ㅏㅣㅇㄴ러ㅣㅁㄴ리ㅏㅓㅣㅇㅁ나 ㅣㅏㅇ나러아ㅣ',
        },
        {
          index: 2,
          title: '롤렉스 시계 선물어때?',
          content: '어떻게 생각해',
        },
        {
          index: 3,
          title: '테스트세트스',
          content: '오오아아아아아',
        },
        {
          index: 4,
          title: '우오아아아아ㅏㅇ',
          content: '오오아아아아아',
        },
      ],
    };
  },
  methods: {
    getBoardListApi() {
      axiosService.post(`/board/get-board-list.do`)
        .then((response) => {
          if(response.data.success)
          {
            console.log(response.data.data);
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
