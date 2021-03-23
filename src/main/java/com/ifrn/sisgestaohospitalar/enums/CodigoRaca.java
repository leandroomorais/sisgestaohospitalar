package com.ifrn.sisgestaohospitalar.enums;

public enum CodigoRaca {

	BRANCA("BRANCA", 1), PRETA("PRETA", 2), PARDA("PARDA", 3), AMARELA("AMARELA", 4), INDIGENA("INDÍGENA", 5),
	SEMINFORMACAO("SEM INFORMAÇÃO", 99);
	private String descricao;
	private int codigo;
	
	private CodigoRaca(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public int getCodigo() {
		return codigo;
	}

}
