package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
/**
 * A classe <code>Lotacao</code> representa os objetos do tipo Lotação e contém
 * seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Lotacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@XmlAttribute(name = "CNES")
	@Column(nullable = false, length = 7)
	private String  cnes;
	
	@XmlAttribute(name = "CO_INE")
	private String codigoine;
	
	@XmlAttribute(name = "CO_CBO")
	@Column(nullable = false, length = 6)
	private String codigocbo;
	
	@XmlAttribute(name = "MICROAREA")
	private String microarea;
	
	/**Getters and setters*/

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

	public String getCodigoine() {
		return codigoine;
	}

	public void setCodigoine(String codigoine) {
		this.codigoine = codigoine;
	}

	public String getCodigocbo() {
		return codigocbo;
	}

	public void setCodigocbo(String codigocbo) {
		this.codigocbo = codigocbo;
	}

	public String getMicroarea() {
		return microarea;
	}

	public void setMicroarea(String microarea) {
		this.microarea = microarea;
	}
}
