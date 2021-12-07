
$("#customRadio1").on("click", function() {
	if ($(this).is(':checked')) {
		$("#adicionar-a-lista").fadeIn(100);
	} else {
		$("#adicionar-a-lista").fadeOut(100);
	}
})


$("input[name='atendimento.condutaCidadao']").click(function() {
	if ($("input[name='atendimento.condutaCidadao']:checked").val() == "OBSERVACAO") {
		$("#form-observacao").fadeIn(100);
	} else {
		$("#form-observacao").fadeOut(100);
	}
})

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

	$.each($("input[name='tipoServicos']:checked"), function() {
		tipoServicos.push($(this).val());
	})


	atendimentoDTO.tempoObservacao = $("#tempo-observacao").val();
	atendimentoDTO.caraterAtendimento = $("input[name='atendimento.tipoAtendimento']:checked").val();
	$.ajax({
		url: '/atendimento/finalizar/triagem',
		method: 'post',
		data: atendimentoDTO,
		success: function(data) {
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
