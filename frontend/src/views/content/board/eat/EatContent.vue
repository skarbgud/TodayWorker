<template>
  <div>
    <div class="container">
      <div class="arrow"></div>
      <div class="eq8" id="roullete">
        <div
          v-for="(item, index) in pannalList"
          :key="index"
          class="panel panel_1"
          :style="`background: ${colorList[index]};`"
        >
          <strong class="txt">{{ item }}</strong>
        </div>
        <!-- <div class="panel panel_2" style="background: #F2CB61;">
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
        </div> -->
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
      @keyup.enter="addItem()"
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
        pannalList: ["1번"],
        colorList: [
          "#F2CB61",
          "#FAECC5",
          "#665C00",
          "#476600",
          "#2F9D27",
          "#003399",
          "#6B66FF",
          "#F2CB61",
        ],
        clipPathList: [
          "",
          "50% -83%, 105% 50%, 50% 50%, -6% 50%",
          "102% -40%, 50% 50%, 0% 50%, 0% 0%",
          "50% -30%, 50% 50%, 0% 50%, 0% 0%",
          "28% -22%, 50% 50%, 0% 50%, 0% 0%",
          "12% -20%, 50% 50%, 0% 50%, 0% 0%",
          "0% -18%, 50% 50%, 0% 50%, 0% 0%",
        ],
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
      rReset() {
        setTimeout(() => {
          this.$refs.btn.disabled = false;
        }, 5500);
      },
      startBtn() {
        this.rotate();
        this.rReset();
      },
      addItem() {
        if (!this.inputText) return;
        this.pannalList.push(this.inputText);
        this.inputText = "";
        this.$nextTick(() => {
          this.setRoulettePanel();
        });
      },
      setRoulettePanel() {
        const panelArr = document.querySelectorAll(".panel");
        const txtEl = document.querySelectorAll(".txt");
        const panelArrSize = panelArr.length;
        const rotate = 360 / panelArrSize;

        console.log(panelArr.length);

        for (const item of txtEl) {
          item.style.transform = `rotate(${-rotate}deg)`;
        }

        let sumDeg = 0;
        for (let i = 0; i < panelArrSize; i++) {
          panelArr[i].style.clipPath = `polygon(${
            this.clipPathList[panelArrSize - 1]
          })`;
          panelArr[i].style.transform = `rotate(${sumDeg}deg)`;
          sumDeg += rotate;
        }
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
    transform: rotate(-58deg);
  }

  .panel {
    position: absolute;
    width: 400px;
    height: 400px;
    border-radius: 50%;
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
