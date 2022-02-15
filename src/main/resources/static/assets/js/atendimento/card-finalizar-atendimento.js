
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

$("#cancelar-atendimento").click(function() {
	swal({
		title: 'Tem certeza que deseja cancelar este atendimento?',
		text: 'O cidadão retornará para a lista de atendimentos com o status "Aguardando atendimento". Os dados da consulta e diagnósticos serão apagados!',
		icon: 'warning',
		buttons: {
			cancel: {
				visible: true,
				text: 'Não, retornar ao atendimento!',
				className: 'btn btn-success btn-border'
			},
			confirm: {
				text: 'Sim, cancelar!',
				className: 'btn btn-success'
			}
		}
	}).then((willDelete) => {
		if (willDelete) {
			swal("Infelizmente essa função ainda não foi implementada!", {
				buttons: {
					confirm: {
						className: 'btn btn-success'
					}
				}
			});
		} else {
			swal("Certo, não iremos excluir!", {
				buttons: {
					confirm: {
						className: 'btn btn-success'
					}
				}
			});
		}
	});
	return false;
})

$("#form-finalizar-atendimento").submit(function(evt) {
	evt.preventDefault();
	var atendimentoDTO = {};
	atendimentoDTO.id = $("#id-atendimento").val();
	atendimentoDTO['profissionalDestino'] = $("#atendimento-profissionalDestino").val();
	atendimentoDTO['condutaCidadao'] = $("input[name='atendimento.condutaCidadao']:checked").val();

	var tipoServicos = new Array();

	$.each($("input[name='tipoServicos']:checked"), function() {
		tipoServicos.push($(this).val());
	})

	if (tipoServicos.length > 0) {
		atendimentoDTO['tipoServicos'] = tipoServicos.toString();
	} else if (tipoServicos.length == 0) {
		atendimentoDTO['tipoServicos'] = null;
	}
	atendimentoDTO.tempoObservacao = $("#tempo-observacao").val();
	atendimentoDTO.caraterAtendimento = $("input[name='atendimento.tipoAtendimento']:checked").val();
	console.log(atendimentoDTO);
	$.ajax({
		url: '/atendimento/finalizar/',
		method: 'post',
		data: atendimentoDTO,
		success: function(data) {
			window.location.replace("/atendimento/listar-atendimentos");
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
		statusCode: {
			403: function(xhr) {
					swal("Atenção!", xhr.responseText,{
					icon: "warning",
					buttons: {
						confirm: {
							className: 'btn btn-primary'
						}
					}
				});
			},
			422: function(xhr) {
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val) {
					$.notify({
						// options
						icon: 'flaticon-exclamation',
						title: 'ATENÇÃO',
						message: val,
						target: '_blank'
					}, {
						// settings
						element: 'body',
						position: null,
						type: "danger",
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

					$("input[name='" + key + "']").parent().parent().parent().addClass("has-error has-feedback");

				})
			}
		}
	})
})
