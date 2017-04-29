const express = require('express')
const User = require('../models/schema/user')
const router = express.Router()
const moment = require('moment')
const objectIdToTimestamp = require('objectid-to-timestamp')
const sha1 = require('sha1')
const checkLogin = require('../middlewares/checkLogin').checkLogin
const checkNotLogin = require('../middlewares/checkLogin').checkNotLogin

// 注册
const Register = (req, res) => {
  // 这里的userRegister为mongoose中的
  // Entity —— 由Model创建的实体，使用save方法保存数据

  let userRegister = new User({
    name: req.body.name,
    password: sha1(req.body.password) // 将密码加密
  })

  userRegister.create_time = moment(objectIdToTimestamp(userRegister._id))
    .format('YYYY-MM-DD HH:mm:ss');

  User.findOne({
      name: (userRegister.name).toLowerCase()
    })
    .then(user => {
      if (user) {
        res.json({
          success: false,
          error: '该账户已注册'
        })
      } else {
        userRegister.save((err, user) => {
          if (err) {
            res.json(err)
          } else {
            res.json(user)
          }
        })
      }
    })
    .catch(err => res.json(err))
}

// 登录
const Login = (req, res) => {
  let userLogin = new User({
    name: req.body.name,
    password: sha1(req.body.password)
  })
  User.findOne({
      name: userLogin.name
    })
    .then(user => {
      if (!user) {
        res.json({
          success: false,
          message: "账号不存在"
        })
      } else if (userLogin.password === user.password) {
        var name = req.body.name;
        // 用户信息写入 session
        user.password = null;
        req.session.user = user;
        res.json({
          success: true,
          message: "登录成功",
          // session: req.session,
          name: user.name,
          // 账户创建日期
          time: moment(objectIdToTimestamp(user._id))
            .format('YYYY-MM-DD HH:mm:ss')
        })
      } else {
        res.json({
          success: false,
          message: "密码错误"
        })
      }
    })
    .catch(err => res.json(err))
}

// get user Session
const getSession = (req, res) => {
  res.json({
    session: true // 提供前端验证session存在与否
  })
}

// delete user session
const delSession = (req, res) => {
  req.session.user = null;
  res.json({
    message: '登出成功'
  })
}



// 删除用户
const delUser = (req, res) => {
  User.findOneAndRemove({
    _id: req.body.id
  }, err => {
    if (err) console.log(err)
    console.log('删除用户成功')
    res.json({
      success: true
    })
  })
}

module.exports = (router) => {
  router.post('/register', checkNotLogin, Register),
    router.post('/login', checkNotLogin, Login),
    router.get('/user', checkLogin, delSession),
    router.get('/', checkLogin, getSession)
}