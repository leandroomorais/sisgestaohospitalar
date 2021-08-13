$(document).ready(
	function() {

		//Aplica máscaras aos inputs do Formulário
		$("#cns").mask('000.0000.0000.0000');
		$("#cpf").mask('000.000.000-00');
		$("#cpf-busca").mask('000.000.000-00');
		$("#cns-busca").mask('000.0000.0000.0000');
		$("#telefone").mask('(00) 00000-0000');
		$("#cep").mask('00000-000');

		//Aplica uper case no input nome
		$("#nome-busca").keyup(function() {
			this.value = this.value.toUpperCase();
		});
		//Oculta o status da pesquisa
		$("#status-pesquisa-cns").hide();
		$("#status-pesquisa-cpf").hide();

		//Oculta o formulário de pesquisa por CPF
		$("#form-busca-cpf").hide();
		//Oculta o formulário de pesquisa por NOME
		$("#form-busca-nome").hide();
		//Oculta o formulário de CADASTRO DO CIDADÃO
		if ($("#hasErrors").text() != "true") {
			$("#form-cidadao-div").hide();
		} else {
			$("#form-cidadao-div").show();
		}


		$("#svg-status").hide();

		$("#info-cidadao").hide();

		//Muda o formulário ao clicar no botão de pesquisa por CPF
		$("#button-cpf").click(function() {
			//Limpa o formulário
			$("#form-busca").each(function() {
				this.reset();
			});
			$("#form-busca-cns").hide();
			$("#form-busca-nome").hide();
			$("#form-busca-cpf").fadeIn(500);
			$("#button-cpf").removeClass().addClass("btn btn-primary btn-xs");
			$("#button-cns").removeClass().addClass("btn btn-primary btn-border btn-xs");
			$("#button-nome").removeClass().addClass("btn btn-primary btn-border btn-xs");

		});
		//Muda o formulário ao clicar no botão de pesquisa por CNS
		$("#button-cns").click(function() {
			$("#form-busca").each(function() {
				this.reset();
			});
			$("#form-busca-cpf").hide();
			$("#form-busca-nome").hide();
			$("#form-busca-cns").fadeIn(500);
			$("#button-cns").removeClass().addClass("btn btn-primary btn-xs");
			$("#button-cpf").removeClass().addClass("btn btn-primary btn-border btn-xs");
			$("#button-nome").removeClass().addClass("btn btn-primary btn-border btn-xs");
		});
		//Muda o formulário ao clicar no botão de pesquisa por NOME
		$("#button-nome").click(function() {
			$("#form-busca").each(function() {
				this.reset();
			});
			$("#form-busca-cpf").hide();
			$("#form-busca-cns").hide();
			$("#form-busca-nome").fadeIn(500);
			$("#button-cpf").removeClass().addClass("btn btn-primary btn-border btn-xs");
			$("#button-cns").removeClass().addClass("btn btn-primary btn-border btn-xs");
			$("#button-nome").removeClass().addClass("btn btn-primary btn-xs");
		});
	}
)

//Funções auxliares do Formulário de Cadastro do Cidadão

$("#semInfo-mae").click(function() {
	if ($("#semInfor-mae").prop("checked", "checked")) {
		$("#nomemae").val("SEM INFORMAÇÃO");
	} else if ($("#semInfor-mae").prop("checked", false)) {
		$("#nomemae").val("");
	}

});

$("#semInfo-pai").click(function() {
	if ($("#semInfor-pai").prop("checked", true)) {
		$("#nomepai").val("SEM INFORMAÇÃO");
	} else if ($("#semInfor-pai").prop("checked", false)) {
		$("#nomepai").val("");
	}

});

$("#semNumero").click(function() {
	if ($("#semNumero").prop("checked", true)) {
		$("#endereco-numero").val("S/N");
	} else {
		$("#endereco-numero").val("");
	}
});


