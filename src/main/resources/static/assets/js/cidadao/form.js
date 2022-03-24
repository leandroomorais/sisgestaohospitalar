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
			$("#card-pesquisa").hide();
			$("#form-cidadao-div").show();
		}
		$("#svg-status").hide();
		$("#info-cidadao").hide();
	}
)

var idCidadao = null;

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
	const isChecked = !$(this).attr('checked')
	$(this).attr("checked", isChecked)
	if (isChecked) {
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
	limparFormularioCidadao();
	if (info.cns == "" && info.cpf == "" && info.nome == "" && info.dataNascimento == "") {
		notificacao('Atenção!', 'Preencha um dos campos da pesquisa', 'top', 'right', 'warning', 'withicon', '#', '');
	} else if (info.cns != "" && info.cns.length < 15) {
		notificacao('Atenção!', 'Preencha o campo CNS corretamente', 'top', 'right', 'warning', 'withicon', '#', '');
	} else if (info.cpf != "" && info.cpf.length < 11) {
		notificacao('Atenção!', 'Preencha o campo CPF corretamente', 'top', 'right', 'warning', 'withicon', '#', '');
	} else if (info.nome == "" && info.dataNascimento.length == 10 || info.nome.length > 1 && info.dataNascimento == "") {
		notificacao('Atenção!', 'Preencha os campos Nome e Data de nascimento corretamente', 'top', 'right', 'warning', 'withicon', '#', '');
	} else {
		$.ajax({
			method: "POST",
			url: "/cidadao/busca-local",
			data: info,
			beforeSend: function() {
				console.log(info);
				removeMsgErro();
			},
			success: function(data) {
				idCidadao = data.id;
				$("#form-cidadao-div").fadeOut(500);
				limpaFormularioBusca();
				$("#pesquisa-status").text("Cidadão encontrado na base Local");
				limpaFormularioBusca();
				$("#info-cidadao").fadeIn(500);
				$("#card-info-cidadao").empty().append(creatCardDetalheCidadao(data));
			},
			statusCode: {
				//Code Here
				404: function() {
					$.ajax({
						method: "POST",
						url: "/cidadao/busca-cadsus",
						data: info,
						beforSend: function() {
							removeMsgErro();
						},
						success: function(data) {
							limpaFormularioBusca();
							$("#pesquisa-status").text("Cidadão encontrado na base do CADSUS");
							$("#form-cidadao-div").fadeIn(500);
							if (data.nome != null) {
								$("#nome").val(data.nome);
							} else {
								preenchimentoObrigatorio($("#small-nome"), $('label[for="' + $("#nome").attr("id") + '"]').text());
							}
							if (data.dataNascimento != null) {
								$("#datanascimento").val(data.dataNascimento);
							} else {
								preenchimentoObrigatorio($("#small-dataNascimento"), $('label[for="' + $("#datanascimento").attr("id") + '"]').text());
							}
							if (data.nomeMae != null) {
								$("#nomemae").val(data.nomeMae);
								if (data.nomeMae == "SEM INFORMAÇÃO") {
									$("#semInfo-mae").attr("checked", "checked");
								}
							} else {
								preenchimentoObrigatorio($("#small-nomeMae"), $('label[for="' + $("#nomemae").attr("id") + '"]').text());
							}
							if (data.nomePai != null) {
								$("#nomepai").val(data.nomePai);
								if (data.nomePai == "SEM INFORMAÇÃO") {
									$("#semInfo-pai").attr("checked", "checked");
								}
							} else {
								preenchimentoObrigatorio($("#small-nomePai"), $('label[for="' + $("#nomepai").attr("id") + '"]').text());
							}
							if (data.sexo != null) {
								$("#sexo").val(data.sexo).change();
							} else {
								preenchimentoObrigatorio($("#small-sexo"), $('label[for="' + $("#sexo").attr("id") + '"]').text());
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
								preenchimentoObrigatorio($("#small-codigoraca"), $('label[for="' + $("#codigoraca").attr("id") + '"]').text());
							}

							if (data.municipioNascimento != null) {
								$("#municipioNascimento").val(data.municipioNascimento.nomeMunicipioSiglaUF);
								$("#id-municipioNascimento").val(data.municipioNascimento.id);
							} else {
								preenchimentoObrigatorio($("#small-municipioNascimento"), $('label[for="' + $("#municipioNascimento").attr("id") + '"]').text());
							}

							if (data.codigoNacionalidade != null) {
								$("#codigonacionalidade").val(data.codigoNacionalidade).change();
							} else {
								$("#codigonacionalidade").val(10).change();
							}

							if (data.cns != null) {
								$("#cns").val(data.cns);
							} else {
								preenchimentoObrigatorio($("#small-cns"), $('label[for="' + $("#cns").attr("id") + '"]').text());
							}

							if (data.cpf != null) {
								$("#cpf").val(data.cpf);
							} else {
								preenchimentoObrigatorio($("#small-cpf"), $('label[for="' + $("#cpf").attr("id") + '"]').text());
							}

							if (data.endereco.cep != null) {
								$("#cep").val(data.endereco.cep);
								$("#nameCep").val(data.endereco.cep);
							} else {
								preenchimentoObrigatorio($("#small-endereco-cep"), $('label[for="' + $("#cep").attr("id") + '"]').text());
							}

							if (data.endereco.municipio.estado.nome != null) {
								$("#nomeEstado").val(data.endereco.municipio.estado.nome);
								$("#estadoId").val(data.endereco.municipio.estado.id);
							} else {
								preenchimentoObrigatorio($("#small-estado"), $('label[for="' + $("#nomeEstado").attr("id") + '"]').text());
							}

							if (data.endereco.municipio.nomeMunicipio != null) {
								$("#nomemunicipio").val(data.endereco.municipio.nomeMunicipio);
								$("#endereco-municipio").val(data.endereco.municipio.id);
							} else {
								preenchimentoObrigatorio($("#small-nomemunicipio"), $('label[for="' + $("#nomemunicipio").attr("id") + '"]').text());
							}

							if (data.endereco.logradouro.descricao != null) {
								$("#desc-logradouro").val(data.endereco.logradouro.descricao);
								$("#endereco-logradouro").val(data.endereco.logradouro.codigo);
							} else {
								preenchimentoObrigatorio($("#small-endereco-logradouro"), $('label[for="' + $("#desc-logradouro").attr("id") + '"]').text())
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
								preenchimentoObrigatorio($("#small-bairro"), $('label[for="' + $("#endereco-bairro").attr("id") + '"]').text());
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
	}
});

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
function preenchimentoObrigatorio(small, campo) {
	small.text("Campo de preenchimento obrigatório");
	small.removeClass().addClass("form-text text-danger");
	notificacao('O campo ' + campo + " é obrigatório", 'Por favor preencha-o', 'top', 'right', 'warning', 'withicon', '#', '');
};


//Emite alerta ao clicar no botão de Cancelar
$("#btn-cancelar-form-editar").click(function() {
	cancelar('Deseja cancelar?', 'Se clicar em sim todos os dados serão apagados!', 'warning', '/cidadao/adicionar');
});

$("#btn-cancelar-form").click(function() {
	cancelar('Deseja cancelar?', 'Se clicar em sim todos os dados serão apagados!', 'warning', '/cidadao/adicionar');
});

//Emite alerta ao clicar no botão de Cancelar
$("#btn-cancelar-local").click(function() {
	cancelar('Deseja cancelar?', 'Se clicar em sim todos os dados serão apagados!', 'warning', '/cidadao/adicionar');
});

function creatCardDetalheCidadao(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-8'>" +
		h5CardInfoCidadao(data.nome, data.sexo) +
		inforCardPrimary(data.cpf, data.cns) +
		itemCardCidadaoDataNascimento(data.dataNascimento) +
		itemCardCidadao("Nome do pai: ", data.nomePai) +
		itemCardCidadao("Nome da mãe: ", data.nomeMae) +
		"</div><div class='col-md-4 text-right'>" +
		infoCardEndereco(data.endereco.enderecoCompleto, data.endereco.cep) +
		"</div></div><div class='text-right'>" +
		"<button type='button' onclick='detalhar()' class='btn btn-light btn-sm'><i class='fa fa-info-circle' aria-hidden='true'></i> Detalhar</button>" +
		"<button type='button' onclick='editar()' class='btn btn-light btn-sm'><i class='fa fa-edit' aria-hidden='true'></i> Editar</button>" +
		"</div></div></div>";
}

function removeMsgErro() {
	//Removendo mensagens de Erro
	$("#svg-status").fadeIn(20);
	$("#pesquisa-status").text("Buscando");
	$("#info-cidadao").hide();
}

function limpaFormularioBusca() {
	$("#form-busca").each(function() {
		this.reset();
	});
}

function limparFormularioCidadao() {
	$("#form-cidadao").each(function() {
		this.reset();
	});
}

function h5CardInfoCidadao(nomeCompleto, sexo) {
	var sexoExtenso = "";
	if (sexo == "M") {
		sexoExtenso = "MASCULINO";
	}
	if (sexo == "F") {
		sexoExtenso = "FEMININO";
	}
	return "<h5 class='text-uppercase fw-bold mb-1'> " + nomeCompleto + " <span class='text-info text-uppercase pl-3'> " + sexoExtenso + " </span></h5>";
}

function inforCardPrimary(cpf, cns) {
	return "<strong> CPF: </strong><span> " + cpf + " </span> | <strong> CNS: </strong><span> " + cns + " </span><br>";
}

function itemCardCidadao(text, info) {
	return "<strong>" + text + "</strong><span> " + info + "</span><br>";
}

function itemCardCidadaoDataNascimento(dn) {
	var data = new Date(dn);
	data = data.toLocaleDateString('pt-BR');
	return "<strong> Data de Nascimento: </strong><span> " + data + "</span><br>";
}

function infoCardEndereco(endereco, cep) {
	return "<span class='text-uppercase fw-bold mb-1'> ENDEREÇO: </span><br><span class='text-muted'> " + endereco + " </span><br><strong>CEP: </strong><span class='text-muted'> " + cep + " </span><br>"
}

function detalhar() {
	$(location).attr('href', '/cidadao/detalhar/' + idCidadao);
}

function editar() {
	$(location).attr('href', '/cidadao/editar/' + idCidadao);
}
$("#btn-adicionar-cidadao").click(function() {
	$(location).attr('href', '/atendimento/adicionar/' + idCidadao);
})








