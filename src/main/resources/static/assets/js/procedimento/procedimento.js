//Função que adiciona procedimentos
//Autocomplete Procedimentos

$("#procedimentos-geral").autocomplete({
	source: "/procedimento/buscar",
	focus: function(event, ui) {
		$("#procedimentos-geral").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-procedimento-geral").removeClass().addClass("fa fa-times");
		$("#qtd-procedimento-geral").val(1);
		$("#procedimentos-geral").val(ui.item.codigo + " - " + ui.item.nome).attr("disabled", true);
		$("#id-procedimento-geral").val(ui.item.codigo);
		$("#nome-procedimento").val(ui.item.nome);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li></li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>")
		.appendTo(ul);
};

$("#button-procedimento").click(function() {
	$("#i-procedimento-geral").removeClass().addClass("fa fa-search");
	limpaInputsProcedimento();
})

$("#submit-procedimento").click(function() {
	var codigoProcedimento = $("#id-procedimento-geral").val();
	//var tipoServico = "TRIAGEM";
	var quantidade = $("#qtd-procedimento-geral").val();
	submitProcedimento(idAtendimento, codigoProcedimento, null, quantidade);
})

//Fim da função
//######### Fim das Funções dos Procedimentos ###########

function submitProcedimento(idAtendimento, codigoProcedimento, tipoServico, quantidade) {
	var relAtendimentoProcedimento = {};
	relAtendimentoProcedimento.idAtendimento = idAtendimento;
	relAtendimentoProcedimento['procedimento.codigo'] = codigoProcedimento;
	relAtendimentoProcedimento.quantidade = quantidade;
	relAtendimentoProcedimento.tipoServico = tipoServico;

	//REFAZER ESSA FUNÇÃO
	//função que verifica se procedimento tem CID obrigatório
	//verificaProcedimentoCid(codigoProcedimento);

	$.ajax({
		url: '/atendimento-procedimento/adicionar',
		method: 'POST',
		data: relAtendimentoProcedimento,
		success: function() {
			$("#i-procedimento-geral").removeClass().addClass("fa fa-search");
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
				icon: "success",
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
}

function limpaInputsProcedimento() {
	$("#procedimentos-geral").val("").attr("disabled", false);
	$("#qtd-procedimento-geral").val("");
	$("#id-procedimento-geral").val("");
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
				title: 'QTD',
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
			},
			{
				title: 'CID',
				data: 'procedimento.codigo',
				mRender: function(data) {
					return "<button type='button' class='btn btn-success btn-sm' onclick='adicionacidaoprocedimento(" + data+ ");'><i class='fa fa-plus'></i> Adicionar </button>"
				}
			}
		]
	})
}

// Função para remover os Procedimentos da Tabela de Procedimentos
function removeProcedimento(item) {
	swal({
		title: 'Tem certeza que deseja excluir este Procedimento?',
		text: "Você não poderá reverter esta ação!",
		icon: 'warning',
		buttons: {
			cancel: {
				visible: true,
				text: 'Não, cancelar!',
				className: 'btn btn-success btn-border'
			},
			confirm: {
				text: 'Sim, excluir!',
				className: 'btn btn-success'
			}
		}
	}).then((willDelete) => {
		if (willDelete) {
			var idAtendimento = $("#id-atendimento").val();
			var idAtendimentoProcedimento = $(item).attr("data-value");
			$.ajax({
				url: '/atendimento-procedimento/excluir/' + idAtendimento + "/" + idAtendimentoProcedimento,
				method: 'get',
				success: function() {
					swal("Sucesso! O Procedimento foi excluido!", {
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
						console.log(xhr);
						swal("Houve um erro!" + xhr.responseText, {
							icon: "error",
							buttons: {
								confirm: {
									className: 'btn btn-danger'
								}
							},
						});
					}
				}
			})
		} else {
			swal("Certo, não iremos excluir!", {
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
