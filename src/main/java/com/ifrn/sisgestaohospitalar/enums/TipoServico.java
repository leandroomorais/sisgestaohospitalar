package com.ifrn.sisgestaohospitalar.enums;

public enum TipoServico {
	TRIAGEM("Triagem", "Triagem"), CONSULTA("Consulta","Consulta"),
	ADMINMEDICAMENTOS("Administração de Medicamentos","Adm. Medicamento"), CURATIVO("Curativo","Curativo"),
	INATIVO("Inativo","Inativo");

	private String descricao;
	private String resumo;
	
	TipoServico(String descricao, String resumo) {
		this.descricao = descricao;
		this.resumo = resumo;
	}

	public String getDescricao() {
		return descricao;
	}	
	
	public String getResumo() {
		return resumo;
	}
}
