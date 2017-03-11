<template>
  <div id="navigate">
      <el-menu router default-active="1" class="el-menu-demo" mode="horizontal" theme="dark">
        <el-menu-item index="1" :route="{path: '/'}">
          <i class="el-icon-message">
          </i>
          Contacts
        </el-menu-item>
        <el-menu-item index="2" :route="{path: '/group'}">
          <i class="el-icon-menu">
          </i>
          群組
        </el-menu-item>
        <el-menu-item index="3" :route="{path: '/more'}">
          <i class="el-icon-setting">
          </i>
          更多
        </el-menu-item>
      </el-menu>
  </div>
</template>
<script>
  export default {
    name: 'navigate',
    data() {
      return {}
    },
    mounted () {
      
    },
    methods: {

    }
  }
</script>
<style lang="scss">
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
</style>