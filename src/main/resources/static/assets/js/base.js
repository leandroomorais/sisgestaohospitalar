function notificacao(title, message, placementFrom, placementAlign, state, style, url, target) {
	var content = {};
	content.message = message;
	content.title = title;
	if (style == "withicon") {
		content.icon = 'fa fa-bell';
	} else {
		content.icon = 'none';
	}
	content.url = url;
	content.target = target;
	$.notify(content, {
		type: state,
		placement: {
			from: placementFrom,
			align: placementAlign
		},
		time: 1000,
		delay: 0,
	});
}

function cancelar(title, text, type, redirect_url) {
	swal({
		title: title,
		text: text,
		icon: type,
		buttons: {

			cancel: {
				text: 'Não, retornar!',
				visible: true,
				className: 'btn btn-success btn-border'
			},

			confirm: {
				text: 'Sim, cancelar!',
				className: 'btn btn-warning'
			},
		}
	}).then((confirm) => {
		if (confirm) {
			$(location).attr('href', redirect_url)
		} else {
			swal.close();
		}
	});
};
