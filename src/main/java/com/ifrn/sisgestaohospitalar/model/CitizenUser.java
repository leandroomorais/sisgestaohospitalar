package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;


/**Classe para objetos do tipo CidadãoUsuário, onde serão contidos atributos e métodos para o mesmo.
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Entity
public class CitizenUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 30)
	@NotBlank(message = "É necessário preencher o campo Nome")
	private String name;
	
	@Column(nullable = false, length = 15)
	@NotBlank(message = "É necessário preencher o campo Cartão Nacional de Saúde (CNS)")
	private String healthcardnumber;
	
	@Column(nullable = false, length = 8)
	@NotBlank(message = "É necessário preencher o campo Data de Nascimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birth;
	
	@Column(nullable = false, length = 1)
	@NotBlank(message = "É necessário preencher o campo Sexo")
	private String sex;
	
	@Column(nullable = false, length = 100)
	@NotBlank(message = "É necessário preencher o campo Nome da Mãe")
	private String mothername;
	
	@Column(nullable = false, length = 100)
	@NotBlank(message = "É necessário preencher o campo Nome do Pai")
	private String fathername;
	
	@Column(nullable = false, length = 2)
	@NotBlank(message = "É necessário preencher o campo Códido da Raça")
	private int racecode;
	
	@Column(nullable = false, length = 4)
	private int ethnicitycode;
	
	@Column(nullable = false, length = 3)
	private int nationalitycode;
	
	@Column(nullable = false, length = 6)
	private int ibgecitycode;
	
	@Column(nullable = false, length = 2)
	@NotBlank(message = "É necessário preencher o campo CEP")
	private int postalcode;
	
	@Column(nullable = false, length = 2)
	private int placecode;
	
	@Column(nullable = false, length = 30)
	@NotBlank(message = "É necessário preencher o campo Endereço")
	private String address;
	
	@Column(nullable = false, length = 10)
	private String addresscomplement;
	
	@Column(nullable = false, length = 10)
	private String addressneighborhood;
	
	@Column(nullable = false, length = 11)
	private int telephone;
	
	@Column(nullable = false, length = 40)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHealthcardnumber() {
		return healthcardnumber;
	}

	public void setHealthcardnumber(String healthcardnumber) {
		this.healthcardnumber = healthcardnumber;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public int getRacecode() {
		return racecode;
	}

	public void setRacecode(int racecode) {
		this.racecode = racecode;
	}

	public int getEthnicitycode() {
		return ethnicitycode;
	}

	public void setEthnicitycode(int ethnicitycode) {
		this.ethnicitycode = ethnicitycode;
	}

	public int getNationalitycode() {
		return nationalitycode;
	}

	public void setNationalitycode(int nationalitycode) {
		this.nationalitycode = nationalitycode;
	}

	public int getIbgecitycode() {
		return ibgecitycode;
	}

	public void setIbgecitycode(int ibgecitycode) {
		this.ibgecitycode = ibgecitycode;
	}

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public int getPlacecode() {
		return placecode;
	}

	public void setPlacecode(int placecode) {
		this.placecode = placecode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddresscomplement() {
		return addresscomplement;
	}

	public void setAddresscomplement(String addresscomplement) {
		this.addresscomplement = addresscomplement;
	}

	public String getAddressneighborhood() {
		return addressneighborhood;
	}

	public void setAddressneighborhood(String addressneighborhood) {
		this.addressneighborhood = addressneighborhood;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
