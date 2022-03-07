package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Triagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "É necessário preencher o motivo do Atendimento")
	private String motivo;

	private LocalDateTime inicioTriagem;

	private LocalDateTime fimTriagem;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private SinaisVitais sinaisVitais;

	@OneToOne
	private Profissional profissional;

	@Transient
	private ClassificacaoDeRisco classificacaoDeRisco;

	@JsonIgnore
	@Valid
	@OneToOne
	private Atendimento atendimento;

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

	public SinaisVitais getSinaisVitais() {
		return sinaisVitais;
	}

	public void setSinaisVitais(SinaisVitais sinaisVitais) {
		this.sinaisVitais = sinaisVitais;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public ClassificacaoDeRisco getClassificacaoDeRisco() {
		return classificacaoDeRisco;
	}

	public void setClassificacaoDeRisco(ClassificacaoDeRisco classificacaoDeRisco) {
		this.classificacaoDeRisco = classificacaoDeRisco;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

}
