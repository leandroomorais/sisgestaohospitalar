
//######### Funções que verifica Procedimento Cid ###########

var idAtendimentoProcedimentoGlobal;

// adiciona cid ao procedimento
function adicionacidaoprocedimento(idProcedimentoCid) {

	idAtendimentoProcedimentoGlobal = idProcedimentoCid;
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
			403: function(xhr) {
				$.notify({
					// options
					icon: 'flaticon-exclamation',
					title: 'ATENÇÃO',
					message: xhr.responseText,
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
				fechaFormularioCidProcedimento();
			},
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

function visualizaciddoprocedimento(idAtendimentoProcedimento){
	
	$.ajax({
		url: '/atendimento-procedimento/buscarporid/'+ idAtendimentoProcedimento,
		method: 'get',
		success: function (data) {
			swal("Ok! Detalhes do CID relacionado ao Procedimento: "+ data.codigo +" - "+ data.nome, {
				icon: "success",
				buttons: {
					confirm: {
						className: 'btn btn-primary'
					}
				},
				timer: 3000
			});
		},
		statusCode: {
			403: function () {
				adicionacidaoprocedimento(idAtendimentoProcedimento);
			},
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
				})				
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

function salvarCidAoAtendimentoProcedimento(){

	var idCid = $("#optionselect-cid").val();
	
	$.ajax({
		url: '/atendimento-procedimento/adicionarcidaoatendprocedimento/'+ idAtendimentoProcedimentoGlobal +'/'+ idCid,
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
	
	idAtendimentoProcedimentoGlobal = null;
	$("#card-cid-procedimento").fadeOut(100);
	$("#card-procedimento").fadeIn(100);
}

// verifica se o procedimento é obrigatório cid e se o cid não foi informado
function verificaProcedimentoObrigatorioCid(codigoProcedimento) {

	$.ajax({
		url: '/procedimentocid/verificaprocedimentocidobrigatorio/'+ codigoProcedimento,
		method: 'get',
		success: function (data) {
		
			if(data == true){
				swal("Atenção! Esse procedimento requer a adição de um CID compatível, por favor selecionar em seguida!", {
					icon: "warning",
					buttons: {
						confirm: {
							className: 'btn btn-primary'
						}
					},
					timer: 2000
				});
			}
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
