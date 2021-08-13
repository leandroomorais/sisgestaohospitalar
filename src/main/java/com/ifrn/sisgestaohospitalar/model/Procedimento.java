package com.ifrn.sisgestaohospitalar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Procedimento {

	@Id
	private Long codigo;

	private String nome;

	private char tipoComplexidade;

	private char tipoSexo;

	private int qtdMaximaExecucao;

	private int qtdDiasPermanencia;

	private int qtdPontos;

	private int vlIdadeMinina;

	private int vlIdadeMaxima;

	private int vlsh;

	private int vlsa;

	private int vlsp;

	private String codigoFinanciamento;

	private String codigoRubrica;

	private int qtdTempoPermanencia;

	private String dataCompetencia;

	private Integer quantidade;

	/**
	 * Relacionamento entre os objetos Procedimento e RegistroSigtap
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "procedimento_tiporegistro", joinColumns = {
			@JoinColumn(name = "procedimento_id") }, inverseJoinColumns = { @JoinColumn(name = "registro_id") })
	private List<RegistroSigtap> registros;

	/**
	 * Relacionamento entre os objetos Procedimento e Ocupacao
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "procedimento_ocupacao", joinColumns = {
			@JoinColumn(name = "procedimento_id") }, inverseJoinColumns = { @JoinColumn(name = "ocupacao_id") })
	private List<Ocupacao> ocupacoes;

	/**
	 * Relacionamento entre os objetos Procedimento e Cid
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "procedimento_cid", joinColumns = {
			@JoinColumn(name = "procedimento_id") }, inverseJoinColumns = { @JoinColumn(name = "cid_id") })
	private List<Cid> cids;

	/** Getters and setters */

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getTipoComplexidade() {
		return tipoComplexidade;
	}

	public void setTipoComplexidade(char tipoComplexidade) {
		this.tipoComplexidade = tipoComplexidade;
	}

	public char getTipoSexo() {
		return tipoSexo;
	}

	public void setTipoSexo(char tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public int getQtdMaximaExecucao() {
		return qtdMaximaExecucao;
	}

	public void setQtdMaximaExecucao(int qtdMaximaExecucao) {
		this.qtdMaximaExecucao = qtdMaximaExecucao;
	}

	public int getQtdDiasPermanencia() {
		return qtdDiasPermanencia;
	}

	public void setQtdDiasPermanencia(int qtdDiasPermanencia) {
		this.qtdDiasPermanencia = qtdDiasPermanencia;
	}

	public int getQtdPontos() {
		return qtdPontos;
	}

	public void setQtdPontos(int qtdPontos) {
		this.qtdPontos = qtdPontos;
	}

	public int getVlIdadeMinina() {
		return vlIdadeMinina;
	}

	public void setVlIdadeMinina(int vlIdadeMinina) {
		this.vlIdadeMinina = vlIdadeMinina;
	}

	public int getVlIdadeMaxima() {
		return vlIdadeMaxima;
	}

	public void setVlIdadeMaxima(int vlIdadeMaxima) {
		this.vlIdadeMaxima = vlIdadeMaxima;
	}

	public int getVlsh() {
		return vlsh;
	}

	public void setVlsh(int vlsh) {
		this.vlsh = vlsh;
	}

	public int getVlsa() {
		return vlsa;
	}

	public void setVlsa(int vlsa) {
		this.vlsa = vlsa;
	}

	public int getVlsp() {
		return vlsp;
	}

	public void setVlsp(int vlsp) {
		this.vlsp = vlsp;
	}

	public String getCodigoFinanciamento() {
		return codigoFinanciamento;
	}

	public void setCodigoFinanciamento(String codigoFinanciamento) {
		this.codigoFinanciamento = codigoFinanciamento;
	}

	public String getCodigoRubrica() {
		return codigoRubrica;
	}

	public void setCodigoRubrica(String codigoRubrica) {
		this.codigoRubrica = codigoRubrica;
	}

	public int getQtdTempoPermanencia() {
		return qtdTempoPermanencia;
	}

	public void setQtdTempoPermanencia(int qtdTempoPermanencia) {
		this.qtdTempoPermanencia = qtdTempoPermanencia;
	}

	public String getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(String dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<RegistroSigtap> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroSigtap> registros) {
		this.registros = registros;
	}

	public List<Ocupacao> getOcupacoes() {
		return ocupacoes;
	}

	public void setOcupacoes(List<Ocupacao> ocupacoes) {
		this.ocupacoes = ocupacoes;
	}

	public List<Cid> getCids() {
		return cids;
	}

	public void setCids(List<Cid> cids) {
		this.cids = cids;
	}

}
