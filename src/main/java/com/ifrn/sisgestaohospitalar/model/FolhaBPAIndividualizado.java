package com.ifrn.sisgestaohospitalar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class FolhaBPAIndividualizado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int numero;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "folha_bpa_individualizado_linha_individualizado")
	private List<LinhaBPAIndividualizado> linhasBPAIndividualizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<LinhaBPAIndividualizado> getLinhasBPAIndividualizado() {
		return linhasBPAIndividualizado;
	}

	public void setLinhasBPAIndividualizado(List<LinhaBPAIndividualizado> linhasBPAIndividualizado) {
		this.linhasBPAIndividualizado = linhasBPAIndividualizado;
	}

}
