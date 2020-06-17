package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo SEXO")
	private String sexo;

	private String codigoibgemunicipio;

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo MUNICIPIO")
	private String nomemunicipio;

	@Column(length = 3)
	private int idade;

	@Column(nullable = false)
	@NotBlank(message = "É necessário preencher o campo NOME")
	private String nome;

	private String nomemae;

	private String nomepai;

	@NotNull
	@PastOrPresent(message = "{PastOrPresent.cidadao.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Column(name = "datanascimento", nullable = false, columnDefinition = "DATE")
	private LocalDate datanascimento;

	@Column(length = 2)
	private int codigoraca;

	@Column(length = 4)
	private int codigoetnia;

	@Column(length = 3)
	private int codigonacionalidade;

	private String cep;

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

	private String bairro;

	private String telefone;

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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCodigoibgemunicipio() {
		return codigoibgemunicipio;
	}

	public void setCodigoibgemunicipio(String codigoibgemunicipio) {
		this.codigoibgemunicipio = codigoibgemunicipio;
	}

	public String getNomemunicipio() {
		return nomemunicipio;
	}

	public void setNomemunicipio(String nomemunicipio) {
		this.nomemunicipio = nomemunicipio;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
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

}
