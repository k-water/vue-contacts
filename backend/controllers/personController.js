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
module.exports = router => {
  router.get('/getPerson', GetPerson),
    router.post('/delPerson', DelPerson)
}