$(document).ready(function() {
	atualizarTabela();
});

$("#atendimento-buscar").submit(function(evt) {
	evt.preventDefault();
	var params = {};
	params.data1 = $("#idData1").val();
	params.data2 = $("#idData2").val();
	$.ajax({
		url: '/atendimento/buscar',
		method: 'get',
		data: params,
		success: function(data) {
			$("#table-server").DataTable().destroy();
			$("#table-server").DataTable({
				responsive: true,
				paging: true,
				searching: false,
				ordering: false,
				data: data,
				columns: [
					{
						title: '',
						data: 'dataEntrada',
						visible: false,
					},
					{
						title: '',
						data: 'cidadao.sexo',
						mRender: function(data) {
							if (data == "F") {
								return "<i class='fa fa-female'></i>";
							} else if (data == "M") {
								return "<i class='fa fa-male'></i>";
							}
						}
					},
					{ title: 'NOME', data: 'cidadao.nome' },
					{ title: 'PROF. DE DESTINO', data: 'profissionalDestino.nome' },
					{
						title: 'SERVIÇO', data: 'tipoServicos',
						mRender: function(data) {
							var retorno = "";
							$.each(data, function(key, item) {
								retorno += createSpan(item.nome);
							})
							return retorno;
						}
					},
					{
						title: 'STATUS', data: 'status', className: "dt[-head|-body]-center", mRender: function(data) {
							if (data == "AGUARDANDOATENDIMENTO") {
								return "<i class='fa fa-circle aguardando-atendimento'></i>"
							}
							if (data == "EMATENDIMENTO") {
								return "<i class='fa fa-circle em-atendimento'></i>"
							}
							if (data == "NAOAGUARDOU") {
								return "<i class='fa fa-circle nao-aguardou-atendimento'></i>"
							}
							if (data == "OBSERVACAO") {
								return "<i class='fa fa-circle observacao'></i>"
							}
							if (data == "FINALIZADO") {
								return "<i class='fa fa-circle'></i>"
							}
						}
					},
					{
						title: 'AÇÕES', data: 'id', mRender: function(data) {
							var retorno = "<a class='btn btn-primary btn-sm' " + "href='/atendimento/editar/" + data + "'>Editar</a> " +
								"<a class='btn btn-primary btn-sm'" + "href='/atendimento/detalhar/" + data + "'>Detalhar</a>"
							return retorno;
						}
					}
				],
			})
		},
		statusCode: {
			400: function(xhr) {
				notificacao('Erro!', xhr.responseText, 'top', 'right', 'danger', 'withicon', '#', '');
			}
		}
	})
});


function atualizarTabela() {
	$("#table-server").DataTable({
		responsive: true,
		paging: true,
		searching: false,
		ordering: false,
		ajax: {
			url: "/atendimento/buscar-data",
			dataSrc: "",
		},
		columns: [
			{
				title: '',
				data: 'dataEntrada',
				visible: false,
			},
			{
				title: '',
				data: 'cidadao.sexo',
				mRender: function(data) {
					if (data == "F") {
						return "<i class='fa fa-female'></i>";
					} else if (data == "M") {
						return "<i class='fa fa-male'></i>";
					}
				}
			},
			{ title: 'NOME', data: 'cidadao.nome' },
			{ title: 'PROF. DE DESTINO', data: 'profissionalDestino.nome' },
			{
				title: 'SERVIÇO', data: 'tipoServicos',
				mRender: function(data) {
					var retorno = "";
					$.each(data, function(key, item) {
						retorno += createSpan(item.nome);
					})
					return retorno;
				}
			},
			{
				title: 'STATUS', data: 'status', className: "dt[-head|-body]-center", mRender: function(data) {
					if (data == "AGUARDANDOATENDIMENTO") {
						return "<i class='fa fa-circle aguardando-atendimento'></i>"
					}
					if (data == "EMATENDIMENTO") {
						return "<i class='fa fa-circle em-atendimento'></i>"
					}
					if (data == "NAOAGUARDOU") {
						return "<i class='fa fa-circle nao-aguardou-atendimento'></i>"
					}
					if (data == "OBSERVACAO") {
						return "<i class='fa fa-circle observacao'></i>"
					}
					if (data == "FINALIZADO") {
						return "<i class='fa fa-circle'></i>"
					}
				}
			},
			{
				title: 'AÇÕES', data: 'id', mRender: function(data) {
					var retorno = "<a class='btn btn-primary btn-sm' " + "href='/atendimento/editar/" + data + "'>Editar</a> " +
						"<a class='btn btn-primary btn-sm'" + "href='/atendimento/detalhar/" + data + "'>Detalhar</a>"
					return retorno;
				}
			}
		],
	});
}

function createSpan(item) {
	var abreSpan = "<span class='badge badge-info'>";
	var fechaSpan = "</span>";
	var span = abreSpan + item + fechaSpan;
	return span;

}
