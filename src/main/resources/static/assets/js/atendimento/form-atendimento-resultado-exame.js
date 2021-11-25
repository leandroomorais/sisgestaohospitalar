
// variaveis globais
var exame;
var procedimento;
var idResultadoExameEdicao;
var dataSolicitacaoExame;

function resgistroResultadoExame(idExame, codigoProcedimento) {
	$("#card-list-exames").fadeOut(100);
	$("#card-list-todos-exames").fadeOut(100);
	$("#card-novo-resultado-exame").fadeIn(100);

	$.ajax({
		url: '/exame/id/' + idExame,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-resultado-exame-solicitado").empty().append("<h5 class='card-title text-center'>O Exame solicitado não foi encontrado</h5>");
			} else {
				exame = data;
				dataSolicitacaoExame = dataFormatadaJSComTraco(data.dataSolicitacao);
				$("#div-resultado-exame-solicitado").empty().append(createCardResultadoExame(data));
			}

		},
	})

	$.ajax({
		url: '/procedimento/codigo/' + codigoProcedimento,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-procedimento-exame-solicitado").empty().append("<h5 class='card-title text-center'>O Procedimento solicitado não foi encontrado</h5>");
			} else {
				procedimento = data;
				$("#div-procedimento-exame-solicitado").empty().append(createCardProcedimentoResultadoExame(data));
			}

		},
	})
}

function detalheResultadoExame(resultadoId) {
	$.ajax({
		url: '/resultadoexame/id/' + resultadoId,
		method: 'get',
		success: function(data) {
			$("#divDecricaoResultadoExame").empty().append(createCardModalResultadoExame(data));
			$("#modalResultadoExame").modal("show");
		}
	})

}

function createCardModalResultadoExame(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-12'>" +
		"<strong>Descrição: </strong>" +
		"<br>" + descricaoResultadoExame(data) +
		"</div><div class='col-md-12 row'><div class='col-md-6'><strong>Realizado: </strong>" +
		dataRealizacaoResultadoExame(data)
		+ "</div><div class='col-md-6'> <strong>Resultado: </strong>" +
		dataResultadoResultadoExame(data)
		+ "</div></div></div></div></div>";
}

function descricaoResultadoExame(data) {
	return "<span>" + data.descricao + "</span><br><br>";
}

function dataRealizacaoResultadoExame(data) {
	if (data.dataRealizacao == null) {
		return "<span> " + " " + " </span>";
	}

	return "<span> " + dataFormatadaJS(data.dataRealizacao) + " </span>";
}

function dataResultadoResultadoExame(data) {
	if (data.dataResultado == null) {
		return "<span> " + " " + " </span>";
	}

	return "<span> " + dataFormatadaJS(data.dataResultado) + "</span>";
}

function editarResultadoExame(idExame, codigoProcedimento, resultadoId) {

	$("#card-list-exames").fadeOut(100);
	$("#card-list-todos-exames").fadeOut(100);
	$("#card-novo-resultado-exame").fadeIn(100);

	$.ajax({
		url: '/exame/id/' + idExame,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-resultado-exame-solicitado").empty().append("<h5 class='card-title text-center'>O Exame solicitado não foi encontrado</h5>");
			} else {
				exame = data;
				dataSolicitacaoExame = dataFormatadaJSComTraco(data.dataSolicitacao);
				$("#div-resultado-exame-solicitado").empty().append(createCardResultadoExame(data));
			}

		},
	})

	$.ajax({
		url: '/procedimento/codigo/' + codigoProcedimento,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-procedimento-exame-solicitado").empty().append("<h5 class='card-title text-center'>O Procedimento solicitado não foi encontrado</h5>");
			} else {
				procedimento = data;
				$("#div-procedimento-exame-solicitado").empty().append(createCardProcedimentoResultadoExame(data));
			}
		},
	})
	$.ajax({
		url: '/resultadoexame/id/' + resultadoId,
		method: 'get',
		success: function(data) {
			idResultadoExameEdicao = data.id;
			//tinymce.get("descricao").setMode('design');
			tinymce.get("descricao").setContent(data.descricao);
			//$("#descricao").html(data.descricao);
			$("#dataRealizacao").val(data.dataRealizacao);
			$("#dataResultado").val(data.dataResultado);
		}
	})

}

