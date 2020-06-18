//Funções para o autocomplete dos Procedimentos

//Variável que guarda os Ids dos Procedimentos adicionados;
var idProcedimentos = [];

$(document)
		.ready(
				function() {
					// Adiciona o Procedimento de Avaliação Antropométrica caso
					// peso seja preenchido;
					$('#peso')
					.change(
							function() {
								$("#table-procedimento")
										.append(
												'<tr><td>0101040024</td><td>AVALIAÇÃO ANTROPOMÉTRICA</td><td><button type="button" disabled="disabled" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button></td></tr>');
								idProcedimentos.push("957");
								console.log(idProcedimentos);
								$("#ids_procedimentos").val(
										idProcedimentos.toString());
							});
					// Adicionao procedimento de Aferição de Pressão Arterial
					// caso o campo Pressão Arterial seja preenchido
					$('#pressaoarterial')
					.change(
							function() {
								$("#table-procedimento")
										.append(
												'<tr><td>0301100039</td><td>AFERICAO DE PRESSAO ARTERIAL</td><td><button type="button" disabled="disabled" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button></td></tr>');
								idProcedimentos.push("2201");
								console.log(idProcedimentos);
								$("#ids_procedimentos").val(
										idProcedimentos.toString());
							});
					// Adicionao procedimento de Glicemia Capilar
					// caso o campo Glicemia Capilar seja preenchido
					$('#glicemiacapilar')
					.change(
							function() {
								$("#table-procedimento")
										.append(
												'<tr><td>0214010015</td><td>GLICEMIA CAPILAR</td><td><button type="button" disabled="disabled" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button></td></tr>');
								idProcedimentos.push("2056");
								console.log(idProcedimentos);
								$("#ids_procedimentos").val(
										idProcedimentos.toString());
							});
					// Autocomplete dos Procedimentos
					$(function() {
						$("#procedimentos")
								.on(
										"keydown",
										function(event) {
											$(this).autocomplete("instance")._renderItem = function(
													select, item) {
												return $("<option>")
														.append(
																"<div>"
																		+ item.codigoprocedimento
																		+ " - "
																		+ item.nomeprocedimento
																		+ "</div>")
														.appendTo(select);
											};
										})
								.autocomplete(
										{
											source : "/procedimento-resource/search",
											focus : function(event, ui) {
												$("#procedimentos")
														.val(
																ui.item.nomeprocedimento);
												return false;
											},
											select : function(event, ui) {
												console
														.log("Id do Procedimento: "
																+ ui.item.id);
												idProcedimentos
														.push(ui.item.id.toString());
												$("#ids_procedimentos").val(
														idProcedimentos
																.toString());
												$("#procedimentos").val("");
												$("#table-procedimento")
														.append(
																'<tr id="'
																		+ ui.item.id
																		+ '"><td>'
																		+ ui.item.codigoprocedimento
																		+ '</td>'
																		+ '<td>'
																		+ ui.item.nomeprocedimento
																		+ '</td>'
																		+ '<td>'
																		+ '<button type="button" onclick="remove(this)" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button>'
																		+ '</td>'
																		+ '</tr>');
												console
														.log("Array Ids Procedimentos: "
																+ idProcedimentos);
												return false;
											}
										});
					});
					
					// Função para remover os Procedimentos da Tabela de
					// Procedimentos Realizados e do Array de Ids de
					// Procedimentos
					(function($) {
						remove = function(item) {
							swal({
								title: 'Tem certeza que deseja remover esse procedimento?',
								type: 'warning',
								buttons:{
									cancel: {
										visible: true,
										text : 'Não, cancelar!',
										className: 'btn btn-danger'
									},        			
									confirm: {
										text : 'Sim, remova!',
										className : 'btn btn-success'
									}
								}
							}).then((willDelete) => {
								if (willDelete) {
									var tr = $(item).closest('tr');
									var id = $(item).parent().parent().attr("id");
									var index = idProcedimentos.indexOf(id);
									idProcedimentos.splice(idProcedimentos.indexOf(id), 1);
									tr.fadeOut(400, function() {
										tr.remove();
									});
									console.log(idProcedimentos);
									$("#ids_procedimentos").val(idProcedimentos);
									swal("Procedimento removido com sucesso!", {
										icon: "success",
										buttons : {
											confirm : {
												className: 'btn btn-success'
											}
										}
									});
								}
							});
							
							
							return false;
						}
					})(jQuery);
				})
