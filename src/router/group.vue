<template>
  <el-col id="group" :span="20">
    <!-- 排序表格 -->
    <el-table :data="groupData" border style="width: 100%" 
      :default-sort = "{prop: 'date', order: 'descending'}"
      highlight-current-row @current-change="handleCurrentChange">
      <el-table-column
        prop="name"
        label="姓名"
        sortable
        width="150">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        sortable>
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
      <el-table-column
        prop="group"
        label="分组"
        width="150">
        <template scope="scope">
          <el-tag type="primary"> {{scope.row.group}} </el-tag>
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
</template>

<script>
  export default {
    data() {
      return {
        groupData: [],
        currentRow: ''
      }
    },
    mounted () {
      this.$nextTick(() => {
        this.init()
      })
    },
    methods: {
      init() {
        this.$http.get('../../static/data/info.json', { credentials: true }).then((res) => {
          let tempData = res.body.list
          for(let i = 0; i < tempData.length; i++) {
            for(let value in tempData[i]) {
              if(tempData[i][value] === '家') {
                this.groupData.push(tempData[i])
                break;
              }
            }
          }
        })
      },
      handleCurrentChange(val) {
        this.currentRow = val
      },
      delContact(index, row) {
        if(confirm('您确认删除吗？')) {
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
</style>