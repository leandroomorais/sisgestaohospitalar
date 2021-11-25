package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ExameSimplificado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "É necessário preencher o campo nome")
	private String nome;

	@NotNull(message = "Deve conter um procedimento associdado")
	@OneToOne
	private Procedimento procedimentoAssociado;
	
	@JsonIgnore
	@OneToOne
	private GrupoExame grupoExame;

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

	public Procedimento getProcedimentoAssociado() {
		return procedimentoAssociado;
	}

	public void setProcedimentoAssociado(Procedimento procedimentoAssociado) {
		this.procedimentoAssociado = procedimentoAssociado;
	}

	public GrupoExame getGrupoExame() {
		return grupoExame;
	}

	public void setGrupoExame(GrupoExame grupoExame) {
		this.grupoExame = grupoExame;
	}

}
