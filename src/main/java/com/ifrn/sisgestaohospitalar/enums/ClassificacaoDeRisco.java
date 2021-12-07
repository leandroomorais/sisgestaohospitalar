package com.ifrn.sisgestaohospitalar.enums;

public enum ClassificacaoDeRisco {

	AZUL("AZUL", "Casos não urgentes", 1), VERDE("VERDE", "Casos pouco urgentes", 2),
	AMARELO("AMARELO", "Casos de urgência", 3), LARANJA("LARANJA", "Casos muito urgentes", 4),
	VERMELHO("VERMELHO", "Casos de emergência", 5);

	String nome;
	String descricao;
	int valor;

	private ClassificacaoDeRisco(String nome, String descricao, int valor) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
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

	public int getPrioridade() {
		return valor;
	}

	public void setPrioridade(int prioridade) {
		this.valor = prioridade;
	}

}
