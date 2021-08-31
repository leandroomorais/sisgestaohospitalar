package com.ifrn.sisgestaohospitalar.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;

import com.ifrn.sisgestaohospitalar.model.Cid;



public class AlergiaDTO {
	
	private Long id;
	
	@NotBlank(message = "É necessário informar o nome")
	private String nome;
	
	private String descricao;
	
	private Cid cid;
	
	private LocalDateTime dataCadastro;

	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cid getCid() {
		return cid;
	}

	public void setCid(Cid cid) {
		this.cid = cid;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
