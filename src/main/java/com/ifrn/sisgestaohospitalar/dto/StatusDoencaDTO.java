package com.ifrn.sisgestaohospitalar.dto;

import java.time.LocalDate;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ifrn.sisgestaohospitalar.enums.SituacaoCondicao;

public class StatusDoencaDTO {

	private Long id;

	private String nota;

	private LocalDate dataInicio;

	private LocalDate dataFim;

	@NotNull(message = "É necessário selecionar a situação da Condição")
	private SituacaoCondicao situacaoCondicao;

	@Valid
	@NotNull
	private DoencaDTO doenca;

	@Transient
	private Long idProntuario;

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

	public DoencaDTO getDoenca() {
		return doenca;
	}

	public void setDoenca(DoencaDTO doenca) {
		this.doenca = doenca;
	}

	public Long getIdProntuario() {
		return idProntuario;
	}

	public void setIdProntuario(Long idProntuario) {
		this.idProntuario = idProntuario;
	}

	@Override
	public String toString() {
		return "StatusDoencaDTO [id=" + id + ", nota=" + nota + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", situacaoCondicao=" + situacaoCondicao + ", doenca=" + doenca + ", idProntuario=" + idProntuario
				+ "]";
	}

}
