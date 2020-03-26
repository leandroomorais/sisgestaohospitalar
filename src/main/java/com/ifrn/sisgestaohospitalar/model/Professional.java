package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.ifrn.sisgestaohospitalar.enums.ProfessionalType;

/**Classe para os objetos do Tipo Professional, onde serão contidos atributos e métodos para o mesmo
 * @author Leandro Morais
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Professional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@XmlAttribute(name="NM_PROF")
	@NotBlank(message = "Por favor, preencha o campo nome!")
	private String nameprof;

	@XmlAttribute(name = "CPF_PROF")
	@NotBlank(message = "Por favor, preencha o campo CPF")
	private String cpf;

	@XmlAttribute(name = "CO_CNS")
	private String healthcardnumber;

	@XmlAttribute(name = "DT_NASC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birth;

	@XmlAttribute(name = "SEXO")
	private String sex;

	@XmlAttribute(name = "CONSELHO_ID")
	private int idcouncil;

	@XmlAttribute(name = "SIG_UF_EMIS")
	private String ufemiss;

	@XmlAttribute(name = "NU_REGISTRO")
	private int registernumber;

	@XmlAttribute(name = "E_MAIL")
	private String email;

	@XmlAttribute(name = "TELEFONE")
	private String telephone;
	
	private ProfessionalType professionaltype;
	
	private boolean status;
	
	private boolean enabled;
	
	private String username;
	
	private String password;
	
	private String firstname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameprof() {
		return nameprof;
	}

	public void setNameprof(String nameprof) {
		this.nameprof = nameprof;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public int getIdcouncil() {
		return idcouncil;
	}

	public void setIdcouncil(int idcouncil) {
		this.idcouncil = idcouncil;
	}

	public String getUfemiss() {
		return ufemiss;
	}

	public void setUfemiss(String ufemiss) {
		this.ufemiss = ufemiss;
	}

	public int getRegisternumber() {
		return registernumber;
	}

	public void setRegisternumber(int registernumber) {
		this.registernumber = registernumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public ProfessionalType getProfessionaltype() {
		return professionaltype;
	}

	public void setProfessionaltype(ProfessionalType professionaltype) {
		this.professionaltype = professionaltype;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	
}
