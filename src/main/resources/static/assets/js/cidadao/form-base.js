function mascaraInputs() {
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
}

//Funções auxliares do Formulário de Cadastro do Cidadão

$("#semInfo-mae").click(function() {
	const isChecked = !$(this).attr('checked')
	$(this).attr("checked", isChecked)
	if (isChecked) {
		$("#nomemae").val("SEM INFORMAÇÃO");
	} else {
		$("#nomemae").val("");
	}

});

$("#semInfo-pai").click(function() {
	const isChecked = !$(this).attr('checked')
	$(this).attr("checked", isChecked)
	if (isChecked) {
		$("#nomepai").val("SEM INFORMAÇÃO");
	} else {
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

//Função autocompletar município de nascimento
$("#municipioNascimento").autocomplete({
	maxShowItems: 5,
	source: "/municipio",
	focus: function(event, ui) {
		$(this).val(ui.item.nomeMunicipioSiglaUF);
		return false;
	},
	select: function(event, ui) {
		$("#municipioNascimento").val(ui.item.nomeMunicipioSiglaUF);
		$("#id-municipioNascimento").val(ui.item.id);
		return false;
	}
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h5'>" + item.nomeMunicipioSiglaUF + "</div>")
		.appendTo(ul);
}


//Função autocompletar Logradouro
$("#desc-logradouro").autocomplete({
	maxShowItems: 5,
	source: "/logradouro",
	focus: function(event, ui) {
		$(this).val(ui.item.descricao);
		return false;
	},
	select: function(event, ui) {
		$("#desc-logradouro").val(ui.item.descricao);
		$("#endereco-logradouro").val(ui.item.codigo);
		return false;
	},
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h5'>" + item.descricao + "</div>")
		.appendTo(ul);
};

$("#profissao").autocomplete({
	maxShowItems: 5,
	source: "/ocupacao/autocomplete",
	focus: function(event, ui) {
		$(this).val(ui.item.nome.trim());
		return false;
	},
	select: function(event, ui) {
		$(this).val(ui.item.nome.trim());
		return false;
	},
}).autocomplete("instance")._renderItem = function(ul, item) {
	return $("<li>")
		.append("<div class='h5'>" + item.nome.trim() + "</div>")
		.appendTo(ul);
};

