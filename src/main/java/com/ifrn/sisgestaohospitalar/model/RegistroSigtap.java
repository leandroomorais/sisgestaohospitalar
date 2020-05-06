package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A classe <code>RegistroSigtap</code> representa os objetos do tipo
 * RegistroSigtap e contém os atributos dos tipos de registros da Tabela Sigtap
 * SUS.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
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
