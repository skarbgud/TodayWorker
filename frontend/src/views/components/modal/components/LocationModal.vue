<template>
  <el-dialog
    :visible.sync="locationVisible"
    width="30%"
    :fullscreen="modalFull"
    @close="close"
    append-to-body
  >
    <!-- 모달 제목  -->
    <span slot="title">
      <span class="write-modal-title">
        동네 태그
      </span>
    </span>

    <!-- 위치 지도 -->
    <div id="map" style="width:500px;height:400px;"></div>
  </el-dialog>
</template>

<script>
export default {
  name: 'LocationModal',
  props: ['position'],
  data() {
    return {
      map: null,
      locationVisible: false,
      modalFull: false,
    };
  },
  mounted() {
    this.initMap();
    window.addEventListener('resize', this.handleResize);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
  },
  watch: {
    width: {
      immediate: true,
      handler() {
        this.handleResize();
      },
    },
  },
  methods: {
    // 반응형을 위한 사이즈
    handleResize() {
      this.width = window.innerWidth;
      if (this.width < 950) {
        this.modalFull = true;
      } else {
        this.modalFull = false;
      }
    },
    // 모달창 열기
    open() {
      this.locationVisible = true;
    },
    // 모달 닫기
    close() {
      this.locationVisible = false;
    },
    addKakaoMapScript() {
      const script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=16aa0ff8556c688656fe838916e638c1';
      document.head.appendChild(script);
    },
    initMap() {
      let container = document.getElementById('map');
      let options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 3,
      };

      let map = new kakao.maps.Map(container, options);

      this.map = map;

      this.geocoder = new kakao.maps.services.Geocoder();
    },
  },
};
</script>

<style lang="scss" scoped>
@import '@/assets/scss/components/locationModal.scss';
.map {
  width: 100%;
  height: 400px;
}
</style>
