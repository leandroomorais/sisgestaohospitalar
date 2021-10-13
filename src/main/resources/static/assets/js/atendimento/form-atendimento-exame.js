//Função Habilita pesquisa de Medicamentos
$("#button-procedimento").click(function() {
	limpaExame();
	$("#i-procedimento").removeClass().addClass("fa fa-search");
})

function exibeFormularioExame() {
	limpaExame();
	removeInvalidFedbackExame();
	$("#card-list-exames").fadeOut(100);
	//$("#card-edit-prescricao").hide();
	//$("#card-novo-registro-administracao").hide();
	//$("#card-list-registros-administracao").hide();
	$("#card-novo-exame").fadeIn(100);
	//atualizaProcedimentoExame();
	//atualizaPrescricoExames();
	//atualizaProcedimentoExame();
	$("#table-procedimentos-exame").DataTable().ajax.reload();
}

function limpaExame() {
	$("#procedimento-exame").val("").attr("disabled", false);
	$("#id-procedimento-exame").val("");
	$("#grupoexame").val("");
	$("#nomeexame").val("");
	$("#procedimentocid").val("");
	$("#justificativacid").val("");
	$("#observacoes").val("");
	$("#procedimentocid").val("");
}


function fechaFormularioExame() {
	limpaExame();
	$("#card-novo-exame").fadeOut(100);
	$("#card-list-exames").fadeIn(100);
	$("#table-list-exame").DataTable().ajax.reload();
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

//$("#button-procedimento-exame").click(function() {
//	$("#i-procedimento-exame").removeClass().addClass("fa fa-search");
//	limpaInputsProcedimento();
//})


$("#submit-procedimento-exame").click(function() {	
	var procedimento = {};
	
	procedimento.codigo = $("#id-procedimento-exame").val();
	
$.ajax({
		url: '/exame/procedimentos/',
		method: 'post',
		data: procedimento,
		beforeSend: function() {
			//console.log(procedimento);
			removeInvalidFedbackExame();
		},
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O procedimento foi salvo',
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
			limpaExame();
			//fechaFormularioExame();
			//atualizaExames();
			//atualizaPrescricoExames();
			$("#table-procedimentos-exame").DataTable().ajax.reload();
		},

		statusCode: {
			400: function() {
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

$("#form-exame").submit(function(evt) {
	evt.preventDefault();
	var exame = {};

	//exame['medicamento'] = $("#id-medicamento").val();
	//exame['viaAdministracao'] = $("#via option:selected").val();
	//exame['procedimentos'] = $("#id-procedimento-exame").val();
	//exame['procedimentos'] = procedimentos();
	exame.grupoexame = $("#grupoexame").val();
	exame.nomeexame = $("#nomeexame").val();
	exame.procedimentocid = $("#procedimentocid").val();
	exame.justificativacid = $("#justificativacid").val();
	exame.observacoes = $("#observacoes").val();
	exame['atendimento'] = $("#id-atendimento").val();
	exame['prontuario'] = $("#id-prontuario").val();

	$.ajax({
		url: '/exame/',
		method: 'post',
		data: exame,
		beforeSend: function() {
			console.log(exame);
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
			//atualizaProcedimentoExame();
		},

		statusCode: {
			400: function() {
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
		//console.log("aqui----");
	//var atendimentoId = $("#id-atendimento").val();
	$("#table-procedimentos-exame").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/exame/listarprocedimentos/',
			dataSrc: ''
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
	//console.log("item ---- ", item);
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
		//var idAtendimento = $("#id-atendimento").val();
		var idProcedimentoExame = $(item).attr("data-value");
		//console.log("item ---- ", idProcedimentoExame);
		$.ajax({
			url: '/exame/excluirprocedimentos/' + idProcedimentoExame,
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
				$("#table-procedimentos-exame").DataTable().ajax.reload();
				//atualizaProcedimentoExame();
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

function atualizaListaExames() {
	console.log("aqui----");
	var atendimentoId = $("#id-atendimento").val();
	console.log("aqui----", atendimentoId);
	$("#table-list-exame").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/exame/listar/atendimento/'+ atendimentoId,
			dataSrc: ''
		},
		columns: [
			{
				title: 'NOME',
				data: 'nomeexame',
			},
			{
				title: 'GRUPO',
				data: 'grupoexame',
			},
//			{
//				title: 'AÇÕES',
//				data: 'id',
//				mRender: function(data) {
//					return "<button type='button' class='btn btn-warning btn-sm' data-value='" + data + "' onclick='removeExame(this)'><i class='fa fa-trash'></i> Excluir </button>"
//				}
//			}
		]
	})
}

function removeExame(item) {
	//console.log("item ---- ", item);
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
		//var idAtendimento = $("#id-atendimento").val();
		var idProcedimentoExame = $(item).attr("data-value");
		//console.log("item ---- ", idProcedimentoExame);
		$.ajax({
			url: '/exame/excluirprocedimentos/' + idProcedimentoExame,
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
				$("#table-procedimentos-exame").DataTable().ajax.reload();
				//atualizaProcedimentoExame();
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


//************Funções autocomplete */
//Função autocomplete Procedimentos
$("#procedimento-exame").autocomplete({
	source: "/procedimento/buscarexame",
	focus: function(event, ui) {
		$("#procedimento-exame").val(ui.item.codigo + " ; " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-procedimento").removeClass("fa fa-search").addClass("fa fa-times");
		$("#qtd-procedimento").val(1);
		$("#procedimento-exame").val(ui.item.codigo + " - " + ui.item.nome).attr("disabled", true);
		$("#id-procedimento-exame").val(ui.item.codigo);
		$("#nome-procedimento").val(ui.item.nome);
		return false;

	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li></li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>")
		.appendTo(ul);
};

//Função pesquisa de Cids
$("#diagnostico-cid").autocomplete({
	source: "/cid/buscar",
	focus: function(event, ui) {
		$("#alergia-cid").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#diagnostico-cid").val(ui.item.codigo + " - " + ui.item.nome);
		$("#id-cid").val(ui.item.codigo);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>").appendTo(ul);
		console.log("aqui no autocomplete");
}
//Fim da função pesquisa Cids

$("#submit-diagnostico").click(function() {
	var diagnostico = {};
	diagnostico['atendimento'] = $("#id-atendimento").val();
	diagnostico['prontuario'] = $("#id-prontuario").val();
	diagnostico['cid'] = $("#id-cid").val();
	diagnostico.nota = $("#nota").val();

	console.log(hipoteseDiagnostica);

	$.ajax({
		url: '/diagnostico/',
		method: 'post',
		data: diagnostico,
		success: function() {
			$("#table-diagnosticos").DataTable().ajax.reload();
			limpaInputsDiagnostico();
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O diagnóstico foi salvo',
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
	})

})

//Inicio da funcao atualizar Diagnósticos
function atualizaDiagnostico() {
	var atendimentoId = $("#id-atendimento").val();
	$("#table-diagnosticos").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/diagnostico/listar/atendimento/' + atendimentoId,
			dataSrc: ''
		},
		columns: [
			{
				title: 'NOTA',
				data: 'nota',
			},
			{
				title: 'CID',
				data: 'cid.codigo',
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

function limpaInputsDiagnostico() {
	$("#nota").val("");
	$("#diagnostico-cid").val("");
	$("#id-cid").val("");
}