//######### Funções para Cadastro de Medicamentos ###########

//Funções atualizar medicamentos
function atulizaMedicamentoUsoContinuo() {
	$("#uso-continuo-lista").empty();
	$.ajax({
		url: '/medicamento-continuo/medicamentos/' + idProntuario,
		method: 'GET',
		success: function(data) {
			if ($.isEmptyObject(data)) {
				$("#uso-continuo-lista").append("<li class='list-group-item'> Sem dados para serem exibidos </li>")
			}
			$.each(data, function(index, value) {
				$("#uso-continuo-lista").append("<li class='list-group-item'>" + value.medicamento.principioAtivo + " ; " + value.medicamento.concentracao + " ; " + value.medicamento.formaFarmaceutica.nome + " | " + value.nota + "</li>");
			})
		},

		error: function() {
			$("#uso-lista").append("<li class='list-group-item'> Sem dados para serem exibidos - Houve um erro ao recuperar as informações </li>")
		}
	})
}

function atulizaMedicamentoEmUso() {
	var atendimentoId = $("#id-atendimento").val();
	$("#uso-lista").empty();
	$.ajax({
		url: '/uso-medicamento/medicamentos/' + atendimentoId,
		method: 'GET',
		success: function(data) {
			if ($.isEmptyObject(data)) {
				$("#uso-lista").append("<li class='list-group-item'> Sem dados para serem exibidos </li>")
			}
			$.each(data, function(index, value) {
				$("#uso-lista").append("<li class='list-group-item'>" + value.medicamento.principioAtivo + " ; " + value.medicamento.concentracao + " ; " + value.medicamento.formaFarmaceutica.nome + " | " + value.nota + "</li>");
			})
		},

		error: function() {
			$("#uso-lista").append("<li class='list-group-item'> Sem dados para serem exibidos - Houve um erro ao recuperar as informações </li>")
		}
	})
}

//Função Habilita pesquisa de Medicamentos
$("#button-medicamento").click(function() {
	$("#i-medicamento").removeClass().addClass("fa fa-search");
	$("#medicamento").val("").attr("disabled", false);
})
//Fim da função habilita pesquisa de Medicamentos




//Função autocomplete Medicamentos
$("#medicamento").autocomplete({
	source: "/medicamento/buscar",
	focus: function(event, ui) {
		$("#medicamento").val(ui.item.principioAtivo + " ; " + ui.item.concentracao + " ; " + ui.item.formaFarmaceutica.nome);
		return false;
	},
	select: function(event, ui) {
		$("#i-medicamento").removeClass("fa fa-search").addClass("fa fa-times");
		$("#medicamento").val(ui.item.principioAtivo + " ; " + ui.item.concentracao + " ; " + ui.item.formaFarmaceutica.nome).attr("disabled", true);
		$("#id-medicamento").val(ui.item.id);
		return false;

	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.principioAtivo + " ; " + "<b>" + item.concentracao + "</b>" + "<br>" + item.formaFarmaceutica.nome + "</div>")
		.appendTo(ul);
};

//Fim da função autocomplete Medicamentos

//Submit dos medicamentos
$("#submit-uso-medicamento").click(function(evt) {
	evt.preventDefault();
	var usoMedicamento = {};
	usoMedicamento['medicamento.id'] = $("#id-medicamento").val();
	usoMedicamento.nota = $("#nota-uso-medicamento").val();
	usoMedicamento.usoContinuo = $("#usoContinuo").prop("checked");
	usoMedicamento.idProntuario = idProntuario;
	usoMedicamento.idAtendimento = idAtendimeto;

	$.ajax({
		url: "/uso-medicamento/salvar",
		method: "POST",
		data: usoMedicamento,

		success: function() {
			$("#id-medicamento").val("");
			$("#nota-uso-medicamento").val("");
			$("#usoContinuo").prop("checked", false);
			$("#medicamento").val("");
			$("#i-medicamento").removeClass().addClass("fa fa-search");
			$("#medicamento").val("").attr("disabled", false);
			atulizaMedicamentoEmUso();
			atulizaMedicamentoUsoContinuo();
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'Medicamento em uso salvo',
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

		error: function() {
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
//Fim submit dos medicamentos
//######### Fim das Funções para Cadastro de Medicamentos ###########