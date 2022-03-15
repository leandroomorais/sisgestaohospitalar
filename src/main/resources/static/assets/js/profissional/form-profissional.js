$(document).ready(
	function() {
		//Aplica máscaras aos inputs do Formulário
		$("#cns").mask('000.0000.0000.0000');
		$("#cpf").mask('000.000.000-00');

		$("#table-list-profissionais").DataTable();
	}
)
