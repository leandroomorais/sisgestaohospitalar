function ocultarDoenca() {
	$("#card-nova-doenca").hide();
	$("#card-edit-doenca").hide();
}

//***************Inicio Doenças */

//Submit da função cadastrar nova Doença
$("#form-status-doenca").submit(function(evt) {
	evt.preventDefault();

	var statusDoenca = {};

	statusDoenca.id = $("#id-status-doenca").val();
	statusDoenca['doenca.nome'] = $("#doenca-nome").val();
	statusDoenca['doenca.cid'] = $("#id-cid-doenca").val();
	statusDoenca.nota = tinyMCE.get('nota').getContent();
	statusDoenca.dataInicio = $("#dataInicio-doenca").val();
	statusDoenca.dataFim = $("#dataFim-doenca").val();
	statusDoenca.situacaoCondicao = $("input[name = 'situacaoCondicao']:checked").val();
	statusDoenca.idProntuario = $("#id-prontuario").val();

	$.ajax({
		url: '/status-doenca/salvar',
		method: 'post',
		data: statusDoenca,
		beforeSend: function() {
		},
		success: function() {
			fechaFormularioDoenca();
			notificacao('Sucesso!', 'A doença/comorbidade foi salva', 'top', 'right', 'success', 'withicon', '#', '');
			$("#table-doencas").DataTable().ajax.reload();
		},

		statusCode: {
			422: function(xhr) {
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val) {
					notificacao('Atenção!', val, 'top', 'right', 'danger', 'withicon', '#', '');
					$("input[name='" + key + "']").parent().parent().parent().addClass("has-error has-feedback");
				})
			},
		}

	})


});
//Fim da função cadastrar nova Doenca

$("#nova-doenca-voltar").click(function() {
	fechaFormularioDoenca();
})

$("#edit-doenca-voltar").click(function() {
	fechaFormularioEditDoenca();
})

//Submit da função editar doença
//Inicio da Função Editar Doenca
$("#editar-nome-doenca").click(function() {
	$("#doenca-nome-dto").prop("disabled", false);

});

$("#editar-cid-doenca").click(function() {
	$("#doenca-cid-dto").prop("disabled", false);
});

$("#form-status-doenca-edit").submit(function(evt) {
	evt.preventDefault();

	var statusDoencaDTO = {};

	statusDoencaDTO.id = $("#id-status-doenca-dto").val();
	statusDoencaDTO['doenca.id'] = $("#id-doenca-dto").val();
	statusDoencaDTO['doenca.nome'] = $("#doenca-nome-dto").val();
	statusDoencaDTO['doenca.cid'] = $("#id-cid-doenca-dto").val();
	statusDoencaDTO.nota = tinymce.get("nota-dto").getContent();
	statusDoencaDTO.dataInicio = $("#dataInicio-doenca-dto").val();
	statusDoencaDTO.dataFim = $("#dataFim-doenca-dto").val();
	statusDoencaDTO.situacaoCondicao = $("#situacao-doenca-div").children().children("input[name = 'situacaoCondicao']:checked").val();
	statusDoencaDTO.idProntuario = $("#id-prontuario").val();

	$.ajax({
		url: '/status-doenca/editar',
		method: 'post',
		data: statusDoencaDTO,
		beforeSend: function() {
		},
		success: function() {
			fechaFormularioEditDoenca();
			notificacao('Sucesso!', 'A doença/comorbidade foi salva', 'top', 'right', 'success', 'withicon', '#', '');
			$("#table-doencas").DataTable().ajax.reload();
		},

		statusCode: {
			422: function(xhr) {
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val) {
					notificacao('Atenção!', val, 'top', 'right', 'danger', 'withicon', '#', '');
					$("input[name='" + key + "']").parent().parent().parent().addClass("has-error has-feedback");
				})
			},
		}

	})


});
//Fim do submit editar doença

$("#button-nome-doenca").click(function() {
	$("#doenca-nome").val("");
	$("#id-doenca").val("");
});

$("#button-cid-doenca").click(function() {
	$("#doenca-cid").val("");
	$("#id-cid-doenca").val("");
})

//Função exibe formulário Doença
function exibeFormularioDoenca() {
	limpaFormDoenca();
	$("#card-list-doencas").fadeOut(100);
	$("#card-nova-doenca").fadeIn(200);
}
//Fim Função exibe formulário Doença

//Função exibe formulário Editar Doença
function exibeFormularioEditDoenca() {
	limpaFormEditDoenca();
	$("#card-list-doencas").fadeOut(100);
	$("#card-edit-doenca").fadeIn(200);
}
//Fim Função exibe formulário Editar Doença

//Função fecha formulário de Doença
function fechaFormularioDoenca() {
	limpaFormDoenca();
	$("#card-nova-doenca").fadeOut(100);
	$("#card-list-doencas").fadeIn(100);
}
//Fim Função fecha formulário de Doença

//Função fecha formulário edit Doença
function fechaFormularioEditDoenca() {
	limpaFormEditDoenca();
	$("#card-edit-doenca").fadeOut(100);
	$("#card-list-doencas").fadeIn(100);
}
//Fim Função fecha formulário edit Doença

