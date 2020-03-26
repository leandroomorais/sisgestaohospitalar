package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**Classe para os objetos do tipo Workplace (local de trabalho), onde estão contidos métodos e atributos do mesmo
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da aplicação
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Workplace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@XmlAttribute(name = "CNES")
	private String  codecnes;
	
	@XmlAttribute(name = "CO_INE")
	private String codeine;
	
	@XmlAttribute(name = "CO_CBO")
	private String codecbo;
	
	@XmlAttribute(name = "MICROAREA")
	private String microarea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodecnes() {
		return codecnes;
	}

	public void setCodecnes(String codecnes) {
		this.codecnes = codecnes;
	}

	public String getCodeine() {
		return codeine;
	}

	public void setCodeine(String codeine) {
		this.codeine = codeine;
	}

	public String getCodecbo() {
		return codecbo;
	}

	public void setCodecbo(String codecbo) {
		this.codecbo = codecbo;
	}

	public String getMicroarea() {
		return microarea;
	}

	public void setMicroarea(String microarea) {
		this.microarea = microarea;
	}

}
