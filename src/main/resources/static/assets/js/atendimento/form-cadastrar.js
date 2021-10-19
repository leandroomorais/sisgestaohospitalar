$(document).ready(function() {
	var dataNascimento = $("#dt-nascimento").val();
	dataNascimento = moment(dataNascimento).format('YYYY-MM-DD');
	var anos = moment().diff(dataNascimento, 'years');
	var idade;
	if (anos == 1) {
		idade = anos + " ano ";
	} else if (anos > 1) {
		idade = anos + " anos ";
	}
	$("#idade-cidadao").text(idade);
});

$("#btn-cancela-guia-antendimento").click(function() {
	alerta("/cidadao/adicionar")
});

function alerta(redirect) {
	swal({
		title: 'Tem certeza que deseja cancelar?',
		text: "Se clicar em sim todos os dados serão apagados e você será redirecionado para a busca de cidadãos.",
		type: 'warning',
		buttons: {

			cancel: {
				text: 'Não, retornar!',
				visible: true,
				className: 'btn btn-success btn-border'
			},

			confirm: {
				text: 'Sim, cancelar!',
				className: 'btn btn-success'
			},
		}
	}).then((confirm) => {
		if (confirm) {
			$(location).attr('href', redirect)
		} else {
			swal.close();
		}
	});
};