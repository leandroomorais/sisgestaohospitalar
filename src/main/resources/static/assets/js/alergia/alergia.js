function ocultarAlergia() {
	$("#card-nova-alergia").hide();
	$("#card-edit-alergia").hide();
}

//****** Alergias */
//Submit da função cadastrar nova alergia
$("#form-status-alergia").submit(function(evt) {
	evt.preventDefault();

	var statusAlergia = {};

	statusAlergia.id = $("#id-status-alergia").val();
	statusAlergia['alergia.id'] = $("#id-alergia").val();
	statusAlergia['alergia.nome'] = $("#alergia-nome").val();
	statusAlergia['alergia.cid'] = $("#id-cid").val();
	statusAlergia.dataInicio = $("#dataInicio").val();
	statusAlergia.dataFim = $("#dataFim").val();
	statusAlergia.situacaoCondicao = $("input[name = 'situacaoCondicao']:checked").val();
	statusAlergia.idProntuario = $("#id-prontuario").val();

	$.ajax({
		url: '/status-alergia/salvar',
		method: 'post',
		data: statusAlergia,
		beforeSend: function() {
		},
		success: function() {
			fechaFormularioAlergia();
			notificacao('Sucesso!', 'O registro de Alergia foi salvo', 'top', 'right', 'success', 'withicon', '#', '');
			$("#table-alergias").DataTable().ajax.reload();
			cardInfoCidadao(idAtendimento);
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
//Fim da função cadastrar nova alergia

$("#button-nome-alergia").click(function() {
	$("#alergia-nome").val("");
	$("#id-alergia").val("");
})

$("#button-cid-alergia").click(function() {
	$("#alergia-cid").val("");
	$("#id-cid").val("");
})

$("#nova-alergia-voltar").click(function() {
	fechaFormularioAlergia();
})

$("#edit-alergia-voltar").click(function() {
	fechaFormularioEditAlergia()
})

//Submit da função editar alergia
$("#editar-nome-alergia").click(function() {
	$("#alergia-nome-dto").prop("disabled", false);
});

$("#editar-cid-alergia").click(function() {
	$("#alergia-cid-dto").prop("disabled", false);
});

$("#form-status-alergia-edit").submit(function(evt) {
	evt.preventDefault();

	var statusAlergiaDTO = {};

	statusAlergiaDTO.id = $("#id-status-alergia-dto").val();
	statusAlergiaDTO['alergia.id'] = $("#id-alergia-dto").val();
	statusAlergiaDTO['alergia.nome'] = $("#alergia-nome-dto").val();
	statusAlergiaDTO['alergia.cid'] = $("#id-cid-dto").val();
	statusAlergiaDTO.dataInicio = $("#dataInicio-dto").val();
	statusAlergiaDTO.dataFim = $("#dataFim-dto").val();
	statusAlergiaDTO.situacaoCondicao = $("#div-situacao-edit").children().children("input[name = 'situacaoCondicao']:checked").val();
	statusAlergiaDTO.idProntuario = $("#id-prontuario").val();

	$.ajax({
		url: '/status-alergia/editar',
		method: 'post',
		data: statusAlergiaDTO,
		beforeSend: function() {
		},
		success: function() {
			fechaFormularioEditAlergia();
			notificacao('Sucesso!', 'O registro de Alergia foi salvo', 'top', 'right', 'success', 'withicon', '#', '');
			$("#table-alergias").DataTable().ajax.reload();
			cardInfoCidadao(idAtendimento);
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
//Fim da função editar alergia

//Função exibe formulário Alergia
function exibeFormularioAlergia() {
	$("input").parent().parent().parent().removeClass("has-error has-feedback");
	$("#form-status-alergia input[type='text']").val("");
	$("#form-status-alergia input[type='hidden']").val("");
	$("#form-status-alergia input[type='date']").val("");
	$("#card-list-alergias").hide();
	$("#card-nova-alergia").fadeIn(100);
}
//Fim Função exibe formulário Alergia

//Função exibe formulário Alergia
function exibeFormularioEditAlergia() {
	$("input").parent().parent().parent().removeClass("has-error has-feedback");
	$("#form-status-alergia-edit input[type='text']").val("");
	$("#form-status-alergia-edit input[type='hidden']").val("");
	$("#form-status-alergia-edit input[type='date']").val("");
	$("#card-list-alergias").fadeOut(100);
	$("#card-edit-alergia").fadeIn(100);
}
//Fim Função exibe formulário Alergia

//Função fecha formulário de alergia
function fechaFormularioAlergia() {
	$("#form-status-alergia input[type='text']").val("");
	$("#form-status-alergia input[type='hidden']").val("");
	$("#form-status-alergia input[type='date']").val("");
	$("#form-status-doenca input[type='radio']").prop("checked", false);
	$("#card-nova-alergia").fadeOut(100);
	$("#card-list-alergias").fadeIn(100);
}
//Fim Função fecha formulário de alergia

//Função fecha formulário de alergia
function fechaFormularioEditAlergia() {
	$("#form-status-alergia-edit input[type='text']").val("");
	$("#form-status-alergia-edit input[type='hidden']").val("");
	$("#form-status-alergia-edit input[type='date']").val("");
	$("#form-status-doenca input[type='radio']").prop("checked", false);
	$("#card-edit-alergia").fadeOut(100);
	$("#card-list-alergias").fadeIn(100);
}
//Fim Função fecha formulário de alergia

//Inicio da funcao atualizar alergia
function atualizaAlergia() {
	var prontuarioId = $("#id-prontuario").val();
	$("#table-alergias").DataTable({
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/status-alergia/alergias/' + prontuarioId,
			dataSrc: ''
		},
		columns: [
			{
				title: 'NOME',
				data: 'alergia.nome',
			},
			{
				title: 'CID',
				data: 'alergia.cid',
				mRender: function(data) {
					if (data != null) {
						return "<small>" + data.codigo + " - " + data.nome + "</small>";
					} else {
						return "<small> CID não cadastrado</small>";
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
						" <button class='btn btn-primary btn-sm' data-value='" + data + "' onclick='editarAlergia(this)'><i class='fa fa-edit'></i> Editar </button>"
					return retorno;
				}
			}
		]
	})
};

//Inicio da Função Editar Alergia
function editarAlergia(item) {

	var statusAlergiaId = $(item).attr("data-value");

	$.ajax({
		method: "GET",
		url: "/status-alergia/editar/" + statusAlergiaId,
		success: function(data) {
			exibeFormularioEditAlergia();
			$("#id-status-alergia-dto").val(statusAlergiaId);
			$("#div-table-alergias").fadeOut(200);
			$("#form-status-alergia-edit").fadeIn(200);
			$("#alergia-nome-dto").val(data.alergia.nome);
			$("#id-alergia-dto").val(data.alergia.id).attr("disabled", true);
			if (data.alergia.cid != null) {
				$("#alergia-cid-dto").val(data.alergia.cid.codigo + " - " + data.alergia.cid.nome).attr("disabled", true);
				$("#id-cid-dto").val(data.alergia.cid.id);
			} else {
				$("#alergia-cid-dto").val("");
				$("#id-cid-dto").val("");
			}
			if (data.dataInicio != null) {
				$("#dataInicio-dto").val(data.dataInicio);
			}
			if (data.dataFim != null) {
				$("#dataFim-dto").val(data.dataFim)
			}
			$("input[value = '" + data.situacaoCondicao + "']").prop("checked", true);
		}
	})
}
//Fim da Função Editar Alergia

//Inicio da função Excluir Alergia
function excluiAlergia(item) {
	var prontuarioId = $("#id-prontuario").val();
	var alergiaId = $(item).parent().attr("data-value");
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
//Fim da função excluir alergia

//Função pesquisa de Alergias
$("#alergia-nome").autocomplete({
	source: "/alergia/buscar",
	maxShowItems: 5,
	focus: function(event, ui) {
		$("#alergia-nome").val(ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#alegia-nome").val(ui.item.nome);
		$("#id-alergia").val(ui.item.id);
		$("#alergia-cid").val(ui.item.cid.codigo + " - " + ui.item.cid.nome);
		$("#id-cid").val(ui.item.cid.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.nome + "</div>")
		.appendTo(ul);
}

//Fim da Função pesquisa de Alergias

//Função pesquisa de Cids
$("#alergia-cid").autocomplete({
	source: "/cid/buscar",
	maxShowItems: 5,
	focus: function(event, ui) {
		$("#alergia-cid").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#alegia-cid").val(ui.item.codigo + " - " + ui.item.nome);
		$("#id-cid").val(ui.item.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>").appendTo(ul);
}

$("#alergia-cid-dto").autocomplete({
	source: "/cid/buscar",
	maxShowItems: 5,
	focus: function(event, ui) {
		$("#alergia-cid-dto").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#alegia-cid-dto").val(ui.item.codigo + " - " + ui.item.nome);
		$("#id-cid-dto").val(ui.item.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>").appendTo(ul);
}
//Fim da função pesquisa Cids
//***************Fim Alergias */