const express = require('express')
const UserController = require('../controllers/userController')
const router = express.Router()

UserController(router)

module.exports = router