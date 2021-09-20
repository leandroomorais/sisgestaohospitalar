
//Função Habilita pesquisa de Medicamentos
$("#button-medicamento").click(function() {
	limpaPrescricao();
	$("#i-medicamento").removeClass().addClass("fa fa-search");
})
//Fim da função habilita pesquisa de Medicamentos

function exibeFormularioPrescricao() {
	limpaPrescricao();
	$("#card-header").fadeOut(100);
	$("#info-prescricoes").fadeOut(100);
	$("#div-form-prescricao").fadeIn(100);
}

function fechaFormularioPrescricao() {
	limpaPrescricao();
	$("#div-form-prescricao").fadeOut(100);
	$("#card-header").fadeIn(100);
	$("#info-prescricoes").fadeIn(100);
}

function exibeFormularioEditPrescricao(element) {
	limpaPrescricaoDto();
	$("#card-header").fadeOut(100);
	$("#info-prescricoes").fadeOut(100);
	$("#div-form-edit-prescricao").fadeIn(100);
	editarPrescricao(element);
}

function fechaFormularioEditPrescricao() {
	limpaPrescricao();
	$("#div-form-edit-prescricao").fadeOut(100);
	$("#card-header").fadeIn(100);
	$("#info-prescricoes").fadeIn(100);
}


