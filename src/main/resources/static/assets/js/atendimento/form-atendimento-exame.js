//Função Habilita pesquisa de Medicamentos
$("#button-procedimento").click(function() {
	limpaExame();
	$("#i-procedimento").removeClass().addClass("fa fa-search");
})

function exibeFormularioExame() {
	limpaExame();
	removeInvalidFedbackExame();
	$("#card-list-exames").fadeOut(100);
	$("#card-list-todos-exames").fadeOut(100);
	$("#card-novo-exame").fadeIn(100);

	$("#table-procedimentos-exame").DataTable().ajax.reload();

	$("#i-procedimento").removeClass().addClass("fa fa-search");
	$("#i-exame-cid").removeClass().addClass("fa fa-search");
}

function limpaExame() {
	$("#procedimento-exame").val("").attr("disabled", false);
	$("#id-procedimento-exame").val("");
	$("#justificativa").val("");
	$("#observacoes").val("");
	$("#exame-cid").val("");
}


function fechaFormularioExame() {
	limpaExame();
	$("#card-novo-exame").fadeOut(100);
	$("#card-list-exames").fadeIn(100);
	$("#card-list-todos-exames").fadeIn(100);

	$.ajax({
		url: '/exame/limparprocedimentosexame/',
		method: 'get',
		success: function() { },
		statusCode: {}
	})

	$("#table-procedimentos-exame").DataTable().ajax.reload();
	atualizaExames();

}


$("#novo-exame-voltar").click(function() {
	fechaFormularioExame();
})

function removeInvalidFedbackExame() {
	$("#form-exame input, #form-exame textarea").each(
		function(index) {
			var str = $(this).parent().parent().attr("class");
			if (str.match(/has-error/)) {
				$(this).parent().parent().removeClass("has-error has-feedback");
			}

		}
	);
}



$("#form-exame").submit(function(evt) {
	evt.preventDefault();
	var exame = {};


	exame.justificativa = $("#justificativa").val();
	exame.observacoes = $("#observacoes").val();
	exame['atendimento'] = $("#id-atendimento").val();
	exame['prontuario'] = $("#id-prontuario").val();
	exame['cid'] = $("#id-cid").val();

	$.ajax({
		url: '/exame/',
		method: 'post',
		data: exame,
		beforeSend: function() {
			removeInvalidFedbackExame();
		},
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O Exame foi salvo',
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
			fechaFormularioExame();
			atualizaTodosExames();
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
			}
		},

	})
})


function atualizaProcedimentoExame() {
	$("#table-procedimentos-exame").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/exame/listarprocedimentos/',
			dataSrc: '',

		},
		columns: [
			{
				title: 'CÓDIGO',
				data: 'codigo',
			},
			{
				title: 'NOME',
				data: 'nome',
			},
			{
				title: 'AÇÕES',
				data: 'codigo',
				mRender: function(data) {
					return "<button type='button' class='btn btn-warning btn-sm' data-value='" + data + "' onclick='removeProcedimentoExame(this)'><i class='fa fa-trash'></i> Excluir </button>"
				}
			}
		]
	})
}

