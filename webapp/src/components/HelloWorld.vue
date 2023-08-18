<template>
  <div class="hello">
    <el-form class="form">
      <el-form-item label="用户ID">
        <el-input v-model="id"> </el-input>
      </el-form-item>
      <el-form-item label="推荐数目">
        <el-input v-model="num"> </el-input>
      </el-form-item>
      <el-form-item>
    <el-radio-group v-model="choice">
      <el-radio label="user"></el-radio>
      <el-radio label="recommend"></el-radio>
    </el-radio-group>
  </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">提交</el-button>
        <el-button class="lable" @click="submit">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  mounted() {
    this.id = window.localStorage.getItem("userID");
    this.num = window.localStorage.getItem("size")
  },
  name: "HelloWorld",
  props: {
    msg: String,
  },
  data() {
    return{
      id:'',
      num:'',
      choice:'',
    }
  },
  methods:{
    submit(){
      localStorage.setItem('userID',this.id);
      localStorage.setItem('size',this.num);
      console.log(this.choice)
      console.log(window.localStorage.getItem("userID"))
      if(this.choice === 'recommend'){
          this.$router.push({ path: '/movie'});
      }else{
          this.$router.push({ path: '/usermovie'});
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.lable {
  margin-left: 20%;
}

.form {
  // margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
  width: 100%;
}
</style>
