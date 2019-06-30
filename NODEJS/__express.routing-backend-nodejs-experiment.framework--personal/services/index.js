'use strict';

const jwt = require('jwt-simple');
const moment = require('moment');
const config = require('../config');

function createToken (user){
	const payload = {
		// No recomendable!!!, usar el mismo ID de Mongo DB
		sub: user._id,
		// When is created the token?
		iat: moment().unix(),
		// When is expired the token?
		exp: moment().add(14, 'days').unix(),
	}


	// SECRET_TOKEN
	// SECRET_TOKEN: 'miclavedetokens'
	return jwt.encode(payload, config.SECRET_TOKEN);
}

function decodeToken(token){
	const decoded = new Promise((resolve, reject) => {
		try {
			const payload = jwt.decode(token, config.SECRET_TOKEN);

			if (payload.exp <= moment().unix()){
				reject({
					status: 401,
					message: 'El token ha expirado'
				});
			}

			resolve(payload.sub);
		}catch(err){
			reject({
				status: 500,
				message: 'Invalid Token'
			});
		}
	});

	return decoded;
}

module.exports = {
	createToken,
	decodeToken
};

