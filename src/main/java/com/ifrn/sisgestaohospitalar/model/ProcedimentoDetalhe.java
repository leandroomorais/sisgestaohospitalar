package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProcedimentoDetalhe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long codigoProcedimento;
	
	private String codigoDetalhe;

	private String competencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(Long codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}

	public String getCodigoDetalhe() {
		return codigoDetalhe;
	}

	public void setCodigoDetalhe(String codigoDetalhe) {
		this.codigoDetalhe = codigoDetalhe;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

}
