//JS Form Atendimento
$(document).ready(function() {

	$("#card-nova-prescricao").hide();
	$("#card-edit-prescricao").hide();
	$("#card-list-registros-administracao").hide();
	$("#card-novo-registro-administracao").hide();
	$("#card-novo-atestado").hide();

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

});

$("#submit-consulta").click(function() {
	var consulta = {};

	consulta.historiaClinica = tinymce.get("historia-clinica").getContent();
	consulta['avaliacao.notas'] = tinymce.get("avaliacao").getContent();
	consulta['atendimento'] = $("#id-atendimento").val();
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
	diagnostico['atendimento'] = $("#id-atendimento").val();
	diagnostico['prontuario'] = $("#id-prontuario").val();
	diagnostico['cid'] = $("#id-cid").val();
	diagnostico.nota = $("#nota").val();

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
		$("#id-cid").val(ui.item.codigo);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + item.nome + "</div>").appendTo(ul);
}
//Fim da função pesquisa Cids

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
	var idProntuario = $("#id-prontuario").val();
	$.ajax({
		url: '/diagnostico/' + idDiagnostico + "/" + idProntuario,
		method: 'delete',
		success: function() {
			$("#table-diagnosticos").DataTable().ajax.reload();
			alert("Excluido");

		},
		error: function() {
			alert("Não foi possivel excluir");
		}
	})
}