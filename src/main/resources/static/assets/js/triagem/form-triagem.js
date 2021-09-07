//JS Form Triagem
$(document).ready(function() {

	$("#conduta-cidadao").hide();
	$("#form-status-alergia").hide();
	$("#form-status-alergia-edit").hide();
	$("#form-status-doenca").hide();
	$("#form-status-doenca-edit").hide();


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
		selector: '#motivo, #nota, #nota-dto',
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
	//Fim da função que inicia o TinyMCE

	//Chamada da função 
	atualizaAlergia();

	//Chamada da função 
	atualizaDoenca();

	//Chamada da função 
	atulizaMedicamentoUsoContinuo();

	//Chamada da Função
	atulizaMedicamentoEmUso();

	//Chamada da Função
	atualizaProcedimento();

});

//######### Funções do Formulário da Triagem ###########

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

			window.location.replace("/triagem/listar");
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

		}
	})


});
//Fim do submit formulário triagem


//######### Fim das Funções do Formulário da Triagem ###########

//######### Funções para Cadastro de Medicamentos ###########

//Funções atualizar medicamentos
function atulizaMedicamentoUsoContinuo() {
	var prontuarioId = $("#id-prontuario").val();
	$("#uso-continuo-lista").empty();
	$.ajax({
		url: '/medicamento-continuo/medicamentos/' + prontuarioId,
		method: 'GET',
		success: function(data) {
			if ($.isEmptyObject(data)) {
				$("#uso-continuo-lista").append("<li class='list-group-item'> Sem dados para serem exibidos </li>")
			}
			$.each(data, function(index, value) {
				$("#uso-continuo-lista").append("<li class='list-group-item'>" + value.medicamento.principioAtivo + " ; " + value.medicamento.concentracao + " ; " + value.medicamento.formaFarmaceutica.nome + " | " + value.nota + "</li>");
			})
		},

		error: function() {
			$("#uso-lista").append("<li class='list-group-item'> Sem dados para serem exibidos - Houve um erro ao recuperar as informações </li>")
		}
	})
}

function atulizaMedicamentoEmUso() {
	var atendimentoId = $("#id-atendimento").val();
	$("#uso-lista").empty();
	$.ajax({
		url: '/uso-medicamento/medicamentos/' + atendimentoId,
		method: 'GET',
		success: function(data) {
			if ($.isEmptyObject(data)) {
				$("#uso-lista").append("<li class='list-group-item'> Sem dados para serem exibidos </li>")
			}
			$.each(data, function(index, value) {
				$("#uso-lista").append("<li class='list-group-item'>" + value.medicamento.principioAtivo + " ; " + value.medicamento.concentracao + " ; " + value.medicamento.formaFarmaceutica.nome + " | " + value.nota + "</li>");
			})
		},

		error: function() {
			$("#uso-lista").append("<li class='list-group-item'> Sem dados para serem exibidos - Houve um erro ao recuperar as informações </li>")
		}
	})
}

//Função Habilita pesquisa de Medicamentos
$("#button-medicamento").click(function() {
	$("#i-medicamento").removeClass().addClass("fa fa-search");
	$("#medicamento").val("").attr("disabled", false);
})
//Fim da função habilita pesquisa de Medicamentos




