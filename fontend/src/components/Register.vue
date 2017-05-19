<template>
  <div id="register">
    <div class="bg"></div>
    <div class="wrap">
      <header>Easy Contacts</header>
      <div class="register-form">
        <el-form
         label-position="left" 
         label-width="80px" 
         :model="formRegister"
         :rules="rules"
         ref="formRegister">
          <el-form-item label="账号" prop="name">
            <el-input v-model="formRegister.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="formRegister.password" type="password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPassword">
            <el-input v-model="formRegister.checkPassword" type="password"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="addUser">立即注册</el-button>
              <el-button @click="jump">取消</el-button>
            </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script type="text/javascript">
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
         }else if(value !== this.formRegister.password){
          return cb(new Error('两次输入密码不一致!'));
         }else{
          cb();
         }
      }

      return{
        formRegister:{
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
      // 增加单条电影
      addUser(){
        let user = this.formRegister;
        let formData = {
          name: user.name,
          password: user.password
        };
        // 表单验证
        this.$refs['formRegister'].validate((valid)=>{
          if(valid){
            this.$http.post('http://119.29.151.195:3000/api/token/register',qs.stringify(formData))
            .then(res => {
              if (res.data.message) {
                this.$message.error(res.data.message);
                return false;
              }else{
                this.$message.success('注册成功！')
                setTimeout(() => {
                  this.$router.push('/login')
                },1500)
              }
            })
            .catch(err => {
                this.$message.error(`${err.message}`)
            })
          }else{
            this.$message.error('表单验证失败!')
            return false;
          }
        })
      },
      jump() {
        this.$router.push({
          name: 'login'
        })
      }
    }
  }
</script>

<style lang="scss">
   html, body {
    height: 100% !important;
  }
  #register {
    .bg {
      width: 100%;
      height: 100%;
      background: url(https://oc1gyfe6q.qnssl.com/17-5-18/51286320-file_1495096954560_177f8.jpg) no-repeat;
      -webkit-filter: blur(1px); /* Chrome, Opera */
      -moz-filter: blur(1px);
      -ms-filter: blur(1px);    
          filter: blur(1px);
      background-size: 100% 100%;
    }
    margin: 0 auto;
    width: 100%;
    height: 100%;
    overflow: hidden;
    min-height: 585px;
    position: relative; 
    header {
      text-align: center;
      font: 40px/150% 'Microsoft YaHei','Hiragino Sans GB',Arial,sans-serif;
      color: #45484d;
      width: 400px;
      height: 60px;
    }
    .wrap {
      width: 400px;
      height: 300px;
      background: rgba(197, 196, 231, 0.81);
      border: 1px solid #ddd;
      z-index: 999;
      border-radius: 5px;
      box-shadow: 1px 1px 1px 1px gainsboro;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translateX(-50%) translateY(-50%);
      .register-form { 
        display: flex;
        flex-direction: column;
        padding: 20px;
      }
    }
  }
</style>
