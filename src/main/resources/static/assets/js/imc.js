//Realiza cálculo do IMC do Cidadão
$(document).ready(function() {
	$("#altura").change(function() {
		var peso = $("#peso").val();
		var altura = $("#altura").val();
		var imc = (peso / (altura * altura)) * 10000;
		$("#imc").text(imc.toFixed(2));
		$("#valor-imc").val(imc.toFixed(2));
		if (imc < 17) {
			$("#imc-desc").text("Muito abaixo");
		} else if (imc > 17 && imc < 18.49) {
			$("#imc-desc").text("Abaixo do Peso");
		} else if (imc > 18.5 && imc < 24.99) {
			$("#imc-desc").text("Normal");
		} else if (imc > 25 && imc < 29.99) {
			$("#imc-desc").text("Acima do Peso");
		} else if (imc > 30 && imc < 34.99) {
			$("#imc-desc").text("Obesidade I");
		} else if (imc > 35 && imc < 39.99) {
			$("#imc-desc").text("Obesidade II");
		} else if (imc > 40) {
			$("#imc-desc").text("Obesidade III");
		}
	});
});