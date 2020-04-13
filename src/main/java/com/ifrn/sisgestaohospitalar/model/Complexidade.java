package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * A classe <code>Complexidade</code> representa os objetos do
 * tipo Complexidade e contém seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Complexidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@XmlAttribute(name = "SG_COMPLEXIDADE")
	private String siglacomplexidade;
	
	/**Getters and setters*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiglacomplexidade() {
		return siglacomplexidade;
	}

	public void setSiglacomplexidade(String siglacomplexidade) {
		this.siglacomplexidade = siglacomplexidade;
	}

}
