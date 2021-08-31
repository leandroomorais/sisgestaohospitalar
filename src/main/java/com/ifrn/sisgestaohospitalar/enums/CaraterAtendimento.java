package com.ifrn.sisgestaohospitalar.enums;

public enum CaraterAtendimento {

	ELETIVO(1, "Eletivo"), URGENCIA(2, "Urgência"),
	ACIDENTETRABALHO(3, "Acidente no local de trabalho ou a serviço da empresa"),
	ACIDENTETRAJETO(4, "Acidente no trajeto para o trabalho"),
	ACIDENTETRANSITO(5, "Outros tipos de acidente de trânsito"),
	LESOES(6, "Outros tipos de lesões e envenenamentos por agentes químicos ou físicos");

	private int codigo;
	private String descricao;

	CaraterAtendimento(int codigo, String descricao) {
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
