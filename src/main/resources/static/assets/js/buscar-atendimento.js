$(document).ready(
	function(){
		
		$("#form-pesquisa-periodo").hide();
		// Muda o formulário de Pesquisa por CPF ao Clicar
			$("#pesquisa-data").click(
					function() {
						$("#form-pesquisa-periodo").hide();
						$("#form-pesquisa-data").fadeIn(1000);
						$("#pesquisa-data").removeClass().addClass(
								"btn btn-primary btn-xs");
						$("#pesquisa-periodo").removeClass().addClass("btn btn-primary btn-border btn-xs");
					});

			//Muda para o formulário de Pesquisa por CNS ao clicar
			$("#pesquisa-periodo").click(
					function() {
						$("#form-pesquisa-data").hide();
						$("#form-pesquisa-periodo").fadeIn(1000);
						$("#pesquisa-data").removeClass().addClass("btn btn-primary btn-border btn-xs");
						$("#pesquisa-periodo").removeClass().addClass(
						"btn btn-primary btn-xs");
					});
			
	}
)