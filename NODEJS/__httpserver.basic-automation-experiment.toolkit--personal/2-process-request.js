/*
Sources:
https://nodejs.org/en/docs/guides/anatomy-of-an-http-transaction/

https://www.sitepoint.com/creating-a-http-server-in-node-js/
*/

var http = require("http");

function process_request_json(request, response) {
		// magic happens here!
		console.log("Request-Method:", request.method);
		console.log("Request-URL:", request.url);
		
		response.writeHead(200, {
			'Content-Type' : 'application/json'
		});

		var msg = {
			text: "Hola Mundo"
		};


		/*
		the response headers and body have been sent, and that the request has been fulfilled.
		*/
		response.end(JSON.stringify(msg));
}

// Create the Server
var server = http.createServer(process_request_json);

// Port: 4000
server.listen(4000);

console.log("Server listening...");

/* Run - Terminal: 
$ curl -i localhost:4000
*/