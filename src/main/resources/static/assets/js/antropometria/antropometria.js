var idAtendimento = $("#id-atendimento").val();
var idProntuario = $("#id-prontuario").val();

$("#form-antropometria").submit(function(evt) {
	evt.preventDefault();
	var antropometria = {};
	antropometria.altura = $("#altura").val();
	antropometria.peso = $("#peso").val();
	antropometria.imc = $("#valor-imc").val();
	antropometria.perimetroCefalico = $("#perimetrocefalico").val();
	antropometria['atendimento'] = idAtendimento;
	antropometria['prontuario'] = idProntuario;

	$.ajax({
		url: '/antropometria/',
		method: 'post',
		data: antropometria,
		beforeSend: function() {
			removeInvalidFedbackAntropometria();
		},
		success: function() {
			$("#table-antropometrias").DataTable().ajax.reload();
			$.notify({
				// options
				icon: 'flaticon-success',
				title: 'SUCESSO',
				message: 'O registro de Antropometria foi salvo',
				target: '_blank'
			}, {
				// settings
				element: 'body',
				position: null,
				type: "success",
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
			cardInfoCidadao(idAtendimento);
		},
		statusCode: {
			422: function(xhr) {
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val) {
					$.notify({
						// options
						icon: 'flaticon-exclamation',
						title: 'ATENÇÃO',
						message: val,
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
					$("input[name='" + key + "']").parent().parent().addClass("has-error has-feedback");
				})
			}
		}
	})
})

function atualizaAntropometria() {
	$("#table-antropometrias").DataTable({
		responsive: true,
		paging: true,
		searching: false,
		order: [0, "asc"],
		ordering: false,
		ajax: {
			url: '/antropometria/listar/prontuario/' + idProntuario,
			dataSrc: '',
		},
		columns: [
			{
				title: '',
				visible: false,
				data: 'dataCadastro',
			},
			{
				title: 'PESO',
				data: 'peso',
				mRender: function(data) {
					if (data != null) {
						return data + " kg";
					} else {
						return "";
					}
				}
			},
			{
				title: 'ALTURA',
				data: 'altura',
				mRender: function(data) {
					if (data != null) {
						return data + " cm";
					} else {
						return "";
					}
				}
			},
			{
				title: 'PERIMETRO CEFÁLICO',
				data: 'perimetroCefalico',
				mRender: function(data) {
					if (data != null) {
						return data + " cm";
					} else {
						return "";
					}
				}
			},
			{
				title: 'IMC',
				data: 'imc',
				mRender: function(data) {
					if (data != null) {
						if (data < 17) {
							return "<span class='badge badge-danger'>" + data + "</span>";
						} else if (data > 17 && data < 18.49) {
							return "<span class='badge badge-warning'>" + data + "</span>";
						} else if (data > 18.5 && data < 24.99) {
							return "<span class='badge badge-success'>" + data + "</span>";
						} else if (data > 25 && data < 29.99) {
							return "<span class='badge badge-warning'>" + data + "</span>";
						} else if (data > 30 && data < 34.99) {
							return "<span class='badge badge-danger'>" + data + "</span>";
						} else if (data > 35 && data < 39.99) {
							return "<span class='badge badge-danger'>" + data + "</span>";
						} else if (data > 40) {
							return "<span class='badge badge-danger'>" + data + "</span>";
						}
					} else {
						return "";
					}
				}
			},
			{
				title: 'DATA',
				data: 'dataCadastro',
				mRender: function(data) {
					if (data != null) {
						var dataCadastro = new Date(data);
						dataCadastro = dataCadastro.toLocaleDateString('pt-BR');
						return dataCadastro;
					} else {
						return "";
					}
				}
			},
			{
				title: 'PROFISSIONAL',
				data: 'profissional.nome',
				mRender: function(data) {
					if (data != null) {
						return data;
					} else {
						return "";
					}
				}
			},

		]

	})
}

function limpaAntropometria() {
	$("#altura").val("");
	$("#peso").val("");
	$("#valor-imc").val("");
	$("#perimetrocefalico").val("");
}

function removeInvalidFedbackAntropometria() {
	$("#form-antropometria input").each(
		function(index) {
			var str = $(this).parent().parent().attr("class");
			if (str.match(/has-error/)) {
				$(this).parent().parent().removeClass("has-error has-feedback");
			}
		}
	);
}

//Função que calcula o IMC
$("#altura").change(function() {
	var peso = $("#peso").val();
	var altura = $("#altura").val();
	var imc = (peso / (altura * altura)) * 10000;
	$("#imc").text(imc.toFixed(2));
	$("#valor-imc").val(imc.toFixed(2));
	if (imc < 17) {
		$("#imc-desc").text("Muito abaixo do peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 17 && imc < 18.49) {
		$("#imc-desc").text("Abaixo do Peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-warning");
	} else if (imc > 18.5 && imc < 24.99) {
		$("#imc-desc").text("Normal");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-success");
	} else if (imc > 25 && imc < 29.99) {
		$("#imc-desc").text("Acima do Peso");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-warning");
	} else if (imc > 30 && imc < 34.99) {
		$("#imc-desc").text("Obesidade I");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 35 && imc < 39.99) {
		$("#imc-desc").text("Obesidade II");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	} else if (imc > 40) {
		$("#imc-desc").text("Obesidade III");
		$("#div-imc").removeClass();
		$("#div-imc").addClass("card card-danger");
	}
});
//Fim da função
