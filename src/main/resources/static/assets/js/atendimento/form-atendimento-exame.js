//Função Habilita pesquisa de Medicamentos
$("#button-procedimento").click(function() {
	limpaExame();
	$("#i-procedimento").removeClass().addClass("fa fa-search");
})

function exibeFormularioExame() {
	limpaExame();
	removeInvalidFedbackExame();
	$("#card-list-exames").fadeOut(100);
	$("#card-novo-exame").fadeIn(100);
	
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
	
	$.ajax({
		url: '/exame/limparprocedimentosexame/',
		method: 'get',
		success: function() { },
		statusCode: { }
	})
	
	//$("#table-procedimentos-exame").DataTable().ajax.reload();
	$("#table-lista-exame-atendimento").DataTable().ajax.reload();
	$("#table-cids-exame").DataTable().ajax.reload();
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
			//console.log(exame);
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
					swal("Sucesso! O CID foi excluido!", {
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

function atualizaListaExames() {
	console.log("aqui----");
	var atendimentoId = $("#id-atendimento").val();
	console.log("aqui----", atendimentoId);
	$("#table-lista-exame-atendimento").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/exame/listarexamesatendimento/' + atendimentoId,
			dataSrc: '',
		},
		columns: [
			{
				title: 'NOME',
				data: 'justificativacid',
			},
			{
				title: 'GRUPO',
				data: 'observacoes',
			},
			{
				title: 'AÇÕES',
				data: 'id',
				mRender: function(data) {
					return "<button type='button' class='btn btn-warning btn-sm' data-value='" + data + "' onclick='removeExame(this)'><i class='fa fa-trash'></i> Excluir </button>"
				}
			}
		]
	})
	
	
}

function removeExame(item) {
	
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
					$("#table-lista-exame-atendimento").DataTable().ajax.reload();
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
		$("#i-procedimento").removeClass("fa fa-search").addClass("fa fa-times");
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
$("#cid-exame").autocomplete({
	source: "/cid/buscar",
	focus: function(event, ui) {
		$("#cid-exame").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$.ajax({
			url: '/exame/cid/' + ui.item.codigo,
			method: 'get',
			success: function() {
				$("#cid-exame").val("");
				$.notify({
					// options
					icon: 'flaticon-success',
					title: 'SUCESSO',
					message: 'O CID foi adicionado ao Exame',
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
				$("#table-cids-exame").DataTable().ajax.reload();
			},
			error: function() {
				$("#cid-exame").val("");
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
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>").appendTo(ul);
}
//Fim da função pesquisa Cids

//Função atualiza tabela de cid do exame
function atualizaCidExame() {
	$("#table-cids-exame").DataTable().destroy();
	$("#table-cids-exame").DataTable({
		responsive: true,
		paging: true,
		searching: false,
		ordering: false,
		ajax: {
			url: '/exame/listar/cids/',
			dataSrc: '',
		},
		columns: [
			{
				title: 'CÓDIGO',
				data: 'codigo',
				mRender: function(data) {
					return "<span class='badge badge-info'>" + data + "</span>";
				}
			},
			{
				title: 'DESCRIÇÃO',
				data: 'nome',
			},
			{
				title: 'AÇÕES',
				data: 'codigo',
				mRender: function(data) {
					return "<button type='button' class='btn btn-warning btn-sm' data-value='" + data + "' onclick='removeCidExame(this)'><i class='fa fa-trash'></i> Excluir </button>"
				}
			}
		]

	})
}

function removeCidExame(item) {
	swal({
		title: 'Tem certeza que deseja excluir este CID?',
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
			var codigoCid = $(item).attr("data-value");
			$.ajax({
				url: '/exame/cid/excluir/' + codigoCid,
				method: 'delete',
				success: function() {
					swal("Sucesso! O CID foi excluido!", {
						icon: "success",
						buttons: {
							confirm: {
								className: 'btn btn-success'
							}
						}
					});
					atualizaCidExame();
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


