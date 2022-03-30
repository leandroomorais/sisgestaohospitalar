var idAtendimento;
var idSinaisVitais;
var inicioConsulta;
//JS Form Atendimento
$(document).ready(function() {

	idAtendimento = $("#id-atendimento").val();
	verificaConsulta();
	ocultarAlergia();
	ocultarDoenca();

	$("#card-nova-prescricao").hide();
	$("#card-nova-prescricao-externa").hide();
	$("#card-edit-prescricao").hide();
	$("#card-list-registros-administracao").hide();
	$("#card-novo-registro-administracao").hide();
	$("#card-novo-atestado").hide();
	$("#card-novo-exame").hide();
	$("#card-novo-resultado-exame").hide();
	$("#card-cid-procedimento").hide();

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
		selector: '#historia-clinica, #avaliacao, #nota-administracao,  #nota, #nota-dto, #descricao, #conduta',
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
	$("#adicionar-a-lista").hide();
	$("#form-observacao").hide();
	atualizaPrescricoes();
	atualizaAtestados();
	//Chamada da função 
	atualizaAlergia();
	//Chamada da função 
	atualizaDoenca();
	// Funções dos Exames
	atualizaProcedimentoExame();
	atualizaExames();
	//tabelaTodosExame();
	//ExamesSolicitados();
	atualizaTodosExames();
	//Chamada da Função
	atualizaProcedimento();
	//Chamada da função 
	atulizaMedicamentoUsoContinuo();
	atualizaAntropometria();
	//Chamada da Função
	atulizaMedicamentoEmUso();
	cardInfoCidadao(idAtendimento);


});

$("#form-consulta").submit(function(evt) {
	evt.preventDefault();
	var consulta = {};

	consulta.id = $("#idConsulta").val();
	consulta.historiaClinica = tinymce.get("historia-clinica").getContent();
	consulta['atendimento'] = idAtendimento;
	consulta.avaliacao = tinymce.get("avaliacao").getContent();
	consulta['sinaisVitais'] = $("#idSinaisVitais").val();
	consulta['sinaisVitais.pressaoSistolica'] = $("#sinaisVitais-pressaoSistolica").val();
	consulta['sinaisVitais.pressaoDiastolica'] = $("#sinaisVitais-pressaoDiastolica").val();
	consulta['sinaisVitais.temperaturaCorporal'] = $("#sinaisVitais-temperaturaCorporal").val();
	consulta['sinaisVitais.frequenciaCardiaca'] = $("#sinaisVitais-frequenciaCardiaca").val();
	consulta['sinaisVitais.saturacao'] = $("#sinaisVitais-saturacaoOxigenio").val();
	consulta['sinaisVitais.frequenciaRespiratoria'] = $("#sinaisVitais-frequenciaRespiratoria").val();
	consulta['sinaisVitais.glicemiaCapilar'] = $("#sinaisVitais-glicemiaCapilar").val();
	consulta['sinaisVitais.momentoColeta'] = $("#sinaisVitais-momentoColeta option:selected").val();
	consulta.diagnostico = $("#diagnostico").val();
	consulta.conduta = tinymce.get("conduta").getContent();
	consulta.cids = $("#cids").val();
	$.ajax({
		url: '/consulta/',
		method: 'post',
		data: consulta,
		beforeSend: function() {

		},
		success: function() {
			notificacao('Sucesso!', 'Consulta salva', 'top', 'right', 'success', 'withicon', '#', '');
			cardInfoCidadao(idAtendimento);
			verificaConsulta();
		},

		statusCode: {
			422: function(xhr) {
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val) {
					notificacao('Atenção!', val, 'top', 'right', 'danger', 'withicon', '#', '');
					$("input[name='" + key + "']").addClass("has-error has-feedback");
				})
			},
		},
		complete: function() {

		}
	})
})

function verificaConsulta() {
	$.ajax({
		url: '/consulta/verificar/' + idAtendimento,
		method: 'get',
		success: function(data) {
			$("#idConsulta").val(data.id);
			$("#historia-clinica").html(data.historiaClinica);
			$("#avaliacao").html(data.avaliacao);
			$("#idSinaisVitais").val(data.sinaisVitais.id);
			$("#sinaisVitais-pressaoSistolica").val(data.sinaisVitais.pressaoSistolica);
			$("#sinaisVitais-pressaoDiastolica").val(data.sinaisVitais.pressaoDiastolica);
			$("#sinaisVitais-frequenciaRespiratoria").val(data.sinaisVitais.frequenciaRespiratoria);
			$("#sinaisVitais-frequenciaCardiaca").val(data.sinaisVitais.frequenciaCardiaca);
			$("#sinaisVitais-temperaturaCorporal").val(data.sinaisVitais.temperaturaCorporal);
			$("#sinaisVitais-saturacaoOxigenio").val(data.sinaisVitais.saturacao);
			$("#sinaisVitais-glicemiaCapilar").val(data.sinaisVitais.glicemiaCapilar);
			$("#sinaisVitais-momentoColeta").find("option[value=" + data.sinaisVitais.momentoColeta + "]").attr("selected", true);
			$("#conduta").html(data.conduta);
			$("#diagnostico").val(data.diagnostico);
			$("#cids").val(data.cids);
			$("#form-consulta").each(function() {
				$(this).find('input, textarea, select').attr('disabled', true);
			})
			tinymce.get("historia-clinica").setMode('readonly');
			tinymce.get("avaliacao").setMode('readonly');
			tinymce.get("conduta").setMode('readonly');

		},

		statusCode: {
			404: function() {
				$("#card-action").empty().append("<button class='btn btn-primary'> Salvar consulta</button>");
			}
		}
	})
}

function editarConsulta() {
	$("#form-consulta").each(function() {
		$(this).find('input, textarea, select').attr('disabled', false);
	});
	tinymce.get("historia-clinica").setMode('design');
	tinymce.get("avaliacao").setMode('design');
	tinymce.get("conduta").setMode('design');
	$("#card-action").empty().append("<button class='btn btn-primary'> Salvar</button>");
}