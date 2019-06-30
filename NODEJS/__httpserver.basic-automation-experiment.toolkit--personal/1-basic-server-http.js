/*
Sources:
https://nodejs.org/en/docs/guides/anatomy-of-an-http-transaction/

https://www.sitepoint.com/creating-a-http-server-in-node-js/
*/

var http = require("http");

// Create the Server
var server = http.createServer(function(request, response) {
		// magic happens here!

		/* 
		Sends:
			a) HTTP Status code: 200 OK
			b) a collection of response headers back to the client. 
				- MIME type is "text/html".
		*/
		response.writeHead(200, {
			'Content-Type' : 'text/html'
		});

		// write the HTML page
		response.write('<html>');
		response.write('<body>');
		response.write('<h1>Hello, World!</h1>');
		response.write('</body>');
		response.write('</html>');

		/*
		the response headers and body have been sent, and that the request has been fulfilled.
		*/
		response.end();
});

// Port: 4000
server.listen(4000);

console.log("Server listening...");