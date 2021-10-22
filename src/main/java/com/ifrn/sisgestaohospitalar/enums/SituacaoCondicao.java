package com.ifrn.sisgestaohospitalar.enums;

public enum SituacaoCondicao {
	ATIVA("Ativa", "Doença/Alergia ativa"),
	LATENTE("Latente", "A  doença/alergia foi resolvida, porém pode trazer risco ao Cidadão"),
	CURADA("Curada", "O Cidadão não possui mais a doença/alergia");

	private SituacaoCondicao(String nome, String descricao) {
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
