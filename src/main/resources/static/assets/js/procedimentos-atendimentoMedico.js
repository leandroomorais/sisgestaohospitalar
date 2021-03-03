//Funções para o autocomplete dos Procedimentos

//Variável que guarda os Ids dos Procedimentos adicionados;
//var idProcedimentos = [];

var cpf = $("#cpfCidadao").val();
var div = $('<div class="col-md-4"><strong>Principio ativo: </strong><p id="principioAtivo"></p></div><div class="col-md-3"><strong>Forma: </strong><p id="forma"></p></div><div class="col-md-3"><strong>Concentração: </strong><p id="concentracao"></p></div><div class="col-md-4"><strong>Posologia: </strong><p id="posologia"></p></div><div class="col-md-4"><strong>Data e hora prescrição: </strong><p id="prescricao"></p></div><div class="col-md-6"><strong>Orientações: </strong><p id="orientacao"></p></div>');
var divExames = $('<div class="col-md-4"><strong>Principio ativo: </strong><p id="principioAtivo"></p></div><div class="col-md-3"><strong>Forma: </strong><p id="forma"></p></div><div class="col-md-3"><strong>Concentração: </strong><p id="concentracao"></p></div><div class="col-md-4"><strong>Posologia: </strong><p id="posologia"></p></div><div class="col-md-4"><strong>Data e hora prescrição: </strong><p id="prescricao"></p></div><div class="col-md-6"><strong>Orientações: </strong><p id="orientacao"></p></div>');



$("#medicamentosPrescritos").click(function () {
	console.log(cpf);
	$.ajax({
		url: "http://localhost:9090/api/receitamedicamentos/cpf/" + cpf,
		success: function (data) {
			var i = 0;
			$.each(data, function () {

				$("#conteudo").append(div);
				$("#principioAtivo").text(data[i].medicamento.principio_ativo);
				$("#forma").text(data[i].medicamento.formaFarmaceutica.nomeFormaFarmaceutica);
				$("#concentracao").text(data[i].medicamento.concentracao);
				$("#posologia").text(data[i].posologia);
				var dataPrescicao = data[i].atendimentoProfissional.dataFim;
				var ano = dataPrescicao.substring(0,4);
				var mes = dataPrescicao.substring(5,7);
				var dia = dataPrescicao.substring(8,10);
				var hora = dataPrescicao.substring(12,19);
				$("#prescricao").text(dia +"-" + mes + "-" + ano + " | " + hora);
				$("#orientacao").text(data[i].recomendacao);
				i++;
			})


		},

	})
});

$("#examesSolicitados").click(function () {
	console.log(cpf);
	$.ajax({
		url: "http://localhost:9090/api/receitamedicamentos/cpf/" + cpf,
		success: function (data) {
			var i = 0;
			$.each(data, function () {

				$("#conteudo").append(div);
				$("#principioAtivo").text(data[i].medicamento.principio_ativo);
				$("#forma").text(data[i].medicamento.formaFarmaceutica.nomeFormaFarmaceutica);
				$("#concentracao").text(data[i].medicamento.concentracao);
				$("#posologia").text(data[i].posologia);
				var dataPrescicao = data[i].atendimentoProfissional.dataFim;
				var ano = dataPrescicao.substring(0,4);
				var mes = dataPrescicao.substring(5,7);
				var dia = dataPrescicao.substring(8,10);
				var hora = dataPrescicao.substring(12,19);
				$("#prescricao").text(dia +"-" + mes + "-" + ano + " | " + hora);
				$("#orientacao").text(data[i].recomendacao);
				i++;
			})


		},

	})
});

$(document).ready(function () {
	// Autocomplete dos Procedimentos
	$(function () {
		$("#procedimentos-atendimento-medico")
			.on("keydown", function (event) {
				$(this).autocomplete("instance")._renderItem = function (select, item) {
					return $("<option>").append("<div>"
						+ item.codigoprocedimento
						+ " - "
						+ item.nomeprocedimento
						+ "</div>").appendTo(select);
				};
			})
			.autocomplete({
				source: "/procedimento-resource/search",
				focus: function (event, ui) {
					$("#procedimentos-atendimento-medico").val(ui.item.nomeprocedimento);
					return false;
				},
				select: function (event, ui) {
					console.log("Id do Procedimento: " + ui.item.id);
					var procedimento = {};
					procedimento.id = ui.item.id;
					$.ajax({
						method: "POST",
						url: "/atendimento-medico/add-procedimento",
						data: procedimento,
						success: function () {
							console.log('Enviado com sucesso');
						},
						error: function (xhr) {
							console.log('Erro >', xhr.reponseText);
						},
					})

					$("#procedimentos-atendimento-medico").val("");

					$("#table-procedimento-atendimento-medico").append('<tr id="'
						+ ui.item.id
						+ '"><td>'
						+ ui.item.codigoprocedimento
						+ '</td>'
						+ '<td>'
						+ ui.item.nomeprocedimento
						+ '</td>'
						+ '<td>'
						+ '<button type="button" onclick="removeProcedimentoAtendimentoMedico(this)" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button>'
						+ '</td>'
						+ '</tr>');

					return false;


				}
			});
	});

	// Função para remover os Procedimentos da Tabela de
	// Procedimentos Realizados e do Array de Procedimentos
	(function ($) {
		var procedimento = {};
		removeProcedimentoAtendimentoMedico = function (item) {
			swal({
				title: 'Tem certeza que deseja remover esse procedimento?',
				type: 'warning',
				buttons: {
					cancel: {
						visible: true,
						text: 'Não, cancelar!',
						className: 'btn btn-danger'
					},
					confirm: {
						text: 'Sim, remova!',
						className: 'btn btn-success'
					}
				}
			}).then((willDelete) => {
				if (willDelete) {
					var tr = $(item).closest('tr');
					var id = $(item).parent().parent().attr("id");
					procedimento.id = id;
					$.ajax({
						method: "DELETE",
						url: "/atendimento-medico/delete-procedimento",
						data: procedimento,
						success: function () {
							console.log('Deletado com sucesso');
						},
						error: function (xhr) {
							console.log('Erro >', xhr.reponseText);
						},
					});

					tr.fadeOut(400, function () {
						tr.remove();
					});
					swal("Procedimento removido com sucesso!", {
						icon: "success",
						buttons: {
							confirm: {
								className: 'btn btn-success'
							}
						}
					});
				}
			});


			return false;
		}
	})(jQuery);
})
