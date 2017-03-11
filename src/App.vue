<template>
  <div id="app">
    <Headers></Headers>
    <Navigate></Navigate>
    <!--路由出口-->
    <router-view
        keep-alive
        transition="fade"
        transition-mode='out-in'></router-view>
  </div>
</template>

<script>
  import Headers from './components/Headers'
  import Navigate from './components/Navigate'
  export default {
    data() {
      return {

      }
    },
    components: {
      Headers,
      Navigate
    }
  }
</script>

<style lang="scss">
  @import url("../node_modules/element-ui/lib/theme-default/index.css");
  * {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    margin: 0;
    padding: 0;
  }
  
  body {
    background: #fff;
  }
</style>