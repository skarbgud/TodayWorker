<template>
  <!-- Navigation 영역 -->
  <nav>
    <ul style="height: calc(100% - 100px);">
      <!-- <li class="logo-wrapper">
        <div>
          <i class="navbar-brand-icon"></i>
          <span>WorkerHolic</span>
        </div>
      </li> -->
      <li class="unread">
        <div>
          <i class="fas fa-tachometer-alt"></i>
          <span>A</span>
        </div>
      </li>
      <li class="unread">
        <div>
          <i class="fas fa-sitemap"></i>
          <span>B</span>
        </div>
      </li>
      <li class="unread" @click="perfClick()">
        <div>
          <i class="fas fa-chart-line"></i>
          <span>C</span>
        </div>
      </li>
    </ul>
    <ul class="fix-menu" style="height: 100px;">
      <li class="unread">
        <div>
          <i class="fas fa-file-alt"></i>
          <span>D</span>
        </div>
      </li>
      <li class="unread" @click="settingsClick()">
        <div>
          <i class="fas fa-cog"></i>
          <span>설정</span>
        </div>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'FrameMenu',
  methods: {
  },
};
</script>

<style></style>
