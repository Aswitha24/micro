var express = require('express')
var app = express()

var myLogger = function (req, res, next) {
  req.user='Anil'
  console.log('LOGGED')
  next()
}

var myLogger2 = function (req, res, next) {
    console.log('LOGGED- Second')
    next()
  }

app.use(myLogger)
app.use(myLogger2)

app.get('/', function (req, res) {
  res.send('Hello World!' + req.user)
})

app.listen(3000)
