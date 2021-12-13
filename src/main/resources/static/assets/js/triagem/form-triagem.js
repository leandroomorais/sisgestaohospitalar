var idAtendimento;
var idProntuario;
var idSinaisVitais;

//JS Form Triagem
$(document).ready(function() {
	idAtendimento = $("#id-atendimento").val();
	idProntuario = $("#id-prontuario").val();

	verificaTriagem();

	ocultarAlergia();
	ocultarDoenca();

	$("#sinaisVitais-pressaoSistolica").mask('000');
	$("#sinaisVitais-pressaoDiastolica").mask('000');
	$("#sinaisVitais-frequenciaRespiratoria").mask("000");
	$("#sinaisVitais-frequenciaCardiaca").mask("000");
	$("#sinaisVitais-temperaturaCorporal").mask("00.0");
	$("#sinaisVitais-glicemiaCapilar").mask("000");
	$("#sinaisVitais-saturacaoOxigenio").mask("000");

	$("#conduta-cidadao").hide();


	//Função que inicia o TinyMCE
	tinymce.init({
		selector: '#motivo, #nota, #nota-dto',
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

	//Chamada da função 
	atualizaAlergia();

	//Chamada da função 
	atualizaDoenca();

	//Chamada da função 
	atulizaMedicamentoUsoContinuo();

	//Chamada da Função
	atulizaMedicamentoEmUso();

	//Chamada da Função
	atualizaProcedimento();

	atualizaAntropometria();

	cardInfoCidadao(idAtendimento);

});

//######### Funções do Formulário da Triagem ###########

//Submit do Formulário Triagem
$("#form-triagem").submit(function(evt) {
	//Bloqueia o comportamento padrão do submit
	evt.preventDefault();

	var triagem = {};

	triagem.id = $("#idTriagem").val();
	triagem.motivo = tinymce.get("motivo").getContent();
	triagem.inicioTriagem = $("#inicioTriagem").val();
	triagem['classificacaoDeRisco.id'] = $("input[name='classificacaoDeRisco']:checked").val();
	triagem['sinaisVitais'] = $("#idSinaisVitais").val();
	triagem['sinaisVitais.pressaoSistolica'] = $("#sinaisVitais-pressaoSistolica").val();
	triagem['sinaisVitais.pressaoDiastolica'] = $("#sinaisVitais-pressaoDiastolica").val();
	triagem['sinaisVitais.temperaturaCorporal'] = $("#sinaisVitais-temperaturaCorporal").val();
	triagem['sinaisVitais.frequenciaCardiaca'] = $("#sinaisVitais-frequenciaCardiaca").val();
	triagem['sinaisVitais.saturacao'] = $("#sinaisVitais-saturacaoOxigenio").val();
	triagem['sinaisVitais.frequenciaRespiratoria'] = $("#sinaisVitais-frequenciaRespiratoria").val();
	triagem['sinaisVitais.glicemiaCapilar'] = $("#sinaisVitais-glicemiaCapilar").val();
	triagem['sinaisVitais.momentoColeta'] = $("#sinaisVitais-momentoColeta option:selected").val();
	triagem['atendimento'] = idAtendimento;

	console.log(triagem);

	$.ajax({
		url: "/triagem/salvar",
		method: "POST",
		data: triagem,
		beforeSend: function() {

		},
		success: function() {
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'A Triagem foi salva',
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
			verificaTriagem();
			cardInfoCidadao(idAtendimento);
		},

		statusCode: {
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

					$("input[name='" + key + "']").parent().parent().parent().addClass("has-error has-feedback");

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


});
//Fim do submit formulário triagem


//######### Fim das Funções do Formulário da Triagem ###########



//######### Funções dos Procedimentos ###########

//Funçao que adiciona Procedimentos automaticamente
$("#sinaisVitais-pressaoDiastolica").change(function() {
	var pressaoSistolica = $("#sinaisVitais-pressaoSistolica").val();
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimento(idAtendimento, 301100039, tipoServico, quantidade);
})

$("#sinaisVitais-glicemiaCapilar").change(function() {
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimento(idAtendimento, 214010015, tipoServico, quantidade);
})




//Função para atualizar checkbox Habitos
function atualizarHabitos(element) {
	element.empty();
	$.getJSON('/habito/listar', function(data) {
		$.each(data, function(key, item) {
			element.append("<div class='custom-control custom-checkbox'> <input type = 'checkbox' value ='" + item.id + "' name = '" + "habitos" + "' class= 'custom-control-input' id='habitos" + item.id + "'><label for='habitos" + item.id + "' class='custom-control-label'>" + item.nome + "</label></div>");
		});
	});
}
//Fim da função

//Função para atualizar checkbox comorbidades
function atualizarComorbidades(element) {
	$.getJSON('/comorbidade/listar', function(data) {
		$.each(data, function(key, item) {
			element.append("<div class='custom-control custom-checkbox'> <input type = 'checkbox' value ='" + item.id + "' name = '" + "comorbidades" + "' class= 'custom-control-input' id='comorbidades" + item.id + "'><label for='comorbidades" + item.id + "' class='custom-control-label' data-togle='tooltip' data-placement='top' title='" + item.descricao + "'>" + item.nome + "</label></div>");
		});
	});
}
//Fim da função




function verificaTriagem() {
	$.ajax({
		url: '/triagem/verificar/' + idAtendimento,
		method: 'get',
		success: function(data) {
			$("#idTriagem").val(data.id);
			if (data.sinaisVitais.id != null) {
				$("#idSinaisVitais").val(data.sinaisVitais.id);
			}
			$("#inicioTriagem").val(data.inicioTriagem);
			$("#motivo").html(data.motivo);
			$("#sinaisVitais-pressaoSistolica").val(data.sinaisVitais.pressaoSistolica);
			$("#sinaisVitais-pressaoDiastolica").val(data.sinaisVitais.pressaoDiastolica);
			$("#sinaisVitais-frequenciaRespiratoria").val(data.sinaisVitais.frequenciaRespiratoria);
			$("#sinaisVitais-frequenciaCardiaca").val(data.sinaisVitais.frequenciaCardiaca);
			$("#sinaisVitais-temperaturaCorporal").val(data.sinaisVitais.temperaturaCorporal);
			$("#sinaisVitais-saturacaoOxigenio").val(data.sinaisVitais.saturacao);
			$("#sinaisVitais-glicemiaCapilar").val(data.sinaisVitais.glicemiaCapilar);
			$("#sinaisVitais-momentoColeta").find("option[value=" + data.sinaisVitais.momentoColeta + "]").attr("selected", true);
			$("input:radio[name=classificacaoDeRisco][value= " + data.classificacaoDeRisco.id + " ]").attr('checked', true);
			$("input:radio[name=classificacaoDeRisco]").attr('disabled', true);
			$("#form-triagem").each(function() {
				$(this).find('input, textarea, select').attr('disabled', true);
			});
			tinymce.get("motivo").setMode('readonly');
			$("#card-action").empty().append("<button type = 'button' onclick='editarTriagem()' class='btn btn-secondary'> Editar triagem </button>");
		},

		statusCode: {
			400: function() {
				$("#card-action").empty().append("<button type='submit' class='btn btn-primary'> Salvar triagem</button>");
			}
		}
	})
}

function editarTriagem() {
	$("#form-triagem").each(function() {
		$(this).find('input, textarea, select').attr('disabled', false);
	});
	tinymce.get("motivo").setMode('design');


	$("#card-action").empty().append("<button type='submit' class='btn btn-primary'> Salvar triagem</button>");
}