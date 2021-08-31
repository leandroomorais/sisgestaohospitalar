$(document).ready(function() {
	atualizarTabela();
});

$("#btn-atualizar-tabela").click(function() {
	$("#table-server").DataTable().ajax.reload();
})


function atualizarTabela() {
	$("#table-server").DataTable({
		processing: true,
		serverSide: true,
		responsive: true,
		lengthMenu: [10, 15, 20, 25],
		ajax: {
			url: "/atendimento/datatables/server",
			data: "data",
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
				title: 'SERVIÇO', data: 'condutaTipoServico', mRender: function(data) {
					if (data == "TRIAGEM") {
						return "<span class='badge badge-info'>Triagem</span>"
					}
					if (data == "CONSULTA") {
						return "<span class='badge badge-info'>Consulta</span>"
					}
					if (data = "ADMINMEDICAMENTOS") {
						return "<span class='badge badge-info'>Medicamentos</span>"
					}
					if (data = "CURATIVO") {
						return "<span class='badge badge-info'>Curativo</span>"
					}
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
					if (data = "NAOAGUARDOU") {
						return "<i class='fa fa-circle nao-aguardou-atendimento'></i>"
					}
					if (data = "FINALIZADO") {
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