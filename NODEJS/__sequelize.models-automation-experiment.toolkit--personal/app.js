/* 

http://docs.sequelizejs.com/manual/installation/getting-started

http://docs.sequelizejs.com/class/lib/query-interface.js~QueryInterface.html#instance-method-bulkInsert

*/

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


// Model: Person
const Person = sequelize.define('person', {
	id_person: {
		type: Sequelize.INTEGER,
		primaryKey: true,
		autoIncrement: true
	},
	nombres: {
		type: Sequelize.STRING(100),
		allowNull: false
	},
	apellidos: {
		type: Sequelize.STRING(100),
		allowNull: false
	},
	fecha_nacimiento: {
		type: Sequelize.STRING(100),
		allowNull: false
	}
});

// Model: Hobby
const Hobby = sequelize.define('hobby', {
	id_hobbie: {
		type: Sequelize.INTEGER,
		primaryKey: true,
		autoIncrement: true
	},
	description: {
		type: Sequelize.STRING(100),
		allowNull: false
	}	
});


// Associations
Hobby.belongsTo(Person, {
		as: 'people',
		foreignKey: 'fk_persona_id',
		allowNull: false

}); // Adds fk_persona_id to Hobby


Person.hasMany(Hobby, {
	as: 'hobbies',
	foreignKey: 'fk_persona_id',
	allowNull: false
});

// Drop all tables
sequelize.drop().then(() => {
	console.log("Tables dropped.");
}).catch(error => {
	console.error(error);
})

queryInterface.bulkInsert('people', [{
	nombres: 'Juan',
	apellidos: 'Perez Flores',
	fecha_nacimiento: '1985-05-03'
 }, {
	nombres: 'Mario',
	apellidos: 'Rojas Aguilera',
	fecha_nacimiento: '1985-05-03'
 }]);

sequelize.sync().then(() => {
	console.log("Tables created.");
}).catch(error => {
	console.error(error);
});

