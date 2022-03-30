//######### Funções para Cadastro de Medicamentos ###########

var idAtendimento = $("#id-atendimento").val();
var idProntuario = $("#id-prontuario").val();


//Função autocomplete Medicamentos
$("#medicamento-uso").autocomplete({
	maxShowItems: 5,
	source: "/medicamento/buscar",
	select: function(event, ui) {
		$("#i-medicamento-uso").removeClass("fa fa-search").addClass("fa fa-times");
		$(this).val(ui.item.principioAtivo + " ; " + ui.item.concentracao + " ; " + ui.item.formaFarmaceutica.nome).attr("disabled", true);
		$("#id-medicamento-uso").val(ui.item.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.principioAtivo + " ; " + "<b>" + item.concentracao + "</b>" + "<br>" + item.formaFarmaceutica.nome + "</div>")
		.appendTo(ul);
};

//Fim da função autocomplete Medicamentos

//Submit dos medicamentos
$("#form-uso-medicamento").submit(function(evt) {
	evt.preventDefault();
	var usoMedicamento = {};
	usoMedicamento['medicamento.id'] = $("#id-medicamento-uso").val();
	usoMedicamento.nota = $("#nota-uso-medicamento").val();
	usoMedicamento.usoContinuo = $("#usoContinuo").prop("checked");
	usoMedicamento.idProntuario = idProntuario;
	usoMedicamento.idAtendimento = idAtendimento;

	$.ajax({
		url: "/uso-medicamento/salvar",
		method: "POST",
		data: usoMedicamento,
		success: function() {
			limpaFormulario();
			atulizaMedicamentoEmUso();
			atulizaMedicamentoUsoContinuo();
			notificacao('Sucesso!', 'O medicamento em uso foi salvo', 'top', 'right', 'success', 'withicon', '#', '');
		},

		error: function() {
			notificacao('Atenção!', 'Não foi possível processar a solicitação', 'top', 'right', 'danger', 'withicon', '#', '');
		}
	})
})

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
	$("#uso-lista").empty();
	$.ajax({
		url: '/uso-medicamento/medicamentos/' + idAtendimento,
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
$("#button-medicamento-uso").click(function() {
	limpaFormulario();
	$("#i-medicamento-uso").removeClass().addClass("fa fa-search");
	$("#medicamento-uso").val("").attr("disabled", false);
})
//Fim da função habilita pesquisa de Medicamentos
//Fim submit dos medicamentos
//######### Fim das Funções para Cadastro de Medicamentos ###########

function limpaFormulario() {
	$("#id-medicamento-uso").val("");
	$("#nota-uso-medicamento").val("");
	$("#usoContinuo").prop("checked", false).parent().removeClass().addClass("toggle btn btn-black off");
	$("#medicamento-uso").val("");
	$("#i-medicamento-uso").removeClass().addClass("fa fa-search");
	$("#medicamento-uso").val("").attr("disabled", false);
}