package com.ifrn.sisgestaohospitalar.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

	private BigDecimal vlsh;

	private BigDecimal vlsa;

	private BigDecimal vlsp;

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
	@JoinTable(name = "procedimento_tiposregistro", joinColumns = {
			@JoinColumn(name = "procedimento_id") }, inverseJoinColumns = { @JoinColumn(name = "registro_id") })
	private List<RegistroSigtap> registros;
	

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

	public BigDecimal getVlsh() {
		return vlsh;
	}

	public void setVlsh(BigDecimal vlsh) {
		this.vlsh = vlsh;
	}

	public BigDecimal getVlsa() {
		return vlsa;
	}

	public void setVlsa(BigDecimal vlsa) {
		this.vlsa = vlsa;
	}

	public BigDecimal getVlsp() {
		return vlsp;
	}

	public void setVlsp(BigDecimal vlsp) {
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

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Procedimento other = (Procedimento) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	
	

}
