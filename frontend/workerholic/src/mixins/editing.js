import _ from 'lodash';
/**
 * 단일 편집 Mixin
 * @type {Object}
 */
export default {
  data() {
    return {
      editing: {
        isEditing: false, // true면 Editor활성화 상태, false면 비활성화 상태.
        target: null, // 편집 대상 객체 (node, row 등)
        property: null, // 편집할 데이터 필드
        data: null, // 편집할 데이터
        value: null, // 편집할 값
        originalValue: null, // 편집할 값의 기존 값
      },
    };
  },
  methods: {
    /**
     * 전달한 대상의 Editor를 제공한다.
     * @param  {object} target   [편집할 대상]
     * @param  {object} data     [편집할 데이터]
     * @param  {string} property [편집할 데이터 인덱스]
     */
    startEdit(target, data, property) {
      if (!this.editing.target || !(this.editing.isEditing && this.editing.target === target)) {
        // 이미 동일 대상에 대한 Editor가 활성화된 경우 중복 설정을 하지않는다.)
        this.editing.target = target;
        this.editing.property = property;
        if (!_.has(data, 'dirty')) {
          // dirty 필드가 없는 경우 처음 편집을 하는 것이므로
          this.$set(data, 'dirty', false);
        }

        if (data.modified && data.modified[property]) {
          this.editing.originalValue = data.modified[property];
        } else {
          this.editing.originalValue = data[property];
        }

        this.editing.data = data;
        this.editing.value = data[property];

        this.editing.isEditing = true;
        // this.$nextTick(() => {
        //   console.debug('start--', this.editing.data);
        //   this.$refs.editor.focus();
        // });
      }
    },

    /**
     * 데이터 편집을 종료한다.
     * 값 변경 여부 확인 후 dirty 및 modified에 변경된 필드의 기존 값 설정
     * @param  {?} newValue [변경된 값]
     */
    endEdit(newValue) {
      const data = this.editing.data;
      const property = this.editing.property;

      if (data && property) {
        // TODO: Ediotr의 keyup 이벤트로 endEdit가 호출되는 경우, keyup 이벤트에 endEdit 한번 호출,
        // blur 이벤트에서 한번 호출 총 2번 호출이 가서 2번째에서 editing 객체가 초기화되어 에러남.
        // 일단  data와 property가 있을 경우만 하단의 로직 수행하도록 처리함.
        data[property] = newValue;

        if (this.editing.originalValue === newValue) {
          // 변경 x
          data.dirty = false;
          if (_.has(data, 'modified') && data.modified[property]) {
            delete data.modified[property];
          }
        } else {
          // 변경
          data.dirty = true;
          if (!_.has(data, 'modified')) {
            data.modified = {};
          }
          data.modified[property] = this.editing.originalValue;
        }

        this.editing = {
          isEditing: false,
          target: null,
          property: null,
          data: null,
          value: null,
          originalValue: null,
        };
      }zz
    },
  },
};
