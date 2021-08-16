<template>
 <div>
  <b-card :title="post.title" class="card-link">

      <b-card-text class="mt-4 mb-4">
        <b-link  class="card-link">
        {{post.content}}
        </b-link>
      </b-card-text>

    <!--유저 정보-->
    <div class="user-info">
        <a href="#" class="card-link">삼성전자</a>
        <b-link href="#" class="card-link">m****</b-link><br>
    </div>

    <!-- 조회수, 좋아요, 댓글 갯수-->
    <div class="icon-color mt-1">
    <b-icon class="ml-0 " icon="hand-index-thumb" /> 505
     <b-icon class="mx-2 ml-3 " icon="hand-thumbs-up"/>좋아요
     <b-icon class="mx-2 ml-3 " icon="chat"/>38
    </div>
  </b-card>
</div>
</template>

<script>
export default {
    props:{
     post: {
      type: Object,
    },
    },
    data() {
        return {
            
        }
    }
}
</script>

<style>
.icon-color{
    color:rgb(145, 141, 141);
    font-size: 14px;
}
a{
 color: black;
}
.user-info {
    font-size: 14px;
}
</style>