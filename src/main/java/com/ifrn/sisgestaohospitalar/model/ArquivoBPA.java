package com.ifrn.sisgestaohospitalar.model;

import java.math.BigDecimal;
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

	private char incadorOrgao;

	private String versaoSistema;

	private String fim;

	private LocalDate dataGeracao;

	private LocalDateTime horaGeracao;

	private String link;
//  byte[]
	private String nomeArquivo;

	private boolean gerado;

	private String cnes;

	private BigDecimal valorTotal;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "arquivo_bpa_folha_consolidado", joinColumns = @JoinColumn(name = "id_arquivobpa"), inverseJoinColumns = @JoinColumn(name = "id_folha_bpa_consolidado"))
	private List<FolhaBPAConsolidado> folhasBPAConsolidado;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "arquivo_bpa_folha_individualizado", joinColumns = @JoinColumn(name = "id_arquivobpa"), inverseJoinColumns = @JoinColumn(name = "id_folha_bpa_individualizado"))
	private List<FolhaBPAIndividualizado> folhasBPAIndividualizado;

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
		this.cabecalhoHeader = String.format("%-2.2s", cabecalhoHeader);
	}

	public String getIndicadorHeader() {
		return indicadorHeader;
	}

	public void setIndicadorHeader(String indicadorHeader) {
		this.indicadorHeader = String.format("%-5.5s", indicadorHeader);
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = String.format("%-6.6s", competencia);
	}

	public String getQtdLinhas() {
		return qtdLinhas;
	}

	public void setQtdLinhas(String qtdLinhas) {
		this.qtdLinhas = String.format("%06d", new Object[] { Integer.parseInt(qtdLinhas) });
	}

	public String getQtdFolhas() {
		return qtdFolhas;
	}

	public void setQtdFolhas(String qtdFolhas) {
		this.qtdFolhas = String.format("%06d", new Object[] { Integer.parseInt(qtdFolhas) });
	}

	public String getControleDominio() {
		return controleDominio;
	}

	public void setControleDominio(String controleDominio) {
		this.controleDominio = String.format("%-4.4s", controleDominio);
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

	public char getIncadorOrgao() {
		return incadorOrgao;
	}

	public void setIncadorOrgao(char incadorOrgao) {
		this.incadorOrgao = incadorOrgao;
	}

	public String getVersaoSistema() {
		return versaoSistema;
	}

	public void setVersaoSistema(String versaoSistema) {
		this.versaoSistema = String.format("%-10.10s", versaoSistema);
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
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

	public List<FolhaBPAConsolidado> getFolhasBPAConsolidado() {
		return folhasBPAConsolidado;
	}

	public void setFolhasBPAConsolidado(List<FolhaBPAConsolidado> folhasBPAConsolidado) {
		this.folhasBPAConsolidado = folhasBPAConsolidado;
	}

	public List<FolhaBPAIndividualizado> getFolhasBPAIndividualizado() {
		return folhasBPAIndividualizado;
	}

	public void setFolhasBPAIndividualizado(List<FolhaBPAIndividualizado> folhasBPAIndividualizado) {
		this.folhasBPAIndividualizado = folhasBPAIndividualizado;
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return cabecalhoHeader + indicadorHeader + competencia + qtdLinhas + qtdFolhas + controleDominio
				+ orgaoResponsavel + siglaOrgaoResponsavel + cnpjOrgaoResponsavel + orgaoDestino + incadorOrgao
				+ versaoSistema;
	}

}
