package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.ifrn.sisgestaohospitalar.enums.CriticidadeAlergia;
import com.sun.istack.NotNull;

@Entity
public class StatusAlergia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataInicio;
	
	private LocalDate dataFim;
	
	private CriticidadeAlergia criticidadeAlergia;
	
	@OneToOne
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
	public CriticidadeAlergia getCriticidadeAlergia() {
		return criticidadeAlergia;
	}
	public void setCriticidadeAlergia(CriticidadeAlergia criticidadeAlergia) {
		this.criticidadeAlergia = criticidadeAlergia;
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
