function cardInfoCidadao(idAtendimento) {
	$.ajax({
		url: '/atendimento/' + idAtendimento,
		method: 'get',
		success: function(data) {
			$("#info-cidadao").empty().append(createCardInfoCidadao(data));
		},
		error: function() {
			$("#info-cidadao").empty().append("<h5 class='card-title text-center'>Não foi possível recuperar as informações do Cidadão</h5>");
		}
	})
}
function createCardInfoCidadao(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-6'>" +
		h5NomeCidadao(data.cidadao.nome) +
		cpf(data.cidadao.cpf) +
		nascimentoIdadeSexo(data.cidadao.dataNascimento, data.cidadao.sexo) +
		"</div><div class='col-md-4'>" +
		classificacaoDeRisco(data.classificacaoDeRisco) + "<br>" +
		verificaSinaisVitais(data.sinaisVitais) +
		"</div><div class='col-md-2'>" +
		antropometria(data.cidadao.prontuario.antropometrias) +
		"</div></div>" +
		"<div class='page-divider'></div>" +
		"<div class='col-md-12 row'>" +
		motivosAtendimento(data) +
		alergias(data.cidadao.prontuario.statusAlergias) +
		"</div>" +
		"</div></div></div>";
}

function h5NomeCidadao(nome) {
	return "<h5 class='text-uppercase fw-bold mb-1'> " + nome + " </h5>";
}

function motivosAtendimento(data) {
	if (data.triagem != null) {
		if (data.triagem.motivo != null) {
			return "<div class='col-md-7 mt-2'><strong>Motivos do Atendimento: </strong>" + data.triagem.motivo +
				"</div>";
		}
	} else {
		return ""
	}
}

function alergias(alergias) {
	if (alergias.length != 0) {
		var retorno;
		var retornoConcat = "";
		var classe;
		$.each(alergias, function(index, item) {
			if (item.situacaoCondicao == "ATIVA") {
				classe = "badge badge-danger";
			}
			if (item.situacaoCondicao == "LATENTE") {
				classe = "badge badge-warning";
			}
			if (item.situacaoCondicao == "CURADA") {
				classe = "badge badge-success";
			}
			if (item.situacaoCondicao == "ATIVA" || item.situacaoCondicao == "LATENTE") {
				retorno = "<span class='" + classe + "'>" + item.alergia.nome + "</span><br>";
			} else {
				retorno = "";
			}
			retornoConcat += retorno;
		})
		return "<div class='col-md-5 mt-2'><strong>Alergias: </strong><br>" + retornoConcat + "</div>";
	} else {
		return "<div class='col-md-5 mt-2'><strong>Alergias: </strong><span class='badge badge-info'>Sem alergias cadastradas</span></div>";
	}

}

function cpf(cpf) {
	return "<i class='fa fa-id-card' aria-hidden='true'></i><strong> CPF: </strong><span> " + formataCPF(cpf) + " </span>";
}

function antropometria(antropometria) {
	if (antropometria.length != 0) {
		var registro;
		moment.locale("pt-br");
		$.each(antropometria, function(index, item) {
			registro = createTextSinaisVitais("PESO: ", item.peso + " kg") + createTextSinaisVitais(" ALTURA: ", item.altura + " cm") + "<br>" + createTextSinaisVitais(" IMC: ", item.imc) +
				"<br><p class='text-muted'>Em: " + moment(item.dataCadastro).format('DD/MM/YYYY') + "</p>";
		})
		return registro;
	} else {
		return "<span class='badge badge-info'></span>";
	}
}

