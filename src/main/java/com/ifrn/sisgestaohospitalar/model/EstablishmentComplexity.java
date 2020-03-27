package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Classe para objetos do tipo Complexidade do estabelecimento, onde serão
 * contidos atributos e métodos para o mesmo.
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class EstablishmentComplexity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@XmlAttribute(name = "SG_COMPLEXIDADE")
	private String sgcomplexity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSgcomplexity() {
		return sgcomplexity;
	}

	public void setSgcomplexity(String sgcomplexity) {
		this.sgcomplexity = sgcomplexity;
	}
}