function limpaFormDoenca() {
	$("input").parent().parent().parent().removeClass("has-error has-feedback");
	$("#form-status-doenca input[type='text']").val("");
	$("#form-status-doenca input[type='hidden']").val("");
	$("#form-status-doenca input[type='date']").val("");
	$("#form-status-doenca input[type='radio']").prop("checked", false);
}

function limpaFormEditDoenca() {
	$("input").parent().parent().parent().removeClass("has-error has-feedback");
	$("#form-status-doenca-edit input[type='text']").val("");
	$("#form-status-doenca-edit input[type='hidden']").val("");
	$("#form-status-doenca-edit input[type='date']").val("");
}

//Inicio da funcao atualizar Doenca
function atualizaDoenca() {
	var prontuarioId = $("#id-prontuario").val();
	$("#table-doencas").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/status-doenca/doencas/' + prontuarioId,
			dataSrc: ''
		},
		columns: [
			{
				title: 'NOME',
				data: 'doenca.nome',
			},
			{
				title: 'CID',
				data: 'doenca.cid',
				mRender: function(data) {
					if (data != null) {
						return "<small>" + data.codigo + " - " + data.nome + "</small>";
					} else {
						return "<small> CID não cadastrado </small>";
					}

				}
			},
			{
				title: 'SITUAÇÃO',
				data: 'situacaoCondicao',
				mRender: function(data) {
					if (data != null) {
						if (data == 'ATIVA') {
							return "<span class='badge badge-danger'>Ativa</span>"
						}
						if (data == 'LATENTE') {
							return "<span class='badge badge-warning'>Latente</span>"
						}
						if (data == 'CURADA') {
							return "<span class='badge badge-success'>Curada</span>"
						}
						if (data == 'EMTRATAMENTO') {
							return "<span class='badge badge-primary'>Em tratamento</span>"
						}
					}

					return "<span class='badge badge-info'>Não informada</span>"

				}
			},
			{
				title: 'DATA REGISTRO',
				data: 'dataRegistro',
				mRender: function(data) {
					return moment(data).format("DD/MM/YYYY");
				}
			},
			{
				title: 'AÇÕES',
				data: 'id',
				mRender: function(data) {
					var retorno =
						" <button class='btn btn-primary btn-sm' data-value='" + data + "' onclick='editarDoenca(this)'><i class='fa fa-edit'></i> Editar </button>"
					return retorno;
				}
			}
		]
	})
};
//Fim da função atualizar doencas


function editarDoenca(item) {
	var statusDoencaId = $(item).attr("data-value");
	$.ajax({
		method: "GET",
		url: "/status-doenca/editar/" + statusDoencaId,
		success: function(data) {
			exibeFormularioEditDoenca();
			$("#id-status-doenca-dto").val(statusDoencaId);
			$("#doenca-nome-dto").val(data.doenca.nome);
			tinymce.get("nota-dto").setContent(data.nota);
			$("#id-doenca-dto").val(data.doenca.id);
			if (data.doenca.cid != null) {
				$("#doenca-cid-dto").val(data.doenca.cid.codigo + " - " + data.doenca.cid.nome).attr("disabled", true);
				$("#id-cid-doenca-dto").val(data.doenca.cid.id);
			} else {
				$("#doenca-cid-dto").val("").attr("disabled", true);
				$("#id-cid-doenca-dto").val("");
			}
			$("#dataInicio-doenca-dto").val(data.dataInicio);
			$("#dataFim-doenca-dto").val(data.dataFim);
			$("input[value = '" + data.situacaoCondicao + "']").prop("checked", true);
		}
	})
}
//Fim da Função Editar Doenca


//Função pesquisa de Doencas
$("#doenca-nome").autocomplete({
	maxShowItems: 5,
	source: "/doenca/buscar",
	select: function(event, ui) {
		$("#doenca-nome").val(ui.item.nome);
		$("#id-doenca").val(ui.item.id);
		$("#doenca-cid").val(ui.item.cid.codigo + " - " + ui.item.cid.nome);
		$("#id-cid-doenca").val(ui.item.cid.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li></li>")
		.append("<div class='h6'>" + item.nome + "</div>")
		.appendTo(ul);
}

//Função pesquisa de Cids
$("#doenca-cid").autocomplete({
	maxShowItems: 5,
	source: "/cid/buscar",
	select: function(event, ui) {
		$("#doenca-cid").val(ui.item.codigo + " - " + ui.item.nome);
		$("#id-cid-doenca").val(ui.item.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li></li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>")
		.appendTo(ul);
}


//Função pesquisa de Cids Edit
$("#doenca-cid-dto").autocomplete({
	maxShowItems: 5,
	source: "/cid/buscar",
	select: function(event, ui) {
		$("#doenca-cid-dto").val(ui.item.codigo + " - " + ui.item.nome);
		$("#id-cid-doenca-dto").val(ui.item.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li></li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>")
		.appendTo(ul);
}

//Fim da função pesquisa Cids Edit


//***************Fim Doenças */