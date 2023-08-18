<template>
  <body>
    <div class="cardList">
      <button class="cardList__btn btn btn--left" @click="left">
        <div class="icon">
          <svg>
            <use xlink:href="#arrow-left"></use>
          </svg>
        </div>
      </button>

      <div class="cards__wrapper">
        <div class="card current--card">
          <div class="card__image">
            <img :src="picC" alt="" />
          </div>
        </div>

        <div class="card next--card">
          <div class="card__image">
            <img :src="picNex" alt="" />
          </div>
        </div>

        <div class="card previous--card">
          <div class="card__image">
            <img :src="picPre" alt="" />
          </div>
        </div>
      </div>

      <button class="cardList__btn btn btn--right" @click="right">
        <div class="icon">
          <svg>
            <use xlink:href="#arrow-right"></use>
          </svg>
        </div>
      </button>
    </div>

    <div class="infoList">
      <div class="info__wrapper">
        <div class="info current--info">
          <h1 class="text name">{{this.curText.name}}</h1>
          <h4 class="text location">{{this.curText.prefe}}</h4>
          <p class="text description">{{this.curText.year}}</p>
          <p class="text description">{{this.curText.type}}</p>
        </div>

        <div class="info next--info">
          <h1 class="text name">{{this.nextText.name}}</h1>
          <h4 class="text location">{{this.nextText.prefe}}</h4>
          <p class="text description">{{this.nextText.year}}</p>
          <p class="text description">{{this.nextText.type}}</p>
        </div>

        <div class="info previous--info">
          <h1 class="text name">{{this.preText.name}}</h1>
          <h4 class="text location">{{this.preText.prefe}}</h4>
          <p class="text description">{{this.preText.year}}</p>
          <p class="text description">{{this.preText.type}}</p>
        </div>
      </div>
    </div>

    <div class="app__bg">
      <div class="app__bg__image current--image">
        <img :src="picC" alt="" />
      </div>
      <div class="app__bg__image next--image">
        <img :src="picNex" alt="" />
      </div>
      <div class="app__bg__image previous--image">
        <img :src="picPre" alt="" />
      </div>
    </div>

    <div class="loading__wrapper">
      <div class="loader--text">Loading...</div>
      <div class="loader">
        <span></span>
      </div>
    </div>

    <svg class="icons" style="display: none">
      <symbol
        id="arrow-left"
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 512 512"
      >
        <polyline
          points="328 112 184 256 328 400"
          style="
            fill: none;
            stroke: #fff;
            stroke-linecap: round;
            stroke-linejoin: round;
            stroke-width: 48px;
          "
        />
      </symbol>
      <symbol
        id="arrow-right"
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 512 512"
      >
        <polyline
          points="184 112 328 256 184 400"
          style="
            fill: none;
            stroke: #fff;
            stroke-linecap: round;
            stroke-linejoin: round;
            stroke-width: 48px;
          "
        />
      </symbol>
    </svg>
  </body>
</template>

<script>
import axios from "axios";

export default {
  created() {
    this.addScript('script.js');
  },
  mounted() {
    this.userid = window.localStorage.getItem("userID");
    this.size = window.localStorage.getItem("size");
    this.getData(this.userid,this.size);
    this.showData();

  },
  setup() {},
  data() {
    return {
      userid:'',
      size:'',

      seqNum : 0,
      seq : [[0,1,2],[2,0,1],[1,2,0]],
      curNum: 0,
      picC:'',
      picNex:'',
      picPre:'',

      curText: {},
      nextText: {},
      preText: {},

      picSrc: [],
      picText: [
        
      ],
      
    };
  },
  methods: {
    right(){
      //((-1 % 5) + 5) % 5
      this.curNum = (((this.curNum + 1) % this.picSrc.length) + this.picSrc.length) % this.picSrc.length;
      this.seqNum = (((this.seqNum + 1) % this.seq.length) + this.seq.length) % this.seq.length;
      this.showData();
    },
    left(){
      this.curNum = (((this.curNum - 1) % this.picSrc.length) + this.picSrc.length) % this.picSrc.length;
      this.seqNum = (((this.seqNum - 1) % this.seq.length) + this.seq.length) % this.seq.length;
      this.showData();
    },
    seqDo(pre,cur,nex){
      this.picC = 'img/movies_pic/' + this.picSrc[cur] + '.webp';
      this.picNex = 'img/movies_pic/' + this.picSrc[nex] + '.webp';
      this.picPre = 'img/movies_pic/' + this.picSrc[pre] + '.webp';

      this.curText = this.picText[cur];
      this.nextText = this.picText[nex];
      this.preText = this.picText[pre];
    },
    showData(){
      let cur = ((this.curNum % this.picSrc.length) + this.picSrc.length) % this.picSrc.length;
      let nex = (((this.curNum + 1) % this.picSrc.length) + this.picSrc.length) % this.picSrc.length;
      let pre = (((this.curNum - 1) % this.picSrc.length) + this.picSrc.length) % this.picSrc.length;

      let index = [pre,cur,nex];
      this.seqDo(index[this.seq[this.seqNum][0]],index[this.seq[this.seqNum][1]],index[this.seq[this.seqNum][2]]);
      
      console.log(this.picSrc[pre] + "---" + this.picSrc[cur] + '---' + this.picSrc[nex] )
      console.log(pre + "---" + cur + '---' + nex )
      console.log(this.picPre + '--' + this.picC + '--' + this.picNex)
    },
    getData(id,sz) {
      let _this = this;
      axios({
        method: "post",
        params: {
          userID : id,
          size : sz
        },
        url: "http://localhost:80/movie/ranking",
      }).then(function (res) {
        console.log(res.data);
        for (let i = 0; i < res.data.length; i++) {
          _this.picSrc.push(res.data[i].id.toString());
          _this.picText.push({
            name : res.data[i].name,
            prefe: res.data[i].preference.toString().substring(0,3),
            type: res.data[i].type,
            year: res.data[i].published_year,
        });
        }
        console.log(_this.picSrc)
        console.log(_this.picText)
        _this.showData();
      });
    },
    
    addScript(url) {
      var script = document.createElement("script");
      script.setAttribute("type", "text/javascript");
      script.setAttribute("src", url);
      document.getElementsByTagName("body")[0].appendChild(script);
    },
    addMainScript(url) {
      var script = document.createElement("script");
      script.setAttribute("type", "javascript");
      script.setAttribute("src", url);
      document.getElementsByTagName("body")[0].appendChild(script);
    },
  },
};
</script>

<style scoped>
@import "../assets/css/style.css";
@import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.1/css/all.min.css");
@import url("https://fonts.googleapis.com/css?family=Montserrat&amp;display=swap");
@import url("https://unpkg.com/element-ui/lib/theme-chalk/index.css");
</style>