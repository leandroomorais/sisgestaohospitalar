package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FormaFarmaceutica {
	
	@Id
	private Long id;
	
	private String nome;
	
	private String nomeFiltro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	@Override
	public String toString() {
		return "FormaFarmaceutica [id=" + id + ", nome=" + nome + ", nomeFiltro=" + nomeFiltro + "]";
	}
	
	

}
