//Submit para o formulário recuperar-senha
$("#form-recuperar-senha")
		.submit(
				function(evt) {
					// bloquear comportamento padrão do formulário
					evt.preventDefault();

					var profissional = {};
					profissional.email = $("#email").val();
					console.log('Email: ', profissional);
					$
							.ajax({
								method : "POST",
								url : "/recuperar-senha",
								data : profissional,
								beforeSend: function(){
									$("#form-recuperar-senha").hide();
									$("#loader-form").addClass("loader loader-lg is-loading-lg").show();
									
								},
								success : function() {
									$("#alert")
											.addClass("text-success")
											.text(
													"Um email foi enviado para "
															+ profissional.email
															+ " contendo as instruções para redefinição de senha.");
									console.log('Email enviado com sucesso');
								},
								error : function(xhr) {
									$("#alert")
											.addClass("text-danger")
											.text(
													"Não foi possível enviar o email de recuperação. Verifique se o email digitado está cadastrado");
									console.log('Erro >', xhr.reponseText);
								},
								complete : function(){
									$("#loader-form").fadeOut(800, function(){
										$("#form-recuperar-senha").fadeIn(250);
										$("#loader-form").removeClass("loader");
									})
								}
							})

				});