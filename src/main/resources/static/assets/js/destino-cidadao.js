$(document).ready(function(){
	$("#profissional-destino").hide();
	$("#adicionar").click(function(){
		$("#profissional-destino").fadeIn();
	});
	
	$("#liberar").click(function(){
		$("#profissional-destino").fadeOut();
	})
});