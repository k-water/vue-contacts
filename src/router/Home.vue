  <template>
    <div id="home">
      <!--搜索框-->
      <el-col :span="13" class="searchPos">
        <div class="grid-content bg-purple-light">
          <el-input placeholder="请输入你要寻找的内容..." v-model="filtersKey" @keyup.enter.native="fuzzyQuery">
            <el-button slot="append" class="btn" icon="search" @click="fuzzyQuery">
            </el-button>
          </el-input>
        </div>
      </el-col>
      <!--添加按钮 点击触发dialog-->
      <el-col :span="5" style="background: #e5e9f2" class="pos">
        <div class="grid-content bg-purple-light" style="float: right">
          <el-button type="primary" @click="openDialog" icon="edit">
            Add
          </el-button>
        </div>
      </el-col>

      <!--侧栏图片-->
      <el-col :span="4" v-for="(o, index) in 1" :offset="index > 0 ? 1 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <img src="https://oc1gyfe6q.qnssl.com/XqjPCz.jpg?raw=true" class="image">
          <div style="padding: 14px;">
            <span>联系人</span>
            <div class="bottom clearfix">
              <time class="time">{{ currentDate }}</time>
            </div>
          </div>
        </el-card>
      </el-col>
      <!--Table展示数据-->
      <el-col :span="18" :offset="1">
        <el-table :data="contacts">
          <el-table-column type="expand">
            <template scope="props">
              <p>
                家庭电话: {{ props.row.homeNumber }}
              </p>
              <p>
                生日：{{ props.row.birthday }}
              </p>
              <p>
                分组：{{ props.row.battery }}
              </p>
              <p>
                地址：{{ props.row.address }}
              </p>
              <p>
                个人主页：<a :href="props.row.site" target="_blank"> {{ props.row.site }} </a>
              </p>
            </template>
          </el-table-column>
          <el-table-column label="姓名" prop="name" width="150">
          </el-table-column>
          <el-table-column label="邮箱" prop="email">
          </el-table-column>
          <el-table-column label="电话" prop="phoneNumber">
          </el-table-column>
          <el-table-column prop="battery" label="分组" width="100" :filters="groups"
          :filter-method="filterTag">
            <template scope="scope">
              <el-tag :type="scope.row.battery === '家' ? 'primary' : 'success'" close-transition>
                {{scope.row.battery}}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                Edit
              </el-button>
              <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
                Del
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <!--dialog模拟框添加数据-->
      <el-dialog title="添加联系人" v-model="dialogVisible" size="small">
        <el-form :model="form" :rules="rules" ref="from" :label-position="labelPosition"
        label-width="120px">
          <el-form-item label="姓名" required>
            <el-input v-model="form.name" auto-complete="off">
            </el-input>
          </el-form-item>
          <el-form-item label="电子邮箱" prop="email">
            <el-input v-model="form.email" auto-complete="off">
            </el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phoneNumber">
            <el-input v-model="form.phoneNumber" auto-complete="off">
            </el-input>
          </el-form-item>
          <el-form-item label="家庭电话" prop="homeNumber" required>
            <el-input v-model.number="form.homeNumber" auto-complete="off">
            </el-input>
          </el-form-item>
          <el-form-item label="生日" prop="birthday" required>
            <el-input type="date" format="yyyy-MM-dd" placeholder="选择日期" v-model="form.birthday"
            style="width: 70%;">
            </el-input>
          </el-form-item>
          <el-form-item label="个人主页" prop="site">
            <el-input v-model="form.site" autocomplete="off">
            </el-input>
          </el-form-item>
          <el-form-item>
          </el-form-item>
          <el-form-item label="分组" prop="battery" required>
            <el-input v-model="form.battery" placeholder="请填写分组">
            </el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address" required>
            <el-input type="textarea" v-model="form.address" style="width: 70%;" auto-complete="off">
            </el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible=false" v-if="sure">
            取 消
          </el-button>
          <el-button @click="dialogVisible=false" type="info" v-else>
            取 消
          </el-button>
          <el-button type="primary" @click="addContacts" v-if="sure">
            确 定
          </el-button>
          <el-button type="warning" @click="changeContact" v-else>
            修 改
          </el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  <script>
      export default {
      name: 'home',
      data() {
        return {
          contacts: [],
          groups: [],
          tempContacts: [],
          dialogVisible: false,
          dialogFormVisible: false,
          labelPosition: 'left',
          sure: 'true',
          currentForm: {},
          currentIndex: '',
          filtersKey: '',
          currentDate: new Date(),
          form: {
            name: '',
            email: '',
            phoneNumber: '',
            homeNumber: '',
            birthday: '',
            address: '',
            site: '',
            battery: ''
          },
          rules: {
            email: [
              { required: true, message: '请输入邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur, change' }
            ],
            phoneNumber: [
              { required: true, message: '请填写手机号码', trigger: 'blur' },
            ],
            site: [
              { required: true, message: '请输入一个网址', trigger: 'blur' }
            ]
          }
        }
      },
      mounted() {
        this.$nextTick(() => {
          this.init()
          this.getGroup()
        })
      },
      methods: {
        // 数据加载初始化
        init() {
          this.$http.get('http://localhost:8081/ContactsBe/getPerson').then((res) => {
            let person = res.body
            this.contacts = JSON.parse(person)
            this.tempContacts = JSON.parse(person)            
          }, error => {
            console.log(error)
          })
        },
        
        getGroup() {
          this.$http.get('http://localhost:8081/ContactsBe/getGroup').then(response => {
            this.groups = JSON.parse(response.body)
          }, error => {
            return console.log(error)
          })
        },
        // 点击Add打开Dialog 并清空上一次的数据
        openDialog() {
          this.dialogVisible = true
          this.sure = true
          for (let value in this.form) {
            this.form[value] = ''
          }
        },

        //添加新的数据
        async addContacts() {
          this.dialogVisible = false
          this.currentForm = this.form
          this.currentForm = Object.assign({}, this.currentForm)
          this.$nextTick(() => {
            this.contacts.push(this.currentForm)
          })
          this.currentForm = Object.assign({}, this.currentForm, { id: this.contacts.length + 1 })
            // console.log(this.currentForm['battery'])
            // 增加联系人
          await this.$http.post('http://localhost:8081/ContactsBe/addPerson', this.currentForm).then(response => {
              console.log(response.status)
            }, error => {
              console.log(error)
            })
            // 增加分组
          await this.addGroup()
        },
        addGroup() {
          let battery = this.currentForm['battery']
          this.$http.post('http://localhost:8081/ContactsBe/addGroup', { valname: battery, text: battery }).then(response => {
            console.log(response.status)
          }, error => {
            return console.log(error)
          })
        },
        // 删除一行数据
        handleDelete(index, row) {
          if (confirm('您确定删除此联系人吗？')) {
            this.contacts.splice(index, 1);
            this.$http.post('http://localhost:8081/ContactsBe/delPerson', row).then(response => {
              console.log(response.status)
            }, error => {
              console.log(error)
            })
          }
        },

        //编辑一行数据
        handleEdit(index, row) {
          this.sure = false
          this.dialogVisible = true
          this.form = this.initItemForUpdate(row)
          this.currentIndex = index
        },

        // 修改一行数据
        changeContact() {
          for (let k = 0; k < this.contacts.length; k++) {
            if (typeof this.contacts[k]['index'] === 'undefined') {
              this.$set(this.contacts[k], 'index', k)
            }
          }
          let tmpContact = {}
          for (let i = 0; i < this.contacts.length; i++) {
            // 根据主键查找要修改的数据，然后将this.form数据更新到this.contacts[i]
            if (this.contacts[i]['index'] === this.currentIndex) {

              for (let j in this.form) {
                this.contacts[i][j] = this.form[j]
              }
              tmpContact = Object.assign({}, this.contacts[i])
              break;
            }
          }
          // console.log(tmpContact)
          // 发送数据update sql语句
          this.$http.post('http://localhost:8081/ContactsBe/updatePerson', tmpContact).then(response => {
            console.log(response.status)
          }, error => {
            console.log(error)
          })

          // 关闭dialog
          this.dialogVisible = false
          this.form = {}
        },

        // 对象深拷贝
        // 卧槽 卡了我十天半个月 真是深奥
        initItemForUpdate(p, c) {
          c = c || {};
          for (var i in p) {
            // 属性i是否为p对象的自有属性
            if (p.hasOwnProperty(i)) {
              // 属性i是否为复杂类型
              if (typeof p[i] === 'object') {
                // 如果p[i]是数组，则创建一个新数组
                // 如果p[i]是普通对象，则创建一个新对象
                c[i] = Array.isArray(p[i]) ? [] : {};
                // 递归拷贝复杂类型的属性
                this.initItemForUpdate(p[i], c[i]);
              } else {
                // 属性是基础类型时，直接拷贝
                c[i] = p[i];
              }
            }
          }
          return c;
        },

        // 分组过滤method
        filterTag(value, row) {
          return row.battery === value;
        },
        // 模糊查询
        fuzzyQuery() {

          if(this.filtersKey === '') {
            confirm('查询的内容不能为空，请重新输入！！')
          } else {
            this.$http.post('http://localhost:8081/ContactsBe/SearchPerson', {key: this.filtersKey}).then(response => {
              this.contacts = JSON.parse(response.body)
              let len = this.contacts.length
              if(len === 0) {
                return confirm('您查询的联系人不存在...')
              } else {
                return confirm('查询到了' + len + '位联系人')
              }
            }, err => {
              return console.log(err)
            })
          }
          this.filtersKey = ''
          setTimeout(() => {
            this.contacts = this.tempContacts
          }, 3000)
        }
      }
    }
  </script>
  <style lang="scss">
    #home {
      padding: 30px 30px 0 20px;
    }
    .pos {
      position: absolute;
      top: 0;
      right: 0;
    }
    .searchPos {
      position: absolute;
      top: 0;
      right: 20.833333%
    }
    .time {
      font-size: 13px;
      color: #999;
    }
    
    .bottom {
      margin-top: 13px;
      line-height: 12px;
    }

    .button {
      padding: 0;
      float: right;
    }

    .image {
      width: 100%;
      display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    
    .clearfix:after {
        clear: both
    }
  </style>