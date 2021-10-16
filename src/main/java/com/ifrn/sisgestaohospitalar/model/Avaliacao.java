package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String notas;

	@OneToOne(cascade = CascadeType.ALL)
	private SinaisVitais sinaisVitais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public SinaisVitais getSinaisVitais() {
		return sinaisVitais;
	}

	public void setSinaisVitais(SinaisVitais sinaisVitais) {
		this.sinaisVitais = sinaisVitais;
	}

}
