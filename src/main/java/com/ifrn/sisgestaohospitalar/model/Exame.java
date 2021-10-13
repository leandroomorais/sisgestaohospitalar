package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.ifrn.sisgestaohospitalar.enums.StatusExame;

@Entity
public class Exame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String grupoexame;
	
	private String nomeexame;
	
	private String procedimentocid;
	
	private String justificativacid;
	
	private String observacoes;
	
	private StatusExame statusexame;
	
	private LocalDateTime dataRegistro;
	
	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	@OneToOne
	private Prontuario prontuario;
	
	@OneToOne
	private Profissional profissional;
	
	//@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "exame_procedimentos", joinColumns = @JoinColumn(name = "id_exame"), inverseJoinColumns = @JoinColumn(name = "id_procedimento"))
	private List<Procedimento> procedimentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrupoexame() {
		return grupoexame;
	}

	public void setGrupoexame(String grupoexame) {
		this.grupoexame = grupoexame;
	}

	public String getNomeexame() {
		return nomeexame;
	}

	public void setNomeexame(String nomeexame) {
		this.nomeexame = nomeexame;
	}

	public String getProcedimentocid() {
		return procedimentocid;
	}

	public void setProcedimentocid(String procedimentocid) {
		this.procedimentocid = procedimentocid;
	}

	public String getJustificativacid() {
		return justificativacid;
	}

	public void setJustificativacid(String justificativacid) {
		this.justificativacid = justificativacid;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}


	public StatusExame getStatusexame() {
		return statusexame;
	}

	public void setStatusexame(StatusExame statusexame) {
		this.statusexame = statusexame;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
	

}
