function Student(_name, _lastname, career){
	Person.call(this, _name, _lastname);
	this.career = career;
}

// ⭐
Student.prototype = Object.create(Person.prototype);
Student.prototype.constructor = Student;

var Maria = new Student("Maria", "Camacho", "Turism");
var Pamela = new Student("Pamela", "Miranda", "Engineering");

Maria.sayHello();
Pamela.sayHello();