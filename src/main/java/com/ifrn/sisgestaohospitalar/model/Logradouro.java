package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Logradouro {
	
	@Id
	private Long codigo;
	
	private String descricao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
