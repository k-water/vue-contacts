const express = require('express')
const PersonController = require('../controllers/personController')
const router = express.Router()

PersonController(router)

module.exports = router