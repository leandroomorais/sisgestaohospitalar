package com.ifrn.sisgestaohospitalar.enums;

public enum CriticidadeAlergia {

	ALTA("Alta"), BAIXA("Baixa"), NAOINFORMADA("Não informada");

	private String nivel;

	private CriticidadeAlergia(String nivel) {
		this.nivel = nivel;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
