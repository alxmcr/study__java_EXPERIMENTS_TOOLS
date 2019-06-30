function enviar(){
	console.log("Enviar");
}

var btnEnviar = document.getElementById("btn-enviar");

function mostrarAlert(){
	alert("Enviando...");
}

btnEnviar.addEventListener("click", mostrarAlert)