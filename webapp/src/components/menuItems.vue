<template>
  <!-- <el-card>
          
      </el-card> -->
  <!-- <el-button type="primary">主要按钮</el-button> -->
  <div class="block">
    <span class="demonstration"></span>
    <el-cascader
      v-model="value"
      :options="options"
      :props="{ expandTrigger: 'hover' }"
      @active-item-change="handleChange"
      @change="handleChange"
    ></el-cascader>
  </div>
</template>

<script>
import axios from "axios";

export default {
  mounted() {
    this.init();
  },
  setup() {},
  data() {
    return {
      refrash: true,
      value: [],
      options: [
      ],
    };
  },
  methods: {
    reload() {
      this.refrash = false;
      this.$nextTick(function () {
        this.refrash = true;
      });
    },
    handleChange(value) {
      console.log(value);
      this.load(value);
    },
    init() {
      let _this = this;
      axios({
        method: "post",
        data: {},
        url: "http://localhost:80/menu/findAllCountry",
        // url: "http://127.0.0.1:4523/m1/1847345-0-default/findAllCountry",
      }).then(function (res) {
        // console.log(res.data);
        for (let i = 0; i < res.data.length; i++) {
          let tmp = {
            value: res.data[i].code,
            label: res.data[i].name,
            children: [],
          };
          _this.options.push(tmp);
        }
        console.log(_this.options);
      });
    },
    load(code) {
      //   console.log(code);
      let _this = this;
      axios({
        method: "post",
        // data: {
        //   code: code[0],
        // },
        params: {
          code: code[0],
        },
        url: "http://localhost:80/menu/findByCode",
      }).then(function (res) {
        console.log(res.data);
        for (let j = 0; j < _this.options.length; j++) {
          if (_this.options[j].value == code[0]) {
            for (let i = 0; i < res.data.length; i++) {
              let tmp = {
                value: res.data[i].id,
                label: res.data[i].name,
              };
              _this.options[j].children.push(tmp);
            }
            break;
          }
        }
      });
    },
  },
};
</script>

<style scoped>
.plain {
  width: 80%;
}
</style>