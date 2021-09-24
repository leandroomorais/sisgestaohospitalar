
//Função Habilita pesquisa de Medicamentos
$("#button-medicamento").click(function() {
	limpaPrescricao();
	$("#i-medicamento").removeClass().addClass("fa fa-search");
})
//Fim da função habilita pesquisa de Medicamentos

$("#administracaoRealizada").click(function() {
	if ($(this).prop("checked") == true) {
		$("#exampleModalCenter").modal("show");

	}
})

function exibeFormularioPrescricao() {
	limpaPrescricao();
	removeInvalidFedbackPrescricao();
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

function fechaFormularioEditPrescricao() {
	limpaPrescricaoDto();
	$("#div-form-edit-prescricao").fadeOut(100);
	$("#card-header").fadeIn(100);
	$("#info-prescricoes").fadeIn(100);
}

function exibeFormularioEditPrescricao(element) {
	limpaPrescricaoDto();
	removeInvalidFedbackPrescricaoDTO();
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

function confirmaPrescricao(element) {
	var idPrescricao = $(element).attr("data-value");
	detalharPrescricao(idPrescricao);
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
	$("#dose-unica").parent().removeClass().addClass("toggle btn btn-black off");
	$("#uso-continuo").parent().removeClass().addClass("toggle btn btn-black off");
	$("#administracao-no-atendimento").parent().removeClass().addClass("toggle btn btn-black off");
}

function limpaPrescricaoDto() {
	$("#medicamento-prescricao-dto").val("");
	$("#id-medicamento-dto").val("");
	$("#concentracao-dto").val("");
	$("#forma-dto").val("");
	$("#quantidade-dto-small").text("");
	$("#dose-unica-dto").parent().removeClass().addClass("toggle btn btn-black off");
	$("#uso-continuo-dto").parent().removeClass().addClass("toggle btn btn-black off");
	$("#administracao-no-atendimento-dto").parent().removeClass().addClass("toggle btn btn-black off");

}


$("#form-prescricao").submit(function(evt) {
	evt.preventDefault();
	var prescricao = {};

	prescricao['medicamento'] = $("#id-medicamento").val();
	prescricao['viaAdministracao'] = $("#via option:selected").val();
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
		beforeSend: function() {
			console.log(prescricao);
			removeInvalidFedbackPrescricao();
		},
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
			400: function() {
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

					$("input[name='" + key + "']").parent().parent().addClass("has-error has-feedback");

				})
			}
		},

	})
})

$("#form-edit-prescricao").submit(function(evt) {
	evt.preventDefault();
	var prescricaoDTO = {};

	prescricaoDTO.id = $("#id-prescricao").val();
	prescricaoDTO['medicamento'] = $("#id-medicamento-dto").val();
	prescricaoDTO['viaAdministracao'] = $("#via-dto option:selected").val();
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
		beforeSend: function() {
			removeInvalidFedbackPrescricaoDTO();
		},
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

		statusCode: {
			400: function() {
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

					if (key == 'medicamento') {
						$("#medicamento-prescricao-dto").parent().parent().addClass("has-error has-feedback");
					}
					$("#form-edit-prescricao input[name='" + key + "']").each(function(index) {
						$(this).parent().parent().addClass("has-error has-feedback");
					})
				})
			}
		},

	})

})

