package com.ifrn.sisgestaohospitalar.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifrn.sisgestaohospitalar.enums.MomentoColeta;

public class SinaisVitaisDTO {
	
	private Long id;
	
	private String pressaoArterial;
	
	private float temperaturaCorporal;
	
	private int frequenciaCardiaca;
	
	private int saturacao;
	
	private int frequenciaRespiratoria;
	
	private int glicemiaCapilar;
	
	private MomentoColeta momentoColeta;
	
	private LocalDateTime ultimaAtualizacao;
	
	@JsonIgnore
	private TriagemDTO triagemDTO;

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

	public float getTemperaturaCorporal() {
		return temperaturaCorporal;
	}

	public void setTemperaturaCorporal(float temperaturaCorporal) {
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

	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public TriagemDTO getTriagemDTO() {
		return triagemDTO;
	}

	public void setTriagemDTO(TriagemDTO triagemDTO) {
		this.triagemDTO = triagemDTO;
	}
	
}
