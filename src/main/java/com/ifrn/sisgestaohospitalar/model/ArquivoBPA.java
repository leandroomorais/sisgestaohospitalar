package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "arquivo_bpa_linhas_consolidado", joinColumns = @JoinColumn(name = "id_arquivo"), inverseJoinColumns = @JoinColumn(name = "id_linha"))
	private List<LinhaBPAConsolidado> linhasBPAConsolidado;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "arquivo_bpa_linhas_individualizado", joinColumns = @JoinColumn(name = "id_arquivo"), inverseJoinColumns = @JoinColumn(name = "id_linha"))
	private List<LinhaBPAIndividualizado> linhasBPAIndividualizado;

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
		this.qtdLinhas = qtdLinhas;
	}

	public String getQtdFolhas() {
		return qtdFolhas;
	}

	public void setQtdFolhas(String qtdFolhas) {
		this.qtdFolhas = qtdFolhas;
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
		this.orgaoResponsavel = orgaoResponsavel;
	}

	public String getSiglaOrgaoResponsavel() {
		return siglaOrgaoResponsavel;
	}

	public void setSiglaOrgaoResponsavel(String siglaOrgaoResponsavel) {
		this.siglaOrgaoResponsavel = siglaOrgaoResponsavel;
	}

	public String getCnpjOrgaoResponsavel() {
		return cnpjOrgaoResponsavel;
	}

	public void setCnpjOrgaoResponsavel(String cnpjOrgaoResponsavel) {
		this.cnpjOrgaoResponsavel = cnpjOrgaoResponsavel;
	}

	public String getOrgaoDestino() {
		return orgaoDestino;
	}

	public void setOrgaoDestino(String orgaoDestino) {
		this.orgaoDestino = orgaoDestino;
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
		this.versaoSistema = versaoSistema;
	}

	public LocalDate getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDate dataGeracao) {
		this.dataGeracao = dataGeracao;
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

	public boolean isGerado() {
		return gerado;
	}

	public void setGerado(boolean gerado) {
		this.gerado = gerado;
	}

	public List<LinhaBPAConsolidado> getLinhasBPAConsolidado() {
		return linhasBPAConsolidado;
	}

	public void setLinhasBPAConsolidado(List<LinhaBPAConsolidado> linhasBPAConsolidado) {
		this.linhasBPAConsolidado = linhasBPAConsolidado;
	}

	public List<LinhaBPAIndividualizado> getLinhasBPAIndividualizado() {
		return linhasBPAIndividualizado;
	}

	public void setLinhasBPAIndividualizado(List<LinhaBPAIndividualizado> linhasBPAIndividualizado) {
		this.linhasBPAIndividualizado = linhasBPAIndividualizado;
	}
}
