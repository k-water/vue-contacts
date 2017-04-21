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
        <el-menu-item index="" :style="{float: 'right'}">
          <router-link to="/login" v-show="!user.name">登录</router-link>
          <el-dropdown @command="loginOut">
            <span :style="{color:'#FFF'}" v-show="user.name">
            {{user.name}}<i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command>登出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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
        activeIndex:'1',
        user: {
          name: ''
        }
      }
    },
    beforeCreate() {
      this.$http.get('/api').then(res => {
        if(res.data.error) {
          this.$message.error(res.data.error)
          this.user.name = null
          return false
        } else {
          let user = localStorage.getItem('user')
          if(user) {
            this.user.name = user
          }
        }
      }).catch(err => {
        this.$message.error(`${err.message}`)
      })
    },
    mounted () {
      
    },
    methods: {
      ...mapActions(['userLoginOut']),
      loginOut() {
        this.userLoginOut()
        this.user.name = null
        this.$http.get('/api/user').then(res => {

          if(res.data.message) {
            this.$message.success(res.data.message)
            this.$router.push({name: 'login'})
            return false
          }
        }).catch(err => {
          this.$message.error(`${err.message}`)
        })
      }
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