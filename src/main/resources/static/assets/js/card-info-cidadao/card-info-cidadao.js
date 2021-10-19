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
	return "<div class='card'><div class='card-body'><div class='col-md-12 row'><div class='col-md-7'>" +
		h5NomeCidadao(data.cidadao.nome) +
		CpfAndCns(data.cidadao.cpf, data.cidadao.cns) + "<br>" +
		NascimentoIdadeSexo(data.cidadao.dataNascimento, data.cidadao.sexo) +
		"</div><div class='col-md-5'>" +
		infoAtdSinaisVitais(data.triagem) +
		"</div></div></div></div>";
}

function h5NomeCidadao(nome) {
	return "<h5 class='text-uppercase fw-bold mb-1'> " + nome + " </h5>";
}

function CpfAndCns(cpf, cns) {
	return "<i class='fa fa-id-card' aria-hidden='true'></i><strong> CPF: </strong><span> " + formataCPF(cpf) + " </span> | <strong> CNS: </strong><span> " + formataCNS(cns) + " </span>";
}

function NascimentoIdadeSexo(dtNascimento, sexo) {

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

function infoAtdSinaisVitais(triagem) {

	if (triagem == null) {
		return "<span class='badge badge-info'>Triagem não realizada</span>";
	} else {
		var cRisco = triagem.classificacaoDeRisco;
		var spo2 = triagem.sinaisVitais.saturacao;
		var pa = triagem.sinaisVitais.pressaoArterial;
		var temp = triagem.sinaisVitais.temperaturaCorporal;
		var hgt = triagem.sinaisVitais.glicemiaCapilar;
		if (cRisco == "" || cRisco == null || cRisco == undefined) {
			cRisco = "<span class='badge badge-info'>Sem informação</span>"
		} else {
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
			cRisco = "<span class='" + classe + "'>" + cRisco + "</span><br>";
		}

		if (spo2 == 0 || spo2 == null || spo2 == undefined) {
			spo2 = "";
		} else {
			spo2 = createTextSinaisVitais("SPO2: ", spo2 + " % ");
		}

		if (pa == "" || pa == null || pa == undefined) {
			pa = "";

		} else {
			pa = createTextSinaisVitais("PA: ", pa + " mmHg ");
		}

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
		return cRisco + pa + spo2 + temp + hgt + fc;
	}
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