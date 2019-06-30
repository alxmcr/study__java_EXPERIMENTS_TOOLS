// Passport Strategy
let LocalStrategy = require('passport-local').Strategy;

// MONGO DB
const mongoose = require('mongoose');
const config = require('./config');
console.log(config.db);

mongoose.connect(config.db, {
    useNewUrlParser: true
});

// Checking connection
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
    // we're connected!
    console.log("we're connected!");
});

// User
const User = require('./models/User');

module.exports = function(passport) {

    app.post('/login', function(req, res) {

        passport.use(new LocalStrategy(
            function(username, password, done) {
                User.findOne({
                    username: username
                }, function(err, user) {
                    if (err) {
                        return done(err);
                    }
                    if (!user) {
                        return done(null, false, {
                            message: 'Incorrect username.'
                        });
                    }
                    if (!user.validPassword(password)) {
                        return done(null, false, {
                            message: 'Incorrect password.'
                        });
                    }
                    return done(null, user);
                });
            }
        ));
    });
}