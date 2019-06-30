// Configuration
const config = require('./config');
console.log(config.db);

// Body
const bodyParser = require('body-parser');

// Logger
const morgan = require('morgan');

// MONGO DB
const mongoose = require('mongoose');

// Controller
const userController = require('./controllers/UserController');

// User
const User = require('./models/User');

// Passport
var passport = require('passport');

// Passport "Local Authentication"
var LocalStrategy = require('passport-local').Strategy;

/**  APP  ** */
// Express
const express = require('express');
let app = express();

// 👉 Express templating engine
app.set('view engine', 'pug');

// 👉 Serving static files in Express
app.use('/assets', express.static('assets'))

// Logger
app.use(morgan('combined'));

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({
    extended: false
}));

// parse application/json
app.use(bodyParser.json());

// Passport
app.use(passport.initialize());
app.use(passport.session());

// Listening
const port = 8080;
const host = 'localhost';
app.listen(port, host, function() {
    console.log(`Listening the server on http://${host}:${port} ...`);
});

// MONGO DB
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

// Controllers
userController(app);

passport.serializeUser(function(user, done) {
    console.log(`user ${user}`);
    done(null, user.id);
});

passport.deserializeUser(function(id, done) {
    console.log(`id ${id}`);
    User.findById(id, function(err, user) {
        done(err, user);
    });
});

// Passport "Local Authentication"
passport.use(new LocalStrategy(
    function(username, password, done) {

        console.log(`-username ${username}`);
        console.log(`-password ${password}`);

        User.findOne({
            username: username
        }, function(err, user) {
            if (err) {
                return done(err);
            }
            console.log(`->user ${user}`)
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