function classificacaoDeRisco(classDeRisco) {

	if (classDeRisco.nome == 'AZUL') {
		return "<strong>C.R: </strong><span class='badge badge-primary'>AZUL</span>";
	}
	if (classDeRisco.nome == 'VERDE') {
		return "<strong>C.R: </strong><span class='badge badge-success'>VERDE</span>";
	}
	if (classDeRisco.nome == 'AMARELO') {
		return "<strong>C.R: </strong><span class='badge badge-amarelo'>AMARELO</span>";
	}
	if (classDeRisco.nome == 'LARANJA') {
		return "<strong>C.R: </strong><span class='badge badge-warning'>LARANJA</span>";
	}
	if (classDeRisco.nome == 'VERMELHO') {
		return "<strong>C.R: </strong><span class='badge badge-danger'>VERMELHO</span>";
	}
	if (classDeRisco.nome === "Não informada") {
		return "<strong>C.R: </strong><span class='badge badge-info'>Classificação de risco não informada</span>";
	}
}

function nascimentoIdadeSexo(dtNascimento, sexo) {

	var anos = moment().diff(dtNascimento, 'years');
	var idade;
	if (anos == 1) {
		idade = anos + " ano ";
	} else if (anos > 1) {
		idade = anos + " anos ";
	}
	var data = moment(dtNascimento).format('DD/MM/YYYY');
	if (sexo == "M") {
		sexo = "Masculino"
	}
	if (sexo == "F") {
		sexo = "Feminino"
	}
	return "<strong>NASC. </strong > <span> " + data + " </span> | <span> " + idade + "</span > <br> <strong>SEXO:" +
		"</strong><span> " + sexo + " </span>";
}

function verificaSinaisVitais(sinaisVitais) {
	if (sinaisVitais.length != 0) {
		var registro;
		$.each(sinaisVitais, function(index, item) {
			registro = infoAtdSinaisVitais(item);
		})
		return registro;
	} else {
		return "<span class='badge badge-info'>Sem registro de sinais vitais</span>";
	}
}

function infoAtdSinaisVitais(sinaisVitais) {
	var spo2 = sinaisVitais.saturacao;
	var paSistolica = sinaisVitais.pressaoSistolica;
	var paDiastolica = sinaisVitais.pressaoDiastolica;
	var temp = sinaisVitais.temperaturaCorporal;
	var hgt = sinaisVitais.glicemiaCapilar;
	var fc = sinaisVitais.frequenciaCardiaca;

	if (spo2 == 0 || spo2 == null || spo2 == undefined) {
		spo2 = "";
	} else {
		spo2 = createTextSinaisVitais("SPO2: ", spo2 + " % ");
	}

	if (paSistolica == "" || paSistolica == null || paSistolica == undefined) {
		paSistolica = "";

	} else {
		paSistolica = paSistolica;
	}

	if (paDiastolica == "" || paDiastolica == null || paDiastolica == undefined) {
		paDiastolica = "";

	} else {
		paDiastolica = paDiastolica;
	}

	var pressaoArterial = paSistolica + " / " + paDiastolica;

	if (temp == "" || temp == null || temp == undefined) {
		temp = "<br>";

	} else {
		temp = createTextSinaisVitais("TEMP: ", temp + " ºC ") + "<br>";
	}

	if (hgt == 0 || hgt == null || hgt == undefined) {
		hgt = "";

	} else {
		hgt = createTextSinaisVitais("HGT: ", hgt + " mg/dl ");
	}

	if (fc == "" || fc == null || fc == undefined) {
		fc = "";

	} else {
		fc = "<strong>FC: </strong><span class='text-danger'><i class='fa fa-heart' aria-hidden='true'></i>" + fc + " bmp " + "</span>"
	}
	return createTextSinaisVitais("PA:", pressaoArterial + " ") + spo2 + temp + hgt + fc + "<br><p class='text-muted'>Em: " + moment(sinaisVitais.ultimaAtualizacao).format("DD/MM/YYYY , HH:mm:ss") + "</p>";
}

function createTextSinaisVitais(info, text) {
	return "<strong>" + info + "</strong><span class='text-info'>" + text + "</span>";
}

function formataCPF(cpf) {
	return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
}

function formataCNS(cns) {
	return cns.replace(/(\d{3})(\d{4})(\d{4})(\d{4})/, "$1.$2.$3.$4");
}