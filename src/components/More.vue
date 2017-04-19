<template>
  <div id="more">
    <!--侧栏图片 切换-->
    <Headers></Headers>
    <Navigate></Navigate>
    <el-col :span="4" v-for="(o, index) in 1" :offset="index > 0 ? 1 : 0" class="side-img">
      <el-card :body-style="{ padding: '0px' }">
        <img src="https://oc1gyfe6q.qnssl.com/XqjPCz.jpg?raw=true" class="image">
        <div style="padding: 14px;">
          <span style="margin: 10px;font-size: 18px;">更多选项</span>
          <ul class="ul-menu" style="margin-top: 10px;">
           <li class="ul-menu-item" v-for="(item, index) in items" @click="chooseTab(index)">
             {{ item.name }}
            </li>
          </ul>
        </div>
      </el-card>
    </el-col>
          

    <!--上传-->
    <el-col :span="18" :offset="1" v-show="currentIndex === 0" class="warp">
      <form name="uploadForm" method="POST" 
        enctype="MULTIPART/FORM-DATA" 
        action="http://localhost:8080/ContactsBe/Upload"
        target="post" class="upload">

        <!--隐藏input file-->
        <input type="file" name="file" id="fileUpload" class="upload-input" @change="getFileName()" />
        <i class="el-icon-upload icon"></i>

        <!--选择的文件名-->
        <div class="el-upload___text" style="margin-bottom: 10px;" v-show="fileName !== ''">
          您选择的文件：{{fileName}}
        </div>

        <div>
          <!--button代替input-->
          <el-button type="primary" @click="fileUpload">选择</el-button>
          <input type="submit" name="submit" value="上传" class="el-button" @click="uploadJudge">
        </div>
      </form>

      <section class="tip">
        <h3>温馨提示</h3>
        <p>文件上传的格式为Excel</p>
        <span>参考格式如下</span>
        <p>其中姓名，邮箱，手机，地址为必填项</p>
        <img src="http://oc1gyfe6q.qnssl.com/17-3-16/2716775-file_1489676097356_edb9.png" alt="">
        <img src="http://oc1gyfe6q.qnssl.com/17-3-16/33026023-file_1489676113635_beab.png" alt="">
      </section>
    </el-col>
    
    <!--下载-->
    <el-col :span="18" :offset="2" v-show="aIndex === 1" class="download">
      <a href="http://localhost:8080/ContactsBe/POIoutTemplate">
        <el-button type="success" size="large">
          导出<i class="el-icon-edit el-icon--right"></i>

        </el-button>
      </a>
    </el-col>

    <!--表单提交不跳转-->
    <iframe id="id_iframe" name="post" style="display: none">
    </iframe>

  </div>
</template>
<script>
  export default {
    name: 'more',
    data() {
      return {
        items: [{
          name: '导入'
        }, {
          name: '导出'
        }],
        aIndex: -1,
        currentIndex: 0,
        fileName: ''
      }
    },
    components: {
      Headers: require('../components/Headers.vue'),
      Navigate: require('../components/Navigate.vue')
    },
    methods: {
      uploadJudge() {
        let reg = /^xls$/
        let pos = this.fileName.lastIndexOf('.')
        let suffix = this.fileName.substring(pos + 1)
        console.log(suffix)
        if (this.fileName === '') {
          this.$alert('您还没有选择文件，请先选择需上传的文件', '提示', {
            confirmButtonText: '确定',
            type: 'info'
          });
        } else if (!reg.test(suffix)) {
          this.$alert('文件格式不正确，请选择Excel文件，后缀名为xls', '警告', {
            confirmButtonText: '确定',
            type: 'warning'
          });
        } else {
          this.$alert('上传成功，请回首页查看', '提示', {
            confirmButtonText: '确定',
            type: 'success'
          });
        }
        this.fileName = ''
      },
      chooseTab(index) {
        if (index === 0) {
          this.currentIndex = index
          this.aIndex = -1
        } else {
          this.aIndex = index
          this.currentIndex = -1
        }
      },
      getFileName() {
        let obj = document.querySelector('#fileUpload')
        let val = obj.value
        let pos = val.lastIndexOf('\\')
        this.fileName = val.substring(pos + 1)
      },
      fileUpload() {
        let id = document.querySelector('#fileUpload')
        id.click()
      }
    }
  }
</script>
<style lang="scss">
  #more {
    // padding: 30px;
  }
  
  .warp {
    display: flex;
    justify-content: space-around;
    align-items: center;
  }

  .add {
    background-color: #fff;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    box-sizing: border-box;
    width: 360px;
    height: 180px;
    text-align: center;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    &:hover {
      border-color: deepskyblue
    }
  }
  .add-i {
    font-size: 67px;
    color: #97a8be;
    margin: 40px 0 16px;
    line-height: 50px;
  }
  .upload {
    background-color: #fff;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    box-sizing: border-box;
    width: 40%;
    height: 180px;
    text-align: center;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    cursor: pointer;
    background: #9f7e7e;
    background-image: repeating-linear-gradient(30deg, hsla(0,0%,100%,.1), hsla(0,0%,100%,.1) 15px,transparent 0, transparent 30px);
    &:hover {
      cursor: pointer;
      border-color: cornflowerblue;
    }
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }
  
  .upload-input {
    visibility: hidden;
    position: absolute;
    display: none;
  }
  
  .download {
    width: 360px;
    height: 180px;
    border: 1px dashed darkgray;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: border-color ease 1s;
    cursor: pointer;
    background: #58caca;
    background-image: repeating-linear-gradient(30deg, hsla(0,0%,100%,.1), hsla(0,0%,100%,.1) 15px,transparent 0, transparent 30px);
    &:hover {
      border-color: deepskyblue;
    }
  }

  .icon {
    font-size: 67px;
    color: #97a8be;
  }
  .tip {
    width: 50%;
    height: 400px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    background-image: repeating-linear-gradient(30deg, hsla(0,0%,100%,.1), hsla(0,0%,100%,.1) 15px,transparent 0, transparent 30px);
    img {
      max-width: 100%;
    }
    @media (min-width: 1290px) {
      height: 600px;
    }
  }
</style>