<template>
  <div id="more">
    <!--侧栏图片-->
    <el-col :span="4" v-for="(o, index) in 1" :offset="index > 0 ? 1 : 0">
      <el-card :body-style="{ padding: '0px' }">
        <img src="https://oc1gyfe6q.qnssl.com/XqjPCz.jpg?raw=true" class="image">
        <div style="padding: 14px;">
          <span style="margin: 10px;font-size: 18px;">更多选项</span>
          <ul class="ul-menu" style="margin-top: 10px;">
           <li class="ul-menu-item" v-for="(item, index) in items" @click="choose(index)">
             {{ item.name }}
            </li>
          </ul>
        </div>
      </el-card>
    </el-col>
          
    <el-col :span="18" :offset="2" v-show="currentIndex === 0">
      <form name="uploadForm" method="POST" 
        enctype="MULTIPART/FORM-DATA" 
        action="http://localhost:8080/ContactsBe/SmartUploadServlet"
        target="post" class="upload"> 
        <input type="file" name="file" id="upload" class="upload-input" />
        
        <i class="el-icon-upload icon"></i>
        <div class="el-upload__text upload-text">点击导入文件</div>
        <input type="submit" name="submit" value="上传" class="upload-file" @click="success">
      </form>
    </el-col>
    

    <el-col :span="18" :offset="2" v-show="aIndex === 1">
      <a href="http://localhost:8080/ContactsBe/SmartDownloadServlet?filename=personlist.json">
        <el-button type="success" size="large">
          导出<i class="el-icon-edit el-icon--right">
        </el-button>
      </a>
    </el-col>

    <!--表单提交不跳转-->
    <iframe id="id_iframe" name="post" style="display: none"></iframe>
  </div>
</template>
<script>
  export default {
    name: 'more',
    data() {
      return {
        items: [
          {name: '导入'},
          {name: '导出'}
        ],
        aIndex: -1,
        currentIndex: 0
      }
    },
    mounted() {},
    methods: {
      success() {
        confirm('导入成功');
      },
      choose(index) {
        if(index === 0) {
          this.currentIndex = index
          this.aIndex = -1
        }else {
          this.aIndex = index
          this.currentIndex = -1
        }
      }
    }
  }
</script>
<style lang="scss">
  #more {
    padding: 30px;
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
    width: 360px;
    height: 180px;
    text-align: center;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    cursor: pointer;
    &:hover {
      cursor: pointer;
      border-color: cornflowerblue;
    }
  }
  .upload-text {
    position: absolute;
    bottom: 25%;
    left: 38%;
  }
  .icon {
    font-size: 67px;
    color: #97a8be;
    margin: 40px 0 16px;
    line-height: 50px;
    display: inline-block;
    position: absolute;
    top: 5%;
    left: 42%;
  }
  .upload-input {
    width: 100%;
    height: 100%;
    opacity: 0;
  }
  .upload-file {
    position: absolute;
    bottom: 5%;
    right: 10%;
    display: inline-block;
    line-height: 1;
    white-space: nowrap;
    cursor: pointer;
    background: #fff;
    border: 1px solid #bfcbd9;
    color: #1f2d3d;
    -webkit-appearance: none;
    text-align: center;
    box-sizing: border-box;
    outline: none;
    margin: 0;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
    padding: 10px 15px;
    font-size: 14px;
    border-radius: 4px;
    color: #fff;
    background-color: #20a0ff;
    border-color: #20a0ff;
  }
</style>