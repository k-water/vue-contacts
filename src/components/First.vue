<template>
  <div id="first">
    <el-row>
      <el-col :span="4">
        <div class="grid-content bg-purple-light">
          <img :src=img alt="">
        </div>
      </el-col>
      <el-col :span="24">
        <div class="grid-content-2 bg-blue">
          <span>
            <a href="javascript:;">联系人</a>
          </span>
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
        img: require('../assets/logo.png'),
        msg: ''
      }
    },
    components: {
    },
    methods: {
      handleClick() {
        if (this.msg === '') {
          alert("输入的内容不能为空...")
        } else {
          alert("您输入的内容是: " + this.msg)
          this.msg = ''
        }
      }
    }
  }
</script>
<style lang="scss">
  body {
    font: normal 16px Roboto,arial,sans-serif;
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
    /*border-radius: 4px;*/
    min-height: 36px;
    height: 36px;
    padding: 12px 20px;
    img {
      height: 36px;
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