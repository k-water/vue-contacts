<template>
  <div id="app">
    <router-view keep-alive transition="fade" transition-mode='out-in'></router-view>
  </div>
</template>

<script>
  export default {
    data() {
      return {
      }
    },
    components: {
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
  #app {
    height: 100%;
  }
  body {
    background: #fff;
  }
</style>