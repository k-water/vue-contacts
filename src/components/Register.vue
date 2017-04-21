<template>
  <div id="register">
    <el-col :lg="{span:8,offset:8}" :md="{span:8,offset:8}">
      <div class="container">
        <header>
          <h3>Register</h3>
        </header>
        <section>
          <el-form
            label-position="right" 
            label-width="80px" 
            :model="formRegister"
            :rules="rules"
            ref="formRegister">
            <el-form-item label="账号" prop="name">
              <el-input v-model="formRegister.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" >
              <el-input v-model="formRegister.password" type="password"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPassword" >
              <el-input v-model="formRegister.checkPassword" type="password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addUser">立即注册</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </section>
      </div>
    </el-col>
  </div>
</template>
<script>
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
    methods: {
      addUser() {
        let user = this.formRegister
        let formData = {
          name: user.name,
          password: user.password
        }

        // form valid
        this.$refs['formRegister'].validate(valid => {
          if(valid) {
            this.$http.post('/api/register', formData).then(res => {
              if(res.data.error) {
                this.$message.error(res.data.error)
                return false
              }else {
                this.$router.push('/login')
              }
            }).catch(err => {
              this.$message.error(`${err.message}`)
            })
          }else {
            this.$message.error('表单验证失败')
            return false
          }
        })
      }
    }
  }
</script>
<style lang="scss">
  #register {
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
