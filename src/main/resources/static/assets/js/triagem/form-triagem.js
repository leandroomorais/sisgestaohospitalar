//JS Form Triagem
$(document).ready(function() {

	$("#conduta-cidadao").hide();
	$("#card-nova-alergia").hide();
	$("#card-edit-alergia").hide();
	$("#card-nova-doenca").hide();
	$("#card-edit-doenca").hide();

	//Função que aplica máscara aos inputs 
	$("#sinaisVitais-pressaoArterial").keydown(function() {
		var pressao = $("#sinaisVitais-pressaoArterial").val();
		if (pressao < 5) {
			$("#sinaisVitais-pressaoArterial").mask("000/00");
		}
		else {
			$("#sinaisVitais-pressaoArterial").mask("000/000");
		}
	});
	$("#sinaisVitais-frequenciaRespiratoria").mask("000");
	$("#sinaisVitais-frequenciaCardiaca").mask("000");
	$("#sinaisVitais-temperaturaCorporal").mask("00.0");
	$("#sinaisVitais-glicemiaCapilar").mask("000");
	$("#sinaisVitais-saturacaoOxigenio").mask("000");
	//Fim da função

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

	verificaTriagem();

	atualizaAntropometria()

});

//######### Funções do Formulário da Triagem ###########

var idAtendimeto = $("#atendimento-id").val();
var idProntuario = $("#id-prontuario").val();


//Submit do Formulário Triagem
$("#form-triagem").submit(function(evt) {
	//Bloqueia o comportamento padrão do submit
	evt.preventDefault();

	var triagem = {};

	triagem.id = $("#idTriagem").val();
	triagem.motivo = tinymce.get("motivo").getContent();
	triagem.inicioTriagem = $("#inicioTriagem").val();
	triagem.classificacaoDeRisco = $("input[name='classificacaoDeRisco']:checked").val();
	triagem['sinaisVitais'] = $("#idSinaisVitais").val();
	triagem['sinaisVitais.pressaoArterial'] = $("#sinaisVitais-pressaoArterial").val();
	triagem['sinaisVitais.temperaturaCorporal'] = $("#sinaisVitais-temperaturaCorporal").val();
	triagem['sinaisVitais.frequenciaCardiaca'] = $("#sinaisVitais-frequenciaCardiaca").val();
	triagem['sinaisVitais.saturacao'] = $("#sinaisVitais-saturacaoOxigenio").val();
	triagem['sinaisVitais.frequenciaRespiratoria'] = $("#sinaisVitais-frequenciaRespiratoria").val();
	triagem['sinaisVitais.glicemiaCapilar'] = $("#sinaisVitais-glicemiaCapilar").val();
	triagem['sinaisVitais.momentoColeta'] = $("#sinaisVitais-momentoColeta option:selected").val();

	triagem['atendimento'] = idAtendimeto;

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
$("#sinaisVitais-pressaoArterial").change(function() {
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimento(idAtendimeto, 301100039, tipoServico, quantidade);
})

$("#sinaisVitais-glicemiaCapilar").change(function() {
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimento(idAtendimento, 214010015, tipoServico, quantidade);
})

//Função que adiciona procedimentos
//Autocomplete Procedimentos

$("#button-procedimento").click(function() {
	$("#i-procedimento").removeClass().addClass("fa fa-search");
	limpaInputsProcedimento();
})

$("#submit-procedimento").click(function() {
	var codigoProcedimento = $("#id-procedimento").val();
	var tipoServico = "TRIAGEM";
	var quantidade = $("#qtd-procedimento").val();
	submitProcedimento(idAtendimeto, codigoProcedimento, tipoServico, quantidade);
})

//Fim da função
//######### Fim das Funções dos Procedimentos ###########


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


//Função que calcula o IMC
$("#altura").change(function() {
	var peso = $("#peso").val();
	var altura = $("#altura").val();
	var imc = (peso / (altura * altura)) * 10000;
	$("#imc").text(imc.toFixed(2));
	$("#valor-imc").val(imc.toFixed(2));
	if (imc < 17) {
		$("#imc-desc").text("Muito abaixo do peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 17 && imc < 18.49) {
		$("#imc-desc").text("Abaixo do Peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-warning");
	} else if (imc > 18.5 && imc < 24.99) {
		$("#imc-desc").text("Normal");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-success");
	} else if (imc > 25 && imc < 29.99) {
		$("#imc-desc").text("Acima do Peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-warning");
	} else if (imc > 30 && imc < 34.99) {
		$("#imc-desc").text("Obesidade I");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 35 && imc < 39.99) {
		$("#imc-desc").text("Obesidade II");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 40) {
		$("#imc-desc").text("Obesidade III");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	}
});
//Fim da função

function verificaTriagem() {
	$.ajax({
		url: '/triagem/verificar/' + idAtendimeto,
		method: 'get',
		success: function(data) {
			$("#idTriagem").val(data.id);
			$("#idSinaisVitais").val(data.sinaisVitais.id);
			$("#inicioTriagem").val(data.inicioTriagem);
			tinymce.get('motivo').setContent(data.motivo);
			$("#sinaisVitais-pressaoArterial").val(data.sinaisVitais.pressaoArterial);
			$("#sinaisVitais-frequenciaRespiratoria").val(data.sinaisVitais.frequenciaRespiratoria);
			$("#sinaisVitais-frequenciaCardiaca").val(data.sinaisVitais.frequenciaCardiaca);
			$("#sinaisVitais-temperaturaCorporal").val(data.sinaisVitais.temperaturaCorporal);
			$("#sinaisVitais-saturacaoOxigenio").val(data.sinaisVitais.saturacao);
			$("#sinaisVitais-glicemiaCapilar").val(data.sinaisVitais.glicemiaCapilar);
			$("#sinaisVitais-momentoColeta").find("option[value=" + data.sinaisVitais.momentoColeta + "]").attr("selected", true);
			$("input:radio[name=classificacaoDeRisco][value= " + data.classificacaoDeRisco + " ]").attr('checked', true);
			$("input:radio[name=classificacaoDeRisco]").attr('disabled', true);
			$('#div-form-triagem').find('input, textarea, select').attr('disabled', true);
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
	$('#div-form-triagem').find('input, textarea, select').attr('disabled', false);
	$("#card-action").empty().append("<button type='submit' class='btn btn-primary'> Salvar triagem</button>");
}