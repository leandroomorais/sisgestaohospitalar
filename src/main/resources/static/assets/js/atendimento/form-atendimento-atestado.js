var idAtendimento = $("#id-atendimento").val();
var idProntuario = $("#id-prontuario").val();

$("#form-atestado").submit(function(evt) {
	evt.preventDefault();
	var atestado = {};
	atestado.texto = $("#conteudo-atestado").text();
	atestado.periodo = $("#periodo").val();
	atestado.autorizaImpressaoCid = $("#autorizaImpressaoCid").prop("checked");
	atestado['atendimento'] = idAtendimento;
	atestado['prontuario'] = idProntuario;

	$.ajax({
		url: '/atestado/',
		method: 'post',
		data: atestado,
		success: function() {
			console.log(atestado.texto);
			fechaFormularioAtestado();
			atualizaAtestados();
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O Atestado foi salvo',
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


function limpaFormularioAtestado() {
	$("#periodo").val("");
	$("#id-cid").val("");
}

function fechaFormularioAtestado() {
	$("#card-novo-atestado").fadeOut(100);
	$("#card-list-atestados").fadeIn(100);
	idAtestado = null;
	limpaFormularioAtestado();
}

function exibeFormularioAtestado() {
	$("#card-list-atestados").fadeOut(100);
	$("#card-novo-atestado").fadeIn(100);
	atualizaCidAtestado();
}

$("#novo-atestado-voltar").click(function() {
	//excluirAtestadoFuncaoVoltar();
	limpaFormularioAtestado();
	limpaAtestado();
	$("#card-novo-atestado").fadeOut(100);
	$("#card-list-atestados").fadeIn(100);
})


function limpaAtestado() {	
	$("#conteudo-atestado").val("");
	$("#strong-periodo").val("");
	$("#autorizaImpressaoCid").parent().removeClass().addClass("toggle btn btn-black off");
	
	limparCidsAtestado();
}

function atestadoVoltar() {
	limpaFormularioAtestado();
	limpaAtestado();
	$("#card-novo-atestado").fadeOut(100);
	$("#card-list-atestados").fadeIn(100);

}

function limparCidsAtestado(){
	
	$.ajax({
		url: '/atestado/limpar/cids/',
		method: 'get',
		success: function() { }
	})
}

//Função pesquisa de Cids
$("#cid-atestado").autocomplete({
	source: "/cid/buscar",
	focus: function(event, ui) {
		$("#cid-atestado").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$.ajax({
			url: '/atestado/cid/' + ui.item.id,
			method: 'get',
			success: function() {
				$("#cid-atestado").val("");
				$.notify({
					// options
					icon: 'flaticon-success',
					title: 'SUCESSO',
					message: 'O CID foi adicionado ao Atestado',
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
				$("#table-cids").DataTable().ajax.reload();
			},
			error: function() {
				$("#cid-atestado").val("");
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

$("#periodo").on('change', function() {
	$("#strong-periodo").text($(this).val());
});


function atualizaAtestados() {
	$("#div-prescricoes").empty();
	$.ajax({
		url: '/atestado/listar/atendimento/' + idAtendimento,
		method: 'get',
		success: function(data) {
			if (isEmpty(data)) {
				$("#div-atestados").empty().append("<h5 class='card-title text-center'>Não existem atestados para este atendimento</h5><p class='card-text text-center'>Clique no botão Nova prescrição para cadastrar um.</p>");
			} else {
				$.each(data, function(key, item) {
					$("#div-atestados").empty().append(creatCardAtestado(item));
				})
			}
		},
		error: function() {
			$("#div-atestados").empty().append("<h5 class='card-title text-center'>Houve um erro ao recuperar os dados para este Atendimento</h5><p class='card-text text-center'>Clique no botão Nova prescrição para cadastrar um.</p>");
		}
	})
};

function atualizaCidAtestado() {
	$("#table-cids").DataTable().destroy();
	$("#table-cids").DataTable({
		responsive: true,
		paging: true,
		searching: false,
		ordering: false,
		ajax: {
			url: '/atestado/listar/cids/',
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
				data: 'id',
				mRender: function(data) {
					return "<button type='button' class='btn btn-warning btn-sm' data-value='" + data + "' onclick='removeCid(this)'><i class='fa fa-trash'></i> Excluir </button>"
				}
			}
		]

	})
}


function removeCid(item) {
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
			var idCid = $(item).attr("data-value");
			$.ajax({
				url: '/atestado/cid/excluir/' + idCid,
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
					atualizaCidAtestado();
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

//function imprimirAtestado(data) {
//	const { cids, profissional, texto, dataRegistro, autorizaImpressaoCid } = data
//	const innerHTML = autorizaImpressaoCid ? `
//	<div class="text-left">
//		<p class="strong">CIDS:</p>
//	</div>
//	<div class="row">
//		${ExtractPDF.extractMap(cids, (value, index) =>
//		`<span style="padding-left: 12px;"> 
//				<p>${value.codigo}</p>
//			</span>`
//	)}
//	</div>
//	<div class="text-center">
//		<p>Autorizo a listagem dos CIDS nesse atestado</p>
//	</div>`
//		: ''
//	const div = `
//	<div class="card">
//		${header()}
//		<div class="card-body">
//			<div class="text-center">
//				<h1 class="strong">Atestado</h1>
//			</div>
//			<div class="text-justify">
//                <p>${texto}</p>
//            </div>
//			${innerHTML}
//			<div class="text-center">
//				<p>${user.nome}</p>
//			</div>
//			<div class="text-center">
//				<p>Data: ${moment(dataRegistro).format("DD/MM/YYYY")}</p>
//			</div>
//			<br/>
//			<div class="text-center">
//				<p>${profissional.nome}</p>
//				<span>CRM: ${profissional.numeroRegistro + " / " + profissional.siglaUfEmissao}</span>
//			</div>
//		</div>
//		<div class="card-footer">
//			<button class="btn btn-primary" onclick="window.print()">Imprimir</button>
//		</div>
//	</div>`
//	Docs.doc(div)
//}

function creatCardAtestado(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-8'>" +
		h5CardAtestado(data.periodo) +
		paragrafo(data.texto) +
		"</div><div class='col-md-4 text-right'>" +
		infoCardDataProfissional(data.dataRegistro, data.profissional.nome, data.profissional.numeroRegistro + " / " + data.profissional.siglaUfEmissao) +
		"</div></div><div class='text-right'>" +
		"<a class='btn btn-light btn-sm' target='_blank' href='/relatorio/atestado/ " + data.id + "'><i class='fa fa-print'></i> Imprimir</a>" +
		buttonExcluir(data) +
		"</div></div></div>";
}

function h5CardAtestado(periodo) {
	return "<h5 class='text-uppercase fw-bold mb-1'> ATESTADO <span class='text-danger text-uppercase pl-3'> " + periodo + " dia(s) </span></h5><br>";
}

function paragrafo(texto) {
	return "<div class='text-center'><p>" + texto + "</p></div>"
}

function infoCardDataProfissional(date, nomeProfissional, crm) {
	date = my_date_format;
	return "<span class='text-warning'> " + date + " </span><br><span class='text-muted'> " + nomeProfissional + " </span><br><strong>CRM: </strong><span class='text-muted'> " + crm + " </span><br>"
}

function excluirAtestado(element) {
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
			var idAtestado = $(element).attr("data-value");
			$.ajax({
				url: '/atestado/excluir/' + idAtestado,
				method: 'delete',
				success: function() {
					swal("Sucesso! O Atestado foi excluido!", {
						icon: "success",
						buttons: {
							confirm: {
								className: 'btn btn-success'
							}
						}
					});
					atualizaAtestados();
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

function buttonExcluir(data) {
	var username = $("#nome-usuario").text();
	if (data.profissional.cpf != username) {
		return "";
	} else if (data.profissional.cpf == username) {
		return "<button type='button' class='btn btn-light btn-sm' data-value='" + data.id + "' onclick='excluirAtestado(this)'><i class='fa fa-trash'></i> Excluir</button>";
	}
}

var my_date_format = function(d) {
	var month = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
	var date = d.getDate() + " " + month[d.getMonth()] + ", " + d.getFullYear();
	var time = d.toLocaleTimeString().toLowerCase();
	return (date + " às " + time);
}(new Date());

