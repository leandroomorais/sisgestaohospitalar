package com.ifrn.sisgestaohospitalar.enums;

public enum SituacaoDoenca {
	ATIVA("Ativa", "Doença/Comorbidade ativa"), EMTRATAMENTO("Em tratamento", "Doença/Comorbidade em tratamento"),
	CURADA("Curada", "O Cidadão não possui mais a doença/comorbidade"),
	LATENTE("Latente", "A  doença/comorbidade foi resolvida, porém pode trazer risco ao Cidadão");

	private SituacaoDoenca(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	private String nome;

	private String descricao;

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
