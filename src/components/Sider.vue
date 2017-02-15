<template>
  <div id="sider">
    <el-col :span="4" class="sider-left">
      <el-menu router default-active="1">
        <el-menu-item index="1" :route="{path: '/'}">
          <i class="el-icon-message">
          </i>
          Contacts
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu">
            </i>
            群组
          </template>
          <el-menu-item-group title="分组1">
            <el-menu-item index="2-1" :route="{path: '/groupHome'}">
              家
            </el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-2" :route="{path:'/groupCompany'}">
              公司
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="3">
          <i class="el-icon-setting">
          </i>
          更多
        </el-menu-item>
      </el-menu>
    </el-col>
  </div>
</template>
<script>
  export default {
    name: 'sider',
    data() {
      return {}
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
</style>