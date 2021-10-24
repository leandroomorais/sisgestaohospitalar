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
		ordering: false,
		ajax: {
			url: '/antropometria/listar/prontuario/' + idProntuario,
			dataSrc: '',
		},
		columns: [
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
						return data;
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