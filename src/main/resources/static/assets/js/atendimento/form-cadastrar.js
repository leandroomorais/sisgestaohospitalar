$("#btn-cancela-guia-antendimento").click(function() {
	alerta("/atendimento/listar")
});

function alerta(redirect) {
	swal({
		title: 'Deseja cancelar a operação?',
		text: "Se clicar em sim todos os dados serão apagados.",
		type: 'warning',
		buttons: {
			confirm: {
				text: 'Sim, cancelar!',
				className: 'btn btn-warning'
			},
			cancel: {
				text: 'Não, retornar!',
				visible: true,
				className: 'btn btn-primary'
			}
		}
	}).then((confirm) => {
		if (confirm) {
			$(location).attr('href', redirect)
		} else {
			swal.close();
		}
	});
};