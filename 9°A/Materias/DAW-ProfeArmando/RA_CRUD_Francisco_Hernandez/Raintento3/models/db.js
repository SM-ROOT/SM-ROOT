const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/EmployeeDB',{ useNewUrlParser: true}, (err) =>{
    if (!err){ console.log('MongoDB conection succeeded')}
    else{ console.log(' Error de DB connetion : ' + err) }
} );

require('./employee.model');