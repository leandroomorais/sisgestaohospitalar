package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.ifrn.sisgestaohospitalar.enums.MomentoColeta;
import com.ifrn.sisgestaohospitalar.validation.Temperatura;

@Entity
public class SinaisVitais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int pressaoSistolica;

	private int pressaoDiastolica;

	@Temperatura
	private double temperaturaCorporal;

	private String frequenciaCardiaca;

	private String saturacao;

	private String frequenciaRespiratoria;

	private String glicemiaCapilar;

	private MomentoColeta momentoColeta;

	private LocalDateTime ultimaAtualizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPressaoSistolica() {
		return pressaoSistolica;
	}

	public void setPressaoSistolica(int pressaoSistolica) {
		this.pressaoSistolica = pressaoSistolica;
	}

	public int getPressaoDiastolica() {
		return pressaoDiastolica;
	}

	public void setPressaoDiastolica(int pressaoDiastolica) {
		this.pressaoDiastolica = pressaoDiastolica;
	}

	public double getTemperaturaCorporal() {
		return temperaturaCorporal;
	}

	public void setTemperaturaCorporal(double temperaturaCorporal) {
		this.temperaturaCorporal = temperaturaCorporal;
	}

	public String getFrequenciaCardiaca() {
		return frequenciaCardiaca;
	}

	public void setFrequenciaCardiaca(String frequenciaCardiaca) {
		this.frequenciaCardiaca = frequenciaCardiaca;
	}

	public String getSaturacao() {
		return saturacao;
	}

	public void setSaturacao(String saturacao) {
		this.saturacao = saturacao;
	}

	public String getFrequenciaRespiratoria() {
		return frequenciaRespiratoria;
	}

	public void setFrequenciaRespiratoria(String frequenciaRespiratoria) {
		this.frequenciaRespiratoria = frequenciaRespiratoria;
	}

	public String getGlicemiaCapilar() {
		return glicemiaCapilar;
	}

	public void setGlicemiaCapilar(String glicemiaCapilar) {
		this.glicemiaCapilar = glicemiaCapilar;
	}

	public MomentoColeta getMomentoColeta() {
		return momentoColeta;
	}

	public void setMomentoColeta(MomentoColeta momentoColeta) {
		this.momentoColeta = momentoColeta;
	}

	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
