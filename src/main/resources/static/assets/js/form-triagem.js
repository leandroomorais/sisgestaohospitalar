//JS Triagem
$(document).ready(function() {

	$("#conduta-cidadao").hide();

	//Função que aplica máscara aos inputs 
	$("#sinaisVitais-pressaoArterial").keydown(function() {
		var pressao = $("#sinaisVitais-pressaoArterial").val();
		if (pressao < 5) {
			$("#sinaisVitais-pressaoArterial").mask("000/00");
		}
		else {
			$("#sinaisVitais-pressaoArterial").mask("000/000");
		}
	});
	$("#sinaisVitais-frequenciaRespiratoria").mask("000");
	$("#sinaisVitais-frequenciaCardiaca").mask("000");
	$("#sinaisVitais-temperaturaCorporal").mask("00.0");
	$("#sinaisVitais-glicemiaCapilar").mask("000");
	$("#sinaisVitais-saturacaoOxigenio").mask("000");
	//Fim da função

	//Função que inicia o TinyMCE
	tinymce.init({
		selector: '#motivo',
		language: 'pt_BR',
		height: 150,
		menubar: false,
		plugins: [
			'advlist autolink lists link image charmap print preview anchor',
			'searchreplace visualblocks code fullscreen',
			'insertdatetime media table paste code help wordcount'
		],
		toolbar: 'undo redo | formatselect | ' +
			'bold italic backcolor | alignleft aligncenter ' +
			'alignright alignjustify | bullist numlist outdent indent | ' +
			'removeformat | help',
		content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
	});

	//Fim da função

	//Chamada da função atualizar Alergias
	atualizaAlergia();

	//Chamada da função atualizar hábitos
	atualizarHabitos($("#check-habitos"));

	//Chamada da função atualizar comorbidades;
	atualizarComorbidades($("#check-comorbidades"));
});

$("#customRadio2").click(function() {
	$("#adicionar-a-lista").hide();
	$("#conduta-cidadao").show();
});

$("#customRadio1").click(function() {
	$("#conduta-cidadao").hide();
	$("#adicionar-a-lista").show();
})

//Submit do Formulário Triagem
$("#form-triagem").submit(function(evt) {
	//Bloqueia o comportamento padrão do submit
	evt.preventDefault();

	var triagem = {};

	triagem.motivo = tinymce.get("motivo").getContent();
	triagem.inicioTriagem = $("#inicioTriagem").val();
	triagem.classificacaoDeRisco = $("input[name='classificacaoDeRisco']:checked").val();

	triagem['sinaisVitais.pressaoArterial'] = $("#sinaisVitais-pressaoArterial").val();
	triagem['sinaisVitais.temperaturaCorporal'] = $("#sinaisVitais-temperaturaCorporal").val();
	triagem['sinaisVitais.frequenciaCardiaca'] = $("#sinaisVitais-frequenciaCardiaca").val();
	triagem['sinaisVitais.saturacao'] = $("#sinaisVitais-saturacaoOxigenio").val();
	triagem['sinaisVitais.frequenciaRespiratoria'] = $("#sinaisVitais-frequenciaRespiratoria").val();
	triagem['sinaisVitais.glicemiaCapilar'] = $("#sinaisVitais-glicemiaCapilar").val();
	triagem['sinaisVitais.momentoColeta'] = $("#sinaisVitais-momentoColeta option:selected").val();

	triagem['atendimento.id'] = $("#atendimento-id").val();
	triagem['atendimento.profissionalDestino'] = $("#atendimento-profissionalDestino option:selected").val();
	triagem['atendimento.cidadao'] = $("#cidadao-id").val();

	var condutaCidadao;
	var condutaTipoServico;

	if ($("#customRadio1").is(":checked")) {
		condutaTipoServico = $("input[name='atendimento.condutaTipoServico']:checked").val();
		condutaCidadao = null;
	}
	if ($("#customRadio2").is(":checked")) {
		condutaCidadao = $("input[name='atendimento.condutaCidadao']:checked").val();
		condutaTipoServico = "INATIVO";
	}

	triagem['atendimento.condutaTipoServico'] = condutaTipoServico;
	triagem['atendimento.condutaCidadao'] = condutaCidadao;

	console.log(triagem);

	$.ajax({
		url: "/triagem/salvar",
		method: "POST",
		data: triagem,
		beforeSend: function() {

		},

		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'A Triagem foi salva',
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
			422: function(xhr) {
				console.log('status error:', xhr.status);
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
		},

		complete: function() {
			window.location.replace("/triagem/listar");
		}
	})


});
//Fim do submit formulário triagem

