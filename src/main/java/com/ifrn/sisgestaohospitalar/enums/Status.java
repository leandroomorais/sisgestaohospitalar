package com.ifrn.sisgestaohospitalar.enums;

public enum Status {

	AGUARDANDOATENDIMENTO("Aguardando Atendimento"), EMATENDIMENTO("Em Atendimento"),
	NAOAGUARDOU("Não aguardou atendimento"), OBSERVACAO("Em observação"), FINALIZADO("Finalizado");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
