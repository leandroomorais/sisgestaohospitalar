package com.ifrn.sisgestaohospitalar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Classe para Objetos do Tipo Estabelecimento, onde são declarados atributos e
 * métodos do mesmo
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Establishment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@XmlAttribute(name = "NM_FANTA")
	private String fantasyname;

	@XmlAttribute(name = "CNPJ")
	private String cnpj;

	@XmlAttribute(name = "CNES")
	private String cnes;

	@XmlAttribute(name = "TP_UNID_ID")
	private String idtypeunit;

	@XmlAttribute(name = "DS_TP_UNID")
	private String descripttypeunit;

	@XmlAttribute(name = "TELEFONE1")
	private String telephone1;

	@XmlAttribute(name = "TELEFONE2")
	private String telephone2;

	@XmlAttribute(name = "FAX")
	private String fax;

	@XmlAttribute(name = "E_MAIL")
	private String email;

	/**
	 * Mapeia e relaciona a Lista de Endereços dos Estabelecimentos contidos no
	 * arquivo Xml
	 */
	@XmlElementWrapper(name = "ENDERECO")
	@XmlElement(name = "DADOS_ENDERECO")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "establishment_address_relationship", joinColumns = { @JoinColumn(name = "address_id") })
	private List<EstablishmentAddress> establishmentAddresses;

	/**
	 * Mapeia e relaciona os dados de complexidade do Estabelecimento contidos no
	 * arquivo Xml
	 */
	@XmlElementWrapper(name = "COMPLEXIDADE")
	@XmlElement(name = "DADOS_COMPLEXIDADE")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "establishment_complexity_relationship", joinColumns = { @JoinColumn(name = "establishment_id") })
	private List<EstablishmentComplexity> establishmentComplexities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFantasyname() {
		return fantasyname;
	}

	public void setFantasyname(String fantasyname) {
		this.fantasyname = fantasyname;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getIdtypeunit() {
		return idtypeunit;
	}

	public void setIdtypeunit(String idtypeunit) {
		this.idtypeunit = idtypeunit;
	}

	public String getDescripttypeunit() {
		return descripttypeunit;
	}

	public void setDescripttypeunit(String descripttypeunit) {
		this.descripttypeunit = descripttypeunit;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EstablishmentAddress> getEstablishmentAddresses() {
		return establishmentAddresses;
	}

	public void setEstablishmentAddresses(List<EstablishmentAddress> establishmentAddresses) {
		this.establishmentAddresses = establishmentAddresses;
	}

	public List<EstablishmentComplexity> getEstablishmentComplexities() {
		return establishmentComplexities;
	}

	public void setEstablishmentComplexities(List<EstablishmentComplexity> establishmentComplexities) {
		this.establishmentComplexities = establishmentComplexities;
	}

}
