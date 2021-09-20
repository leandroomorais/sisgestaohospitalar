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
		destroy: true,
		lengthMenu: [10, 15, 20, 25],
		ajax: {
			url: "/triagem/datatables/server",
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
				title: 'SERVIÇO', data: 'tipoServicos',
				mRender: function(data) {
					var retorno = "";
					$.each(data,function(key,item){
						retorno += createSpan(item.nome);
					})
					return retorno;
				}
			},
			{
				title: 'STATUS', data: 'status', mRender: function(data) {
					if (data == "AGUARDANDOATENDIMENTO") {
						return "<i class='fa fa-circle aguardando-atendimento'></i>"
					}
					if (data == "EMATENDIMENTO") {
						return "<i class='fa fa-circle em-atendimento'></i>"
					}
					if (data = "OBSERVACAO") {
						return "<i class='fa fa-circle observacao'></i>"
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
					var retorno = "<a class='btn btn-primary btn-sm' " + "href='/triagem/adicionar/" + data + "'><i class='fa fa-user-md'></i> Atender</a>"
					return retorno;
				}
			}
		],
	});
}

function createSpan(item){
	var abreSpan = "<span class='badge badge-info'>";
	var fechaSpan = "</span>";
	var span = abreSpan + item + fechaSpan;
	return span;
	
}