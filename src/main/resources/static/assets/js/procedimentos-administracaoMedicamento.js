//Funções para o autocomplete dos Procedimentos

//Variável que guarda os Ids dos Procedimentos adicionados;
//var idProcedimentos = [];


$(document).ready(function() {
					// Autocomplete dos Procedimentos
					$(function() { 
						$("#procedimentos-admin-medicamento")
						.on("keydown", function(event) {
							$(this).autocomplete("instance")._renderItem = function(select, item) {
								return $("<option>").append("<div>"
									+ item.codigoprocedimento
									+ " - "
									+ item.nomeprocedimento
									+ "</div>").appendTo(select);
							};
						})
						.autocomplete({
							source : "/procedimento-resource/search",
							focus : function(event, ui) {
								$("#procedimentos-admin-medicamento").val(ui.item.nomeprocedimento);
								return false;
							},
							select : function(event, ui) {
								console.log("Id do Procedimento: " + ui.item.id);
								var procedimento = {};
								procedimento.id = ui.item.id;
								$.ajax({
									method : "POST",
									url : "/administracao-medicamento/add-procedimento",
									data : procedimento,
									success : function() {
										console.log('Enviado com sucesso');
									},
									error : function(xhr) {
										console.log('Erro >', xhr.reponseText);
									},
								})
								
								$("#procedimentos-admin-medicamento").val("");
							
								$("#table-procedimento-admin-medicamento").append('<tr id="'
									+ ui.item.id
									+ '"><td>'
									+ ui.item.codigoprocedimento
									+ '</td>'
									+ '<td>'
									+ ui.item.nomeprocedimento
									+ '</td>'
									+ '<td>'
									+ '<button type="button" onclick="removeProcedimentoAdminMedicamento(this)" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button>'
									+ '</td>'
									+ '</tr>');
									
									return false;
									
									
							}
						});
					});
					
					// Função para remover os Procedimentos da Tabela de
					// Procedimentos Realizados e do Array de Procedimentos
					(function($) {
						var procedimento = {};
						removeProcedimentoAdminMedicamento = function(item) {
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
									procedimento.id = id;
									$.ajax({
										method : "DELETE",
										url : "/administracao-medicamento/delete-procedimento",
										data : procedimento,
										success : function() {
											console.log('Deletado com sucesso');
										},
										error : function(xhr) {
											console.log('Erro >', xhr.reponseText);
										},
									});
									
									tr.fadeOut(400, function() {
										tr.remove();
									});
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
