package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProcedimentoCid {
	
	@Id
	private Long codigoProcedimento;
	private String codigoCid;
	private String competencia;
	
	public Long getCodigoProcedimento() {
		return codigoProcedimento;
	}
	public void setCodigoProcedimento(Long codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}
	public String getCodigoCid() {
		return codigoCid;
	}
	public void setCodigoCid(String codigoCid) {
		this.codigoCid = codigoCid;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	
}
