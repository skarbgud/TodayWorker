<template>
  <div>
      
    <b-nav>
        <b-nav-item v-for="(title,index) in cardTitles" :key="index" class="item" @click="golive(index)">
            {{cardTitles[index].title}}
        </b-nav-item>
  </b-nav>
</div>
</template>

<script>
import cardTitles from '@/constant/index';

export default {
    data() {
        return{
            cardTitles,
        }
    },
     methods: {
    golive(index) {
            console.log(this.cardTitles[index]);
            this.$router.push(this.cardTitles[index].path).catch(() => {});
      },
    }
}
</script>

<style>
.item:hover {
 border-bottom: 2px solid #0b0c0c;
}
</style>