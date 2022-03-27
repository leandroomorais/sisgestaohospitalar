var idCidadao = $("#idCidadao").val();
$(document).ready(function() {
	mascaraInputs();
	$("#card-action").hide();
	$("input,select,button").prop("disabled", true);
})

$("#editar").click(function() {
	$("#card-action").show();
	$("input,select,button").prop("disabled", false);
})

$("#btn-cancelar-form").click(function() {
	cancelar('Deseja cancelar?', 'Se clicar em sim todos os dados serão apagados!', 'warning', '/cidadao/detalhe/' + idCidadao);
})

