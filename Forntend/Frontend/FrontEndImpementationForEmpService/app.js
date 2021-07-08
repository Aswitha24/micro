var express = require('express');
var app = express();
// parse application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: false }))
// parse application/json
app.use(express.json())
app.use(express.static('public'));   
// library for invoking restservice

var Client = require('node-rest-client').Client;
var client = new Client();

app.set('view engine', 'ejs');
// to show login form
app.get('/add', function(req, res){
    data= {         
            eid: "",
            designation: "",
            name: "" ,  
            emailId: "",  
            for: "addemp"       
    }
    res.render('showupdateform', { 'empobj' : data} )
    
 });
 

// Show update form to collect new valuaes
app.get('/update/:id', function (req, res) {       
    serviceurl= 'http://localhost:9090/employee/'+ req.params.id    
    client.get(serviceurl, function (data, response) {
        data.for="updateemp";        
        res.render('showupdateform', { 'empobj' : data} );          
    });    
 })

 // handle add employee and update employee post methods
 app.post('/addupdate', function (req, res) {     
    serviceurl= 'http://localhost:9090/employee/'+ req.body.eid 
    received = {
        eid : req.body.eid,
        designation: req.body.designation,
        name:req.body.name,  
        emailId:req.body.emailId  
    };
    var args = {
        data: received,
        headers: { "Content-Type": "application/json" }
    };
    
    //from form hidden file extract for filed value to decide add or update employee
    if (req.body.for == 'addemp') {          
        client.post("http://localhost:9090/employee", args, function (data, response) {        
        res.redirect('/'); 
         
    });
    }    //for if
  
    if (req.body.for == 'updateemp') {
        client.put(serviceurl, args , function (data, response) {
            res.redirect('/');      
     });
   }  // for if

 })
 //


 app.get('/deleteemployee/:id', function(req, res){    
    serviceurl= 'http://localhost:9090/employee/'+ req.params.id
    client.delete(serviceurl, function (data, response) {       
        res.redirect('/');          
    });    
 });

 //Start of application url http://localhost:8000/
 
 app.get('/', function(req, res){    
    serviceurl= 'http://localhost:9090/employee'
    client.get(serviceurl, function (data, response) { 
        res.render('showallemployee', { 'myresult' : data} );           
    });    
 });


var server = app.listen(8000, function () {  
    var host = server.address().address  
    var port = server.address().port  
    console.log("Example app listening at http://%s:%s", host, port)  
  })  
