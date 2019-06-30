'use strict';

// Config
const config = require('./config');

const appExpress = require('./app')

// mongoose
const mongoose = require('mongoose');


// Use connect method to connect to the Server
mongoose.connect(config.db, {
	useNewUrlParser: true
});

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function() {
	// we're connected!
	console.log("Connected successfully to server");

	appExpress.listen(config.port, () => {
		console.log(`API REST corriendo en el localhost:${config.port}`);
	});
});