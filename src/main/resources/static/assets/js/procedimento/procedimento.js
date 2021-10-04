
function submitProcedimento(idAtendimento, codigoProcedimento, tipoServico, quantidade) {
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
			url: '/triagem/procedimentos/' + atendimentoId,
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

			},

			statusCode: {
				403: function(xhr) {
					$.notify({
						// options
						icon: 'flaticon-exclamation',
						title: 'ERRO',
						message: xhr.responseText,
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
			}
		})
	});


	return false;
}