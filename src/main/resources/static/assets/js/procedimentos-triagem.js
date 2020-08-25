//Funções para o autocomplete dos Procedimentos

//Variável que guarda os Ids dos Procedimentos adicionados;
//var idProcedimentos = [];


$(document).ready(function() {
					// Adiciona o Procedimento de Avaliação Antropométrica caso
					// peso seja preenchido;
					$('#peso').change(function() {
						var rootUrl = "/procedimento-resource/searchByCodigo?term=";
						var procedimento = {};
						$.getJSON(rootUrl + "0101040024",function(data){
									
						})
						.done(function(data){
							procedimento.id = data.id;
							$.ajax({
								method : "POST",
								url : "/triagem/add-procedimento",
								data : procedimento,
									success : function() {
										console.log('Enviado com sucesso');
										$("#table-procedimento-triagem").append('<tr><td>0101040024</td><td>AVALIAÇÃO ANTROPOMÉTRICA</td><td><button type="button" disabled="disabled" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button></td></tr>');
									},
									error : function(xhr) {
										console.log('Erro >', xhr.reponseText);
									},
							})
						});			
					});
					// Adicionao procedimento de Aferição de Pressão Arterial
					// caso o campo Pressão Arterial seja preenchido
					$('#pressaoarterial').change(function() {
						var rootUrl = "/procedimento-resource/searchByCodigo?term=";
						var procedimento = {};
						$.getJSON(rootUrl + "0301100039",function(data){
									
						})
						.done(function(data){
							procedimento.id = data.id;
							$.ajax({
								method : "POST",
								url : "/triagem/add-procedimento",
								data : procedimento,
									success : function() {
										console.log('Enviado com sucesso');
										$("#table-procedimento-triagem").append('<tr><td>0301100039</td><td>AFERICAO DE PRESSAO ARTERIAL</td><td><button type="button" disabled="disabled" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button></td></tr>');
									},
									error : function(xhr) {
										console.log('Erro >', xhr.reponseText);
									},
							})
						});
					});
					// Adicionao procedimento de Glicemia Capilar
					// caso o campo Glicemia Capilar seja preenchido
					$('#glicemiacapilar').change(function() {
						var rootUrl = "/procedimento-resource/searchByCodigo?term=";
						var procedimento = {};
						$.getJSON(rootUrl + "0214010015",function(data){
									
						})
						.done(function(data){
							procedimento.id = data.id;
							$.ajax({
								method : "POST",
								url : "/triagem/add-procedimento",
								data : procedimento,
									success : function() {
										console.log('Enviado com sucesso');
										$("#table-procedimento-triagem").append('<tr><td>0214010015</td><td>GLICEMIA CAPILAR</td><td><button type="button" disabled="disabled" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button></td></tr>');					
									},
									error : function(xhr) {
										console.log('Erro >', xhr.reponseText);
									},
							})
						});
					});
					// Autocomplete dos Procedimentos
					$(function() { 
						$("#procedimentos-triagem")
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
								$("#procedimentos-triagem").val(ui.item.nomeprocedimento);
								return false;
							},
							select : function(event, ui) {
								console.log("Id do Procedimento: " + ui.item.id);
								var procedimento = {};
								procedimento.id = ui.item.id;
								$.ajax({
									method : "POST",
									url : "/triagem/add-procedimento",
									data : procedimento,
									success : function() {
										console.log('Enviado com sucesso');
									},
									error : function(xhr) {
										console.log('Erro >', xhr.reponseText);
									},
								})
								
								$("#procedimentos-triagem").val("");
							
								$("#table-procedimento-triagem").append('<tr id="'
									+ ui.item.id
									+ '"><td>'
									+ ui.item.codigoprocedimento
									+ '</td>'
									+ '<td>'
									+ ui.item.nomeprocedimento
									+ '</td>'
									+ '<td>'
									+ '<button onclick="removeProcedimentoTriagem(this)" type="button" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button>'
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
						removeProcedimentoTriagem = function(item) {
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
										url : "/triagem/delete-procedimento",
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
