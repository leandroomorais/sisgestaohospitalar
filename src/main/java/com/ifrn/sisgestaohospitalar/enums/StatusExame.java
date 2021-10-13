package com.ifrn.sisgestaohospitalar.enums;

public enum StatusExame {

	SOLICITADO("Solicitado"), AVALIADO("Avaliado"), FINALIZADO("Finalizado");

	private String descricao;

	private StatusExame(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
