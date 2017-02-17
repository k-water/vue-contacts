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
          <el-menu-item-group title="">
            <el-menu-item index="2-1" v-for="(item,index) in items" :route="{path: item.path}">
              {{item.name}}
            </el-menu-item>
          </el-menu-item-group>
          <li class="add-group">
            <i class="el-icon-plus"></i>
            <el-button type="text" @click="addGroup">创建分组</el-button>
          </li>
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
      return {
        currentIndex: 0,
        items: [
          {name: '家', path: 'groupHome'},
          {name: '公司', path: 'groupCompany'}
        ]
      }
    },
    methods: {
      addGroup() {
        this.$prompt('请输入分组名称', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '你添加的分组是: ' + value
          });
          this.items.push({name: value, path: value})
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });       
        });
      }
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