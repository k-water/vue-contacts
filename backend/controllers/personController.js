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

module.exports = router => {
  router.get('/getPerson', GetPerson)
}