const mongoose = require('mongoose')

// schema 模式

const userSchema = mongoose.Schema({
  name: {
    type: String,
    index: true,
    unique: true
  },
  password: String,
  create_time: Date
})

const User = module.exports = mongoose.model('UserSession', userSchema)