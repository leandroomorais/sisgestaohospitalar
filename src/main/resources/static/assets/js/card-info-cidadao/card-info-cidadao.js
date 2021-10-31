function cardInfoCidadao(idAtendimento) {
	$.ajax({
		url: '/atendimento/' + idAtendimento,
		method: 'get',
		success: function(data) {
			$("#info-cidadao").empty().append(createCardInfoCidadao(data));
			console.log(data);
		},
		error: function() {
			$("#info-cidadao").empty().append("<h5 class='card-title text-center'>Não foi possível recuperar as informações do Cidadão</h5>");
		}
	})
}

function createCardInfoCidadao(data) {
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-6'>" +
		h5NomeCidadao(data.cidadao.nome) +
		cns(data.cidadao.cns) +
		nascimentoIdadeSexo(data.cidadao.dataNascimento, data.cidadao.sexo) +
		"</div><div class='col-md-4'>" +
		classificacaoDeRisco(data.triagem) + "<br>" +
		verificaSinaisVitais(data) +
		"</div><div class='col-md-2'>" +
		antropometria(data.cidadao.prontuario.antropometrias) +
		"</div></div>" +
		motivosAtendimento(data) +
		"</div></div></div>";
}

function h5NomeCidadao(nome) {
	return "<h5 class='text-uppercase fw-bold mb-1'> " + nome + " </h5>";
}

function motivosAtendimento(data) {
	if (data.triagem != null) {
		if (data.triagem.motivo != null) {
			return "<div class='col-md-12 mt-1'><strong>Motivos do Atendimento: </strong><div class='card-sub'>" + data.triagem.motivo +
				"</div>";
		}
	} else {
		return "";
	}
}

function cns(cns) {
	return "<i class='fa fa-id-card' aria-hidden='true'></i><strong> CNS: </strong><span> " + formataCNS(cns) + " </span>";
}

function antropometria(antropometria) {
	if (antropometria.length != 0) {
		var registro
		$.each(antropometria, function(index, item) {
			registro = createTextSinaisVitais("PESO: ", item.peso + " kg") + createTextSinaisVitais(" ALTURA: ", item.altura + " cm") + "<br>" + createTextSinaisVitais(" IMC: ", item.imc);
		})
		return registro;
	} else {
		return "<span class='badge badge-info'></span>";
	}
}

function classificacaoDeRisco(triagem) {
	if (triagem == null) {
		return "<span class='badge badge-info'> Sem classificação de risco </span>"
	} else {
		var classDeRisco = triagem.classificacaoDeRisco;
		if (classDeRisco == 'AZUL') {
			return "<span class='badge badge-primary'>AZUL</span>";
		}
		if (classDeRisco == 'VERDE') {
			return "<span class='badge badge-success'>VERDE</span>";
		}
		if (classDeRisco == 'AMARELO') {
			return "<span class='badge badge-warning'>AMARELO</span>";
		}
		if (classDeRisco == 'LARANJA') {
			return "<span class='badge badge-warning'>LARANJA</span>";
		}
		if (classDeRisco == 'VERMELHO') {
			return "<span class='badge badge-danger'>VERMELHO</span>";
		}

		return "";
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

function verificaSinaisVitais(data) {
	if (data.triagem != null) {
		if (data.triagem.sinaisVitais != null) {
			var sinaisVitaisTriagem = data.triagem.sinaisVitais;
		}
	}
	if (data.consulta != null) {
		if (data.consulta.avaliacao != null) {
			if (data.consulta.avaliacao.sinaisVitais != null) {
				var sinaisVitaisAvaliacao = data.consulta.avaliacao.sinaisVitais;
			}
		}
	}

	if (sinaisVitaisTriagem != null && sinaisVitaisAvaliacao != null) {
		var ultimaAtualizacaoTriagem = sinaisVitaisTriagem.ultimaAtualizacao;
		var ultimaAtualizacaoAvaliacao = sinaisVitaisAvaliacao.ultimaAtualizacao;
		if (moment(ultimaAtualizacaoAvaliacao).isAfter(ultimaAtualizacaoTriagem)) {
			return infoAtdSinaisVitais(sinaisVitaisAvaliacao);
		} else {
			return infoAtdSinaisVitais(sinaisVitaisTriagem);
		}
	} else if (sinaisVitaisTriagem != null && sinaisVitaisAvaliacao == null) {
		console.log("Sinais vitais Triagem : " + sinaisVitaisTriagem + " Sinais vitais avali: " + sinaisVitaisAvaliacao);
		return infoAtdSinaisVitais(sinaisVitaisTriagem);
	} else if (sinaisVitaisAvaliacao != null && sinaisVitaisTriagem == null) {
		return infoAtdSinaisVitais(sinaisVitaisAvaliacao);
	}
	return "<span class='badge badge-info'>Nenhum registro de sinais vitais cadastrado</span>";
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
	return createTextSinaisVitais("PA:", pressaoArterial + " ") + spo2 + temp + hgt + fc;
}

function createTextSinaisVitais(info, text) {
	return "<strong>" + info + "</strong><span class='text-warning'>" + text + "</span>";
}

function formataCPF(cpf) {
	return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
}

function formataCNS(cns) {
	return cns.replace(/(\d{3})(\d{4})(\d{4})(\d{4})/, "$1.$2.$3.$4");
}