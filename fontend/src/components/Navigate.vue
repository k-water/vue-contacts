<template>
  <div id="navigate">
      <el-menu :router="true" default-active="1" class="el-menu-demo" mode="horizontal" theme="dark">
        <el-menu-item index="1" :route="{name: 'home'}">
          <i class="el-icon-message">
          </i>
          主页
        </el-menu-item>
        <el-menu-item index="2" :route="{name: 'group'}">
          <i class="el-icon-menu">
          </i>
          群组
        </el-menu-item>
        <el-menu-item index="3" :route="{name: 'more'}">
          <i class="el-icon-setting">
          </i>
          更多
        </el-menu-item>
      </el-menu>
  </div>
</template>
<script>
  import { mapActions } from 'vuex'
  export default {
    name: 'navigate',
    data() {
      return {
        user: {
          name: null
        }
      }
    },
    
    mounted () {
      
    },
    methods: {
      
    }
  }
</script>
<style lang="scss">
  #navigate {
    padding-bottom: 30px;
  }
  .sider-left {
    padding: 30px 10px 0 30px;
    .el-menu {
      background: whitesmoke;
      color: black;
    }
  }
  .add-group {
    height: 50px;
    line-height: 50px;
    padding-left: 20px;
    &:hover {
      background-color: #d1dbe5;
    }
    button {
      color: #000;
    }
  }
  a {
    text-decoration: none;
  }
</style>