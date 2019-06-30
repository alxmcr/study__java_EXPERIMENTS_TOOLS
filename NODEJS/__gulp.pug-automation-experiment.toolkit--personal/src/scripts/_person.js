function Person(name, lastname){
	this.name = name;
	this.lastname = lastname;
}

Person.prototype.sayHello = function(){
	console.log("Hi!, My name is " + this.name);
}

// new objects
var Alejandro = new Person("Alejandro", "Coca");
var Pablo = new Person("Pablo", "Rojas");
var Mauricio = new Person("Mauricio", "Flores");

// sayings
Alejandro.sayHello();
Pablo.sayHello();
Mauricio.sayHello();