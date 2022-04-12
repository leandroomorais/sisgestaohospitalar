//Funçao que adiciona Procedimentos automaticamente
$("#sinaisVitais-pressaoDiastolica").change(function() {
	var pressaoSistolica = $("#sinaisVitais-pressaoSistolica").val();
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimento(idAtendimento, 301100039, tipoServico, quantidade);
})

$("#sinaisVitais-glicemiaCapilar").change(function() {
	var tipoServico = "TRIAGEM";
	var quantidade = 1;
	submitProcedimento(idAtendimento, 214010015, tipoServico, quantidade);
})