<template>
  <b-form-textarea
    class="mt-3 mb-3 input-area"
    id="textarea-rows"
    :placeholder="contentPlaceholder"
    :rows="rows"
    :max-rows="maxRow"
    no-resize
    v-model="content"
  ></b-form-textarea>
</template>

<script>
export default {
  name: 'TextArea',
  props: {
    // 최소 보여주게 될 rows 수
    rows: {
      type: Number,
    },
    // 최대로 늘어나게 될 row수
    maxRow: {
      type: Number,
    },
    // placeholder
    contentPlaceholder: {
      type: String,
    },
  },
};
</script>

<style></style>
