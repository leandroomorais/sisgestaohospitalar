
function cardInfoCidadao(idAtendimento) {
	$.ajax({
		url: '/atendimento/' + idAtendimento,
		method: 'get',
		success: function(data) {
			$("#info-cidadao").empty().append(createCardInfoCidadao(data));
		},
		error: function() {

		}
	})
}

function createCardInfoCidadao(data) {
	h5NomeCidadao(data.cidadao.nome);
}

function h5NomeCidadao(nome) {
	return "<h5 class='text-uppercase fw-bold mb-1'> " + nome + " </h5>";
}

function CpfAndCns(cpf, cns) {
	return "<i class='fa fa-id-card' aria-hidden='true'></i><strong> CPF: </strong><span data-mask='000.000.000-00'> " + cpf + " </span> | <strong> CNS: </strong><span data-mask='000.0000.0000.0000'> " + cns + " </span>";
}

function NascimentoIdadeSexo(dtNascimento, idade, sexo) {
	return "<strong>NASC. </strong > <span> " + dtNascimento + " </span> | <span> " + idade + "</span > <br> <strong>SEXO:" +
		"</strong><span> " + sexo + " </span>";
}

function infoAtdSinaisVitais(cRisco, spo2, fc, pa, temp, hgt) {
	var pressaoArterial;
	var
	if (!cRisco.isEmpty()) {
		var classe;
		if (cRisco == "AZUL") {
			classe = "badge badge-primary";
		}
		if (cRisco == "VERDE") {
			classe = "badge badge-success";
		}
		if (cRisco == "AMARELO") {
			classe = "badge badge-warning";
		}
		if (cRisco == "LARANJA") {
			classe = "badge badge-warning";
		}
		if (cRisco == "VERMELHO") {
			classe = "badge badge-danger";
		}
		cRisco = "<span class='" + classe + "'>" + cRisco + "</span>";
	} else {
		cRisco = "<span class='badge badge-info'>Sem informação</span>"
	}

	if (!pa.isEmpty()) {
		pa = createTextSinaisVitais("PA", pa); s
	} else {
		pa = "";
	}

	return cRisco + " " + pa;

}

function createTextSinaisVitais(info, text) {
	return "<strong>" + info + "</strong><span class='text-warning'>" + text + "</span>";
}