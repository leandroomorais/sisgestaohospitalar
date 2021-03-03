package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CidSigtap {

	@Id
	private String codigocid;

	private String nomecid;

	private char tipoagravo;

	private char tiposexo;

	private char tipoestadio;

	private int valorcamposirradiados;

	/** Getters and setters */

	public String getCodigocid() {
		return codigocid;
	}

	public void setCodigocid(String codigocid) {
		this.codigocid = codigocid;
	}

	public String getNomecid() {
		return nomecid;
	}

	public void setNomecid(String nomecid) {
		this.nomecid = nomecid;
	}

	public char getTipoagravo() {
		return tipoagravo;
	}

	public void setTipoagravo(char tipopagravo) {
		this.tipoagravo = tipopagravo;
	}

	public char getTiposexo() {
		return tiposexo;
	}

	public void setTiposexo(char tiposexo) {
		this.tiposexo = tiposexo;
	}

	public char getTipoestadio() {
		return tipoestadio;
	}

	public void setTipoestadio(char tipoestadio) {
		this.tipoestadio = tipoestadio;
	}

	public int getValorcamposirradiados() {
		return valorcamposirradiados;
	}

	public void setValorcamposirradiados(int valorcamposirradiados) {
		this.valorcamposirradiados = valorcamposirradiados;
	}

}
