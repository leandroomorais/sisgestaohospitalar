
//JS Form Atendimento
$(document).ready(function() {

	$("#card-nova-prescricao").hide();
	$("#card-edit-prescricao").hide();
	$("#card-list-registros-administracao").hide();
	$("#card-novo-registro-administracao").hide();
	$("#card-novo-atestado").hide();
	$("#card-novo-exame").hide();
	$("#card-novo-resultado-exame").hide();
	
	//Função que aplica máscara aos inputs 
	$("#sinaisVitais-pressaoSistolica").mask('000');
	$("#sinaisVitais-pressaoDiastolica").mask('000');
	$("#sinaisVitais-frequenciaRespiratoria").mask("000");
	$("#sinaisVitais-frequenciaCardiaca").mask("000");
	$("#sinaisVitais-temperaturaCorporal").mask("00.0");
	$("#sinaisVitais-glicemiaCapilar").mask("000");
	$("#sinaisVitais-saturacaoOxigenio").mask("000");
	//Fim da função

	//Função que inicia o TinyMCE
	tinymce.init({
		selector: '#historia-clinica, #avaliacao, #nota-administracao',
		language: 'pt_BR',
		height: 150,
		menubar: false,
		plugins: [
			'advlist autolink lists link image charmap print preview anchor',
			'searchreplace visualblocks code fullscreen',
			'insertdatetime media table paste code help wordcount'
		],
		toolbar: 'undo redo | formatselect | ' +
			'bold italic backcolor | alignleft aligncenter ' +
			'alignright alignjustify | bullist numlist outdent indent | ' +
			'removeformat | help',
		content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
	});
	//Fim da função que inicia o TinyMCE

	atualizaDiagnostico();
	atualizaPrescricoes();
	atualizaAtestados();
	
	// Funções dos Exames
	atualizaProcedimentoExame();
	atualizaExames();
	//tabelaTodosExame();
	//ExamesSolicitados();
	 atualizaTodosExames();
	
		//Chamada da função 
	atulizaMedicamentoUsoContinuo();

	//Chamada da Função
	atulizaMedicamentoEmUso();

	cardInfoCidadao(idAtendimento);
	

});

$("#submit-consulta").click(function() {
	var consulta = {};

	consulta.historiaClinica = tinymce.get("historia-clinica").getContent();
	consulta['avaliacao.notas'] = tinymce.get("avaliacao").getContent();
	consulta['atendimento'] = idAtendimento;
	consulta['avaliacao.sinaisVitais.pressaoArterial'] = $("#sinaisVitais-pressaoArterial").val();
	consulta['avaliacao.sinaisVitais.temperaturaCorporal'] = $("#sinaisVitais-temperaturaCorporal").val();
	consulta['avaliacao.sinaisVitais.frequenciaCardiaca'] = $("#sinaisVitais-frequenciaCardiaca").val();
	consulta['avaliacao.sinaisVitais.saturacao'] = $("#sinaisVitais-saturacaoOxigenio").val();
	consulta['avaliacao.sinaisVitais.frequenciaRespiratoria'] = $("#sinaisVitais-frequenciaRespiratoria").val();
	consulta['avaliacao.sinaisVitais.glicemiaCapilar'] = $("#sinaisVitais-glicemiaCapilar").val();
	consulta['avaliacao.sinaisVitais.momentoColeta'] = $("#sinaisVitais-momentoColeta option:selected").val();

	$.ajax({
		url: '/consulta/',
		method: 'post',
		data: consulta,
		beforeSend: function() {

		},
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'A Consulta foi salva',
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
			422: function(xhr) {
				console.log(consulta);
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

					$("input[name='" + key + "']").addClass("has-error has-feedback");

				})
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
		},

		complete: function() {

		}
	})

})


$("#submit-diagnostico").click(function() {
	var diagnostico = {};
	diagnostico['atendimento'] = idAtendimento;
	diagnostico['prontuario'] = idProntuario;
	diagnostico['cid'] = $("#id-cid").val();
	diagnostico.nota = $("#nota").val();
	console.log(diagnostico);

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

//Função pesquisa de Cids
$("#diagnostico-cid").autocomplete({
	source: "/cid/buscar",
	focus: function(event, ui) {
		$("#alergia-cid").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function(event, ui) {
		$("#diagnostico-cid").val(ui.item.codigo + " - " + ui.item.nome);
		$("#id-cid").val(ui.item.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>").appendTo(ul);
}
//Fim da função pesquisa Cids

//Inicio da funcao atualizar Diagnósticos
function atualizaDiagnostico() {
	$("#table-diagnosticos").DataTable({
		responsive: true,
		paging: false,
		searching: false,
		ordering: false,
		ajax: {
			url: '/diagnostico/listar/atendimento/' + idAtendimento,
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
				title: 'DESCRICÃO',
				data: 'cid.nome',
			},
			{
				title: 'AÇÕES',
				data: 'id',
				mRender: function(data) {
					var retorno =
						" <button class='btn btn-warning btn-sm' data-value='" + data + "' onclick='excluirDiagnostico(this)'><i class='fa fa-trash'></i> Excluir </button>"
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

function excluirDiagnostico(element) {
	var idDiagnostico = $(element).attr("data-value");
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
			$.ajax({
				url: '/diagnostico/' + idDiagnostico,
				method: 'delete',
				success: function() {
					$("#table-diagnosticos").DataTable().ajax.reload();
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

//function verificaConsulta(){
//	$.ajax({
//		url: '/consulta/verificar/' + idAtendimento,
//		method: 'get',
//		success: function(data){
//			$("#idConsulta").val(data.id);
//			$("#idSinaisVitais").val(data.sinaisVitais.id);
//			$("#inicioConsulta").val(data.inicioConsuta);
//			
//			
//		}
//	})
//}