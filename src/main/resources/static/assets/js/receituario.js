var medicamentos = [];

//var medEssenciais = {
//	url:"/medicamento-resource/search",
//	getValue: "principioAtivoDroga",
//	
//	list: {
//    match: {
//      enabled: true
//    }
//  },
//
//  theme: "square",
//};
//
//$("#nome-medicamento").easyAutocomplete(medEssenciais);
//$("document").ready(function(){
//	$(function(){
//		$("#nome-medicamento").on("keydown", function(event){
//			$(this).autocomplete("instance")._renderItem = function(select,item){
//				return $("<option>").append(item.principioAtivoDroga).appendTo(select);
//			};
//		}).autocomplete({
//			source:"/medicamento-resource/search",
//			
//		});
//	})
//})


$( "#add-medicamento").click(function() {
	
	var medicamento = {};
	
	medicamento.nome = $("#nome-medicamento").val();
	medicamento.concentracao = $("#concentracao").val();
	medicamento.posologia = $("#posologia").val();
	medicamento.forma = $("#forma").val();
	medicamentos.push(medicamento);
	console.log("ArrayMedicamentos > ",medicamentos);
	var json = JSON.stringify(medicamentos);
	console.log("Stringfy > ",json);
	$("#medicamentos").val(json);
	$("#table-medicamentos").append('<tr><td>' + medicamento.nome + '</td> + <td>' + medicamento.concentracao + '</td> <td>' + medicamento.forma + '</td><td>' + medicamento.posologia + '</td>');
	$("#nome-medicamento").val("");
	$("#concentracao").val("");
	$("#posologia").val("");
	$("#forma").val("");
	$("#modalContactForm").modal("hide");
});
