package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EnderecoCidadao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int tipoEndereco;

	@NotBlank(message = "É necessário preencher o campo NUMERO. Caso não possua selecione a opção SEM NÚMERO")
	@Size(max = 10)
	private String numero;
	@Size(max = 30)
	private String complemento;
	@NotBlank(message = "É necessário preencher o campo BAIRRO")
	@Size(max = 30)
	private String bairro;
	@NotBlank(message = "É necessário preencher o campo CEP")
	private String cep;
	@NotBlank(message = "É necessário preencher o campo NOME DO LOGRADOURO")
	private String nomeLogradouro;
	@NotNull(message = "É necessário selecionar o MUNICÍPIO")
	@OneToOne
	private Municipio municipio;
	@NotNull(message = "É necessário preencher o campo LOGRADOURO")
	@OneToOne
	private Logradouro logradouro;

	private String enderecoCompleto;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		enderecoCompleto = this.logradouro.getDescLogradouro() + " " + nomeLogradouro + ", BAIRRO " + bairro + ", "
				+ municipio.getNomeMunicipioSiglaUF();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(int tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

}
