// User
const User = require('../models/User');

// passport
let passport = require('passport');

module.exports = function(app) {

    app.get('/', function(req, res) {
        console.log(req.url);
        res.status(200).render('index');
    });

    app.get('/login', function(req, res) {
        console.log(req.url);
        res.status(200).render('formLogin');
    });

    app.get('/profile', function(req, res) {
        console.log(req.url);
        res.status(200).render('profile');
    });

    app.get('/users', (req, res, next) => {
        User.find(function(err, users) {
            if (err) return res.status(400).send(`Error ${error}`);
            res.status(200).send(users);
        })
    });

    app.post('/login',
        passport.authenticate('local', {
            successRedirect: '/profile',
            failureRedirect: '/login'
        })
    );
}