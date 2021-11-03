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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifrn.sisgestaohospitalar.enums.StatusExame;

@Entity
public class Exame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe a justificativa do procedimento")
	private String justificativa;
	
	private String observacoes;
	
	private LocalDateTime dataSolicitacao;
	
	private StatusExame status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "atendimento_id")
	private Atendimento atendimento;
	
	@JsonIgnore
	@OneToOne
	private Prontuario prontuario;
	
	@OneToOne
	private Profissional profissional;
	
	@Valid
	@ManyToMany
	@JoinTable(name = "exame_procedimentos", joinColumns = @JoinColumn(name = "id_exame"), inverseJoinColumns = @JoinColumn(name = "id_procedimento"))
	private List<Procedimento> procedimentos;
	
	//@OneToMany
	//private List<ResultadoExame> resultados;
	
	@OneToOne
	private Cid cid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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

	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public StatusExame getStatus() {
		return status;
	}

	public void setStatus(StatusExame status) {
		this.status = status;
	}


}
