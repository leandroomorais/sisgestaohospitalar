package com.ifrn.sisgestaohospitalar.enums;

public enum StatusAtendimento {

	AGUARDANDOTRIAGEM("Aguardando Triagem"), EMATENDIMENTO("Em Atendimento"),
	AGUARDANDOATDMEDICO("Aguardando Atendimento Médico"),
	AGUARDANDOADMMEDICAMENTOS("Aguardando Administração de Medicamentos"), FINALIZADO("Finalizado");
	
	private String descricao;

	StatusAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
