package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@XmlAttribute(name = "CO_CEP")
	private String cep;

	@XmlAttribute(name = "SG_UF")
	private String siglauf;

	@XmlAttribute(name = "CO_IBGE_")
	private String codigoibgemunicipio;

	@XmlAttribute(name = "BAIRRO")
	private String bairro;

	@XmlAttribute(name = "LOGRADOURO")
	private String logradouro;

	@XmlAttribute(name = "NUMERO")
	private String numero;

	@XmlAttribute(name = "COMPLEMENT")
	private String complemento;

	@XmlAttribute(name = "PONTO_REF")
	private String pontoreferencia;

	/** Getters and setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSiglauf() {
		return siglauf;
	}

	public void setSiglauf(String siglauf) {
		this.siglauf = siglauf;
	}

	public String getCodigoibgemunicipio() {
		return codigoibgemunicipio;
	}

	public void setCodigoibgemunicipio(String codigoibgemunicipio) {
		this.codigoibgemunicipio = codigoibgemunicipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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

	public String getPontoreferencia() {
		return pontoreferencia;
	}

	public void setPontoreferencia(String pontoreferencia) {
		this.pontoreferencia = pontoreferencia;
	}

}
