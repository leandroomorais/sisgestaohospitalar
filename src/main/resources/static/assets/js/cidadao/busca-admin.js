$(document).ready(
	function() {
		$("#form-pesquisa-cpf").hide();
		$("#form-pesquisa-nome").hide();
		// Muda o formulário de Pesquisa por CPF ao Clicar

		$("#cns").mask("000.0000.0000.0000");
		$("#cpf").mask("000.000.000-00");

		$("#pesquisa-cpf").click(
			function() {
				$("#cns").val("");
				$("#nome").val("");
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
				$("#cpf").val("");
				$("#nome").val("");
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
				$("#cns").val("");
				$("#cpf").val("");
				$("#form-pesquisa-cpf").hide();
				$("#form-pesquisa-cns").hide();
				$("#form-pesquisa-nome").fadeIn(1000);
				$("#pesquisa-cpf").removeClass().addClass("btn btn-primary btn-border btn-xs");
				$("#pesquisa-nome").removeClass().addClass(
					"btn btn-primary btn-xs");
				$("#pesquisa-cns").removeClass().addClass(
					"btn btn-primary btn-border btn-xs");
			});

		$("#table-list-cidadao").DataTable();
	}
)