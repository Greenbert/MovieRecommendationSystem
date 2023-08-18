<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <!-- <MovieShow></MovieShow> -->
    <HelloWorld></HelloWorld>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import menuItems from '@/components/menuItems.vue'
import MovieShow from '@/components/MovieShow.vue'

export default {
  name: 'HomeView',
  components: {
    // MovieShow
    HelloWorld
  }
}
</script>

<style scoped>
.home{
  height: 100%;
  width: 100%;
}
</style>
