<template>
  <span class="mr-3">
    <label class="input-file-button" for="input-file">
      📷
    </label>
    <input
      ref="files"
      @change="imageUpload"
      multiple
      type="file"
      id="input-file"
      style="display:none;"
      accept="image/jiff, image/pjpeg, image/jpeg, image/pjp, image/jpg, image/png, image/gif, image/tiff, image/tif"
    />
  </span>
</template>

<script>
export default {
  name: 'CameraButton',
  data() {
    return {
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
    };
  },
  methods: {
    imageUpload() {
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          //이미지 업로드
          {
            //실제 파일
            file: this.$refs.files.files[i],
            //이미지 프리뷰
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            //삭제및 관리를 위한 number
            number: i + this.uploadImageIndex,
          },
        ];
        num = i;
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;

      this.$emit('uploadImage', this.files);
    },
  },
};
</script>

<style></style>
