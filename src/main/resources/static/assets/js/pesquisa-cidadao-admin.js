$(document).ready(
	function(){
		
		$("#form-pesquisa-cpf").hide();
		$("#form-pesquisa-nome").hide();
		// Muda o formulário de Pesquisa por CPF ao Clicar
			$("#pesquisa-cpf").click(
					function() {
						$("#form-pesquisa-cns").hide();
						$("#form-pesquisa-nome").hide();
						$("#form-pesquisa-cpf").fadeIn(1000);
						$("#pesquisa-cpf").removeClass().addClass("btn btn-primary btn-xs");
						$("#pesquisa-cns").removeClass().addClass(
								"btn btn-primary btn-border btn-xs");
						$("#pesquisa-nome").removeClass().addClass(
								"btn btn-primary btn-border btn-xs");
					});
			
			// Muda o formulário de Pesquisa por CNS ao Clicar
			$("#pesquisa-cns").click(
					function() {
						$("#form-pesquisa-cpf").hide();
						$("#form-pesquisa-nome").hide();
						$("#form-pesquisa-cns").fadeIn(1000);
						$("#pesquisa-cpf").removeClass().addClass("btn btn-primary btn-border btn-xs");
						$("#pesquisa-cns").removeClass().addClass(
								"btn btn-primary btn-xs");
						$("#pesquisa-nome").removeClass().addClass(
								"btn btn-primary btn-border btn-xs");
					});
			
			// Muda o formulário de Pesquisa por NOME ao Clicar
			$("#pesquisa-nome").click(
					function() {
						$("#form-pesquisa-cpf").hide();
						$("#form-pesquisa-cns").hide();
						$("#form-pesquisa-nome").fadeIn(1000);
						$("#pesquisa-cpf").removeClass().addClass("btn btn-primary btn-border btn-xs");
						$("#pesquisa-nome").removeClass().addClass(
								"btn btn-primary btn-xs");
						$("#pesquisa-cns").removeClass().addClass(
								"btn btn-primary btn-border btn-xs");
					});

		
			
	}
)