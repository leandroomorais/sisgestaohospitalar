package com.ifrn.sisgestaohospitalar.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;

/**
 * A classe <code>Profissional</code> representa os objetos do tipo Profissional
 * e contém seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Profissional implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Profissional() {
		super();
		this.enabled = true;

		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@XmlAttribute(name = "NM_PROF")
	@Column(nullable = false, length = 100)
	@NotBlank(message = "É necessário preencher o campo NOME")
	private String nome;

	@XmlAttribute(name = "CPF_PROF")
	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo CPF")
	private String cpf;

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo CNS")
	@XmlAttribute(name = "CO_CNS")
	private String cns;
	
	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo DATA DE NASCIMENTO")
	@XmlAttribute(name = "DT_NASC")
	private String datanascimento;
	
	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo SEXO")
	@XmlAttribute(name = "SEXO")
	private String sexo;

	@XmlAttribute(name = "CONSELHO_ID")
	private int conselhoid;

	@XmlAttribute(name = "SIG_UF_EMIS")
	private String siglaufemissao;

	@XmlAttribute(name = "NU_REGISTRO")
	private String numeroregistro;

	@XmlAttribute(name = "E_MAIL")
	private String email;

	@XmlAttribute(name = "TELEFONE")
	private String telefone;

	private TipoProfissional tipoprofissional;

	private boolean enabled;

	private String firstname;

	private String username;

	private String password;

	/**
	 * Relacionamento entre os objetos Profissional e Lotação
	 */
	@XmlElementWrapper(name = "LOTACOES")
	@XmlElement(name = "DADOS_LOTACOES")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "profissional_lotacao", joinColumns = {
			@JoinColumn(name = "profissional_id") }, inverseJoinColumns = { @JoinColumn(name = "lotacao_id") })
	private List<Lotacao> lotacoes;
	
	
	/**
	 * Relacionamento entre os objetos Profissional e Role
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private Set<Role> role = new HashSet<Role>();
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}


	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public int getConselhoid() {
		return conselhoid;
	}

	public void setConselhoid(int conselhoid) {
		this.conselhoid = conselhoid;
	}

	public String getSiglaufemissao() {
		return siglaufemissao;
	}

	public void setSiglaufemissao(String siglaufemissao) {
		this.siglaufemissao = siglaufemissao;
	}
	
	public String getNumeroregistro() {
		return numeroregistro;
	}

	public void setNumeroregistro(String numeroregistro) {
		this.numeroregistro = numeroregistro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	

	public TipoProfissional getTipoprofissional() {
		return tipoprofissional;
	}

	public void setTipoprofissional(TipoProfissional tipoprofissional) {
		this.tipoprofissional = tipoprofissional;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public List<Lotacao> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<Lotacao> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.addAll(getRole());
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

}
