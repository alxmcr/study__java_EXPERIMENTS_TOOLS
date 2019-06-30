console.log('It works :) !!!');

/* UNIQ */
/* npm install uniq */
var unique = require('uniq');

var numbers = [321, 200, 200, 1994, 2018, 65, 1989, 7777, 235, 2006];

console.log('numbers:', numbers);
console.log('unique(numbers):', unique(numbers));

document.addEventListener("DOMContentLoaded", function(){
    // container
    const container = document.getElementById("flexbox-numbers");

    numbers.forEach(function(number){
        // creating node element
        const item = document.createElement('div');
        item.setAttribute('class', 'item');

        const texto = document.createTextNode(number);
        item.appendChild(texto);

        container.appendChild(item);
    });
});