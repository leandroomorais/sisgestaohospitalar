package com.ifrn.sisgestaohospitalar.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Profissional {

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
	private String dataNascimento;

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo SEXO")
	@XmlAttribute(name = "SEXO")
	private String sexo;

	@XmlAttribute(name = "CONSELHO_ID")
	private int conselhoId;

	@XmlAttribute(name = "SG_UF_EMIS")
	private String siglaUfEmissao;

	@XmlAttribute(name = "NU_REGISTRO")
	private String numeroRegistro;

	@XmlAttribute(name = "E_MAIL")
	private String email;

	@XmlAttribute(name = "TELEFONE")
	private String telefone;

	private String nomeAbrev;

	@Enumerated(EnumType.STRING)
	private TipoProfissional tipoProfissional;

	private boolean ativo;

	/**
	 * Relacionamento entre os objetos Profissional e Lotação
	 */
	@XmlElementWrapper(name = "LOTACOES")
	@XmlElement(name = "DADOS_LOTACOES")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "profissional_lotacao", joinColumns = {
			@JoinColumn(name = "profissional_id") }, inverseJoinColumns = { @JoinColumn(name = "lotacao_id") })
	private List<Lotacao> lotacoes;

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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoProfissional getTipoProfissional() {
		return tipoProfissional;
	}

	public void setTipoProfissional(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getConselhoId() {
		return conselhoId;
	}

	public void setConselhoId(int conselhoId) {
		this.conselhoId = conselhoId;
	}

	public String getSiglaUfEmissao() {
		return siglaUfEmissao;
	}

	public void setSiglaUfEmissao(String siglaUfEmissao) {
		this.siglaUfEmissao = siglaUfEmissao;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
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

	public String getNomeAbrev() {
		return nomeAbrev;
	}

	public void setNomeAbrev(String nomeAbrev) {
		this.nomeAbrev = nomeAbrev;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Lotacao> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<Lotacao> lotacoes) {
		this.lotacoes = lotacoes;
	}

}
