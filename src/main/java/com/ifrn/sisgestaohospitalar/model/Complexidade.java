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
public class Complexidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@XmlAttribute(name = "SG_COMPLEXIDADE")
	private String siglaComplexidade;

	/** Getters and setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiglacomplexidade() {
		return siglaComplexidade;
	}

	public void setSiglacomplexidade(String siglacomplexidade) {
		this.siglaComplexidade = siglacomplexidade;
	}

}
