//JS Form Atendimento
$(document).ready(function() {

	$("#card-nova-prescricao").hide();
	$("#card-edit-prescricao").hide();
	$("#card-registros-administracao").hide();
	$("#card-novo-registro-administracao").hide();

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

});


$("#submit-diagnostico").click(function() {
	var hipoteseDiagnostica = {};
	hipoteseDiagnostica.idAtendimento = $("#id-atendimento").val();
	hipoteseDiagnostica['prontuario.id'] = $("#id-prontuario").val();
	hipoteseDiagnostica['profissional.id'] = null;
	hipoteseDiagnostica['cid.codigo'] = $("#id-cid").val();
	hipoteseDiagnostica.nota = $("#nota").val();

	console.log(hipoteseDiagnostica);

	$.ajax({
		url: '/atendimento/diagnostico',
		method: 'post',
		data: hipoteseDiagnostica,
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
			url: '/atendimento/diagnosticos/' + atendimentoId,
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