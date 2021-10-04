//Funções conduta do cidadão
$("#customRadio2").click(function() {
	$("#adicionar-a-lista").hide();
	$("#conduta-cidadao").show();
});

$("#customRadio1").click(function() {
	$("#conduta-cidadao").hide();
	$("#adicionar-a-lista").show();
})
//Fim funções conduta do Cidadão

$("#form-finalizar-atendimento").submit(function(evt) {
	evt.preventDefault();
	var atendimentoDTO = {};
	atendimentoDTO.id = $("id-atendimento").val();
	atendimentoDTO['profissionalDestino'] = $("#atendimento-profissionalDestino").val();
	var condutaCidadao;
	var tipoServicos;
	if ($("#customRadio1").is(":checked")) {
		tipoServicos = $("input[name='tipoServicos']:checked").val();
		condutaCidadao = null;
	}
	if ($("#customRadio2").is(":checked")) {
		condutaCidadao = $("input[name='atendimento.condutaCidadao']:checked").val();
		tipoServicos = 5;
	}
	atendimentoDTO['tipoServicos'] = tipoServicos;
	atendimentoDTO['condutaCidadao'] = condutaCidadao;
	$.ajax({
		url: '/atendimento/finalizar',
		method: 'post',
		data: atendimentoDTO,
		success: function() {
			
		}

	})
})
