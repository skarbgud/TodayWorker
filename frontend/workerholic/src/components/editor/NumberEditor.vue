<template>
  <span>
    <el-input type="number" :min="minValue" :max="maxValue" ref="numberEditor" v-if="visibleEditor" v-model="editing.value" size="mini" :edit="true" controls-position="right" @blur="endEdit()" @keyup.enter.native="endEdit()"></el-input>
    <slot v-else name="data"></slot>
  </span>
</template>

<script>
export default {
  name: 'w-number-editor',
  // number editor로 min, max값을 props로 받을 수 있다.
  props: ['editing', 'target', 'property', 'minValue', 'maxValue'],
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
        this.$nextTick(() =>  this.$refs.numberEditor.focus());
      }
    },
  },
  methods: {
    endEdit() {
      this.$emit('endEdit', _.toInteger(this.editing.value));
    },
  },
}
</script>

<style>

</style>
