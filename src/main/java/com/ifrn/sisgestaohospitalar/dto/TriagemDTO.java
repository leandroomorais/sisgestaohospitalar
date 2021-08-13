package com.ifrn.sisgestaohospitalar.dto;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class TriagemDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "É necessário preencher o motivo do Atendimento")
	private String motivo;

	private LocalDateTime inicioTriagem;

	private LocalDateTime fimTriagem;

	private SinaisVitaisDTO sinaisVitaisDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public LocalDateTime getInicioTriagem() {
		return inicioTriagem;
	}

	public void setInicioTriagem(LocalDateTime inicioTriagem) {
		this.inicioTriagem = inicioTriagem;
	}

	public LocalDateTime getFimTriagem() {
		return fimTriagem;
	}

	public void setFimTriagem(LocalDateTime fimTriagem) {
		this.fimTriagem = fimTriagem;
	}

	public SinaisVitaisDTO getSinaisVitaisDTO() {
		return sinaisVitaisDTO;
	}

	public void setSinaisVitaisDTO(SinaisVitaisDTO sinaisVitaisDTO) {
		this.sinaisVitaisDTO = sinaisVitaisDTO;
	}

}
