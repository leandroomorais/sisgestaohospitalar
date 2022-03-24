package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class PrescricaoExterna {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe o nome do profissional")
	private String nomeProfissional;
	
	@NotBlank(message = "Informe o numero do registro CRM")
	private String numeroRegistro;
	
	@NotBlank(message = "Informe o estado do CRM")
	private String siglaUfEmissao;
	
	@NotNull(message = "Informe a data de solicitação")
	private LocalDate dataSolicitacao;

	@OneToOne
	private Prescricao prescricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProfissional() {
		return nomeProfissional;
	}

	public void setNomeProfissional(String nomeProfissional) {
		this.nomeProfissional = nomeProfissional;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getSiglaUfEmissao() {
		return siglaUfEmissao;
	}

	public void setSiglaUfEmissao(String siglaUfEmissao) {
		this.siglaUfEmissao = siglaUfEmissao;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Prescricao getPrescricao() {
		return prescricao;
	}

	public void setPrescricao(Prescricao prescricao) {
		this.prescricao = prescricao;
	}
	
	
}
