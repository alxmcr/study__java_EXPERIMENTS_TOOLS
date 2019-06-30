const express = require('express');

const app = express();

app.get('/', function (request, response) {
	response.writeHead(200, {
			'Content-Type' : 'text/html'
		});

	response.write('<html>');
	response.write('<body>');
	response.write('<h1>Hello, World!</h1>');
	response.write('</body>');
	response.write('</html>');

	response.end();

});

app.get('/persons', function (request, response) {
	response.send("Hi! You're in /personas");
});

app.get('/hobbies', function (request, response) {
	response.send("Hi! You're in /hobbies");
});

app.listen(3000, function () {
	console.log("App listening on port 3000!");
});