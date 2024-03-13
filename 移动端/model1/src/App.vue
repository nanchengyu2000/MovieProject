<template>
  <div id="app">
     <div class="main">
        <router-view></router-view>
     </div>
     <div class="tabber">
        <Tabber/>
     </div>
  </div>
</template>

<script>
import Tabber from './components/Tabber'

export default {
  name: 'App',
  components: {
    Tabber,
  },
}
</script>

<style>
  #app {
    width: 100%;
    /* height: 100vh; */
  }
  .main {
    margin-bottom: 5rem;
  }
</style>
