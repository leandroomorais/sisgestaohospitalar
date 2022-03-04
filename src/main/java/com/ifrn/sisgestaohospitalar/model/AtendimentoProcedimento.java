package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AtendimentoProcedimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToOne
	private Atendimento atendimento;

	@OneToOne
	private Procedimento procedimento;

	private int quantidade;
	
	private int idadeNoAtendimento;
	
	private String cboProfissional;

	@OneToOne
	private Profissional profissional;

	private String codigoCid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getIdadeNoAtendimento() {
		return idadeNoAtendimento;
	}

	public void setIdadeNoAtendimento(int idadeNoAtendimento) {
		this.idadeNoAtendimento = idadeNoAtendimento;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String getCodigoCid() {
		return codigoCid;
	}

	public void setCodigoCid(String codigoCid) {
		this.codigoCid = codigoCid;
	}

	public String getCboProfissional() {
		return cboProfissional;
	}

	public void setCboProfissional(String cboProfissional) {
		this.cboProfissional = cboProfissional;
	}
	
}