//Submit da função cadastrar nova alergia
$("#form-alergia").submit(function(evt) {
	evt.preventDefault();

	var alergia = {};

	alergia.nome = $("#nome").val();
	alergia.descricao = $("#descricao").val();
	alergia.cid = $("#cid").val();
	alergia.idProntuario = $("#id-prontuario").val();

	console.log(alergia);

	$.ajax({
		url: '/alergia/salvar',
		method: 'post',
		data: alergia,
		beforeSend: function() {

		},

		success: function() {
			$("#exampleModalCenter .close").click();
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'A alergia foi salva',
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

			atualizaAlergia();


		},

		error: function() {
			$.notify({
				// options
				icon: 'flaticon-exclamation',
				title: 'ERRO',
				message: 'Houve um erro ao processar a solicitação',
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


});
//Fim da função cadastrar nova alergia

//Inicio da funcao atualizar alergia
function atualizaAlergia() {
	var prontuarioId = $("#id-prontuario").val();
	$("#card-list").empty();
	$.ajax({
		url: "/alergia/alergias/" + prontuarioId,
		method: "get",
		charset: "UTF-8",
		success: function(response) {
			$("#card-list").fadeIn(250, function() {
				$(this).append(response);
			});

		},
	})

};

function excluiAlergia(item) {
	var prontuarioId = $("#id-prontuario").val();
	var alergiaId = $(item).parent().attr("data-value");
	console.log(alergiaId);
	swal({
		title: 'Tem certeza que deseja remover a alergia do Prontuário do Cidadão?',
		icon: 'warning',
		buttons: {
			cancel: {
				visible: true,
				text: 'Não, cancelar!',
				className: 'btn btn-primary',
			},
			confirm: {
				text: 'Sim, remova!',
				className: 'btn btn-warning'
			}
		}
	}).then((willDelete) => {
		if (willDelete) {
			$.ajax({
				url: "/alergia/excluir/" + prontuarioId + "/" + alergiaId,
				method: "get",
				success: function() {
					atualizaAlergia();
					swal("Alergia removida com sucesso!", {
						icon: "success",
						buttons: {
							confirm: {
								className: 'btn btn-success'
							}
						}
					});
				},

				erro: function() {
					swal("Houve um erro ao processar sua solicitação!", {
						icon: "danger",
						buttons: {
							confirm: {
								className: 'btn btn-success'
							}
						}
					});
				}
			})
		}
	});


} 

	//Função para cadstrar um novo hábito
	//	$("#btn-cadastrar-habito").click(function(e) {
	//		e.preventDefault();
	//		var habito = {};
	//		swal({
	//			title: 'Cadastrar um novo hábito',
	//			content: {
	//				element: "input",
	//				attributes: {
	//					placeholder: "Digite o nome do novo hábito",
	//					type: "text",
	//					id: "input-field",
	//					className: "form-control"
	//				},
	//			},
	//			buttons: {
	//				confirm: {
	//					text: 'Salvar',
	//					className: 'btn btn-primary'
	//				},
	//				cancel: {
	//					text: 'Cancelar',
	//					visible: true,
	//					className: 'btn btn-warning',
	//					closeModal: true,
	//				}
	//			},
	//		}).then(function() {
	//			habito.nome = $("#input-field").val();
	//			$.ajax({
	//				method: "POST",
	//				url: "/habito/salvar",
	//				data: habito,
	//				success: function(data) {
	//					swal({
	//						title: "Sucesso!",
	//						text: "O novo hábito " + data.nome + " foi salvo!",
	//						icon: "success",
	//						buttons: {
	//							confirm: {
	//								text: "Ok",
	//								value: true,
	//								visible: true,
	//								className: "btn btn-success",
	//								closeModal: true
	//							}
	//						}
	//					});
	//					atualizarHabitos($("#check-habitos"));
	//				},
	//				statusCode: {
	//					422: function(data) {
	//						swal({
	//							title: "Erro!",
	//							text: data.responseText,
	//							icon: "error",
	//							buttons: {
	//								confirm: {
	//									text: "Ok",
	//									value: true,
	//									visible: true,
	//									className: "btn btn-danger",
	//									closeModal: true
	//								}
	//							}
	//						});
	//
	//					}
	//				}
	//			})
	//		});
	//	});
	//Fim da função

//Função para cadastrar uma nova comorbidade
$("#btn-cadastrar-comorbidade").click(function(e) {
	e.preventDefault();
	var habito = {};
	var comorbidade = {};
	swal({
		title: 'Cadastrar uma nova doença/comorbidade',
		content: {
			element: "input",
			attributes: {
				placeholder: "Digite o nome da doença/comorbidade",
				type: "text",
				id: "input-field",
				className: "form-control"
			},
		},
		buttons: {
			confirm: {
				text: 'Salvar',
				className: 'btn btn-primary'
			},
			cancel: {
				text: 'Cancelar',
				visible: true,
				className: 'btn btn-warning',
				closeModal: true,
			}
		},
	}).then(function() {
		comorbidade.nome = $("#input-field").val();
		$.ajax({
			method: "POST",
			url: "/comorbidade/salvar",
			data: comorbidade,
			success: function(data) {
				swal({
					title: "Sucesso!",
					text: data.nome + " foi salvo!",
					icon: "success",
					buttons: {
						confirm: {
							text: "Ok",
							value: true,
							visible: true,
							className: "btn btn-success",
							closeModal: true
						}
					}
				});
				atualizarHabitos($("#check-habitos"));
			},
			statusCode: {
				422: function(data) {
					swal({
						title: "Erro!",
						text: data.responseText,
						icon: "error",
						buttons: {
							confirm: {
								text: "Ok",
								value: true,
								visible: true,
								className: "btn btn-danger",
								closeModal: true
							}
						}
					});

				}
			}
		})
	});
});
//Fim da função

//Função pesquisa de Cids
$("#cid").autocomplete({
	source: "/cid/buscar",
})
//$("#cid").on("keydown", function(event) {
//	$(this).autocomplete("instance")._renderItem = function(select, item) {
//		return $("<option>").append("<div>" + item.codigo + " - " + item.nome + "</div>").appendTo(select);
//	};
//}).autocomplete({
//	source: "/cid/buscar",
//	focus: function(event, ui) {
//		$("#cid").val(ui.item.codigo);
//		return false;
//	},
//	select: function(event, ui) {
//
//	}
//})

//Fim da função pesquisa Cids

//Função que adiciona procedimentos
$("#procedimentos-triagem")
	.on("keydown", function(event) {
		$(this).autocomplete("instance")._renderItem = function(select, item) {
			return $("<option>").append("<div>"
				+ item.codigo
				+ " - "
				+ item.nome
				+ "</div>").appendTo(select);
		};
	})
	.autocomplete({
		source: "/procedimento/buscar",
		focus: function(event, ui) {
			$("#procedimentos-triagem").val(ui.item.nome);
			return false;
		},
		select: function(event, ui) {
			console.log("Id do Procedimento: " + ui.item.codigo);
			$("#procedimentos-triagem").val("");
			$("#table-procedimento-triagem").append('<tr id="'
				+ ui.item.codigo
				+ '"><td>'
				+ ui.item.codigo
				+ '</td>'
				+ '<td>'
				+ ui.item.nome
				+ '</td>'
				+ '<td>'
				+ '<button onclick="removeProcedimentoTriagem(this)" type="button" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button>'
				+ '</td>'
				+ '</tr>');
			$("#inputs-procedimentos").append('<input type="hidden" id="procedimento' + ui.item.codigo + '" name="procedimentos" value="' + ui.item.codigo + '">');
			return false;
		}
	});
//Fim da função


// Função para remover os Procedimentos da Tabela de Procedimentos
function removeProcedimentoTriagem(item) {
	swal({
		title: 'Tem certeza que deseja remover esse procedimento?',
		icon: 'warning',
		buttons: {
			cancel: {
				visible: true,
				text: 'Não, cancelar!',
				className: 'btn btn-primary',
			},
			confirm: {
				text: 'Sim, remova!',
				className: 'btn btn-warning'
			}
		}
	}).then((willDelete) => {
		if (willDelete) {
			var tr = $(item).closest('tr');
			var id = $(item).parent().parent().attr("id");

			tr.fadeOut(400, function() {
				tr.remove();
			});
			$("#procedimento" + id).remove();
			swal("Procedimento removido com sucesso!", {
				icon: "success",
				buttons: {
					confirm: {
						className: 'btn btn-success'
					}
				}
			});
		}
	});


	return false;
}
//Fim da função


//Função para atualizar checkbox Habitos
function atualizarHabitos(element) {
	element.empty();
	$.getJSON('/habito/listar', function(data) {
		$.each(data, function(key, item) {
			element.append("<div class='custom-control custom-checkbox'> <input type = 'checkbox' value ='" + item.id + "' name = '" + "habitos" + "' class= 'custom-control-input' id='habitos" + item.id + "'><label for='habitos" + item.id + "' class='custom-control-label'>" + item.nome + "</label></div>");
		});
	});
}
//Fim da função

//Função para atualizar checkbox comorbidades
function atualizarComorbidades(element) {
	$.getJSON('/comorbidade/listar', function(data) {
		$.each(data, function(key, item) {
			element.append("<div class='custom-control custom-checkbox'> <input type = 'checkbox' value ='" + item.id + "' name = '" + "comorbidades" + "' class= 'custom-control-input' id='comorbidades" + item.id + "'><label for='comorbidades" + item.id + "' class='custom-control-label' data-togle='tooltip' data-placement='top' title='" + item.descricao + "'>" + item.nome + "</label></div>");
		});
	});
}
//Fim da função

//Função que calcula o IMC
$("#altura").change(function() {
	var peso = $("#peso").val();
	var altura = $("#altura").val();
	var imc = (peso / (altura * altura)) * 10000;
	$("#imc").text(imc.toFixed(2));
	$("#valor-imc").val(imc.toFixed(2));
	if (imc < 17) {
		$("#imc-desc").text("Muito abaixo do peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 17 && imc < 18.49) {
		$("#imc-desc").text("Abaixo do Peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-warning");
	} else if (imc > 18.5 && imc < 24.99) {
		$("#imc-desc").text("Normal");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-success");
	} else if (imc > 25 && imc < 29.99) {
		$("#imc-desc").text("Acima do Peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-warning");
	} else if (imc > 30 && imc < 34.99) {
		$("#imc-desc").text("Obesidade I");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 35 && imc < 39.99) {
		$("#imc-desc").text("Obesidade II");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 40) {
		$("#imc-desc").text("Obesidade III");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	}
});
//Fim da função