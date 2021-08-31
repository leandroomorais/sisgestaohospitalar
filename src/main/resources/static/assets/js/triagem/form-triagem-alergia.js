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
			$("#table-alergias").DataTable().ajax.reload();
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

//Submit da função editar alergia
$("#editar-nome-alergia").click(function(){
	$("#alergia-nome-dto").prop("disabled",false);
});

$("#editar-cid-alergia").click(function(){
	$("#alergia-cid-dto").prop("disabled",false);
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
			fechaFormularioAlergia();
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
			$("#table-alergias").DataTable().ajax.reload();
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
//Fim da função editar alergia

//Função exibe formulário Alergia
function exibeFormularioAlergia() {
	$("#form-status-alergia input[type='text']").val("");
	$("#form-status-alergia input[type='hidden']").val("");
	$("#form-status-alergia input[type='date']").val("");
	$("#div-table-alergias").hide();
	$("#form-status-alergia").fadeIn(200);
}
//Fim Função exibe formulário Alergia

//Função fecha formulário de alergia
function fechaFormularioAlergia() {
	$("#form-status-alergia input[type='text']").val("");
	$("#form-status-alergia input[type='hidden']").val("");
	$("#form-status-alergia input[type='date']").val("");
	$("#form-status-alergia-edit input[type='text']").val("");
	$("#form-status-alergia-edit input[type='hidden']").val("");
	$("#form-status-alergia-edit input[type='date']").val("");
	$("#form-status-alergia").hide();
	$("#form-status-alergia-edit").hide();
	$("#div-table-alergias").fadeIn(200);
}
//Fim Função fecha formulário de alergia

//Inicio da funcao atualizar alergia
function atualizaAlergia() {
	var prontuarioId = $("#id-prontuario").val();
	$("#table-alergias").DataTable({
		responsive: true,
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
				title: 'CID',
				data: 'alergia.cid.codigo',
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
	$("#form-status-alergia-edit input[type='text']").val("");
	$("#form-status-alergia-edit input[type='hidden']").val("");
	$("#form-status-alergia-edit input[type='date']").val("");
	var statusAlergiaId = $(item).attr("data-value");
	var prontuarioId = $("#id-prontuario").val();

	$.ajax({
		method: "GET",
		url: "/status-alergia/editar/" + statusAlergiaId,
		success: function(data) {
			$("#id-status-alergia-dto").val(statusAlergiaId);
			$("#div-table-alergias").fadeOut(200);
			$("#form-status-alergia-edit").fadeIn(200);
			$("#alergia-nome-dto").val(data.alergia.nome);
			$("#id-alergia-dto").val(data.alergia.id);
			$("#alergia-cid-dto").val(data.alergia.cid.codigo + " - " + data.alergia.cid.nome);
			$("#id-cid-dto").val(data.alergia.cid.codigo);
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
$("#alergia-nome").on("keydown", function(event) {
	$(this).autocomplete("instance")._renderItem = function(select, item) {
		return $("<option>").append("<div>" + item.nome + "</div>").appendTo(select);
	};
}).autocomplete({
	source: "/alergia/buscar",
	focus: function(event, ui) {
		$("#alergia-nome").val(ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#alegia-nome").val(ui.item.nome);
		$("#id-alergia").val(ui.item.id);
		$("#alergia-cid").val(ui.item.cid.codigo + " - " + ui.item.cid.nome);
		$("#id-cid").val(ui.item.cid.codigo);
		return false;
	}
})

//Fim da Função pesquisa de Alergias
//***************Fim Alergias */