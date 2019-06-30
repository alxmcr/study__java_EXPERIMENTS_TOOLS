// http://docs.sequelizejs.com/manual/installation/getting-started

const Sequelize = require('sequelize');

// Connection to database
const sequelize = new Sequelize('taller', 'postgres', 'postgres', {
	host: 'localhost',
	dialect: 'postgres',

	pool: {
		max: 5,
		min: 0,
		acquire: 30000,
		idle: 10000
	},
});

// Test connection
sequelize
	.authenticate()
	.then(() => {
		console.log('Connection has been established successfully.');
	})
	.catch(err => {
		console.error('Unable to connect to the database:', err);
	});
