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