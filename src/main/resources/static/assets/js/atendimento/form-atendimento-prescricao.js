
//Função Habilita pesquisa de Medicamentos
$("#button-medicamento").click(function() {
	limpaPrescricao();
	$("#i-medicamento").removeClass().addClass("fa fa-search");
})
//Fim da função habilita pesquisa de Medicamentos

$("#administracaoRealizada").click(function() {
	if ($(this).prop("checked") == true) {
		var prescricaoId = $(this).attr("data-value");
		$.ajax({
			url: '/prescricao/' + prescricaoId,
			method: 'get',
			success: function(data) {
				$("#exampleModalCenter").modal("show");
				$("#procedimentos-admin-medicamento").val(data.viaAdministracao.procedimento.codigo + " - " + data.viaAdministracao.procedimento.nome);
				$("#procedimentos-admin-medicamento").attr("data-toggle", "tooltip").attr("title", $("#procedimentos-admin-medicamento").val());
				$("#id-procedimento").val(data.viaAdministracao.procedimento.codigo);
				$("#qtd-procedimento").val(1);
			}
		})

	}
})

$("#salva-procedimento-medicamento").click(function() {
	var idAtendimento = $("#id-atendimento").val();
	var procedimeto = $("#id-procedimento").val();
	var quantidade = $("#qtd-procedimento").val();
	submitProcedimentoAutomatico(idAtendimento, procedimeto, null, quantidade);
})

function exibeFormularioPrescricao() {
	limpaPrescricao();
	removeInvalidFedbackPrescricao();
	$("#card-list-prescricoes").fadeOut(100);
	//$("#card-edit-prescricao").hide();
	//$("#card-novo-registro-administracao").hide();
	//$("#card-list-registros-administracao").hide();
	$("#card-nova-prescricao").fadeIn(100);
}

function fechaFormularioPrescricao() {
	limpaPrescricao();
	$("#card-nova-prescricao").fadeOut(100);
	$("#card-list-prescricoes").fadeIn(100);
}
$("#nova-prescricao-voltar").click(function() {
	fechaFormularioPrescricao();
})

function exibeFormularioEditPrescricao() {
	limpaPrescricaoDto();
	removeInvalidFedbackPrescricaoDTO();
	$("#card-list-prescricoes").fadeOut(100);
	$("#card-edit-prescricao").fadeIn(100);
}

$("#edit-prescricao-voltar").click(function() {
	fechaFormularioEditPrescricao();
})

function fechaFormularioEditPrescricao() {
	limpaPrescricaoDto();
	$("#card-edit-prescricao").fadeOut(100);
	$("#card-list-prescricoes").fadeIn(100);
}

function exibeRegistros(element) {
	var idPrescricao = $(element).attr("data-value");
	$("#button-novo-registro").attr("data-value", idPrescricao);
	$("#card-list-prescricoes").fadeOut(100);
	$("#card-list-registros-administracao").fadeIn(100);
	dataTableRegistro(idPrescricao);
}

$("#registro-voltar").click(function() {
	$("#card-list-prescricoes").fadeIn(100);
	$("#card-list-registros-administracao").fadeOut(100);
})

function exibeFormularioNovoRegistro(element) {
	var idPrescricao = $(element).attr("data-value");
	limpaNovoRegistro();
	$("#card-list-registros-administracao").fadeOut(100);
	$("#card-novo-registro-administracao").fadeIn(100);
	$("#card-prescricao-administracao").empty().append(detalharPrescricao(idPrescricao));
	$("#administracaoRealizada").attr("data-value", idPrescricao);
}

$("#form-novo-registro-voltar").click(function() {
	fechaFormularioNovoRegistro();
})

function fechaFormularioNovoRegistro() {
	limpaNovoRegistro();
	$("#card-list-registros-administracao").fadeIn(100);
	$("#card-novo-registro-administracao").fadeOut(100);
}

$("#button-medicamento-dto").click(function() {
	$("#i-medicamento-dto").removeClass("fa fa-edit").addClass("fa fa-search");
	$("#medicamento-prescricao-dto").attr("disabled", false);
	limpaPrescricaoDto();
})

