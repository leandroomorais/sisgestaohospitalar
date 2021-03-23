package com.ifrn.sisgestaohospitalar.enums;

public enum TipoProfissional {

	ATENDENTE("Atendente"), TECNICO("Técnico"), ENFERMEIRO("Enfermeiro"), MEDICO("Médico"),
	ADMINISTRADOR("Administrador");

	private String descricao;

	TipoProfissional(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
