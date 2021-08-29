<template>
  <span class="mr-3">
    <label class="input-file-button" for="input-file" @click="uploadImage()">
      📷
    </label>
    <input
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
  methods: {
    uploadImage() {
      this.$emit('uploadImage', 'emitUploadImage');
      console.log('사진 업로드');
    },
  },
};
</script>

<style></style>
