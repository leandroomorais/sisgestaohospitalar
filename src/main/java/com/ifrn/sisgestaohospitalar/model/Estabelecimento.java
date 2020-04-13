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
 * A classe <code>Estabelecimento</code> representa os objetos do tipo Estabelecimento e contém
 * seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Estabelecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@XmlAttribute(name = "NM_FANTA")
	private String nome;
	
	@XmlAttribute(name = "CNPJ")
	private String cnpj;
	
	@XmlAttribute(name = "CNES")
	private String cnes;
	
	@XmlAttribute(name = "TP_UNID_ID")
	private String tipounidadeid;
	
	@XmlAttribute(name = "DS_TP_UNID")
	private String descricaotipounidade;
	
	@XmlAttribute(name = "TELEFONE1")
	private String telefone1;
	
	@XmlAttribute(name = "TELEFONE2")
	private String telefone2;
	
	@XmlAttribute(name = "FAX")
	private String fax;
	
	@XmlAttribute(name = "E_MAIL")
	private String email;
	
	@XmlElementWrapper(name = "ENDERECO")
	@XmlElement(name = "DADOS_ENDERECO")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "estabelecimento_endereco",
	joinColumns = {@JoinColumn(name="endereco_id")})
	private List<Endereco> enderecos;
	
	@XmlElementWrapper(name = "COMPLEXIDADE")
	@XmlElement(name = "DADOS_COMPLEXIDADE")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "estabelecimento_complexidade",
	joinColumns = {@JoinColumn(name="estabelecimento_id")})
	private List<Complexidade> complexidades;
	
	/**Getters and setters*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getTipounidadeid() {
		return tipounidadeid;
	}

	public void setTipounidadeid(String tipounidadeid) {
		this.tipounidadeid = tipounidadeid;
	}

	public String getDescricaotipounidade() {
		return descricaotipounidade;
	}

	public void setDescricaotipounidade(String descricaotipounidade) {
		this.descricaotipounidade = descricaotipounidade;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Complexidade> getComplexidades() {
		return complexidades;
	}

	public void setComplexidades(List<Complexidade> complexidades) {
		this.complexidades = complexidades;
	}
	
	
	

}
