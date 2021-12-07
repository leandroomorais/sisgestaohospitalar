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
	atendimentoDTO.id = $("#id-atendimento").val();
	atendimentoDTO['profissionalDestino'] = $("#atendimento-profissionalDestino").val();
	var condutaCidadao;
	var tipoServicos = new Array();
	$.each($("input[name='tipoServicos']:checked"), function() {
		tipoServicos.push($(this).val());
	})
	if ($("#customRadio1").is(":checked")) {
		tipoServicos = tipoServicos;
		condutaCidadao = null;
	}
	if ($("#customRadio2").is(":checked")) {
		condutaCidadao = $("input[name='atendimento.condutaCidadao']:checked").val();
		tipoServicos = null;
	}

	if (tipoServicos != null) {
		atendimentoDTO['tipoServicos'] = tipoServicos.toString();
	} else {
		atendimentoDTO['tipoServicos'] = tipoServicos;
	}
	atendimentoDTO['condutaCidadao'] = condutaCidadao;
	$.ajax({
		url: '/atendimento/finalizar/triagem',
		method: 'post',
		data: atendimentoDTO,
		success: function(data) {
			window.location.replace("/triagem/listar");
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'Atendimento finalizado com sucessso',
				target: '_blank'
			}, {
				// settings
				element: 'body',
				position: null,
				type: "success",
				allow_dismiss: true,
				newest_on_top: false,
				showProgressbar: false,
				placement: {
					from: "top",
					align: "right"
				},
				offset: 20,
				spacing: 10,
				z_index: 1031,
				delay: 5000,
				timer: 1000,
				url_target: '_blank',
				mouse_over: null,
				animate: {
					enter: 'animated fadeInDown',
					exit: 'animated fadeOutUp'
				},
				onShow: null,
				onShown: null,
				onClose: null,
				onClosed: null,
				icon_type: 'class',
			});
		},
	})
})
