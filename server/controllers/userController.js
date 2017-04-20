const express = require('express')

// collection
const User = require('../models/schema/user')
const router = express.Router()
const moment = require('moment')
const objectIdToTimestamp = require('objectid-to-timestamp')

// 加密
const sha1 = require('sha1')
const checkLogin = require('../middlewares/checkLogin').checkLogin
const checkNotLogin = require('../middlewares/checkLogin').checkNotLogin

// register

const Register = (req, res) => {
  let userRegister = new User({
    name: req.body.name,
    password: sha1(req.body.password)
  })

  userRegister.create_time = moment(objectIdToTimestamp(userRegister._id)).format('YYYY-MM-DD HH:mm:ss')

  User.findOne({
    name: (userRegister.name).toLowerCase()
  }).then(user => {
    if (uesr) {
      res.json({
        success: false,
        error: '该用户已注册'
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
  }).catch(err => res.json(err))
}

// login

const Login = (req, res) => {
  let userLogin = new User({
    name: req.body.name,
    password: sha1(req.body.password)
  })
  User.findOne({
    name: userLogin.name
  }).then(user => {
    if (!user) {
      res.json({
        success: false,
        message: '该账户不存在'
      })
    } else if (userLogin.password === user.password) {
      let name = req.body.name
      user.password = null
      req.session.user = user
      res.json({
        success: true,
        message: "登录成功",
        name: user.name,
        // 账户创建日期
        time: moment(objectIdToTimestamp(user._id))
          .format('YYYY-MM-DD HH:mm:ss')
      })
    } else {
      res.json({
        success: false,
        message: '密码错误'
      })
    }
  }).catch(err => res.json(err))
}

const getSession = (req, res) => {
  res.json({
    session: true
  })
}

const delSession = (req, res) => {
  req.session.user = nill
  res.json({
    message: '登出成功'
  })
}

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

module.exports = router => {
  router.post('/register', checkNotLogin, Register),
    router.post('./login', checkNotLogin, Login),
    router.get('/user', checkLogin, delSession),
    router.get('/', checkLogin, getSession)
}