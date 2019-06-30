'use strict';

const express = require('express');
// Middleware: 
// Capa por donde pasan las peticiones
const bodyParser = require('body-parser');
// Express
const app = express();
// API
const api = require('./routes/routes');
// Template Handlebars
const hbs = require('express-handlebars');

app.use(bodyParser.urlencoded({
	extended: false
}));
app.use(bodyParser.json());

app.engine('.hbs', hbs({
	defaultLayout: 'default',
	extname: '.hbs'
}));

app.set('view engine', '.hbs');


app.use('/api', api);
app.get('/login', (req, res) => {
	res.render('login');
});

module.exports = app;