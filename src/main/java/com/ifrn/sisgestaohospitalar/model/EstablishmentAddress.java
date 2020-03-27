package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**Classe para objetos do tipo AddresEstablishment (Endereço do Estabelecimento), onde serão contidos atributos e métodos para o mesmo.
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class EstablishmentAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@XmlAttribute(name = "CO_CEP")
	private String codecep;
	
	@XmlAttribute(name = "SG_UF")
	private String sguf;
	
	@XmlAttribute(name = "CO_IBGE_")
	private String codeibgecity;
	
	@XmlAttribute(name = "BAIRRO")
	private String neighborhood;
	
	@XmlAttribute(name = "LOGRADOURO")
	private String publicplace;
	
	@XmlAttribute(name = "NUMERO")
	private String number;
	
	@XmlAttribute(name = "COMPLEMENT")
	private String complement;
	
	@XmlAttribute(name = "PONTO_REF")
	private String referencepoint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodecep() {
		return codecep;
	}

	public void setCodecep(String codecep) {
		this.codecep = codecep;
	}

	public String getSguf() {
		return sguf;
	}

	public void setSguf(String sguf) {
		this.sguf = sguf;
	}

	public String getCodeibgecity() {
		return codeibgecity;
	}

	public void setCodeibgecity(String codeibgecity) {
		this.codeibgecity = codeibgecity;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPublicplace() {
		return publicplace;
	}

	public void setPublicplace(String publicplace) {
		this.publicplace = publicplace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getReferencepoint() {
		return referencepoint;
	}

	public void setReferencepoint(String referencepoint) {
		this.referencepoint = referencepoint;
	}
	
}
