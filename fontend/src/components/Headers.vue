<template>
  <div id="first">
    <el-row class="bg-purple-light">
      <el-col :span="6">
        <div class="grid-content">
          <span class="left-h">简易通讯录</span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  export default {
    name: 'first',
    data() {
      return {
      }
    },
    components: {
    },
    methods: {
    }
  }
</script>
<style lang="scss">
  body {
    font: normal 16px Roboto,arial,sans-serif;
    -webkit-font-smoothing: antialiased;
  }
  img {
    max-width: 100%;
  }
  
  .bg-blue {
    background: #0288d1;
  }
  
  .btn {
    background: cornflowerblue !important;
    color: white !important;
    border-radius: 0px;
    &:hover {
      background: #79a4f2 !important;
    }
  }
  
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  
  .el-col {
    border-radius: 4px;
  }
  
  .bg-purple-dark {
    background: #99a9bf;
  }
  
  .bg-purple {
    background: #d3dce6;
  }
  
  .bg-purple-light {
    background: #e5e9f2;
  }
  
  .grid-content {
    min-height: 36px;
    height: 36px;
    padding: 12px 20px;
    .left-h {
      white-space: nowrap;
      z-index: 986;
      font: 1.5em/150% Roboto,RobotoDraft,Arial,sans-serif;
      padding-left: 20px;
      
      background-image: -webkit-linear-gradient(left, #3498db, #f47920 10%, #d71345 20%, #f7acbc 30%, #ffd400 40%, #3498db 50%, #f47920 60%, #d71345 70%, #f7acbc 80%, #ffd400 90%, #3498db);
      color: transparent;
      /*文字填充色为透明*/
      -webkit-text-fill-color: transparent;
      /*背景剪裁为文字，相当于用背景填充文字 CSS3新属性*/
      -webkit-background-clip: text;
      /*背景图片向水平方向扩大一倍，这样background-position才有移动与变化的空间*/
      -webkit-background-size: 200% 100%;
      background-size: 200% 100%;
      /* 动画 */
      -webkit-animation: masked-animation 4s infinite linear;
    }
  }
  @keyframes masked-animation {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: -100% 0;
    }
  }
  @-webkit-keyframes masked-animation {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: -100% 0;
    }
  }
  .grid-content-2 {
    height: 30px;
    line-height: 30px;
    padding: 7px;
    a {
      text-decoration: none;
      color: #fff;
      padding-left: 20px;
      font-size: 20px;
      display: inline-block;
    }
  }
  
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  
  .el-input {
    width: 70%;
  }
</style>