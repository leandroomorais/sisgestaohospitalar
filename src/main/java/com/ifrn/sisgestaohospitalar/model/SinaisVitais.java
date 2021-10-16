package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifrn.sisgestaohospitalar.enums.MomentoColeta;
import com.ifrn.sisgestaohospitalar.validation.Temperatura;

@Entity
public class SinaisVitais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String pressaoArterial;
	
	@Temperatura
	private double temperaturaCorporal;
	
	private int frequenciaCardiaca;
	
	private int saturacao;
	
	private int frequenciaRespiratoria;
	
	private int glicemiaCapilar;
	
	private MomentoColeta momentoColeta;
	
	private LocalDateTime ultimaAtualizacao;
	
	@JsonIgnore
	@ManyToOne
	private Triagem triagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(String pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public double getTemperaturaCorporal() {
		return temperaturaCorporal;
	}

	public void setTemperaturaCorporal(double temperaturaCorporal) {
		this.temperaturaCorporal = temperaturaCorporal;
	}

	public int getFrequenciaCardiaca() {
		return frequenciaCardiaca;
	}

	public void setFrequenciaCardiaca(int frequenciaCardiaca) {
		this.frequenciaCardiaca = frequenciaCardiaca;
	}

	public int getSaturacao() {
		return saturacao;
	}

	public void setSaturacao(int saturacao) {
		this.saturacao = saturacao;
	}

	public int getFrequenciaRespiratoria() {
		return frequenciaRespiratoria;
	}

	public void setFrequenciaRespiratoria(int frequenciaRespiratoria) {
		this.frequenciaRespiratoria = frequenciaRespiratoria;
	}

	public int getGlicemiaCapilar() {
		return glicemiaCapilar;
	}

	public void setGlicemiaCapilar(int glicemiaCapilar) {
		this.glicemiaCapilar = glicemiaCapilar;
	}

	public MomentoColeta getMomentoColeta() {
		return momentoColeta;
	}

	public void setMomentoColeta(MomentoColeta momentoColeta) {
		this.momentoColeta = momentoColeta;
	}

	public Triagem getTriagem() {
		return triagem;
	}

	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}

	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
