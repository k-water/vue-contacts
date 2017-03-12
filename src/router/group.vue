<template>
  <div id="group">
          <!--搜索框-->
    <el-col :span="13" class="searchPos">
      <div class="grid-content bg-purple-light">
        <el-input placeholder="请输入你要寻找的内容..." v-model="filtersKey" @keyup.enter.native="fuzzyQuery">
          <el-button slot="append" class="btn" icon="search" @click="fuzzyQuery">
          </el-button>
        </el-input>
      </div>
    </el-col>

    <el-col :span="4">
      <ul class="ul-menu">
        <li class="ul-menu-item" v-for="(item, index) in getItems" @click="init(item.value)">
          {{item.value}}
          <i class="el-icon-delete ul-menu-item-delete" @click="delGroup(index,item.value)">
          </i>
        </li>
        <li class="ul-menu-item" style="padding: 0 5px" @click="dialogFormVisible = true">
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
        <el-table-column prop="battery" label="分组" width="150">
          <template scope="scope">
            <el-tag type="primary">
              {{scope.row.battery}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="删除" width="150">
          <template scope="scope">
            <el-button type="danger" size="small" @click="delPerson(scope.$index, scope.row)">
              Del
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-col>

    <!--addGroup dialog-->
    <el-dialog title="添加分组" v-model="dialogFormVisible">
      <el-form :model="form" :label-position="labelPosition">
        <el-form-item label="分组名称" :label-width="formLabelWidth" props="battery">
          <el-input v-model="form.battery" auto-complete="off" placeholder="请输入分组名称" @keyup.enter.native = "addGroup"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelAdd">取 消</el-button>
        <el-button type="primary" @click="addGroup">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  export default {
    data() {
      return {
        groupData: [],
        currentRow: '',
        dialogFormVisible: false,
        labelPosition: 'left',
        formLabelWidth: '80px',
        filtersKey: '',
        form: {
          battery: ''
        }
      }
    },
    computed: {
      ...mapGetters([
        'getItems',
        'allContacts'
      ])
    },
    created() {
      this.$store.dispatch('GET_PERSON')
      this.$store.dispatch('GET_GROUP')
    },
    mounted() {
      this.$nextTick(() => {
        this.init()
      })
    },
    methods: {
      init(tmpGroup = '家') {
        this.groupData = []
        for (let i = 0; i < this.allContacts.length; i++) {
          if (this.allContacts[i]['battery'] === tmpGroup) {
            this.groupData.push(this.allContacts[i])
          }
        }
      },
      addGroup() {
        let judge = this.judge()
        if(judge === 1) {
          this.$message({
            showClose: true,
            message: '分组已存在，请重新添加',
            type: 'warning',
            duration: 2000
          });
        } else if(this.form.battery === ''){
          this.$message({
            showClose: true,
            message: '添加的分组不能为空，请重新输入',
            type: 'error',
            duration: 2000
          });
        } else {
          this.$message({
            showClose: true,
            message: '成功添加分组：' + this.form.battery ,
            type: 'success',
            duration: 2000
          });
          let params = {
            text: this.form.battery,
            value: this.form.battery
          }
          this.$store.dispatch('ADD_GROUP',params)
        }

        this.form.battery = ''
        this.dialogFormVisible = false
      },
      judge() {
        let tmp = []
        for(let i = 0, len = this.getItems.length; i< len; i++) {
          let key = Object.values(this.getItems[i])
          tmp.push(key[0])
        }
        let flag = 0
        for(let k = 0, len = tmp.length; k < len; k++) {
          if(tmp[k] === this.form.battery) {
            flag = 1
          }else {
            continue
          }
        }
        return flag
      },
      cancelAdd() {
        this.$message({
          showClose: true,
          message: '取消添加',
          duration: 2000
        });
        this.form.battery = ''
        this.dialogFormVisible = false
      },
      delGroup(index,item) {
        let params = {
          value: item
        }
        if (confirm('您确认删除吗？')) {
          this.getItems.splice(index, 1)
          this.$store.dispatch('DEL_GROUP', params, index)
        }
      },
      handleCurrentChange(val) {
        this.currentRow = val
      },
      delPerson(index, row) {
        if (confirm('您确认删除吗？')) {
          this.groupData.splice(index, 1)
        }
      },
      // 待完善
      async fuzzyQuery() {
        await this.$store.dispatch('FUZZY_QUERY',this.filtersKey)
        this.filtersKey = ''
        setTimeout(()=>{
          this.$store.dispatch('GET_PERSON')
        },3000)
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