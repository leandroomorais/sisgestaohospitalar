package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ifrn.sisgestaohospitalar.enums.SituacaoCondicao;

@Entity
public class StatusDoenca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nota;
	
	private LocalDate dataInicio;
	
	private LocalDate dataFim;
	
	private SituacaoCondicao situacaoCondicao;
	
	@Valid
	@OneToOne(cascade = CascadeType.DETACH)
	@NotNull
	private Doenca doenca;
	
	@Transient
	private Long idProntuario;
	
	private LocalDateTime dataRegistro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public SituacaoCondicao getSituacaoCondicao() {
		return situacaoCondicao;
	}

	public void setSituacaoCondicao(SituacaoCondicao situacaoCondicao) {
		this.situacaoCondicao = situacaoCondicao;
	}

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}

	public Long getIdProntuario() {
		return idProntuario;
	}

	public void setIdProntuario(Long idProntuario) {
		this.idProntuario = idProntuario;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

}
