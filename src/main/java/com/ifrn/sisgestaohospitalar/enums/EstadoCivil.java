package com.ifrn.sisgestaohospitalar.enums;

public enum EstadoCivil {

	SOLTEIRO("Solteiro(a)"), CASADO("Casado(a)"), SEPARADO("Separado(a)"), DIVORCIADO("Divorciado(a)"),
	VIUVO("Viuvo(a)");

	private String nome;

	private EstadoCivil(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