//Função autocomplete Medicamentos
$("#medicamento").autocomplete({
	source: "/medicamento/buscar",
	focus: function(event, ui) {
		$("#medicamento").val(ui.item.principioAtivo + " ; " + ui.item.concentracao + " ; " + ui.item.formaFarmaceutica.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-medicamento").removeClass("fa fa-search").addClass("fa fa-times");
		$("#medicamento").val(ui.item.principioAtivo + " ; " + ui.item.concentracao + " ; " + ui.item.formaFarmaceutica.nome).attr("disabled", true);
		$("#id-medicamento").val(ui.item.id);
		return false;

	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.principioAtivo + " ; " + "<b>" + item.concentracao + "</b>" + "<br>" + item.formaFarmaceutica.nome + "</div>")
		.appendTo(ul);
};

//Fim da função autocomplete Medicamentos

//Submit dos medicamentos
$("#submit-uso-medicamento").click(function(evt) {
	evt.preventDefault();
	var usoMedicamento = {};
	usoMedicamento['medicamento.id'] = $("#id-medicamento").val();
	usoMedicamento.nota = $("#nota-uso-medicamento").val();
	usoMedicamento.usoContinuo = $("#usoContinuo").prop("checked");
	usoMedicamento.idProntuario = $("#id-prontuario").val();
	usoMedicamento.idAtendimento = $("#id-atendimento").val();

	$.ajax({
		url: "/uso-medicamento/salvar",
		method: "POST",
		data: usoMedicamento,

		success: function() {
			$("#id-medicamento").val("");
			$("#nota-uso-medicamento").val("");
			$("#usoContinuo").prop("checked", false);
			$("#medicamento").val("");
			$("#i-medicamento").removeClass().addClass("fa fa-search");
			$("#medicamento").val("").attr("disabled", false);
			atulizaMedicamentoEmUso();
			atulizaMedicamentoUsoContinuo();
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'Medicamento em uso salvo',
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
//Fim submit dos medicamentos
//######### Fim das Funções para Cadastro de Medicamentos ###########

//######### Funções dos Procedimentos ###########

//Funçao que adiciona Procedimentos automaticamente
$("#sinaisVitais-pressaoArterial").change(function() {
	var idAtendimento = $("#id-atendimento").val();
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimentoAutomatico(idAtendimento, 301100039, tipoServico, quantidade);
})

$("#sinaisVitais-glicemiaCapilar").change(function() {
	var idAtendimento = $("#id-atendimento").val();
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimentoAutomatico(idAtendimento, 214010015, tipoServico, quantidade);
})

//Função que adiciona procedimentos
//Autocomplete Procedimentos

$("#button-procedimento").click(function() {
	$("#i-procedimento").removeClass().addClass("fa fa-search");
	limpaInputsProcedimento();
})

$("#submit-procedimento").click(function() {
	var idAtendimento = $("#id-atendimento").val();
	var codigoProcedimento = $("#id-procedimento").val();
	var tipoServico = "TRIAGEM";
	var quantidade = $("#qtd-procedimento").val();
	submitProcedimentoAutomatico(idAtendimento, codigoProcedimento, tipoServico, quantidade);
})

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

			$("#i-procedimento").removeClass().addClass("fa fa-search");
			limpaInputsProcedimento();
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
			$("#table-procedimentos").DataTable().ajax.reload();
		}
	})
}

$("#procedimentos-triagem").autocomplete({
	source: "/procedimento/buscar",
	focus: function(event, ui) {
		$("#procedimentos-triagem").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-procedimento").removeClass().addClass("fa fa-times");
		$("#qtd-procedimento").val(1);
		$("#procedimentos-triagem").val(ui.item.codigo + " - " + ui.item.nome).attr("disabled", true);
		$("#id-procedimento").val(ui.item.codigo);
		$("#nome-procedimento").val(ui.item.nome);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li></li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>")
		.appendTo(ul);
};

function limpaInputsProcedimento() {
	$("#procedimentos-triagem").val("").attr("disabled", false);
	$("#qtd-procedimento").val("");
	$("#id-procedimento").val("");
	$("#nome-procedimento").val("");
}

function atualizaProcedimento() {
	var atendimentoId = $("#id-atendimento").val();
	$("#table-procedimentos").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/atendimento/procedimentos-triagem/' + atendimentoId,
			dataSrc: ''
		},
		columns: [
			{
				title: 'CÓDIGO',
				data: 'procedimento.codigo',
			},
			{
				title: 'NOME',
				data: 'procedimento.nome',
			},
			{
				title: 'QUANTIDADE',
				data: 'quantidade',
				mRender: function(data) {
					return "<span class='badge badge-info'>" + data + "</span>"
				}
			},
			{
				title: 'AÇÕES',
				data: 'id',
				mRender: function(data) {
					return "<button type='button' class='btn btn-warning btn-sm' data-value='" + data + "' onclick='removeProcedimento(this)'><i class='fa fa-trash'></i> Excluir </button>"
				}
			}
		]
	})
}

//$("#procedimentos-triagem")
//	.on("keydown", function(event) {
//		$(this).autocomplete("instance")._renderItem = function(select, item) {
//			return $("<option>").append("<div>"
//				+ item.codigo
//				+ " - "
//				+ item.nome
//				+ "</div>").appendTo(select);
//		};
//	})
//	.autocomplete({
//		source: "/procedimento/buscar",
//		focus: function(event, ui) {
//			$("#procedimentos-triagem").val(ui.item.nome);
//			return false;
//		},
//		select: function(event, ui) {
//			$("#procedimentos-triagem").val("");
//			$("#table-procedimento-triagem").append('<tr id="'
//				+ ui.item.codigo
//				+ '"><td>'
//				+ ui.item.codigo
//				+ '</td>'
//				+ '<td>'
//				+ ui.item.nome
//				+ '</td>'
//				+ '<td>'
//				+ '<button onclick="removeProcedimentoTriagem(this)" type="button" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button>'
//				+ '</td>'
//				+ '</tr>');
//			$("#inputs-procedimentos").append('<input type="hidden" id="procedimento' + ui.item.codigo + '" name="procedimentos" value="' + ui.item.codigo + '">');
//			return false;
//		}
//	});
//Fim da função


// Função para remover os Procedimentos da Tabela de Procedimentos
function removeProcedimento(item) {
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
		var idAtendimento = $("#id-atendimento").val();
		var idAtendimentoProcedimento = $(item).attr("data-value");

		$.ajax({
			url: '/atendimento-procedimento/excluir/' + idAtendimento + "/" + idAtendimentoProcedimento,
			method: 'get',
			success: function() {
				swal("Procedimento removido com sucesso!", {
					icon: "success",
					buttons: {
						confirm: {
							className: 'btn btn-success'
						}
					}
				});
				$("#table-procedimentos").DataTable().ajax.reload();

			}
		})
	});


	return false;
}
//Fim da função
//######### Fim das Funções dos Procedimentos ###########


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