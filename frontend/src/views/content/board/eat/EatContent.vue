<template>
  <div class="rouletter">
    <div class="rouletter-bg"><div class="rouletter-wacu"></div></div>
    <div class="rouletter-arrow"></div>
    <button class="rouletter-btn" @click="startBtn()">start</button>
  </div>
</template>
<script>
  export default {
    name: "EatContent",
    data() {
      return {
        rolLength: 6,
        setNum: 0,
      };
    },
    mounted() {
      createHiddenInput();
    },
    methods: {
      rRandom() {
        const min = Math.ceil(0);
        const max = Math.floor(this.rolLength - 1);
        return Math.floor(Math.random() * (max - min)) + min;
      },
      rRotate() {
        const panel = document.getElementsByClassName("rouletter-wacu")[0];
        const btn = document.getElementsByClassName("rouletter-btn")[0];
        const deg = [];
        for (let i = 1, len = this.rolLength; i <= len; i++) {
          deg.push((360 / len) * i);
        }

        let num = 0;
        const hiddenInput = document.getElementsByClassName("hidden-input");
        document.body.append(hiddenInput);
        this.setNum = hiddenInput.value = this.rRandom();
        const ani = setInterval(() => {
          num++;
          panel.style.transform = "rotate(" + 0.1 * num + "turn)";

          btn.disabled = true;
          btn.style.pointerEvents = "none";

          if (num === 50) {
            clearInterval(ani);
            panel.style.transform = "rotate(" + deg[this.setNum] + "deg)";
          }
        }, 100);
      },
      createHiddenInput() {
        const hiddenInput = document.createElement("input");
        hiddenInput.className = "hidden-input";
      },
      rLayerPopup() {
        switch (num) {
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
        const hiddenInput = document.createElement("input");
        const btnElement = document.getElementsByClassName("rouletter-btn");
        setTimeout(() => {
          btnElement.disabled = false;
          btnElement.style.pointerEvents = "auto";
          rLayerPopup(this.setNum);
          hiddenInput.remove();
        }, 5500);
      },
      startBtn() {
        this.rRotate();
        this.rReset();
      },
    },
  };
</script>
<style lang="scss" scoped>
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
