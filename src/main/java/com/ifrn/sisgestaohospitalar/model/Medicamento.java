package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Medicamento {

	@Id
	private Long id;
	
	private String principioAtivo;
	
	private String concentracao;
	
	private String unidadeFornecimento;
	
	@OneToOne
	private FormaFarmaceutica formaFarmaceutica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public String getConcentracao() {
		return concentracao;
	}

	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}

	public String getUnidadeFornecimento() {
		return unidadeFornecimento;
	}

	public void setUnidadeFornecimento(String unidadeFornecimento) {
		this.unidadeFornecimento = unidadeFornecimento;
	}

	public FormaFarmaceutica getFormaFarmaceutica() {
		return formaFarmaceutica;
	}

	public void setFormaFarmaceutica(FormaFarmaceutica formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}
	
}
