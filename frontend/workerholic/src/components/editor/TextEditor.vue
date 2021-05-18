<template>
<span>
  <el-input ref="editor" v-if="visibleEditor" v-model="editing.value" size="mini" :edit="true" controls-position="right" @blur="endEdit()" @keyup.enter.native="endEdit()"></el-input>
  <slot v-else name="data"></slot>
</span>
</template>

<script>
export default {
  name: 'w-text-editor',
  props: ['editing', 'target', 'property'],
  computed: {
    visibleEditor() {
      return this.editing.isEditing && this.editing.target === this.target && this.editing.property === this.property;
    },
  },
  watch: {
    visibleEditor(to) {
      if (to) {
        // editing.js의 startEdit에서 nextTick으로 focus줄 경우 아직 관찰자가 변경을 감지하고 update하기 전이라 ref를 못찾아옴.
        // 감지된 변경에 맞게 update완료 후 포커싱이 가도록 한다.
        this.$nextTick(() =>  this.$refs.editor.focus());
      }
    },
  },
  methods: {
    endEdit() {
      this.$emit('endEdit', this.editing.value);
    },
  },
};
</script>

<style>

</style>
