
//######### Funções que verifica Procedimento Cid ###########

var idAtendimentoProcedimento;

// adiciona cid ao procedimento
function adicionacidaoprocedimento(idProcedimentoCid) {

	idAtendimentoProcedimento = idProcedimentoCid;
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
		},
		statusCode: {
			400: function () {
			$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: 'Não foi possível processar sua solicitação!',
					target: '_blank'
				}, {
					// settings
					element: 'body',
					type: "danger",
					allow_dismiss: true,
				});
			},
			404: function () {
			$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: 'Não foi possível processar sua solicitação!',
					target: '_blank'
				}, {
					// settings
					element: 'body',
					type: "danger",
					allow_dismiss: true,
				});
			}
		}
	})
}


function adicionarCidAoAtendimentoProcedimento(){

	var idCid = $("#optionselect-cid").val();
	
	$.ajax({
		url: '/atendimento-procedimento/adicionarcidaoatendprocedimento/'+ idAtendimentoProcedimento +'/'+ idCid,
		method: 'get',
		success: function () {
		$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O CID foi Adicionado ao Procedimento',
				target: '_blank'
			}, {
				// settings
				element: 'body',
				type: "success",
				allow_dismiss: true,
			});
			fechaFormularioCidProcedimento();
		},
		statusCode: {
			400: function () {
			$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: 'Não foi possível processar sua solicitação!',
					target: '_blank'
				}, {
					// settings
					element: 'body',
					type: "danger",
					allow_dismiss: true,
				});
			},
			404: function () {
			$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ERRO',
					message: 'Não foi possível processar sua solicitação!',
					target: '_blank'
				}, {
					// settings
					element: 'body',
					type: "danger",
					allow_dismiss: true,
				});
			}
		}
	})
}

$("#cid-procedimento-voltar").click(function () {
	fechaFormularioCidProcedimento();
})

function fechaFormularioCidProcedimento() {
	
	idAtendimentoProcedimento = null;
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
