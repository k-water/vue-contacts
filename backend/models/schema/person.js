const mongoose = require('mongoose')

const personSchema = mongoose.Schema({
  name: String,
  email: String,
  phoneNumber: String,
  homeNumber: String,
  birthday: String,
  address: String,
  site: String,
  battery: String
}, {
  versionKey: false
})


const Person = module.exports = mongoose.model('People', personSchema)