package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BPAConsolidado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String linhaIdenti;
	
	private String cnes;
	
	private String competencia;
	
	private String cboProfissional;
	
	private String numeroFolha;
	
	private String numeroLinha;
	
	private String codigoProcedimento;
	
	private String idade;
	
	private String quantidade;
	
	private String origem;
	
	private String fim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLinhaIdenti() {
		return linhaIdenti;
	}

	public void setLinhaIdenti(String linhaIdenti) {
		this.linhaIdenti = linhaIdenti;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes =  String.format("%-7.7s", cnes);
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	public String getCboProfissional() {
		return cboProfissional;
	}

	public void setCboProfissional(String cboProfissional) {
		this.cboProfissional =  String.format("%-6.6s", cboProfissional);
	}

	public String getNumeroFolha() {
		return numeroFolha;
	}

	public void setNumeroFolha(String numeroFolha) {
		this.numeroFolha = String.format("%03d", new Object[] {Integer.parseInt(numeroFolha)});
	}

	public String getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(String numeroLinha) {
		this.numeroLinha = String.format("%02d", new Object[] {Integer.parseInt(numeroLinha)});
	}

	public String getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(String codigoProcedimento) {
		this.codigoProcedimento = String.format("%-10.10s", cboProfissional);
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = String.format("%03d", new Object[] {Integer.parseInt(idade)});
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = String.format("%06d", new Object[] {Integer.parseInt(quantidade)});
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = String.format("%-2.2s", fim);
	}

}
