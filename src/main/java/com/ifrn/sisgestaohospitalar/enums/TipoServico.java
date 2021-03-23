package com.ifrn.sisgestaohospitalar.enums;

public enum TipoServico {
	EscutaInicial("Esculta Inicial"), AtendimentoMedico("Atendimento Médico"),
	AdminMedicamentos("Administração de Medicamentos"), Observacao("Observação");

	private String descricao;
	
	
	TipoServico(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}	
}
