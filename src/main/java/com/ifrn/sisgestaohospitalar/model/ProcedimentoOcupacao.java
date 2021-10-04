package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProcedimentoOcupacao {

	@Id
	private Long codigoProcedimento;

	private String codigoOcupacao;

	private String competencia;

	public Long getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(Long codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}

	public String getCodigoOcupacao() {
		return codigoOcupacao;
	}

	public void setCodigoOcupacao(String codigoOcupacao) {
		this.codigoOcupacao = codigoOcupacao;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

}