//Função autocomplete Medicamentos
$("#medicamento-prescricao").autocomplete({
	source: "/medicamento/buscar",
	focus: function(event, ui) {
		$("#medicamento-prescricao").val(ui.item.principioAtivo + " ; " + ui.item.concentracao + " ; " + ui.item.formaFarmaceutica.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-medicamento").removeClass("fa fa-search").addClass("fa fa-times");
		$("#medicamento-prescricao").val(ui.item.principioAtivo).attr("disabled", true);
		$("#id-medicamento").val(ui.item.id);
		$("#medicamento").val(ui.item.principioAtivo);
		$("#concentracao").val(ui.item.concentracao);
		$("#forma").val(ui.item.formaFarmaceutica.nome);
		$("#quantidade-small").text(ui.item.unidadeFornecimento);
		return false;

	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.principioAtivo + " ; " + "<b>" + item.concentracao + "</b>" + "<br>" + item.formaFarmaceutica.nome + " | " + item.unidadeFornecimento + "</div>")
		.appendTo(ul);
};

//Função autocomplete Medicamentos
$("#medicamento-prescricao-dto").autocomplete({
	source: "/medicamento/buscar",
	focus: function(event, ui) {
		$("#medicamento-prescricao-dto").val(ui.item.principioAtivo + " ; " + ui.item.concentracao + " ; " + ui.item.formaFarmaceutica.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-medicamento-dto").removeClass("fa fa-search").addClass("fa fa-times");
		$("#medicamento-prescricao-dto").val(ui.item.principioAtivo).attr("disabled", true);
		$("#id-medicamento-dto").val(ui.item.id);
		$("#concentracao-dto").val(ui.item.concentracao);
		$("#forma-dto").val(ui.item.formaFarmaceutica.nome);
		$("#quantidade-dto-small").text(ui.item.unidadeFornecimento);
		return false;

	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.principioAtivo + " ; " + "<b>" + item.concentracao + "</b>" + "<br>" + item.formaFarmaceutica.nome + " | " + item.unidadeFornecimento + "</div>")
		.appendTo(ul);
};

$("#button-medicamento-dto").click(function() {
	$("#i-medicamento-dto").removeClass("fa fa-edit").addClass("fa fa-search");
	$("#medicamento-prescricao-dto").attr("disabled", false);
	limpaPrescricaoDto();
})

function limpaPrescricao() {
	$("#medicamento-prescricao").val("").attr("disabled", false);
	$("#id-medicamento").val("");
	$("#concentracao").val("");
	$("#forma").val("");
	$("#posologia").val("");
	$("#orientacao").val("");
	$("#quantidade").val("");
	$("#quantidade-small").text("");
}

function limpaPrescricaoDto() {
	$("#medicamento-prescricao-dto").val("");
	$("#id-medicamento-dto").val("");
	$("#concentracao-dto").val("");
	$("#forma-dto").val("");
	$("#quantidade-dto-small").text("");

}


$("#form-prescricao").submit(function(evt) {
	evt.preventDefault();
	var prescricao = {};

	prescricao['medicamento.id'] = $("#id-medicamento").val();
	prescricao['viaAdministracao.id'] = $("#via option:selected").val();
	prescricao.posologia = $("#posologia").val();
	prescricao.orientacoes = $("#orientacao").val();
	prescricao.doseUnica = $("#dose-unica").prop("checked");
	prescricao.usoContinuo = $("#uso-continuo").prop("checked");
	prescricao.administracaoNoAtendimento = $("#administracao-no-atendimento").prop("checked");
	prescricao.quantidade = $("#quantidade").val();
	prescricao.idAtendimento = $("#id-atendimento").val();
	prescricao['prontuario.id'] = $("#id-prontuario").val();

	$.ajax({
		url: '/atendimento/prescricao',
		method: 'post',
		data: prescricao,
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'A prescrição foi salva',
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
			fechaFormularioPrescricao();
			atualizaPrescricoes();
		},

		statusCode: {
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

					$("input[name='" + key + "']").parent().parent().addClass("has-error has-feedback");

				})
			}
		},

		error: function(xhr) {

			$.notify({
				// options
				icon: 'flaticon-exclamation',
				title: 'ERRO',
				message: 'Não foi possível processar sua solicitação',
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

		}

	})
})

$("#form-edit-prescricao").submit(function(evt) {
	evt.preventDefault();
	var prescricaoDTO = {};

	prescricaoDTO.id = $("#id-prescricao").val();
	prescricaoDTO['medicamento.id'] = $("#id-medicamento-dto").val();
	prescricaoDTO['viaAdministracao.id'] = $("#via-dto option:selected").val();
	prescricaoDTO.posologia = $("#posologia-dto").val();
	prescricaoDTO.orientacoes = $("#orientacao-dto").val();
	prescricaoDTO.doseUnica = $("#dose-unica-dto").prop("checked");
	prescricaoDTO.usoContinuo = $("#uso-continuo-dto").prop("checked");
	prescricaoDTO.administracaoNoAtendimento = $("#administracao-no-atendimento-dto").prop("checked");
	prescricaoDTO.quantidade = $("#quantidade-dto").val();

	console.log(prescricaoDTO);

	$.ajax({
		url: '/prescricao/editar',
		method: 'post',
		data: prescricaoDTO,
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'A prescrição foi salva',
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

			fechaFormularioEditPrescricao();
			atualizaPrescricoes();
		},

		error: function() {
			$.notify({
				// options
				icon: 'flaticon-exclamation',
				title: 'ERRO',
				message: 'Não foi possível processar sua solicitação',
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
		}
	})

})

function editarPrescricao(element) {
	var idPrescricao = $(element).attr("data-value");
	$.ajax({
		url: '/prescricao/editar/' + idPrescricao,
		method: 'get',
		success: function(data) {
			$("#id-prescricao").val(data.id);
			$("#medicamento-prescricao-dto").val(data.medicamento.principioAtivo);
			$("#id-medicamento-dto").val(data.medicamento.id);
			$("#concentracao-dto").val(data.medicamento.concentracao);
			$("#forma-dto").val(data.medicamento.formaFarmaceutica.nome);
			$("#via-dto option[" + data.viaAdministracao.id
				+ "]").attr("selected", true);
			$("#posologia-dto").val(data.posologia);
			$("#quantidade-dto").val(data.quantidade);
			$("#quantidade-dto-small").val(data.medicamento.unidadeFornecimento);
			if (data.doseUnica) {
				$("#dose-unica-dto").parent().removeClass().addClass("toggle btn btn-primary");
			}
			if (data.usoContinuo) {
				$("#uso-continuo-dto").parent().removeClass().addClass("toggle btn btn-primary");
			}
			if (data.administracaoNoAtendimento) {
				$("#administracao-no-atendimento-dto").removeClass().addClass("toggle btn btn-primary");
			}
			$("#orientacao-dto").val(data.orientacoes);
		}
	})

}

function excluirPrescricao(element) {
	var idPrescricao = $(element).attr("data-value");
	var idAtendimento = $("#id-atendimento").val();

	swal({
		title: 'Você tem certeza disso?',
		text: "Você não poderá reverter esta ação!",
		type: 'warning',
		buttons: {
			confirm: {
				text: 'Sim, exclua!',
				className: 'btn btn-success'
			},
			cancel: {
				text: 'Cancelar',
				visible: true,
				className: 'btn btn-danger'
			}
		}
	}).then((Delete) => {
		if (Delete) {
			$.ajax({
				url: '/prescricao/excluir/' + idAtendimento + "/" + idPrescricao,
				method: 'delete',
				success: function() {
					swal({
						title: 'Sucesso!',
						text: 'A prescrição foi excluida.',
						type: 'success',
						buttons: {
							confirm: {
								className: 'btn btn-success'
							}
						}
					});
					atualizaPrescricoes();
				},

				error: function() {
					$.notify({
						// options
						icon: 'flaticon-exclamation',
						title: 'ERRO',
						message: 'Não foi possível processar sua solicitação',
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
					swal.close();
				}
			})
		}
	});

};



function atualizaPrescricoes() {
	var atendimentoId = $("#id-atendimento").val();
	$("#div-prescricoes").empty();
	$.ajax({
		url: '/atendimento/prescricoes/' + atendimentoId,
		method: 'get',
		success: function(data) {
			$.each(data, function(key, item) {
				$("#div-prescricoes").append(createCardPrescricao(item.medicamento.principioAtivo, item.medicamento.concentracao, item.quantidade, item.medicamento.unidadeFornecimento, item.viaAdministracao.nome, item.medicamento.formaFarmaceutica.nome, item.posologia, item.orientacoes, item.data, item.id))
			})
		},
	})
}

function createCardPrescricao(principioAtivo, concentracao, quantidade, unidade, viaAdministracao, formaFarmaceutica, posologia, orientacoes, hora, dataValue) {
	return "<div class='card'><div class='card-body'><div class='d-flex'><div class='flex-1 ml-3 pt-1'>" +
		h5CarPrescricao(principioAtivo, viaAdministracao) + inforCardPrimary(concentracao, unidade, quantidade) +
		infoCardPrescricao("Forma famaceutica: ", formaFarmaceutica) +
		infoCardPrescricao("Posologia: ", posologia) +
		infoCardPrescricao("Antônio Cardinael da Silva Gomes", "CRM: 54884/RN") +
		"<p> " + orientacoes + " </p>" +
		"</div><div class='float-right pt-1'><small class='text-muted'> " + hora + " </small></div></div><div class='text-right'>" +
		"<button type='button' class='btn btn-light btn-sm' data-value='" + dataValue + "' onclick='imprimirPrescricao()'><i class='fa fa-print'></i> Imprimir</button>" +
		"<button type='button' class='btn btn-light btn-sm' data-value='" + dataValue + "' onclick='confirmaPrescricao()'><i class='fa fa-check'></i> Confirmar administração</button>" +
		"<button type='button' class='btn btn-light btn-sm' data-value='" + dataValue + "' onclick='exibeFormularioEditPrescricao(this)'><i class='fa fa-edit'></i> Editar</button>" +
		"<button type='button' class='btn btn-light btn-sm' data-value='" + dataValue + "' onclick='excluirPrescricao(this)'><i class='fa fa-trash'></i> Excluir</button>" +
		"</div></div></div>";
}

function h5CarPrescricao(principioAtivo, viaAdmnistracao) {
	return "<h5 class='text-uppercase fw-bold mb-1'> " + principioAtivo + " <span class='text-danger text-uppercase pl-3'> " + viaAdmnistracao + " </span></h5>";
}

function inforCardPrimary(info, unidade, quantidade) {
	return "<strong> Concentração: </strong><span> " + info + " </span> | <strong> Quantidade: </strong><span> " + quantidade + " </span><span> " + unidade + "</span><br>";
}


function infoCardPrescricao(text, info) {
	return "<strong>" + text + "</strong><span> " + info + "</span><br>";
}


