/*$(document)
.ready(
		function() {
			var max_fields = 1000// Maximo valor a ser criado
			var wrapper = $(".input_fields_wrap"); // div a ser
													// colocada
			var add_button = $(".add_field_button"); // adicionando
														// os botões

			var x = 1; // inicial contador

			$(add_button)
					.click(
							function(e) { // quando clicar
								e.preventDefault();
								var length = wrapper.find("input:text").length;

								if (x < max_fields) { // enquanto for
														// menor crie
									x++; // increment
									$(wrapper)
											.append(
													// adicione input
													// box
													
													'<div class="form-group row col-sm-12">'
															+ '<div class =" col-sm-5">'
															+'<label><i>*</i> Medicação:</label>'
															+ '<input type="text" name="medicacao['
															+ (length + 1)
															+ '].nome" th:field="*{medicacao['
															+ (length + 1)
															+ '].nome}" class="form-control form-control-user" required>'
															+ '</div>'
															+ '<div class =" col-sm-2">'
															+'<label>Posologia:</label>'
															+ '<input type="text" name="medicacao['
															+ (length + 1)
															+ '].posologia" th:field="*{medicacao['
															+ (length + 1)
															+ '].posologia}" class="form-control form-control-user" required>'
															+ '</div>'
															+ '<div class =" col-sm-2">'
															+'<label>Duração:</label>'
															+ '<input type="text" name="medicacao['
															+ (length + 1)
															+ '].duracao" th:field="*{medicacao['
															+ (length + 1)
															+ '].duracao}" class="form-control form-control-user" required>'
															+ '</div>'
															+ '<div class =" col-sm-2">'
															+'<label>Forma:</label>'
															+ '<input type="text" name="medicacao['
															+ (length + 1)
															+ '].forma" th:field="*{medicacao['
															+ (length + 1)
															+ '].forma}" class="form-control form-control-user" required>'
															+ '</div>'
															+ '<a class="remove_field col-sm-1 mt-5 pt-0">'
															+ '<button class="btn btn-danger btn-circle" title="Remover Medicamento"><img alt="" src="/img/medicamento-remove.png" height="20px"></button>'
															+ '</a>'
															+'<hr class="col-sm-12">'
															+ '</div>'
											
											);
								}

								// Fazendo com que cada uma escreva seu
								// name
								// wrapper.find("input:text").each(function()
								// {
								// $(this).val($(this).attr('name'))
								// });
							});

			$(wrapper).on("click", ".remove_field", function(e) { // user
																	// click
																	// on
																	// remove
																	// text
				e.preventDefault();
				$(this).parent('div').remove();
				// x--;
			})
		}
	);


function excluirdado(valorClick) {

	if (valorClick === 1) {
		document.getElementById("limpar").value = null;
	}
} 
