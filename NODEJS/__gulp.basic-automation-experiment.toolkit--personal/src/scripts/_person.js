function Person(name, lastname){
	this.name = name;
	this.lastname = lastname;
}

Person.prototype.sayHello = function(){
	console.log("Hi!, My name is " + this.name);
}

// new objects
var Alejandro = new Person("Alejandro", "Coca");
var Reynaldo = new Person("Reynaldo", "Rojas");
var Mauricio = new Person("Mauricio", "Flores");

// sayings
Alejandro.sayHello();
Reynaldo.sayHello();
Mauricio.sayHello();