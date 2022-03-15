package com.ifrn.sisgestaohospitalar.dto;

import javax.persistence.Column;


public class LotacaoDTO {

	@Column(nullable = false, length = 7)
	private String cnes;

	private String codigoIne;

	@Column(nullable = false, length = 6)
	private String codigoCBO;

	private String microArea;

	/** Getters and setters */

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getCodigoIne() {
		return codigoIne;
	}

	public void setCodigoIne(String codigoIne) {
		this.codigoIne = codigoIne;
	}

	public String getCodigoCBO() {
		return codigoCBO;
	}

	public void setCodigoCBO(String codigoCBO) {
		this.codigoCBO = codigoCBO;
	}

	public String getMicroArea() {
		return microArea;
	}

	public void setMicroArea(String microArea) {
		this.microArea = microArea;
	}

}