function editarPrescricao(element) {
	var idPrescricao = $(element).attr("data-value");
	$.ajax({
		url: '/prescricao/' + idPrescricao,
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
				$("#administracao-no-atendimento-dto").parent().removeClass().addClass("toggle btn btn-primary");
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
		icon: 'warning',
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
						icon: 'success',
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

function detalharPrescricao(id) {
	var idPrescricao = id;
	$.ajax({
		url: '/prescricao/' + idPrescricao,
		method: 'get',
		success: function(data) {
			var noAtendimento = "Não";
			var usoContinuo = "Não";
			var doseUnica = "Não";
			if (data.administracaoNoAtendimento) {
				noAtendimento = "Sim";
			}
			if (data.usoContinuo) {
				usoContinuo = "Sim";
			}
			if (data.doseUnica) {
				doseUnica = "Sim";
			}
			$("#card-prescricao-administracao").append(createCardDetalhePrescricao(data.medicamento.principioAtivo, data.medicamento.concentracao, data.quantidade, data.medicamento.unidadeFornecimento, data.viaAdministracao.nome, data.medicamento.formaFarmaceutica.nome, data.posologia, data.orientacoes, data.data, noAtendimento, usoContinuo, doseUnica))
		}
	})
};

function atualizaPrescricoes() {
	var atendimentoId = $("#id-atendimento").val();
	$("#div-prescricoes").empty();
	$.ajax({
		url: '/atendimento/prescricoes/' + atendimentoId,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-prescricoes").append("<h5 class='card-title text-center'>Não existem prescrições para este atendimento</h5><p class='card-text text-center'>Clique no botão Nova prescrição para cadastrar uma.</p>");
			} else {
				$.each(data, function(key, item) {
					$("#div-prescricoes").append(createCardPrescricao(item));
				})
			}

		},
	})
};

function createCardPrescricao(data) {
	return "<div class='card'><div class='card-body'><div class='d-flex'><div class='flex-1 ml-3 pt-1'>" +
		h5CarPrescricao(data.medicamento.principioAtivo, data.viaAdministracao.nome) + inforCardPrimary(data.medicamento.concentracao, data.medicamento.unidadeFornecimento, data.quantidade) +
		infoCardPrescricao("Forma famaceutica: ", data.medicamento.formaFarmaceutica.nome) +
		infoCardPrescricao("Posologia: ", data.posologia) +
		infoCardPrescricao("Antônio Cardinael da Silva Gomes", "CRM: 54884/RN") +
		"<p> " + data.orientacoes + " </p>" +
		+ "<div id='table-registros'></div>" +
		"</div><div class='float-right pt-1'><small class='text-muted'> " + data.data + " </small></div></div><div class='text-right'>" +

		"<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='imprimirPrescricao()'><i class='fa fa-print'></i> Imprimir</button>" +
		buttonConfirmar()
		+ "<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='exibeFormularioEditPrescricao(this)'><i class='fa fa-edit'></i> Editar</button>" +
		"<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='excluirPrescricao(this)'><i class='fa fa-trash'></i> Excluir</button>" +
		"</div></div></div>";

	function buttonConfirmar() {
		if (data.administracaoNoAtendimento) {
			return "<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='confirmaPrescricao(this)'><i class='fa fa-check'></i> Administrar</button>";
		} else {
			return "";
		}
	}

	function buttonEditar() {

	}

	function buttonExcluir() {

	}
}

function createCardDetalhePrescricao(principioAtivo, concentracao, quantidade, unidade, viaAdministracao, formaFarmaceutica, posologia, orientacoes, hora, dataValue, noAtendimento, usoContinuo, doseUnica) {
	return "<div class='card'><div class='card-body'><div class='d-flex'><div class='flex-1 ml-3 pt-1'>" +
		h5CarPrescricao(principioAtivo, viaAdministracao) + inforCardPrimary(concentracao, unidade, quantidade) +
		infoCardPrescricao("Forma famaceutica: ", formaFarmaceutica) +
		infoCardPrescricao("Posologia: ", posologia) +
		infoCardPrescricao("Admin. no atendimento: ", noAtendimento) +
		infoCardPrescricao("Uso contínuo: ", usoContinuo) +
		infoCardPrescricao("Dose única: ", doseUnica) +
		infoCardPrescricao("Antônio Cardinael da Silva Gomes", "CRM: 54884/RN") +
		"<p> " + orientacoes + " </p>" +
		"</div><div class='float-right pt-1'><small class='text-muted'> " + hora + " </small></div></div><div class='text-right'>" +
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

function isEmpty(obj) {
	if (isSet(obj)) {
		if (obj.length && obj.length > 0) {
			return false;
		}

		for (var key in obj) {
			if (hasOwnProperty.call(obj, key)) {
				return false;
			}
		}
	}
	return true;
};

function isSet(val) {
	if ((val != undefined) && (val != null)) {
		return true;
	}
	return false;
};

function removeInvalidFedbackPrescricao() {
	$("#form-prescricao input, #form-prescricao textarea").each(
		function(index) {
			var str = $(this).parent().parent().attr("class");
			if (str.match(/has-error/)) {
				$(this).parent().parent().removeClass("has-error has-feedback");
			}

		}
	);
}

function removeInvalidFedbackPrescricaoDTO() {
	$("#form-edit-prescricao input, #form-edit-prescricao textarea").each(
		function(index) {
			var str = $(this).parent().parent().attr("class");
			if (str.match(/has-error/)) {
				$(this).parent().parent().removeClass("has-error has-feedback");
			}
		}
	);
}


