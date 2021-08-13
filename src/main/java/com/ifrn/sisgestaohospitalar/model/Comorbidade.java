package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.ifrn.sisgestaohospitalar.enums.SituacaoDoenca;

@Entity
public class Comorbidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Cid cid;
	
	private LocalDate dataInicial;
	
	private LocalDate dataFinal;

	@NotBlank(message = "É necessário informar o nome da Doença/Comorbidade")
	private String nome;

	private String descricao;

	private SituacaoDoenca situacaoDoenca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SituacaoDoenca getSituacaoDoenca() {
		return situacaoDoenca;
	}

	public void setSituacaoDoenca(SituacaoDoenca situacaoDoenca) {
		this.situacaoDoenca = situacaoDoenca;
	}
}