// Função para remover os Procedimentos da Tabela de Procedimentos
function removeProcedimentoExame(item) {
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
			var idProcedimentoExame = $(item).attr("data-value");
			$.ajax({
				url: '/exame/excluirprocedimentos/' + idProcedimentoExame,
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
					$("#table-procedimentos-exame").DataTable().ajax.reload();
				},
				statusCode: {
					403: function(xhr) {
						swal("Houve um erro!", xrh.reponseText, {
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
}

function excluirExame(item) {

	swal({
		title: 'Tem certeza que deseja excluir este Exame?',
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
			var idExame = $(item).attr("data-value");
			$.ajax({
				url: '/exame/excluir/' + idExame,
				method: 'get',
				success: function() {
					swal("Sucesso! O Exame foi excluido!", {
						icon: "success",
						buttons: {
							confirm: {
								className: 'btn btn-success'
							}
						}
					});
					atualizaExames();
					atualizaTodosExames();
				},
				statusCode: {
					403: function(xhr) {
						swal("Houve um erro!", xrh.reponseText, {
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
}


//************Funções autocomplete */
//Função autocomplete Procedimentos

$("#procedimento-exame").autocomplete({
	source: "/procedimento/buscarexame",
	focus: function(event, ui) {
		$("#procedimento-exame").val(ui.item.codigo + " ; " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$.ajax({
			url: '/exame/procedimento/' + ui.item.codigo,
			method: 'get',
			success: function() {
				$("#procedimento-exame").val("");
				$.notify({
					// options
					icon: 'flaticon-success',
					title: 'SUCESSO',
					message: 'O Procedimento foi adicionado ao Exame',
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
				$("#table-procedimentos-exame").DataTable().ajax.reload();
			},
			error: function() {
				$("#procedimento-exame").val("");
				console.log("erro aqui");
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

		});
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + "<b>" + item.nome + "<b>" + "</div>").appendTo(ul);
}

//Função autocomplete CID 
$("#exame-cid").autocomplete({
	source: "/cid/buscar",
	focus: function(event, ui) {
		$("#exame-cid").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-exame-cid").removeClass("fa fa-search").addClass("fa fa-times");
		$("#id-cid").val(ui.item.id);
		return false;

	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + "<b>" + item.nome + "</b>" + "</div>")
		.appendTo(ul);
};

// Card de exames
function atualizaExames() {
	var prontuarioId = $("#id-prontuario").val();
	$("#div-exames").empty();
	$.ajax({
		url: '/exame/listarexamesporstatus/' + prontuarioId,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-exames").append("<h5 class='card-title text-center'>Não existem solicitações de exames para este Prontuário</h5><p class='card-text text-center'>Clique no botão Nova solicitação para cadastrar uma.</p>");
			} else {
				$.each(data, function(key, item) {
					$("#div-exames").append(createCardExame(item));
				})
			}

		},
	})
};

function createCardExame(data) {
	
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-8'>" +
		"<strong>Procedimentos: </strong>" +
		"<br>" + infoProcedimentos(data.procedimentos) +
		infoCardCid(data.cid) +
		infoCardJustificativa(data.justificativa) +
		infoCardObservacoes(data.observacoes) +
		"</div><div class='col-md-4 text-right'>" +
		infoCardDataProfissional(data.dataSolicitacao, data.profissional.nome, data.profissional.numeroRegistro + " / " + data.profissional.siglaUfEmissao) +
		"</div></div><div class='text-right'>" +
		"<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='imprimirExame()'><i class='fa fa-print'></i> Imprimir</button>"
		+ buttonExcluir()
		+ "</div></div></div>";


	function buttonExcluir() {
		var username = $("#nome-usuario").text();
		if (data.profissional.cpf != username) {
			return "";
		} else if (data.profissional.cpf == username) {
			return "<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='excluirExame(this)'><i class='fa fa-trash'></i> Excluir</button>";
		}
	}
}

function infoProcedimentos(procedimentos) {
	var retorno;
	var retornoConcat = "";
	$.each(procedimentos, function(key, item) {
		retorno = "<div>" + item.codigo + " - " + "<b>" + item.nome + "</b>" + "</div>";
		retornoConcat += retorno ;
	})
	return retornoConcat;
}


function infoCardCid(cid) {
	if(cid == null){
		return "<strong>CID Relacionado: </strong><br><span><i> Nenhum CID Informado! </i></span><br>"
	}else{
     	return "<strong>CID Relacionado: </strong><br><span> " + cid.nome + " </span><br>"
     }
}

function infoCardJustificativa(justificativa) {
	return "<strong>Justificativa: </strong><br><span> " + justificativa + " </span><br>"
}

function infoCardObservacoes(observacoes) {
	if(observacoes == ""){
		return "<strong>Observações: </strong><br><span><i> Nenhuma Observação Registrada! </i></span><br>"
	}else{
		return "<strong>Observações: </strong><br><span> " + observacoes + " </span><br>"
	}
}

function infoCardDataProfissional(date, nomeProfissional, crm) {
	return "<span class='text-warning'> " + dataFormatada(date) + " </span><br><span class='text-muted'> " + nomeProfissional + " </span><br><strong>CRM: </strong><span class='text-muted'> " + crm + " </span><br>"
}

function imprimirExame() {
	return "";
}



// função do card de todos exames
function atualizaTodosExames() {
	var prontuarioId = $("#id-prontuario").val();
	$("#div-todos-exames").empty();
	$.ajax({
		url: '/exame/listarexamesdoprontuario/' + prontuarioId,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-todos-exames").append("<h5 class='card-title text-center'>Não existem resultados de exames cadastrados neste Prontuario</h5><p class='card-text text-center'>Clique no botão Novo Exame para cadastrar um.</p>");
			} else {
				$("#div-todos-exames").append(createCardTitulo());
				$.each(data, function(key1, item1) {
					$.each(item1.procedimentos, function(key, item) {
						$("#div-todos-exames").append(createCardTodosExame(item1, item));
					})
				})
			}

		},
	})
};

function createCardTodosExame(data1, data) {
	
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'>" +
		infoResultado(data1, data) +
	    "</div></div></div>";
}


function procedimentos(item1, item){
	var dataSolicitacao = dataFormatada(item1.dataSolicitacao);
	
	return "<div class='col-md-2'>"+dataSolicitacao +" </div><div class='col-md-5'><b> " +  item.nome + "</b></div>";
}

function infoResultado(data1, data){
	var resultados = data1.resultados;
	
	if(isEmpty(resultados)){
		return procedimentos(data1, data) + "<div class='col-md-2'>"+ "</div><div class='col-md-3'><b> Não </b>" + "<button type='button' class='btn btn-light btn-sm' data-value='" + data1.id + "' onclick='resgistroResultadoExame("+data1.id+","+data.codigo+");'><i class='fas fa-share' style='font-size:18px;color:green'></i></button></div>";
	
	}else{
		for(let resultado of resultados){
			if(resultado.procedimento.codigo == data.codigo){
				var dataResultado = dataFormatada(resultado.dataResultado);
				
				return procedimentos(data1, data) + "<div class='col-md-2'>" + dataResultado + 
				"</div><div class='col-md-3'><b> Sim </b>" + "<button type='button' class='btn btn-light btn-sm' onclick='detalheResultadoExame("+resultado.id+");'><i class='fa fa-search' style='font-size:18px;color:blue'></i></button>"
				+ "<button type='button' class='btn btn-light btn-sm' data-value='" + data1.id + "' onclick='editarResultadoExame("+data1.id+","+data.codigo+","+resultado.id+");'><i class='fa fa-pencil-square-o' style='font-size:18px;color:red'></i></button></div>";
			}
		}
		return procedimentos(data1, data) + "<div class='col-md-2'>"+ 
		"</div><div class='col-md-3'><b> Não </b>" + 
		"<button type='button' class='btn btn-light btn-sm' data-value='" + data1.id + "' onclick='resgistroResultadoExame("+data1.id+","+data.codigo+");'><i class='fas fa-share' style='font-size:18px;color:green'></i></button></div>";
	}

}

function createCardTitulo(){

	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-2'>" +
			"<b>SOLICITADO </b></div><div class='col-md-5'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; NOME EXAME</b></div><div class='col-md-2'>" +
			"<b>AVALIADO </b></div><div class='col-md-3'><b>&nbsp;RESULTADO</b></div>" +
		 	"</div></div></div>";
}

function dataFormatada(dataAtual){
    	let data = new Date(dataAtual),
        dia  = data.getDate().toString().padStart(2, '0'),
        mes  = (data.getMonth()+1).toString().padStart(2, '0'),
        ano  = data.getFullYear();
    return `${dia}/${mes}/${ano}`;
}

