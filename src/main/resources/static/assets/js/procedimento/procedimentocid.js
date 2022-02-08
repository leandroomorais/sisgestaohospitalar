
//######### Funções que verifica Procedimento Cid ###########

// adiciona cid ao procedimento
function adicionacidaoprocedimento(idProcedimentoCid) {

	$("#card-procedimento").fadeOut(100);
	$("#card-cid-procedimento").fadeIn(100);

	$.ajax({
		url: '/procedimentocid/buscarcidsdoprocedimento/'+ idProcedimentoCid,
		method: 'get',
		success: function (data) {
				
		$("#optionselect-cid").empty();
		$.each(data, function (key, item) {
			$("#optionselect-cid").append("<option value=" + item.id + "><div class='h6'>" 
			+ item.codigo + " - " + item.nome + "</div></option>");
		})
		
		console.log("aqui --- ",data);
		
		},
		statusCode: {
			400: function () {},
			404: function () {}
		}
	})
}


function salvarCidProcedimento(){
	console.log("cid - ", $("#optionselect-cid").val());
	fechaFormularioCidProcedimento();
}

$("#cid-procedimento-voltar").click(function () {
	fechaFormularioCidProcedimento();
})

function fechaFormularioCidProcedimento() {
	
	$("#card-cid-procedimento").fadeOut(100);
	$("#card-procedimento").fadeIn(100);
}

// verifica se o procedimento é obrigatório cid e se o cid não foi informado
function verificaProcedimentoCid(codigoProcedimento) {

	$.ajax({
		url: '/procedimentocid/buscarcodigoprocedimentocid/'+ codigoProcedimento,
		method: 'get',
		success: function (data) {
		adicionarListaCidSelect(data);		
		console.log("aqui --- ",data);
			//$("#divSelecionaCid").empty().append(createCardModalSelecionaCid(data));
			$("#modalSelecionaCid").modal("show");
		},
		statusCode: {
			400: function () {},
			404: function () {}
		}
	})
}



//Função autocomplete CID 
$("#procedimento-cid").autocomplete({
	source: "/procedimentocid/buscarcidselect",
	focus: function (event, ui) {
		$("#procedimento-cid").val(ui.item.codigo + " - " + ui.item.nome);
		return false;
	},
	select: function (event, ui) {
		$("#i-procedimento-cid").removeClass("fa fa-search").addClass("fa fa-times");
		$("#id-procedimento-cid").val(ui.item.id);
		console.log("cid", ui.item.nome);
		return false;

	}	
}).autocomplete("instance")._renderItem = function (ul, item) {
	return $("<li>")
		.append("<div class='h6'>" + item.codigo + " - " + "<b>" + item.nome + "</b>" + "</div>")
		.appendTo(ul);
};

function limpaInputCid(){
	$("#procedimento-cid").val("");
	$("#id-procedimento-cid").val("");
	$("#i-procedimento-cid").removeClass("fa fa-times").addClass("fa fa-search");
	return false;
}


function createCardModalSelecionaCid(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-12'>" +
		"<strong>Selecionar Cid: </strong>" +
		//"<br>" + descricaoResultadoExame(data) +
		"</div><div class='col-md-12 row'><div class='col-md-6'><strong>Realizado: </strong>" +
		//dataRealizacaoResultadoExame(data)
		 "</div><div class='col-md-6'> <strong>Resultado: </strong>" +
		//dataResultadoResultadoExame(data)
		 "</div></div></div></div></div>";
}


function verificaCompatilidadeProcedimentoCid(codigoProcedimento) {

	$.ajax({
		url: '/procedimentocid/buscarcodigoprocedimentocid/'+ codigoProcedimento,
		method: 'get',
		success: function () {
			swal("Atenção! Esse procedimento requer a adição de um CID compatível, por favor selecionar em seguida!", {
				icon: "success",
				buttons: {
					confirm: {
						className: 'btn btn-success'
					}
				}
			});
		},
		statusCode: {
			400: function () {
				
			},
			404: function () {
				
			}
		}
	})
}
