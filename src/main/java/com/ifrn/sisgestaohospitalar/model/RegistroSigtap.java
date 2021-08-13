package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegistroSigtap {

	@Id
	private String codigo;

	private String nome;

	private String dataCompetencia;

	/** Getters and setters */

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(String dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}
}
