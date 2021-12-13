$(document).ready(function() {
	atualizarTabela();
});

$("#btn-atualizar-tabela").click(function() {
	$("#table-server-risco").DataTable().ajax.reload();
})


function atualizarTabela() {
	$("#table-server-risco").DataTable({
		processing: true,
		serverSide: true,
		responsive: true,
		lengthMenu: [5, 10, 15, 20],
		ajax: {
			url: "/atendimento/datatables-risco/server",
			data: "data",
		},
		columns: [
			{
				title: '',
				orderable: false,
				visible: false,
				data: 'classificacaoDeRisco.prioridade',
			},
			{
				title: '',
				orderable: false,
				data: 'cidadao.sexo',
				mRender: function(data) {
					if (data == "F") {
						return "<i class='fa fa-female'></i>";
					} else if (data == "M") {
						return "<i class='fa fa-male'></i>";
					}
				}
			},
			{
				title: 'NOME',
				orderable: false,
				data: 'cidadao.nome'
			},
			{
				title: 'PROF. DE DESTINO',
				orderable: false,
				data: 'profissionalDestino.nome'
			},
			{
				title: 'SERVIÇO', data: 'tipoServicos',
				orderable: false,
				mRender: function(data) {
					var retorno = "";
					$.each(data, function(key, item) {
						retorno += createSpan(item.nome);
					})
					return retorno;
				}
			},
			{
				title: 'STATUS', data: 'status',
				orderable: false,
				className: "dt[-head|-body]-center", mRender: function(data) {
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
				title: 'C. RISCO',
				orderable: false,
				data: 'classificacaoDeRisco.nome', mRender: function(data) {
					console.log(data);
					if (data != null) {
						if (data === "VERMELHO") {
							return "<span class='badge badge-danger'>Vermelho</span>";
						}
						if (data === "LARANJA") {
							return "<span class='badge badge-warning'>Laranja</span>";
						}
						if (data === "AMARELO") {
							return "<span class='badge badge-amarelo'>Amarelo</span>";
						}
						if (data === "VERDE") {
							return "<span class='badge badge-success'>Verde</span>";
						}
						if (data === "AZUL") {
							return "<span class='badge badge-primary'>Azul</span>";
						}
						if (data === "NÃO INFORMADA") {
							return "<span class='badge badge-info'>Não informado</span>";
						}
					} else {
						return "<span class='badge badge-info'>Não informado</span>";
					}
				}
			},
			{
				title: 'AÇÕES',
				orderable: false,
				data: 'id', mRender: function(data) {
					var retorno = "<a class='btn btn-primary btn-sm' " + "href='/atendimento/atender/" + data + "'><i class='fa fa-user-md'></i> Atender</a>"
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