//Submit da função buscar Cidadão
$("#form-busca").submit(function(evt) {
	evt.preventDefault();
	var info = {};

	info.cns = $("#cns-busca").val();
	info.cpf = $("#cpf-busca").val();
	info.nome = $("#nome-busca").val();
	info.dataNascimento = $("#dataNascimentoBusca").val();

	$("#form-cidadao").each(function() {
		this.reset();
	});

	$.ajax({
		method: "POST",
		url: "/cidadao/busca-local",
		data: info,
		beforeSend: function() {
			//Removendo mensagens de Erro
			$("#svg-status").fadeIn(20);
			$("#pesquisa-status").text("Buscando");
			$("#info-cidadao").hide();
		},
		success: function(data) {
			
			$("#form-cidadao-div").fadeOut(500);

			$("#form-busca").each(function() {
				this.reset();
			});

			$("#pesquisa-status").text("Cidadão encontrado na base Local");

			$("#form-busca").each(function() {
				this.reset();
			});

			$("#info-cidadao").fadeIn(500);

			$("#idCidadao").val(data.id);

			if (data.nome != null) {
				$("#info-nome").text(data.nome);
			}

			if (data.dataNascimento != null) {
				var dataNascimento = data.dataNascimento;
				var ano = dataNascimento.substring(0, 4);
				var mes = dataNascimento.substring(5, 7);
				var dia = dataNascimento.substring(8, 10);
				$("#info-dataNascimento").text(dia + "/" + mes + "/" + ano);
			}

			if (data.nomeMae != null) {
				$("#info-nomeMae").text(data.nomeMae);
			}

			if (data.nomePai != null) {
				$("#info-nomePai").text(data.nomePai);
			}

			if (data.sexo != null) {
				$("#info-sexo").text(data.sexo);
			}

			if (data.cns != null) {
				$("#info-cns").text(data.cns).mask('000.0000.0000.0000');
			}

			if (data.cpf != null) {
				$("#info-cpf").text(data.cpf).mask('000.000.000-00');
			}


			if (data.endereco.cep != null) {
				$("#info-cep").text(data.endereco.cep).mask('00000-000');
			}


			if (data.endereco.enderecoCompleto != null) {
				$("#info-endereco").text(data.endereco.enderecoCompleto);
			}

		},
		statusCode: {
			//Code Here
			404: function() {
				$.ajax({
					method: "POST",
					url: "/cidadao/busca-cadsus",
					data: info,
					beforSend: function() {
						//Removendo Mensagens de erro
						$("#svg-status").fadeIn(20);
						$("#pesquisa-status").text("Buscando");
						$("#info-cidadao").hide();
					},
					success: function(data) {
						$("#form-busca").each(function() {
							this.reset();
						});

						$("#pesquisa-status").text("Cidadão encontrado na base do CADSUS");

						$("#form-cidadao-div").fadeIn(500);

						if (data.nome != null) {
							$("#nome").val(data.nome);
						} else {
							preenchimentoObrigatorio($("#small-nome"));
						}

						if (data.dataNascimento != null) {
							$("#datanascimento").val(data.dataNascimento);
						} else {
							preenchimentoObrigatorio($("#small-datanascimento"));
						}

						if (data.nomeMae != null) {
							$("#nomemae").val(data.nomeMae);
							if (data.nomeMae == "SEM INFORMAÇÃO") {
								$("#semInfo-mae").attr("checked", "checked");
							}
						} else {
							preenchimentoObrigatorio($("#small-nomemae"));
						}

						if (data.nomePai != null) {
							$("#nomepai").val(data.nomePai);
							if (data.nomePai == "SEM INFORMAÇÃO") {
								$("#semInfo-pai").attr("checked", "checked");
							}
						} else {
							preenchimentoObrigatorio($("#small-nomepai"));
						}

						if (data.sexo != null) {
							$("#sexo").val(data.sexo).change();
						} else {
							preenchimentoObrigatorio($("#small-sexo"));
						}

						if (data.codigoRaca != null) {
							if (data.codigoRaca == "BRANCA") {
								$("#codigoraca").val(1).change();
							}
							if (data.codigoRaca == "PRETA") {
								$("#codigoraca").val(2).change();
							}
							if (data.codigoRaca == "PARDA") {
								$("#codigoraca").val(3).change();
							}
							if (data.codigoRaca == "AMARELA") {
								$("#codigoraca").val(4).change();
							}
							if (data.codigoRaca == "INDIGENA") {
								$("#codigoraca").val(5).change();
							}
							if (data.codigoRaca == "SEMINFORMACAO") {
								$("#codigoraca").val(99).change();
							}
						} else {
							preenchimentoObrigatorio($("#small-codigoraca"));
						}

						if (data.municipioNascimento.nomeMunicipioSiglaUF != null) {
							$("#municipioNascimento").val(data.municipioNascimento.nomeMunicipioSiglaUF);
							$("#id-municipioNascimento").val(data.municipioNascimento.id);
						} else {
							preenchimentoObrigatorio($("#small-municipioNascimento"));
						}

						if (data.codigoNacionalidade != null) {
							$("#codigonacionalidade").val(data.codigoNacionalidade).change();
						} else {
							preenchimentoObrigatorio($("#small-codigonacionalidade"));
						}

						if (data.cns != null) {
							$("#cns").val(data.cns);
						} else {
							preenchimentoObrigatorio($("#small-cns"));
						}

						if (data.cpf != null) {
							$("#cpf").val(data.cpf);
						} else {
							preenchimentoObrigatorio($("#small-cpf"));
						}

						if (data.endereco.cep != null) {
							$("#cep").val(data.endereco.cep);
							$("#nameCep").val(data.endereco.cep);
						} else {
							preenchimentoObrigatorio($("#small-endereco-cep"));
						}

						if (data.endereco.municipio.estado.nome != null) {
							$("#nomeEstado").val(data.endereco.municipio.estado.nome);
							$("#estadoId").val(data.endereco.municipio.estado.id);
						} else {
							preenchimentoObrigatorio($("#small-estado"));
						}

						if (data.endereco.municipio.nomeMunicipio != null) {
							$("#nomemunicipio").val(data.endereco.municipio.nomeMunicipio);
							$("#endereco-municipio").val(data.endereco.municipio.id);
						} else {
							preenchimentoObrigatorio($("#small-nomemunicipio"));
						}

						if (data.endereco.logradouro.descricao != null) {
							$("#desc-logradouro").val(data.endereco.logradouro.descricao);
							$("#endereco-logradouro").val(data.endereco.logradouro.codigo);
						} else {
							preenchimentoObrigatorio($("#small-endereco-logradouro"))
						}

						if (data.endereco.nomeLogradouro != null) {
							$("#endereco-nomeLogradouro").val(data.endereco.nomeLogradouro);
						} else {
							preenchimentoObrigatorio($("#small-endereco-nomeLogradouro"));
						}

						if (data.endereco.numero != null) {
							$("#endereco-numero").val(data.endereco.numero);
							if (data.endereco.numero == "S/N") {
								$("#semNumero").attr("checked", "checked");
							}
						} else {
							preenchimentoObrigatorio($("#small-numero"));
						}

						if (data.endereco.bairro != null) {
							$("#endereco-bairro").val(data.endereco.bairro);
						} else {
							preenchimentoObrigatorio($("#small-bairro"));
						}

						if (data.endereco.complemento != null) {
							$("#endereco-complemento").val(data.endereco.complemento);
						}
					},
					statusCode: {
						404: function() {
							$("#form-cidadao-div").fadeIn(500);
							$("#pesquisa-status").text("Não encontrado");
							swal("Não encontrado!", "Não localizamos o Cidadão em nossas bases. Por favor preencha os dados do formulário.", {
								icon: "error",
								buttons: {
									confirm: {
										className: 'btn btn-danger',

									}
								},
							}).then(function() {
								$("#card-pesquisa").fadeOut(500);
							})
						},

					}
				})
			}
		},


	})
});



