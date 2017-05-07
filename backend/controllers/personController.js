const express = require('express')
const router = express.Router()
const moment = require('moment')
const objectIdToTimestamp = require('objectid-to-timestamp')
const Person = require('../models/schema/person')

const GetPerson = (req, res) => {
  Person.find({

  }).then(person => {
    if (person.length !== 0) {
      res.json(person)
    } else {
      res.json({
        message: 'Databases is empty!'
      })
    }
  }).catch(
    err => res.json(err)
  )
}
const DelPerson = (req, res) => {
  let delUser = new Person(req.body)
  Person.remove({
    name: delUser.name
  }).then(person => {
    if (person) {
      res.json({
        message: `${delUser.name} was successfully deleted`
      })
    } else {
      res.json({
        error: 'deleted error!'
      })
    }
  }).catch(err => {
    return console.log(err)
  })
}

const AddPerson = (req, res) => {
  let addData = new Person(req.body)
  Person.findOne({
    name: (addData.name).toLowerCase()
  }).then(adder => {
    if (adder) {
      res.json({
        success: false,
        message: '该联系人已存在',
        type: '提示'
      })
    } else if (addData.email === '') {
      res.json({
        success: false,
        message: '联系人邮箱不能为空',
        type: '提示'
      })
    } else if (addData.phoneNumber === '') {
      res.json({
        success: false,
        message: '联系人的手机号码不能为空',
        type: '提示'
      })
    } else if (addData.birthday === '') {
      res.json({
        success: false,
        message: '联系人的生日不能为空',
        type: '提示'
      })
    } else if (addData.address === '') {
      res.json({
        success: false,
        message: '联系人的地址不能为空',
        type: '提示'
      })
    } else {
      addData.save((err, adder) => {
        if (err) {
          res.json(err)
        } else {
          res.json({
            success: true,
            message: `您添加的联系人是${addData.name}`,
            type: '成功'
          })
        }
      })
    }
  }).catch(err => {
    return console.log(err)
  })
}
module.exports = router => {
  router.get('/getPerson', GetPerson),
    router.post('/delPerson', DelPerson),
    router.post('/addPerson', AddPerson)
}