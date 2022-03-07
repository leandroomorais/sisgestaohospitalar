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
public class FolhaBPAConsolidado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int numero;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "folha_bpa_consolidado_linha_consolidado")
	private List<LinhaBPAConsolidado> linhasBPAConsolidado;

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

	public List<LinhaBPAConsolidado> getLinhasBPAConsolidado() {
		return linhasBPAConsolidado;
	}

	public void setLinhasBPAConsolidado(List<LinhaBPAConsolidado> linhasBPAConsolidado) {
		this.linhasBPAConsolidado = linhasBPAConsolidado;
	}

}