function createCardResultadoExame(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-9'>" +
		"<strong>Procedimentos da Solicitação: </strong>" +
		"<br>" + infoProcedimentosResultadoExame(data.procedimentos) +
		infoCardCidResultadoExame(data.cid) +
		infoCardJustificativaResultadoExame(data.justificativa) +
		infoCardObservacoesResultadoExame(data.observacoes) +
		"</div><div class='col-md-3 text-right'>" +
		infoCardDataProfissionalResultadoExame(data.dataSolicitacao, data.profissional.nome, data.profissional.numeroRegistro + " / " + data.profissional.siglaUfEmissao) +
		"</div></div></div></div>";
}

function infoProcedimentosResultadoExame(procedimentos) {
	var retorno;
	var retornoConcat = "";
	$.each(procedimentos, function(key, item) {
		retorno = "<div>" + item.codigo + " - " + "<b>" + item.nome + "</b>" + "</div>";
		retornoConcat += retorno;
	})
	return retornoConcat;
}

function infoCardCidResultadoExame(cid) {
	if (cid == null) {
		return "<strong>CID Relacionado: </strong><br><span><i> Nenhum CID Informado! </i></span><br>"
	} else {
		return "<strong>CID Relacionado: </strong><br><span> " + cid.nome + " </span><br>"
	}
}

function infoCardJustificativaResultadoExame(justificativa) {
	return "<strong>Justificativa: </strong><br><span> " + justificativa + " </span><br>"
}

function infoCardObservacoesResultadoExame(observacoes) {
	if (observacoes == "") {
		return "<strong>Observações: </strong><br><span><i> Nenhuma Observação Registrada! </i></span><br>"
	} else {
		return "<strong>Observações: </strong><br><span> " + observacoes + " </span><br>"
	}
}

function infoCardDataProfissionalResultadoExame(date, nomeProfissional, crm) {
	let dataExame = dataFormatadaJava(date);
	return "<span class='text-warning'> " + dataExame + " </span><br><span class='text-muted'> " + nomeProfissional + " </span><br><strong>CRM: </strong><span class='text-muted'> " + crm + " </span><br>"
}

function createCardProcedimentoResultadoExame(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'>" +
		"<div>" + data.codigo + " - " + "<b>" + data.nome + "</b>" + "</div>" +
		"</div></div></div>";
}

function limpaResultadoExame() {
	tinymce.get("descricao").setContent("");
	$("#dataRealizacao").val("");
	$("#dataResultado").val("");
	
	exame = null;
	procedimento = null;
	idResultadoExameEdicao = null;
	dataSolicitacaoExame = null;
}

function fechaFormularioResultadoExame() {
	limpaResultadoExame();
	$("#card-novo-resultado-exame").fadeOut(100);
	$("#card-list-exames").fadeIn(100);
	$("#card-list-todos-exames").fadeIn(100);
}

$("#novo-resultado-exame-voltar").click(function() {
	fechaFormularioResultadoExame();
})

function removeInvalidFedbackResultadoExame() {
	$("#form-resultado-exame input, #form-resultado-exame textarea").each(
		function(index) {
			var str = $(this).parent().parent().attr("class");
			if (str.match(/has-error/)) {
				$(this).parent().parent().removeClass("has-error has-feedback");
			}

		}
	);
}

$("#form-resultado-exame").submit(function(evt) {
	evt.preventDefault();
	var resultadoexame = {};

	if (idResultadoExameEdicao != null) {
		resultadoexame.id = idResultadoExameEdicao;
	}

	resultadoexame.descricao = tinymce.get("descricao").getContent();
	resultadoexame.dataResultado = $("#dataResultado").val();
	resultadoexame.dataRealizacao = $("#dataRealizacao").val();
	resultadoexame['procedimento'] = procedimento.codigo;
	resultadoexame['exame'] = exame.id;

	$.ajax({
		url: '/resultadoexame/',
		method: 'post',
		data: resultadoexame,
		beforeSend: function() {
			removeInvalidFedbackResultadoExame();
		},
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O Resultado do Exame foi salvo',
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
			atualizaTodosExames();
			atualizaExames();
			fechaFormularioResultadoExame();
//			exame = null;
//			procedimento = null;
//			idResultadoExameEdicao = null;
//			dataSolicitacaoExame = null;
		},

		statusCode: {
			400: function() {
				$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: 'Não foi possível processar sua solicitação, verifique se todos campos obrigatórios estão preenchidos',
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
			},
			500: function(xhr) {
				var resposta = $.parseJSON(xhr.responseText);
				console.log(resposta)
				$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: resposta.message,
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
		},

	})
})


