const express = require('express');
var router = express.Router();
const mongoose = require('mongoose');
const Supply = mongoose.model('Supply');

router.get('/',(req, res) => {
    res.render("supply/addOeditSupply",{
        viewTitle : "Insert Supply"
    });
});

router.post('/',(req, res) => {
    if(req.body._id == '')
        insertRecord(req,res);
        else
        updateRecord(req,res);
});


function insertRecord(req, res){
    var supply = new Supply();
    supply.RFC = req.body.RFC;
    supply.email = req.body.email;
    supply.mobile = req.body.mobile;
    supply.address = req.body.address;
    supply.cp = req.body.cp;
    supply.save((err, doc) => {
        if(!err)
            res.redirect('supply/list');
        else{
            if (err.name == 'ValidationError') {
                handleValidationError(err, req.body);
                res.render("supply/addOeditSupply",{
                    viewTitle : "Insert Supply",
                    supply : req.body
                });
            }else
            console.log('Error during record insertion : ' + err);
        }
    });
}

function updateRecord(req, res){
    Supply.findOneAndUpdate({ _id: req.body._id}, req.body, { new: true }, (err, doc) =>{
        if (!err) { res.redirect('supply/list'); 
    }else {
        if (err.name == 'ValidationError') {
            handleValidationError(err, req.body);
            res.render("supply/addOeditSupply", {
                viewTitle: 'Update Supply',
                employee: req.body
            });
        }
        else
            console.log('Error during record update : ' + err);
        }
    });
}

router.get('/list', (req, res) => {
    Supply.find((err,docs)=>{
        if(!err){
            res.render("supply/list",{
                list : docs
            });
        }
        else{
            console.log('Error in retrieving supply list : ' + err);
        }
    });
});


function handleValidationError( err,body){
    for(field in err.error)
    {
        switch (err.errors[field].path) {
            case 'RFC':
                body['RFCError'] = err.errors[field].message;
                break;
            case 'email':
                body['emailError'] = err.errors[field].message;
                break;
            default:
                break;
        }
    }
}

router.get('/:id',(req, res) => {
    Supply.findById(req.params.id, (err, doc) => {
        if(!err){
            res.render("supply/addOeditSupply",{
                viewTitle : "Update Supply",
                supply : doc
            });
        }
    });

});

router.get('/delete/:id', (req, res) => {
    Supply.findByIdAndRemove(req.params.id, (err, doc) => {
        if (!err) {
            res.redirect('/supply/list');
        }
        else { console.log('Error in supply delete :' + err); }
    });
});

module.exports = router;