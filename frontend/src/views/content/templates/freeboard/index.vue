<template>
  <div>
     <b-container>
      <div style="justify-content-md-center" class="mt-4">
        <nav-bar></nav-bar>
        <b-row
          align-h="center"
          class="justify-content-center"
          cols="1"
          cols-sm="1"
          cols-md="2"
          cols-lg="2"
        >
          <b-col
            class="pr-0 pl-0"
            v-for="(title, index) in post"
            :key="index"
          >
            <card-list :post="post[index]"></card-list>
          </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
 
</template>

<script>
import CardList from '../../../components/cards/CardList.vue';
import NavBar from '../../../components/navBar.vue';
export default {
  components: { CardList, NavBar },
  name: 'FreeBoard',
  data() {
    return {
        post: [
          { 
          "index":1,
          "title": "강원도여행 가려는데",
          "content": "어디가 좋아?? 추천해줘"
          },
          { 
          "index":2,
          "title": "롤렉스 시계 선물어때?",
          "content": "어떻게 생각해"
          },
          { 
          "index":3,
          "title": "테스트세트스",
          "content": "오오아아아아아"
          },
          { 
          "index":4,
          "title": "우오아아아아ㅏㅇ",
          "content": "오오아아아아아"
          }
        ]
    };
  },
  methods: {

  },
}
</script>

<style>
</style>