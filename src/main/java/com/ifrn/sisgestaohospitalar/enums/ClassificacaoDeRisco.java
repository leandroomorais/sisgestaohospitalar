package com.ifrn.sisgestaohospitalar.enums;

public enum ClassificacaoDeRisco {

	AZUL("AZUL", "Casos não urgentes"), VERDE("VERDE", "Casos pouco urgentes"), AMARELO("AMARELO", "Casos de urgência"),
	LARANJA("LARANJA", "Casos muito urgentes"), VERMELHO("VERMELHO", "Casos de emergência");

	String nome;
	String descricao;

	private ClassificacaoDeRisco(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
