package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * A classe <code>Cidadao</code> representa os objetos do tipo Cidadão e contém
 * seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
public class Cidadao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 15)
	@NotBlank(message = "É necessário preencher o campo CNS")
	private String cns;
	
	private String cpf;

	@Column(nullable = false, length = 1)
	@NotBlank(message = "É necessário preencher o campo SEXO")
	private char sexo;

	@Column(nullable = false, length = 6)
	@NotBlank(message = "É necessário preencher o campo MUNICIPIO")
	private int codigoibgemunicipio;

	@Column(length = 3)
	private int idade;

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo NOME")
	private String nome;

	@Column(nullable = false, length = 15)
	@NotBlank(message = "É necessário preencher o campo DATA DE NASCIMENTO")
	private Date datanascimento;

	@Column(length = 2)
	private int codigoraca;

	@Column(length = 4)
	private int codigoetnia;

	@Column(length = 3)
	private int codigonacionalidade;

	@Column(nullable = false, length = 8)
	@NotBlank(message = "É necessário preencher o campo CEP")
	private int cep;

	@Column(length = 3)
	private int codigologradouro;

	@Column(nullable = false, length = 30)
	@NotBlank(message = "É necessário preencher o campo ENDERECO")
	private String endereco;

	@Column(length = 10)
	private String complementoendereco;

	@Column(nullable = false, length = 5)
	@NotBlank(message = "É necessário preencher o campo NUMERO DA CASA")
	private String numeroendereco;

	@Column(nullable = false, length = 30)
	@NotBlank(message = "É necessário preencher o campo BAIRRO")
	private String bairro;

	@Column(length = 11)
	private int telefone;

	@Column(length = 40)
	private String email;

	/** Getters e setters */

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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getCodigoibgemunicipio() {
		return codigoibgemunicipio;
	}

	public void setCodigoibgemunicipio(int codigoibgemunicipio) {
		this.codigoibgemunicipio = codigoibgemunicipio;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
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

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public int getCodigologradouro() {
		return codigologradouro;
	}

	public void setCodigologradouro(int codigologradouro) {
		this.codigologradouro = codigologradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplementoendereco() {
		return complementoendereco;
	}

	public void setComplementoendereco(String complementoendereco) {
		this.complementoendereco = complementoendereco;
	}

	public String getNumeroendereco() {
		return numeroendereco;
	}

	public void setNumeroendereco(String numeroendereco) {
		this.numeroendereco = numeroendereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
