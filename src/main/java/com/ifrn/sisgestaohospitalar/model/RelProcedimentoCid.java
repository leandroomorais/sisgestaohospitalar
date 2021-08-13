package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RelProcedimentoCid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long codigoProcedimento;
	private String codigoCid;
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
	public String getCodigoCid() {
		return codigoCid;
	}
	public void setCodigoCid(String codigoCid) {
		this.codigoCid = codigoCid;
	}
}
