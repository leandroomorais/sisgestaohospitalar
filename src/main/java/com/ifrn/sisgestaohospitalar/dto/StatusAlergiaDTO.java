package com.ifrn.sisgestaohospitalar.dto;

import java.time.LocalDate;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ifrn.sisgestaohospitalar.enums.SituacaoCondicao;


public class StatusAlergiaDTO {

	private Long id;
	
	private LocalDate dataInicio;
	
	private LocalDate dataFim;
	
	private SituacaoCondicao situacaoCondicao;
	
	@Valid
	@NotNull
	private AlergiaDTO alergia;
	
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
	public AlergiaDTO getAlergia() {
		return alergia;
	}
	public void setAlergia(AlergiaDTO alergia) {
		this.alergia = alergia;
	}
	public Long getIdProntuario() {
		return idProntuario;
	}
	public void setIdProntuario(Long idProntuario) {
		this.idProntuario = idProntuario;
	}
	@Override
	public String toString() {
		return "StatusAlergia [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", alergia=" + alergia + ", idProntuario="
				+ idProntuario + "]";
	}
	
	
	
}