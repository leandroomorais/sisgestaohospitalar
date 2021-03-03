package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegistroSigtap {

	@Id
	private String codigoregistro;

	private String nomeregistro;

	private String datacompetencia;

	/** Getters and setters */

	public String getCodigoregistro() {
		return codigoregistro;
	}

	public void setCodigoregistro(String codigoregistro) {
		this.codigoregistro = codigoregistro;
	}

	public String getNomeregistro() {
		return nomeregistro;
	}

	public void setNomeregistro(String nomeregistro) {
		this.nomeregistro = nomeregistro;
	}

	public String getDatacompetencia() {
		return datacompetencia;
	}

	public void setDatacompetencia(String datacompetencia) {
		this.datacompetencia = datacompetencia;
	}

}
