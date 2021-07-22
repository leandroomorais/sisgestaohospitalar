package com.ifrn.sisgestaohospitalar.enums;

public enum TipoServico {
	Triagem("Triagem", "Triagem"), AtendimentoMedico("Atendimento Médico","Atd. Médico"),
	AdminMedicamentos("Administração de Medicamentos","Adm. Medicamento"), Observacao("Observação","Observação"), Curativo("Curativo","Curativo"),
	Inativo("Inativo","Inativo");

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
