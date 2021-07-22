package com.ifrn.sisgestaohospitalar.enums;

public enum StatusAtendimento {

	AGUARDANDOATENDIMENTO("Aguardando Atendimento"), EMATENDIMENTO("Em Atendimento"),
	OBSERVACAO("Paciente em observação"), NAOAGUARDOU("Não aguardou atendimento"), FINALIZADO("Finalizado");

	private String descricao;

	StatusAtendimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
