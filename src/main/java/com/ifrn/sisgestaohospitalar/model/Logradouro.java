package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Logradouro {
	
	@Id
	private Long codigoLogradouro;
	
	private String descLogradouro;

	public Long getCodigoLogradouro() {
		return codigoLogradouro;
	}

	public void setCodigoLogradouro(Long codigoLogradouro) {
		this.codigoLogradouro = codigoLogradouro;
	}

	public String getDescLogradouro() {
		return descLogradouro;
	}

	public void setDescLogradouro(String descLogradouro) {
		this.descLogradouro = descLogradouro;
	}

	
}
