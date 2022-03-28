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
public class StatusAlergia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dataInicio;

	private LocalDate dataFim;

	@NotNull(message = "É necessário selecionar a situação da Condição")
	private SituacaoCondicao situacaoCondicao;

	private LocalDateTime dataRegistro;

	@Valid
	@OneToOne(cascade = CascadeType.DETACH)
	@NotNull
	private Alergia alergia;

	@Transient
	private Long idProntuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Alergia getAlergia() {
		return alergia;
	}

	public void setAlergia(Alergia alergia) {
		this.alergia = alergia;
	}

	public Long getIdProntuario() {
		return idProntuario;
	}

	public void setIdProntuario(Long idProntuario) {
		this.idProntuario = idProntuario;
	}

}
