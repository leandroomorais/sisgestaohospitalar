//Adiciona Máscara aos  inputs 
$(document).ready(function() {
	$("#cns").mask('000.0000.0000.0000');
	$("#cpf").mask('000.000.000-00');
	$("#cns2").mask('000.0000.0000.0000');
	$("#cpf2").mask('000.000.000-00');
	$("#cep").mask('00000-000');
	$("#telefone").mask('(00) 00000-0000');
	$("#perimetrocefalico").mask('000');
	$("#altura").mask('000');
	$("#peso").mask('##.#', {
		reverse : true
	});
	$("#pressaoarterial").mask('000/00', {
		placeholder : "___/__"
	});
	$("#frequenciarespiratoria").mask('000');
	$("#frequenciacardiaca").mask('000');
	$("#temperaturacorporal").mask('00.0', {
		reverse : true
	});
	$("#saturacaooxigenio").mask('000');
	$("#glicemiacapilar").mask('000');
});