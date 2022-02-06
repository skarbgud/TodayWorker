<template>
  <div>
    <div class="container">
      <div class="arrow"></div>
      <div class="eq8" id="roullete">
        <div class="panel panel_1" style="background: red;">
          <strong class="txt">1</strong>
        </div>
        <div class="panel panel_2" style="background: #F2CB61;">
          <strong class="txt">2</strong>
        </div>
        <div class="panel panel_3" style="background: #FAECC5;">
          <strong class="txt">3</strong>
        </div>
        <div class="panel panel_4" style="background: #665C00;">
          <strong class="txt">4</strong>
        </div>
        <div class="panel panel_5" style="background: #476600;">
          <strong class="txt">5</strong>
        </div>
        <div class="panel panel_6" style="background: #2F9D27;">
          <strong class="txt">6</strong>
        </div>
        <div class="panel panel_7" style="background: #003399;">
          <strong class="txt">7</strong>
        </div>
        <div class="panel panel_8" style="background: #6B66FF;">
          <strong class="txt">8</strong>
        </div>
      </div>
    </div>
    <!-- <div class="rouletter">
      <div class="rouletter-bg"><div class="rouletter-wacu"></div></div>
      <div class="rouletter-arrow"></div>
      <button class="rouletter-btn" ref="btn" @click="startBtn()">
        start
      </button>
    </div> -->
    <b-form-input
      v-model="inputText"
      placeholder="메뉴를 추가하세요"
    ></b-form-input>
  </div>
</template>
<script>
  export default {
    name: "EatContent",
    data() {
      return {
        rolLength: 6,
        setNum: 0,
        inputText: "",
      };
    },
    mounted() {
      this.setRoulettePanel();
    },
    methods: {
      getRandomNum() {
        const min = Math.ceil(0);
        const max = Math.floor(this.rolLength - 1);
        return Math.floor(Math.random() * (max - min)) + min;
      },
      rotate() {
        const panel = document.querySelector(".rouletter-wacu");
        const deg = [];
        for (let i = 1, len = this.rolLength; i <= len; i++) {
          deg.push((360 / len) * i);
        }

        let num = 0;
        this.setNum = this.getRandomNum();
        const animate = setInterval(() => {
          num++;
          panel.style.transform = "rotate(" + 0.1 * num + "turn)";

          this.$refs.btn.disabled = true;

          if (num === 50) {
            clearInterval(animate);
            panel.style.transform = "rotate(" + deg[this.setNum] + "deg)";
          }
        }, 100);
      },
      rLayerPopup() {
        switch (this.setNum) {
          case 1:
            alert("당첨!! 스타벅스 아메리카노");
            break;
          case 3:
            alert("당첨!! 햄버거 세트 교환권");
            break;
          case 5:
            alert("당첨!! CU 3,000원 상품권");
            break;
          default:
            alert("꽝! 다음기회에");
        }
      },
      rReset() {
        setTimeout(() => {
          this.$refs.btn.disabled = false;
          this.rLayerPopup(this.setNum);
        }, 5500);
      },
      startBtn() {
        this.rotate();
        this.rReset();
      },
      setRoulettePanel() {
        const panelArr = document.querySelectorAll(".panel");
        const panelArrSize = panelArr.length;
        const rotate = 360 / panelArrSize;

        let i = 0;
        let panelRotate = 0;
        const panelInfoTbody = [];
        while (i < panelArrSize) {
          panelRotate = panelRotate + rotate;
          panelArr[i].style.transform = "rotate(" + panelRotate + "deg)";
          i = i + 1;
          panelInfoTbody.push(
            '<tr><td id="panelInfo' +
              i +
              '" style="background:' +
              panelArr[i - 1].style.background +
              "; color:" +
              panelArr[i - 1].style.color +
              '">' +
              panelArr[i - 1].innerText +
              "</td>"
          );
          panelInfoTbody.push(
            "<td><button onclick=\"openUpdatePop('" +
              i +
              "');\">수정</button></td></tr>"
          );
        }
        panelInfoTbody.push(
          '<tr><td colspan="2"><button class="addBtn" onclick="openAddPop(\'add\');">ADD</button></td></tr>'
        );
        document.getElementById(
          "panelInfoTbody"
        ).innerHTML = panelInfoTbody.join("");
      },
    },
  };
</script>
<style lang="scss" scoped>
  #roullete {
    width: 400px;
    height: 400px;
    border-radius: 50%;
    background: white;
    border: 3px solid black;
    position: relative;
  }
  .container {
    display: grid;
    min-height: 100%;
    align-content: center;
    justify-content: center;
  }
  .arrow {
    left: 50%;
    position: sticky;
    z-index: 1;
    width: 0;
    height: 0;
    border-top: 30px solid purple; /* 화살표 */
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
  }
  .txt {
    font-size: 24px;
    width: 260px;
    height: 260px;
    position: absolute;
    top: 30px;
    left: 30px;
    text-align: center;
    transform: rotate(-67deg);
  }

  .panel {
    position: absolute;
    width: 400px;
    height: 400px;
    border-radius: 50%;
    -webkit-clip-path: polygon(0% 0%, 50% 50%, 0% 50%, 0% 0%) !important;
    clip-path: polygon(0% 0%, 50% 50%, 0% 50%, 0% 0%) !important;
  }

  // @@@@

  .rouletter {
    position: relative;
    width: 400px;
    height: 400px;
    margin: 40px;
  }
  .rouletter-bg {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 350px;
    height: 350px;
    border-radius: 350px;
    overflow: hidden;
  }
  .rouletter-wacu {
    width: 100%;
    height: 100%;
    background: #f5f5f2
      url("https://m.lifeplanet.co.kr:444/commons/slink/administrator/openInnovation/img/MO)%20360%ED%94%8C%EB%9E%98%EB%8B%9B_%EB%A3%B0%EB%A0%9B%ED%8C%90_476x476_201026.png")
      no-repeat;
    background-size: 100%;
    transform-origin: center;
  }
  .rouletter-arrow {
    position: absolute;
    top: 0;
    left: 50%;
    transform: translateX(-50%);
    width: 1px;
    height: 1px;
    border-right: 10px solid transparent;
    border-left: 10px solid transparent;
    border-top: 40px solid red;
    border-bottom: 0px solid transparent;
  }
  .rouletter-btn {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 80px;
    height: 80px;
    border-radius: 80px;
    background: #fff;
    border-image: linear-gradient(to right, #fbfcb9be, #ffcdf3aa, #65d3ffaa);
    border: 2px solid;
  }

  .on {
    animation-name: ani;
    animation-duration: 0.1s;
    animation-fill-mode: forwards;
    animation-iteration-count: infinite;
  }

  @keyframes ani {
    0% {
      transform: rotate(0deg);
      transition-timing-function: ease-out;
    }
    100% {
      transform: rotate(360deg);
    }
  }
</style>
