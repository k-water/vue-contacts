<template>
  <div id="group">
    <el-col :span="4">
      <ul class="ul-menu">
        <li class="ul-menu-item" v-for="(item, index) in getItems" @click="init(item.name)">
          {{item.name}}
          <i class="el-icon-delete ul-menu-item-delete" @click="delGroup(index,item.name)">
          </i>
        </li>
        <li class="ul-menu-item" style="padding: 0 5px" @click="addGroup">
          <i class="el-icon-plus" style="padding-right: 5px">
          </i>
          创建分组
        </li>
      </ul>
    </el-col>
    <!-- 排序表格 -->
    <el-col :span="19" :offset="1">
      <el-table :data="groupData" border style="width: 100%" :default-sort="{prop: 'date', order: 'descending'}"
                highlight-current-row @current-change="handleCurrentChange">
        <el-table-column prop="name" label="姓名" sortable width="150">
        </el-table-column>
        <el-table-column prop="email" label="邮箱" sortable>
        </el-table-column>
        <el-table-column prop="address" label="地址">
        </el-table-column>
        <el-table-column prop="group" label="分组" width="150">
          <template scope="scope">
            <el-tag type="primary">
              {{scope.row.group}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="删除" width="150">
          <template scope="scope">
            <el-button type="danger" size="small" @click="delContact(scope.$index, scope.row)">
              Del
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        groupData: [],
        getItems: [],
        currentRow: ''
      }
    },
    mounted() {
      this.$nextTick(() => {
        this.getGroup()
        this.init()
      })
    },
    methods: {
      init(tmpGroup = '家') {
        this.groupData = []
        this.$http.get('http://localhost:8080/Test/servletSql').then((res) => {
          let tempData = JSON.parse(res.body)
          for (let i = 0; i < tempData.length; i++) {
            if (tempData[i]['group'] === tmpGroup) {
              this.groupData.push(tempData[i])
              // debug
              // console.log(tempData[i])
            }
          }
        })
      },
      getGroup() {
        this.$http.get('http://localhost:8080/Test/getGroup').then(response => {
          this.getItems = JSON.parse(response.body)
          // debug
          // console.log(this.getItems[0]['name'])
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
          this.getItems.push({name: value})
          this.$http.post('http://localhost:8080/Test/addGroup', {name: value}).then(response => {
            console.log(response.status)
          }, error => {
            return console.log(error)
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消添加'
          });       
        }); 
      },
      delGroup(index,item) {
        if (confirm('您确认删除吗？')) {
          // console.log(item)
          this.getItems.splice(index, 1)
        }
        this.$http.post('http://localhost:8080/Test/delGroup', {name: item}).then(response => {
          console.log(response.status)
        }, error => {
          return console.log(error)
        })
      },
      handleCurrentChange(val) {
        this.currentRow = val
      },
      delContact(index, row) {
        if (confirm('您确认删除吗？')) {
          this.groupData.splice(index, 1)
        }
      }
    }
  }
</script>
<style lang="scss">
  #group {
    padding: 30px 30px 0 20px;
  }
  
  ul {
    display: block;
    list-style: none;
  }
  li {
    display: list-item;
    text-align: -webkit-match-parent;
  }
  .ul-menu {
    background-color: #eef1f6;
    margin: 0;
    padding-left: 0;
    border-radius: 2px;
    position: relative;
    .ul-menu-item {
      height: 56px;
      line-height: 56px;
      font-size: 14px;
      color: #48576a;
      padding: 0 30px;
      cursor: pointer;
      position: relative;
      transition: border-color .3s,background-color .3s,color .3s;
      box-sizing: border-box;
      white-space: nowrap;
      &:hover {
        background-color: #d1dbe5;
      }
    }
    .ul-menu-item-delete {
      position: absolute;
      top: 20px;
      right: 20px;
      &:hover {
        color: deepskyblue
      }
    }
  }
</style>