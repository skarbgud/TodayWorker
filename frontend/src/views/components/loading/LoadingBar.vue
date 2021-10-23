<template>
<div v-if="this.loading">
  <b-container fluid class="w-100 h-100 m-0 p-0 d-flex flex-column">
    <b-row class="m-0 flex-grow-1 d-flex justify-content-start">
      <b-col align-self="center" class="text-center my-2">
        <b-spinner class="align-middle mr-1" :variant=this.variant></b-spinner>
        <strong>Loading...</strong>
      </b-col>
    </b-row>
  </b-container>
</div>
</template>

<script>
export default {
  name: 'ProgressBar',
  props: ['variant', 'loading']
}
</script>

<style scoped>

</style>