//Função autocompletar município de nascimento
$(function() {
	$("#municipioNascimento").on("keydown", function(event) {
		$(this).autocomplete("instance")._renderItem = function(select, item) {
			return $("<option  class='form-control'>").append("<div>"
				+ item.nomeMunicipioSiglaUF
				+ "</div>").appendTo(select);
		};
	}).autocomplete({
		source: "/municipio",
		select: function(event, ui) {
			$("#municipioNascimento").val(ui.item.nomeMunicipioSiglaUF);
			$("#id-municipioNascimento").val(ui.item.id);
			return false;
		},

	})
});

//Função para autocompletar endereço por CEP
$("#button-pesquisaCep").click(function(evt) {
	evt.preventDefault();
	var data = {};
	var cep = $("#cep").val();
	var cepU = cep.replace("-", "");
	data.cep = cepU;
	$.ajax({
		method: "GET",
		url: "/municipio/cep",
		data: data,
		beforSend: function() {
			//Removendo Mensagens de erro
		},
		success: function(data) {
			$("#nomeEstado").val(data.estado.nome).attr("disabled", true);
			$("#nomemunicipio").val(data.nomeMunicipio).attr("disabled", true);
			$("#endereco-municipio").val(data.id)
			$("#cep").val(cepU);
		},
		statusCode: {
			404: function() {
				//alert("Cidadão não encontrado");
			},
		}

	})

});


