package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class ArquivoBPA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String cabecalhoHeader;
	
	private String indicadorHeader;
	
	private String competencia;
	
	private String qtdLinhas;
	
	private String qtdFolhas;
	
	private String controleDominio;
	
	private String orgaoResponsavel;
	
	private String siglaOrgaoResponsavel;
	
	private String cnpjOrgaoResponsavel;
	
	private String orgaoDestino;
	
	private String incadorOrgao;
	
	private String versaoSistema;
	
	private LocalDate dataGeracao;
	
	private LocalDateTime horaGeracao;
	
	private String link;
	
	private String nomeArquivo;
	
	private boolean gerado;
	
	@ManyToMany
	@JoinTable(name = "arquivoBpa_bpaConsolidado")
	private List<BPAConsolidado> bpasConsolidado;
	
	@ManyToMany
	@JoinTable(name = "arquivoBpa_bpaIndividualizado")
	private List<BPAIndividualizado> bpasIndividualizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCabecalhoHeader() {
		return cabecalhoHeader;
	}

	public void setCabecalhoHeader(String cabecalhoHeader) {
		this.cabecalhoHeader = cabecalhoHeader;
	}

	public String getIndicadorHeader() {
		return indicadorHeader;
	}

	public void setIndicadorHeader(String indicadorHeader) {
		this.indicadorHeader = indicadorHeader;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	public String getQtdLinhas() {
		return qtdLinhas;
	}

	public void setQtdLinhas(String qtdLinhas) {
		this.qtdLinhas = String.format("%06d", new Object[] {Integer.parseInt(qtdLinhas)});
	}

	public String getQtdFolhas() {
		return qtdFolhas;
	}

	public void setQtdFolhas(String qtdFolhas) {
		this.qtdFolhas = String.format("%06d", new Object[] {Integer.parseInt(qtdFolhas)});
	}

	public String getControleDominio() {
		return controleDominio;
	}

	public void setControleDominio(String controleDominio) {
		this.controleDominio = controleDominio;
	}

	public String getOrgaoResponsavel() {
		return orgaoResponsavel;
	}

	public void setOrgaoResponsavel(String orgaoResponsavel) {
		this.orgaoResponsavel = String.format("%-30.30s", orgaoResponsavel);
	}

	public String getSiglaOrgaoResponsavel() {
		return siglaOrgaoResponsavel;
	}

	public void setSiglaOrgaoResponsavel(String siglaOrgaoResponsavel) {
		this.siglaOrgaoResponsavel = String.format("%-6.6s", siglaOrgaoResponsavel);
	}

	public String getCnpjOrgaoResponsavel() {
		return cnpjOrgaoResponsavel;
	}

	public void setCnpjOrgaoResponsavel(String cnpjOrgaoResponsavel) {
		this.cnpjOrgaoResponsavel = String.format("%-14.14s", cnpjOrgaoResponsavel);
	}

	public String getOrgaoDestino() {
		return orgaoDestino;
	}

	public void setOrgaoDestino(String orgaoDestino) {
		this.orgaoDestino = String.format("%-40.40s", orgaoDestino);
	}

	public String getIncadorOrgao() {
		return incadorOrgao;
	}

	public void setIncadorOrgao(String incadorOrgao) {
		this.incadorOrgao = incadorOrgao;
	}

	public String getVersaoSistema() {
		return versaoSistema;
	}

	public void setVersaoSistema(String versaoSistema) {
		this.versaoSistema = String.format("%-10.10s", versaoSistema);
	}

	public List<BPAConsolidado> getBpasConsolidado() {
		return bpasConsolidado;
	}

	public void setBpasConsolidado(List<BPAConsolidado> bpasConsolidado) {
		this.bpasConsolidado = bpasConsolidado;
	}

	public List<BPAIndividualizado> getBpasIndividualizado() {
		return bpasIndividualizado;
	}

	public void setBpasIndividualizado(List<BPAIndividualizado> bpasIndividualizado) {
		this.bpasIndividualizado = bpasIndividualizado;
	}

	public LocalDate getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDate dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public boolean isGerado() {
		return gerado;
	}

	public void setGerado(boolean gerado) {
		this.gerado = gerado;
	}
	

	public LocalDateTime getHoraGeracao() {
		return horaGeracao;
	}

	public void setHoraGeracao(LocalDateTime horaGeracao) {
		this.horaGeracao = horaGeracao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public String toString() {
		return cabecalhoHeader + indicadorHeader + competencia + qtdLinhas + qtdFolhas 
				+ controleDominio + orgaoResponsavel + siglaOrgaoResponsavel + cnpjOrgaoResponsavel
				+ orgaoDestino + incadorOrgao + versaoSistema;
	}
	
}