function limpaNovoRegistro() {
	$("#administracaoRealizada").prop("checked", false);
	tinymce.get("nota-administracao").setContent("");
}

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

$("#form-confirma-administracao").submit(function(evt) {
	evt.preventDefault();
	var registroAdministracao = {};
	registroAdministracao.administracaoRealizada = $("#administracaoRealizada").prop("checked");
	registroAdministracao.idPrescricao = $("#administracaoRealizada").attr("data-value");
	$.ajax({
		url: '/registro-administracao/',
		method: 'post',
		data: registroAdministracao,
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O registro de administração da prescrição foi salvo',
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
			fechaFormularioNovoRegistro();
			$("#table-registros").DataTable().ajax.reload();
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
	prescricao['atendimento'] = $("#id-atendimento").val();
	prescricao['prontuario'] = $("#id-prontuario").val();

	$.ajax({
		url: '/prescricao/',
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
			},

			403: function() {
				$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: 'Não é possível editar esta prescrição, pois já existe um registro de administração cadastrado.',
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
		},

	})

})

function editarPrescricao(element) {
	var idPrescricao = $(element).attr("data-value");
	$.ajax({
		url: '/prescricao/editar/' + idPrescricao,
		method: 'get',
		success: function(data) {
			exibeFormularioEditPrescricao()
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


			403: function() {
				$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: 'Não é possível editar esta prescrição, pois já existe um registro de administração cadastrado.',
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
		},
	})

}

function excluirPrescricao(element) {
	var idPrescricao = $(element).attr("data-value");
	var idProntuario = $("#id-prontuario").val();

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
				url: '/prescricao/excluir/' + idProntuario + "/" + idPrescricao,
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

				statusCode: {
					403: function() {
						$.notify({
							// options
							icon: 'flaticon-exclamation',
							title: 'ERRO',
							message: 'Não é possível excluir esta prescrição, pois já existe um registro de administração cadastrado ou o atendimento já foi finalizado',
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
				},
			})
		}
	});
};

function detalharPrescricao(id) {
	$.ajax({
		url: '/prescricao/' + id,
		method: 'get',
		success: function(data) {
			$("#card-prescricao-administracao").append(createCardDetalhePrescricao(data));
		}
	})
};

