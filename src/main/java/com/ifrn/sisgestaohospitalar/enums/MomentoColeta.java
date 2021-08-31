package com.ifrn.sisgestaohospitalar.enums;

public enum MomentoColeta {
	
	NAOREALIZADA("Coleta não realizada","Coleta não realizada") , JEJUM("Em jejum",""), PREPRANDIAL("Pré-prandial","Antes da refeição"), POSPRANDIAL("Pós-prandial","Depois das refeições");
	
	private String nome;
	private String descricao;

	private MomentoColeta(String nome, String descricao) {
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
