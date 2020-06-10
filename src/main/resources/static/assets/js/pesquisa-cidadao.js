//Contém métodos para pesquisa do Cidadão
$(document).ready(
		function() {
			// Oculta o formulário de Pesquisa por CPF ao carregar a página
			$("#form-pesquisa-cpf").hide();
			
			
			
			// Oculta o formulário com os Dados do Cidadão ao carregar a página
			$("#form-cidadao").hide();

			// Muda o formulário de Pesquisa ao Clicar
			$("#pesquisa-cpf").click(
					function() {
						$("#form-pesquisa-cns").hide();
						$("#form-pesquisa-cpf").fadeIn(1000);
						$("#pesquisa-cpf").removeClass().addClass(
								"btn btn-primary btn-xs");
						$("#pesquisa-cns").removeClass().addClass("btn btn-primary btn-border btn-xs");
					});

			$("#pesquisa-cns").click(
					function() {
						$("#form-pesquisa-cpf").hide();
						$("#form-pesquisa-cns").fadeIn(1000);
						$("#pesquisa-cpf").removeClass().addClass("btn btn-primary btn-border btn-xs");
						$("#pesquisa-cns").removeClass().addClass(
						"btn btn-primary btn-xs");
					});
			
			// Pesquisa Cidadão pelo CNS
			$("#button-pesquisacns").click(
					function() {
						var param = $("#cns").val();
						var cns = param.replace(/\D/g, '');
						var rootUrl = "http://localhost:8080/cidadao-resource";
						var apiIbge = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/";
						if (cns == "") {
							$("#small-cns").text(
									"O campo CNS não pode ficar em branco")
									.addClass("text-danger");
						} else {
							var validaCns = /^[0-9]{15}$/;
							if (validaCns.test(cns)) {
								$("#fa-button").removeClass().addClass("loadersearch");
								$.getJSON(rootUrl + "/localCns?cns=" + cns, function(data){
								}).done(function(data){
									$("#id").val(data.id);
									$("#form-cns").val(data.cns);
									$("#form-cpf").val(data.cpf);
									$("#nome").val(data.nome);
									$("#datanascimento").val(converteData(data.datanascimento));
									$("#sexo").val(data.sexo);
									$("#nomemae").val(data.nomemae);
									$("#nomepai").val(data.nomepai);
									$('#codigoraca option[value="' + data.codigoraca + '"]').attr({ selected : "selected" });
									$('#codigonacionalidade option[value="' + data.codigonacionalidades + '"]').attr({ selected : "selected" });
									$("#nomemunicipio").val(data.nomemunicipio);
									$('#codigologradouro option[value="' + data.codigologradouro + '"]').attr({ selected : "selected" });
									$("#endereco").val(data.endereco);
									$("#complementoendereco").val(data.complementoendereco);
									$("#numeroendereco").val(data.numeroendereco);
									$("#email").val(data.email);
									$("#telefone").val(data.telefone);
									$("#fa-button").removeClass().addClass("fa fa-check");
									$("#resultado-pesquisa").addClass("text-success").text("Cidadão encontrado na Base de Dados Local. Confira os dados no formulário abaixo.");
									$("#form-cidadao").fadeIn(2000);
								}).fail(function(data){
									$.getJSON(rootUrl + "/cadsusCns?cns=" + cns, function(data){
									}).done(function(data){
										$("#nome").val((data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.name.given));
										$("#datanascimento").val(converteDataCadsus(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.birthTime.value));
										$("#sexo").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.administrativeGenderCode.code);
										$("#nomemae").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.personalRelationship[0].relationshipHolder1.name.given);
										$("#nomepai").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.personalRelationship[1].relationshipHolder1.name.given);
										$("#codigoraca").val(parseInt((data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.raceCode.code)));
										$("#codigonacionalidade").val(parseInt(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.birthPlace.addr.country));
										var codigomunicipio = data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.city;
										$.getJSON(apiIbge + codigomunicipio + "8", function(dataIbge){
										}).done(function(data){
											$("#nomemunicipio").val(data.nome);
										}).fail(function(data){
											$("#small-nomemunicipio").addClass("text-danger").text("Preencha este campo manualmente");
										});
										
										var outrasIds = (data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs.length)-1;
										
										for(var i = 0; i < outrasIds; i++){
											if(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[outrasIds].id.root == "2.16.840.1.113883.13.237"){
												$("#cpf-form").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[outrasIds].id.extension);
											}
										}
										
										for(var i = 0; i < outrasIds; i++){
											if(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[i].id[1].extension == "D"){
												$("#cns-form").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[i].id[0].extension);
											};
										};
				
										$('#codigologradouro option[value="' + data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.streetNameType + '"]').attr({ selected : "selected" });
										$("#endereco").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.streetName);
										$("#complementoendereco").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.additionalLocator);
										$("#numeroendereco").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.houseNumber);
										$("#email").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.telecom[1].value);
										$("#telefone").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.telecom[2].value);
										$("#fa-button").removeClass().addClass("fa fa-check");
										$("#resultado-pesquisa").addClass("text-success").text("Cidadão encontrado na Base de Dados do CADSUS. Confira os dados no formulário abaixo.");
										$("#form-cidadao").fadeIn(500);
									}).fail(function(data){
										$("#fa-button").removeClass().addClass("fa fa-times");
										$("#resultado-pesquisa").addClass("text-danger").text("Cidadão não encontrado. Preencha os dados no formulário abaixo.");
										$("#form-cidadao").fadeIn(1000);
									});
								})

							}else{
								$("#small-cns").text(
								"O campo CNS deve conter 15 números")
								.addClass("text-danger");
							}
						}

					});
			

			// Pesquisa Cidadão pelo CPF
			$("#button-pesquisacpf").click(
					function() {
						var param = $("#cpf").val();
						var cpf = param.replace(/\D/g, '');
						var rootUrl = "http://localhost:8080/cidadao-resource";
						var apiIbge = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios/";
						if (cpf == "") {
							$("#small-cpf").text(
									"O campo CPF não pode ficar em branco")
									.addClass("text-danger");
						} else {
							var validaCpf = /^[0-9]{11}$/;
							if (validaCpf.test(cpf)) {
								$("#fa-buttoncpf").removeClass().addClass("loadersearch");
								$.getJSON(rootUrl + "/localCpf?cpf=" + cpf, function(data){
								}).done(function(data){
									$("#id").val(data.id);
									$("#form-cns").val(data.cns);
									$("#form-cpf").val(data.cpf);
									$("#nome").val(data.nome);
									$("#datanascimento").val(converteData(data.datanascimento));
									$("#sexo").val(data.sexo);
									$("#nomemae").val(data.nomemae);
									$("#nomepai").val(data.nomepai);
									$('#codigoraca option[value="' + data.codigoraca + '"]').attr({ selected : "selected" });
									$('#codigonacionalidade option[value="' + data.codigonacionalidades + '"]').attr({ selected : "selected" });
									$("#nomemunicipio").val(data.nomemunicipio);
									$('#codigologradouro option[value="' + data.codigologradouro + '"]').attr({ selected : "selected" });
									$("#endereco").val(data.endereco);
									$("#complementoendereco").val(data.complementoendereco);
									$("#numeroendereco").val(data.numeroendereco);
									$("#email").val(data.email);
									$("#telefone").val(data.telefone);
									$("#fa-buttoncpf").removeClass().addClass("fa fa-check");
									$("#resultado-pesquisacpf").addClass("text-success").text("Cidadão encontrado na Base de Dados Local. Confira os dados no formulário abaixo.");
									$("#form-cidadao").fadeIn(2000);
								}).fail(function(data){
									$.getJSON(rootUrl + "/cadsusCpf?cpf=" + cpf, function(data){
									}).done(function(data){
										$("#nome").val((data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.name.given));
										$("#datanascimento").val(converteDataCadsus(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.birthTime.value));
										$("#sexo").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.administrativeGenderCode.code);
										$("#nomemae").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.personalRelationship[0].relationshipHolder1.name.given);
										$("#nomepai").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.personalRelationship[1].relationshipHolder1.name.given);
										$("#codigoraca").val(parseInt((data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.raceCode.code)));
										$("#codigonacionalidade").val(parseInt(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.birthPlace.addr.country));
										var codigomunicipio = data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.city;
										$.getJSON(apiIbge + codigomunicipio + "8", function(dataIbge){
										}).done(function(data){
											$("#nomemunicipio").val(data.nome);
										}).fail(function(data){
											$("#small-nomemunicipio").addClass("text-danger").text("Preencha este campo manualmente");
										});
										
										var outrasIds = (data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs.length)-1;

										
										for(var i = 0; i < outrasIds; i++){
											if(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[outrasIds].id.root == "2.16.840.1.113883.13.237"){
												$("#cpf-form").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[outrasIds].id.extension);
											}
										}
										
										for(var i = 0; i < outrasIds; i++){
											if(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[i].id[1].extension == "D"){
												$("#cns-form").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.asOtherIDs[i].id[0].extension);
											};
										};
										$('#codigologradouro option[value="' + data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.streetNameType + '"]').attr({ selected : "selected" });
										$("#endereco").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.streetName);
										$("#complementoendereco").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.additionalLocator);
										$("#numeroendereco").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.addr.houseNumber);
										$("#email").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.telecom[1].value);
										$("#telefone").val(data["soap:Envelope"]["S:Body"].PRPA_IN201306UV02.controlActProcess.subject.registrationEvent.subject1.patient.patientPerson.telecom[2].value);
										$("#fa-buttoncpf").removeClass().addClass("fa fa-check");
										$("#resultado-pesquisacpf").addClass("text-success").text("Cidadão encontrado na Base de Dados do CADSUS. Confira os dados no formulário abaixo.");
										$("#form-cidadao").fadeIn(500);
									}).fail(function(data){
										$("#fa-buttoncpf").removeClass().addClass("fa fa-times");
										$("#resultado-pesquisa").addClass("text-danger").text("Cidadão não encontrado. Preencha os dados no formulário abaixo.");
										$("#form-cidadao").fadeIn(1000);
									});
								})

							}else{
								$("#small-cpf").text(
								"O campo CPF deve conter 11 números")
								.addClass("text-danger");
							}
						}

					});
			
			
			function converteData(parametro) {
				var ano = parametro.toString().substring(0, 4);
				var mes = parametro.toString().substring(5, 7);
				var dia = parametro.toString().substring(8, 10);
				var datanascimento = ano + "-" + mes + "-" + dia;
				return datanascimento;
			}
			
			function converteDataCadsus(parametro) {
				var ano = parametro.toString().substring(0,4);
				var mes = parametro.toString().substring(4,6);
				var dia = parametro.toString().substring(6,8);
				var datanascimento = ano + "-" + mes + "-" + dia;
				return datanascimento;
			}

		});
