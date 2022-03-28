//Função que adiciona procedimentos
//Autocomplete Procedimentos

$("#procedimentos-geral").autocomplete({
	maxShowItems: 5,
	source: "/procedimento/buscar",
	focus: function(event, ui) {
		$("#procedimentos-geral").val(ui.item.codigo + " - " + ui.item.nome.trim());
		return false;
	},
	select: function(event, ui) {
		$("#i-procedimento-geral").removeClass().addClass("fa fa-times");
		$("#qtd-procedimento-geral").val(1);
		$("#procedimentos-geral").val(ui.item.codigo + " - " + ui.item.nome.trim()).attr("disabled", true);
		$("#id-procedimento-geral").val(ui.item.codigo);
		$("#nome-procedimento").val(ui.item.nome.trim());
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li></li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome.trim() + "</div>")
		.appendTo(ul);
};

$("#button-procedimento").click(function() {
	$("#i-procedimento-geral").removeClass().addClass("fa fa-search");
	limpaInputsProcedimento();
})

$("#submit-procedimento").click(function() {
	var codigoProcedimento = $("#id-procedimento-geral").val();
	var quantidade = $("#qtd-procedimento-geral").val();
	submitProcedimento(idAtendimento, codigoProcedimento, null, quantidade);
})

//Fim da função
//######### Fim das Funções dos Procedimentos ###########

function submitProcedimento(idAtendimento, codigoProcedimento, tipoServico, quantidade) {
	var relAtendimentoProcedimento = {};
	relAtendimentoProcedimento.atendimento = idAtendimento;
	relAtendimentoProcedimento['procedimento.codigo'] = codigoProcedimento;
	relAtendimentoProcedimento.quantidade = quantidade;
	relAtendimentoProcedimento.tipoServico = tipoServico;

	//função que verifica se procedimento tem CID obrigatório
	verificaProcedimentoObrigatorioCid(codigoProcedimento);

	$.ajax({
		url: '/atendimento-procedimento/adicionar',
		method: 'POST',
		data: relAtendimentoProcedimento,
		success: function() {
			$("#i-procedimento-geral").removeClass().addClass("fa fa-search");
			limpaInputsProcedimento();
			notificacao('Sucesso!', 'Procedimento adicionado', 'top', 'right', 'success', 'withicon', '#', '');
			$("#table-procedimentos").DataTable().ajax.reload();
		},

		statusCode: {
			403: function(xhr) {
				notificacao('Atenção!', xhr.responseText, 'top', 'right', 'warning', 'withicon', '#', '');
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
				data: 'id',
				mRender: function(data) {
					return "<div><div class='row'><div class='col-md-6'><button type='button'class='btn btn-light btn-sm' onclick='adicionacidaoprocedimento(" + data + ");'><i class='fa fa-pencil-square-o'></i></button></div>"
						+ "<div class='col-md-6'><button type='button' class='btn btn-light btn-sm' onclick='visualizaciddoprocedimento(" + data + ");'><i class='fa fa-search'></i></button></div></div></div>";
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
						},
						timer: 1000
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
							timer: 2000
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
				},
				timer: 1000
			});
		}
	});

	return false;
}
