<template>
  <div id="app">
    <First></First>
    <Sider></Sider>
    <!--路由出口-->
    <router-view
        keep-alive
        transition="fade"
        transition-mode='out-in'></router-view>
  </div>
</template>

<script>
  import First from './components/First'
  import Sider from './components/Sider'
  export default {
    data() {
      return {

      }
    },
    components: {
      First,
      Sider
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
    background: #E5E5E5;
  }
</style>