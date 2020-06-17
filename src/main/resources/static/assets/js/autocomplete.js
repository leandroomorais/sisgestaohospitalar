$(document)
		.ready(
				function() {
					// Auto complete Motivo da Consulta
					$(function() {
						function split(val) {
							return val.split(/,\s*/);
						}
						function extractLast(term) {
							return split(term).pop();
						}

						$("#motivo")
								// don't navigate away from the field on tab
								// when selecting an item
								.on(
										"keydown",
										function(event) {
											if (event.keyCode === $.ui.keyCode.TAB
													&& $(this).autocomplete(
															"instance").menu.active) {
												event.preventDefault();
											}
										})
								.autocomplete(
										{
											source : function(request, response) {
												$
														.getJSON(
																"http://localhost:8080/ciap2-resource/search",
																{
																	term : extractLast(request.term.toUpperCase())
																}, response);
											},
											search : function() {
												// custom minLength
												var term = extractLast(this.value);
												if (term.length < 2) {
													return false;
												}
											},
											focus : function() {
												// prevent value inserted on
												// focus
												return false;
											},
											select : function(event, ui) {
												var terms = split(this.value);
												// remove the current input
												terms.pop();
												// add the selected item
												terms.push(ui.item.value);
												// add placeholder to get the
												// comma-and-space at the end
												terms.push("");
												this.value = terms.join(", ");
												return false;
											}
										});
					});
				})