function atualizaPrescricoes() {
	var atendimentoId = $("#id-atendimento").val();
	$("#div-prescricoes").empty();
	$.ajax({
		url: '/prescricao/listar/atendimento/' + atendimentoId,
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

function createCardDetalhePrescricao(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-8'>" +
		h5CarPrescricao(data.medicamento.principioAtivo, data.viaAdministracao.nome) + inforCardPrimary(data.medicamento.concentracao, data.medicamento.formaFarmaceutica.nome) +
		infoCardQuantidade(data.quantidade, data.medicamento.unidadeFornecimento) +
		infoCardPrescricao("Posologia: ", data.posologia) +
		inforCardBooleans(data) +
		infoCardPrescricao("Orientações: ", data.orientacoes) +
		"</div><div class='col-md-4 text-right'>" +
		infoCardDataProfissional(data.data, data.profissional.nome, data.profissional.numeroRegistro + " / " + data.profissional.siglaUfEmissao) +
		"</div></div><div class='text-right'>" +
		"</div></div></div>";
}

function createCardPrescricao(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-8'>" +
		h5CarPrescricao(data.medicamento.principioAtivo, data.viaAdministracao.nome) + inforCardPrimary(data.medicamento.concentracao, data.medicamento.formaFarmaceutica.nome) +
		infoCardQuantidade(data.quantidade, data.medicamento.unidadeFornecimento) +
		infoCardPrescricao("Posologia: ", data.posologia) +
		inforCardBooleans(data) +
		infoCardPrescricao("Orientações: ", data.orientacoes) +
		"</div><div class='col-md-4 text-right'>" +
		infoCardDataProfissional(data.dataRegistro, data.profissional.nome, data.profissional.numeroRegistro + " / " + data.profissional.siglaUfEmissao) +
		"</div></div><div class='text-right'>" +
		"<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='imprimirPrescricao()'><i class='fa fa-print'></i> Imprimir</button>" +
		buttonRegistros()
		+ buttonEditar()
		+ buttonExcluir()
		+ "</div></div></div>";

	function buttonRegistros() {
		if (data.administracaoNoAtendimento) {
			return "<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='exibeRegistros(this)'><i class='fa fa-check'></i> Registros de administração</button>";
		} else {
			return "";
		}
	}

	function buttonEditar() {
		var username = $("#nome-usuario").text();
		if (data.profissional.cpf != username) {
			return "";
		} else if (data.profissional.cpf == username) {
			return "<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='editarPrescricao(this)'><i class='fa fa-edit'></i> Editar</button>";
		}
	}

	function buttonExcluir() {
		var username = $("#nome-usuario").text();
		if (data.profissional.cpf != username) {
			return "";
		} else if (data.profissional.cpf == username) {
			return "<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='excluirPrescricao(this)'><i class='fa fa-trash'></i> Excluir</button>";
		}
	}
}


function h5CarPrescricao(principioAtivo, viaAdmnistracao) {
	return "<h5 class='text-uppercase fw-bold mb-1'> " + principioAtivo + " <span class='text-danger text-uppercase pl-3'> " + viaAdmnistracao + " </span></h5>";
}

function inforCardPrimary(info, formaFarmaceutica) {
	return "<strong> Concentração: </strong><span> " + info + " </span> | <strong> Forma farmacêutica: </strong><span> " + formaFarmaceutica + " </span><br>";
}

function infoCardQuantidade(quantidade, unidadeForncecimento) {
	return "<strong>Quantidade a dispensar: </strong><span> " + quantidade + " </span><span> " + unidadeForncecimento + " </span><br>"
}

function infoCardDataProfissional(date, nomeProfissional, crm) {
	return "<span class='text-warning'> " + date + " </span><br><span class='text-muted'> " + nomeProfissional + " </span><br><strong>CRM: </strong><span class='text-muted'> " + crm + " </span><br>"
}

function inforCardBooleans(data) {
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
	return "<strong>Admin. no atendimento: </strong><span> " + noAtendimento + " </span> | <strong>Uso contínuo:</strong><span> " + usoContinuo + " </span> | <strong>Dose única: </strong><span> " + doseUnica + " </span><br>"
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

function dataTableRegistro(id) {
	$("#table-registros").DataTable().destroy();
	$("#table-registros").DataTable({
		responsive: true,
		paging: true,
		searching: false,
		ordering: false,
		ajax: {
			url: '/prescricao/' + id,
			dataSrc: 'registrosAdministracao',
		},
		columns: [
			{
				title: 'REALIZADA',
				data: 'administracaoRealizada',
				mRender: function(data) {
					if (data) {
						return "<span class='badge badge-success'>Sim</span>";
					} else {
						return "<span class='badge badge-danger'>Não</span>";
					}
				}
			},
			{
				title: 'DATA',
				data: 'dataAdministracao',
			},
			{
				title: 'PROFISSIONAL',
				data: 'profissionalResponsavel.nome',
			},
			{
				title: 'NOTA',
				data: 'nota',
				mRender: function(data) {
					if (data != null) {
						return "<div>" + data + "</div>";
					} else {
						return "<small class='text-muted'> Nenhuma nota resgistrada</small>";
					}

				}
			}
		]

	})
}

function submitProcedimentoAutomatico(idAtendimento, codigoProcedimento, tipoServico, quantidade) {
	var relAtendimentoProcedimento = {};
	relAtendimentoProcedimento.idAtendimento = idAtendimento;
	relAtendimentoProcedimento['procedimento.codigo'] = codigoProcedimento;
	relAtendimentoProcedimento.quantidade = quantidade;
	relAtendimentoProcedimento.tipoServico = tipoServico;

	$.ajax({
		url: '/atendimento-procedimento/adicionar',
		method: 'POST',
		data: relAtendimentoProcedimento,
		success: function() {
			$("#exampleModalCenter").modal("hide");
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O procedimento foi adicionado ao atendimento',
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
		}
	})
}

//************Funções autocomplete */
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