// verifica se a data da realizaçao e do resultado são menores que a data solicitada  

$("#dataResultado").on("change", function() {
	var dataResultado = $(this).val();
	var dataRealizacao = $("#dataRealizacao").val();
	
	var dataAtual = dataFormatadaJSComTraco(new Date());
	
	if (moment(dataRealizacao).isAfter(dataResultado)) {

		$("#dataRealizacao").parent().parent().addClass("has-error");
		$("#dataRealizacao-small").removeClass().addClass("text-danger").text("A data de realização do exame não pode ser maior que a data do resultado");

		$("#dataResultado").parent().parent().addClass("has-error");
		$("#dataResultado-small").removeClass().addClass("text-danger").text("A data do resultado do exame não pode ser menor que a data de realização");
	} else {
		$("#dataRealizacao").parent().parent().removeClass("has-error");
		$("#dataRealizacao-small").removeClass().text("");

		$("#dataResultado").parent().parent().removeClass("has-error");
		$("#dataResultado-small").removeClass().text("");
	}
	
	if (moment(dataSolicitacaoExame).isAfter(dataResultado)) {

		$("#dataResultado").parent().parent().addClass("has-error");
		$("#dataResultado-small").removeClass().addClass("text-danger").text("A data do resultado do exame não pode ser menor que a data da solicitção do exame");
		$("#dataResultado").val("");	
	} else {

		$("#dataResultado").parent().parent().removeClass("has-error");
		$("#dataResultado-small").removeClass().text("");
	}
	
	if (moment(dataResultado).isAfter(dataAtual)) {

		$("#dataResultado").parent().parent().addClass("has-error");
		$("#dataResultado-small").removeClass().addClass("text-danger").text("A data do resultado do exame não pode ser maior que a data atual");
		$("#dataResultado").val("");	
	} else {

		$("#dataResultado").parent().parent().removeClass("has-error");
		$("#dataResultado-small").removeClass().text("");
	}
})


$("#dataRealizacao").on("change", function() {
	var dataRealizacao = $(this).val();
	var dataResultado = $("#dataResultado").val();
	
	var dataAtual = dataFormatadaJSComTraco(new Date());
	
	if (moment(dataRealizacao).isAfter(dataResultado)) {

		$("#dataRealizacao").parent().parent().addClass("has-error");
		$("#dataRealizacao-small").removeClass().addClass("text-danger").text("A data de realização do exame não pode ser maior que a data do resultado");

		$("#dataResultado").parent().parent().addClass("has-error");
		$("#dataResultado-small").removeClass().addClass("text-danger").text("A data do resultado do exame não pode ser menor que a data de realização");
	} else {
		$("#dataRealizacao").parent().parent().removeClass("has-error");
		$("#dataRealizacao-small").removeClass().text("");

		$("#dataResultado").parent().parent().removeClass("has-error");
		$("#dataResultado-small").removeClass().text("");
	}
	
	if (moment(dataSolicitacaoExame).isAfter(dataRealizacao)) {

		$("#dataRealizacao").parent().parent().addClass("has-error");
		$("#dataRealizacao-small").removeClass().addClass("text-danger").text("A data de realização do exame não pode ser menor que a data da solicitção do exame");
		$("#dataRealizacao").val("");
	} else {
		$("#dataRealizacao").parent().parent().removeClass("has-error");
		$("#dataRealizacao-small").removeClass().text("");

	}
	
	if (moment(dataRealizacao).isAfter(dataAtual)) {

		$("#dataRealizacao").parent().parent().addClass("has-error");
		$("#dataRealizacao-small").removeClass().addClass("text-danger").text("A data de realização do exame não pode ser maior que a data atual");
		$("#dataRealizacao").val("");
	} else {
		$("#dataRealizacao").parent().parent().removeClass("has-error");
		$("#dataRealizacao-small").removeClass().text("");

	}
})

