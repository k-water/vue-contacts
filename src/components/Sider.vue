<template>
  <div id="sider">
      <el-menu router default-active="1" class="el-menu-demo" mode="horizontal" theme="dark">
        <el-menu-item index="1" :route="{path: '/'}">
          <i class="el-icon-message">
          </i>
          Contacts
        </el-menu-item>
        <el-menu-item index="2" :route="{path: '/groupHome'}">
          <i class="el-icon-menu">
          </i>
          群組
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-setting">
          </i>
          更多
        </el-menu-item>
      </el-menu>
  </div>
</template>
<script>
  export default {
    name: 'sider',
    data() {
      return {
        currentIndex: 0,
        items: []
      }
    },
    mounted () {
      this.$nextTick(() => {
        this.init()
      })
    },
    methods: {
      init() {
        this.$http.get('http://localhost:8080/Test/getGroup').then(response => {
          this.items = JSON.parse(response.body)
        }, error => {
          return console.log(error)
        })
      },
      
      addGroup() {
        this.$prompt('请输入分组名称', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '你添加的分组是: ' + value
          });
          this.items.push({name: value})
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