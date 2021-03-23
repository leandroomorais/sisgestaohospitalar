package com.ifrn.sisgestaohospitalar.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ifrn.sisgestaohospitalar.enums.CodigoRaca;
import com.ifrn.sisgestaohospitalar.validation.Cns;

@Entity
public class Cidadao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Cns(message = "O CNS digitado é inválido")
	@Column(nullable = false, length = 15)
	@Size(max = 15, message = "O campo CNS deve conter 15 caracteres")
	@NotBlank(message = "É necessário preencher o campo CNS")
	private String cns;

	@Column(nullable = false, length = 11)
	@Size(max = 11, message = "O campo CPF deve conter 11 caracteres")
	@NotBlank(message = "É necessário preencher o campo CPF")
	@CPF(message = "O CPF digitado é inválido")
	private String cpf;

	@Column(nullable = false, length = 1)
	@Size(max = 1)
	@NotBlank(message = "É necessário preencher o campo SEXO")
	private String sexo;

	@Column(nullable = false, length = 50)
	@Size(max = 50, message = "O campo NOME deve conter no máximo 50 caracteres")
	@NotBlank(message = "É necessário preencher o campo NOME")
	private String nome;

	@Column(nullable = false, length = 50)
	@Size(max = 50, message = "O campo NOME DA MÃE deve conter no máximo 50 caracteres")
	@NotBlank(message = "É necessário preencher o campo NOME DA MÃE, caso não possua, selecione a opção SEM INFORMAÇÕES")
	private String nomemae;

	@Column(nullable = false, length = 50)
	@Size(max = 50, message = "O campo NOME DO PAI deve conter no máximo 50 caracteres")
	@NotBlank(message = "É necessário preencher o campo NOME DO PAI, caso não possua, selecione a opção SEM INFORMAÇÕES")
	private String nomepai;

	@NotNull(message = "É necessário preencher o campo DATA DE NASCIMENTO")
	@PastOrPresent(message = "{PastOrPresent.cidadao.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "datanascimento", nullable = false, columnDefinition = "DATE")
	private LocalDate datanascimento;

	@NotNull(message = "É necessário selecionar a RAÇA do Cidadão")
	@Column(length = 2)
	@Enumerated(EnumType.STRING)
	private CodigoRaca codigoRaca;

	@Column(length = 4)
	private int codigoetnia;

	@NotNull(message = "É necessário selecionar a NACIONALIDADE do Cidadão")
	@Column(length = 3)
	private int codigonacionalidade;

	private String telefone;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(length = 40)
	@Email(message = "Email inválido")
	private String email;

	@NotNull(message = "É necessário selecionar o MUNICÍPIO DE NASCIMENTO do Cidadão")
	@OneToOne
	private Municipio municipioNascimento;

	@NotNull(message = "Preencha os campos do ENDEREÇO do Cidadão")
	@OneToOne(cascade = CascadeType.ALL)
	private EnderecoCidadao endereco;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		
		cns.replaceAll("\\.|-|/", "");
		cpf.replaceAll("\\.|-|/", "");
		cns = cns.toUpperCase();
		cpf = cpf.toUpperCase();
		sexo = sexo.toUpperCase();
		nome = nome.toUpperCase();
		nomemae = nomemae.toUpperCase();
		nomepai = nomepai.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns.replaceAll("\\.|-|/", "");
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.replaceAll("\\.|-|/", "");
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomemae() {
		return nomemae;
	}

	public void setNomemae(String nomemae) {
		this.nomemae = nomemae;
	}

	public String getNomepai() {
		return nomepai;
	}

	public void setNomepai(String nomepai) {
		this.nomepai = nomepai;
	}

	public LocalDate getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(LocalDate datanascimento) {
		this.datanascimento = datanascimento;
	}

	public CodigoRaca getCodigoRaca() {
		return codigoRaca;
	}

	public void setCodigoRaca(CodigoRaca codigoRaca) {
		this.codigoRaca = codigoRaca;
	}

	public int getCodigoetnia() {
		return codigoetnia;
	}

	public void setCodigoetnia(int codigoetnia) {
		this.codigoetnia = codigoetnia;
	}

	public int getCodigonacionalidade() {
		return codigonacionalidade;
	}

	public void setCodigonacionalidade(int codigonacionalidade) {
		this.codigonacionalidade = codigonacionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Municipio getMunicipioNascimento() {
		return municipioNascimento;
	}

	public void setMunicipioNascimento(Municipio municipioNascimento) {
		this.municipioNascimento = municipioNascimento;
	}

	public EnderecoCidadao getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoCidadao endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidadao other = (Cidadao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
