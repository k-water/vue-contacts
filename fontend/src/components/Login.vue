<template>
  <el-card class="box-card">
    <el-row type="flex" justify="center">
      <el-col :span="12">
        <el-form 
          label-position="left" 
          label-width="80px" 
          :model="formLogin"
          :rules="rules"
          ref="formLogin">
          <!-- $refs 只在组件渲染完成后才填充，并且它是非响应式的。它仅仅作为一个直接访问子组件的应急方案——应当避免在模版或计算属性中使用 $refs 。 -->
          <el-form-item label="账号" prop="name">
            <el-input v-model="formLogin.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="formLogin.password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPassword">
            <el-input v-model="formLogin.checkPassword"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="login">登录</el-button>
              <el-button @click="resetForm">取消</el-button>
          </el-form-item>
          <el-form-item>
            <router-link to="/register">
              <el-button type="">没有账号，立即注册<i class="el-icon-arrow-right el-icon--right"></i></el-button>
            </router-link>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<script type="text/javascript">
  // 引入vuex /src/helper.js中的辅助函数，
  // 将actions中的方法直接转为组件中的方法
  import {mapActions} from 'vuex'
  const qs = require('qs')
  export default {
    data(){
      let checkUserName = (rule,value,cb)=>{
        if(!value){
          return cb(new Error('账户不能为空!'))
        }else{
          cb(); // 将判断传递给后面
        }

      }
      let checkPassword = (rule,value,cb)=>{
        if(!value){
          return cb(new Error('密码不能为空!'))
         }else{
          cb();
         }
      }
      let checkPasswordAgain = (rule,value,cb)=>{
        if(!value){
          return cb(new Error('再次输入密码不能为空!'))
         }else if(value !== this.formLogin.password){
          return cb(new Error('两次输入密码不一致!'));
         }else{
          cb();
         }
      }
      return{
        formLogin:{
          name: '',
          password: '',
          checkPassword: ''
        },
        rules:{
          name:[
            {validator:checkUserName,trigger: 'blur'}
          ],
          password:[
            {validator:checkPassword,trigger: 'blur'}
          ],
          checkPassword:[
            {validator:checkPasswordAgain,trigger: 'blur'}
          ]
        }
      }
    },
    methods:{
      ...mapActions(['userLogin']),
      // 向登录接口发起请求
      login(){
        let user = this.formLogin;
        let formData = {
          name: user.name,
          password: user.password
        };
        // 表单验证
        this.$refs['formLogin'].validate((valid) => {
          if (valid) {
            // 通过验证之后才请求登录接口
            this.$http.post('http://localhost:3000/api/token/login', qs.stringify(formData))
                .then(res => {
                    console.dir(res.data)
                    if (res.data.success) {
                      this.userLogin(res.data);
                      this.$message.success(`${res.data.message}`)
                      // 登录成功 跳转至首页
                      this.$router.push({name:'home'}) 
                    }else{
                      this.$message.error(`${res.data.message}`);
                      return false;
                    }
                })
                .catch(err => {
                    this.$message.error(`${err.message}`, 'ERROR!')
                })
          } else {
            this.$message.error('表单验证失败!')
            return false;
          }
        });
      },
      // 表单重置
      resetForm(){
        console.log('token')
        this.$refs['formLogin'].resetFields();
      }
    }
  }
</script>
<style lang="scss">
  html, body {
    height: 100% !important;
  }
  #login {
    margin: 0 auto;
    width: 100%;
    height: 100%;
    overflow: hidden;
    min-height: 585px;
    background: url(../assets/images/Fields-Fresh-Quality-Popular-Wallpaper.jpg) no-repeat;
    background-size: 100% 100%;
    position: relative;
    .el-col {
      text-align: center;
      position: absolute;
      top: 50%;
      transform: translateY(-50%)
    }
    .container {
      width: 100%;
      height: 400px;
      background: #fff;
      box-shadow: -1px 1px 10px rgba(0, 0, 0, 0.5);
      border-radius: 5px;
      header {
        font-family: 'Open Sans', sans-serif;
        font-weight: 900;
        font-size: 2.3em;
        color: #36383c;
        padding: 10px 0 30px 0;
      }
      .el-form-item__content {
        margin-left: 10px !important;
      }
      .el-form-item__error {
        left: 24%;
      }
    }
    
  }
  
</style>