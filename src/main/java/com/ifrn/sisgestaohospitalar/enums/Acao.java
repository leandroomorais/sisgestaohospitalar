package com.ifrn.sisgestaohospitalar.enums;

public enum Acao {
	INSERIDO(1, "Cidadão adicionado a lista de atendimentos"), FINALIZADO(2, "Atendimento finalizado"),
	TRIAGEM(3, "Triagem realizada"), ATENDIMENTO(4, "Atendimento realizado"),
	ATENDIMENTO_INICIO(5, "Iniciou atendimento"), OBSERVACAO(6, "Em observação"),
	NAOAGUARDOU(7, "Cidadão não aguardou o atendimento");

	private int codigo;
	private String descricao;

	private Acao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
