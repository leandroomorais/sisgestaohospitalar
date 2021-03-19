package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Cidadao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 15)
	@NotBlank(message = "É necessário preencher o campo CNS")
	private String cns;
	
	private String cpf;

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo SEXO")
	private String sexo;

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo NOME")
	private String nome;

	private String nomemae;

	private String nomepai;

	@NotNull
	@PastOrPresent(message = "{PastOrPresent.cidadao.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "datanascimento", nullable = false, columnDefinition = "DATE")
	private LocalDate datanascimento;

	@Column(length = 2)
	private int codigoraca;

	@Column(length = 4)
	private int codigoetnia;

	@Column(length = 3)
	private int codigonacionalidade;

	private String telefone;

	@Column(length = 40)
	private String email;
	
	@OneToOne
	private Municipio municipioNascimento;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private EnderecoCidadao endereco;

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
		this.cns = cns;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public int getCodigoraca() {
		return codigoraca;
	}

	public void setCodigoraca(int codigoraca) {
		this.codigoraca = codigoraca;
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


}
