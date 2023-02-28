package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Lotacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@XmlAttribute(name = "CNES")
	@Column(nullable = false, length = 7)
	private String cnes;

	@XmlAttribute(name = "CO_INE")
	private String codigoIne;

	@XmlAttribute(name = "CO_CBO")
	@Column(nullable = false, length = 6)
	private String codigoCBO;

	@XmlAttribute(name = "MICROAREA")
	private String microArea;
	
	/** Getters and setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getCodigoIne() {
		return codigoIne;
	}

	public void setCodigoIne(String codigoIne) {
		this.codigoIne = codigoIne;
	}

	public String getCodigoCBO() {
		return codigoCBO;
	}

	public void setCodigoCBO(String codigoCBO) {
		this.codigoCBO = codigoCBO;
	}

	public String getMicroArea() {
		return microArea;
	}

	public void setMicroArea(String microArea) {
		this.microArea = microArea;
	}

		
}