//Função autocompletar Logradouro
$(function() {
	$("#desc-logradouro").on("keydown", function(event) {
		$(this).autocomplete("instance")._renderItem = function(select, item) {
			return $("<option  class='form-control'>").append("<div>"
				+ item.descricao
				+ "</div>").appendTo(select);
		};
	}).autocomplete({
		source: "/logradouro",
		select: function(event, ui) {
			$("#desc-logradouro").val(ui.item.descricao);
			$("#endereco-logradouro").val(ui.item.codigo);
			return false;
		},

	})
});

//Função para exibir a mensagem CAMPO DE PREENCHIMENTO OBRIGATÓRIO
//caso o serviço do CADSUS não retorne um parâmetro obrigatório
function preenchimentoObrigatorio(small) {
	small.text("Campo de preenchimento obrigatório");
	small.removeClass().addClass("form-text text-danger");
};


//Emite alerta ao clicar no botão de Cancelar
$("#btn-cancelar-form").click(function() {
	alerta("/cidadao/adicionar")
})

//Emite alerta ao clicar no botão de Cancelar
$("#btn-cancelar-local").click(function() {
	alerta("/cidadao/adicionar")
})

function alerta(redirect) {
	swal({
		title: 'Deseja cancelar a operação?',
		text: "Se clicar em sim todos os dados serão apagados.",
		type: 'warning',
		buttons: {
			confirm: {
				text: 'Sim, cancelar!',
				className: 'btn btn-warning'
			},
			cancel: {
				text: 'Não, retornar!',
				visible: true,
				className: 'btn btn-primary'
			}
		}
	}).then((confirm) => {
		if (confirm) {
			$(location).attr('href', redirect)
		} else {
			swal.close();
		}
	});
}

$("#btn-detalhar-cidadao").click(function(){
	$(location).attr('href','/cidadao/detalhar/' + $("#idCidadao").val());
})

$("#btn-editar-cidadao").click(function(){
	$(location).attr('href','/cidadao/editar/' + $("#idCidadao").val());
})
//
//$('#btn-mudar-status').click(function(e) {
//	swal({
//		title: 'Deseja remover o Cidadão da Fila de Atendimentos?',
//		text: "Use esta opção para informar que o Cidadão não aguardou pelo atendimento.",
//		type: 'warning',
//		buttons: {
//			confirm: {
//				text: 'Sim, remover!',
//				className: 'btn btn-primary'
//			},
//			cancel: {
//				text:'Cancelar',
//				visible: true,
//				className: 'btn btn-warning'
//			}
//		}
//	}).then((Delete) => {
//		if (Delete) {
//			$("#status-atendimento").val("NAOAGUARDOU");
//			$("#submit-guia-atendimento").click();
//		} else {
//			swal.close();
//		}
//	});
//});






