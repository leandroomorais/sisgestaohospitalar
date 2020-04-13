package com.ifrn.sisgestaohospitalar.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * A classe <code>ProcedimentoSigtap</code> representa os objetos do tipo
 * ProcedimentoSigtap e contém os atributos da Tabela de Procedimentos do SUS
 * Sigtap.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
public class ProcedimentoSigtap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String codigoprocedimento;

	private String nomeprocedimento;

	private char tipocomplexidade;

	private char tiposexo;

	private int qtdmaximaexecucao;

	private int qtddiaspermanencia;

	private int qtdpontos;

	private int vlidademinina;

	private int vlidademaxima;

	private int vlsh;

	private int vlsa;

	private int vlsp;

	private String codigofinanciamento;

	private String codigorubrica;

	private int qtdtempopermanencia;

	private String datacompetencia;

	/**
	 * Relacionamento entre os objetos ProcedimentoSigtap e RegistroSigtap
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "procedimento_tiporegistro", joinColumns = {
			@JoinColumn(name = "procedimento_id") }, inverseJoinColumns = { @JoinColumn(name = "registro_id") })
	private List<RegistroSigtap> registros;

	/**
	 * Relacionamento entre os objetos ProcedimentoSigtap e OcupacaoSigtap
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "procedimento_ocupacao", joinColumns = {
			@JoinColumn(name = "procedimento_id") }, inverseJoinColumns = { @JoinColumn(name = "ocupacao_id") })
	private List<OcupacaoSigtap> ocupacoes;

	/**
	 * Relacionamento entre os objetos ProcedimentoSigtap e CidSigtap
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "procedimento_cid", joinColumns = {
			@JoinColumn(name = "procedimento_id") }, inverseJoinColumns = { @JoinColumn(name = "cid_id") })
	private List<CidSigtap> cids;

	/** Getters and setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoprocedimento() {
		return codigoprocedimento;
	}

	public void setCodigoprocedimento(String codigoprocedimento) {
		this.codigoprocedimento = codigoprocedimento;
	}

	public String getNomeprocedimento() {
		return nomeprocedimento;
	}

	public void setNomeprocedimento(String numerorocedimento) {
		this.nomeprocedimento = numerorocedimento;
	}

	public char getTipocomplexidade() {
		return tipocomplexidade;
	}

	public void setTipocomplexidade(char tipocomplexidade) {
		this.tipocomplexidade = tipocomplexidade;
	}

	public char getTiposexo() {
		return tiposexo;
	}

	public void setTiposexo(char tiposexo) {
		this.tiposexo = tiposexo;
	}

	public int getQtdmaximaexecucao() {
		return qtdmaximaexecucao;
	}

	public void setQtdmaximaexecucao(int qtdmaximaexecucao) {
		this.qtdmaximaexecucao = qtdmaximaexecucao;
	}

	public int getQtddiaspermanencia() {
		return qtddiaspermanencia;
	}

	public void setQtddiaspermanencia(int qtddiaspermanencia) {
		this.qtddiaspermanencia = qtddiaspermanencia;
	}

	public int getQtdpontos() {
		return qtdpontos;
	}

	public void setQtdpontos(int qtdpontos) {
		this.qtdpontos = qtdpontos;
	}

	public int getVlidademinina() {
		return vlidademinina;
	}

	public void setVlidademinina(int vlidademinina) {
		this.vlidademinina = vlidademinina;
	}

	public int getVlidademaxima() {
		return vlidademaxima;
	}

	public void setVlidademaxima(int vlidademaxima) {
		this.vlidademaxima = vlidademaxima;
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

	public String getCodigofinanciamento() {
		return codigofinanciamento;
	}

	public void setCodigofinanciamento(String codigofinanciamento) {
		this.codigofinanciamento = codigofinanciamento;
	}

	public String getCodigorubrica() {
		return codigorubrica;
	}

	public void setCodigorubrica(String codigorubrica) {
		this.codigorubrica = codigorubrica;
	}

	public int getQtdtempopermanencia() {
		return qtdtempopermanencia;
	}

	public void setQtdtempopermanencia(int qtdtempopermanencia) {
		this.qtdtempopermanencia = qtdtempopermanencia;
	}

	public String getDatacompetencia() {
		return datacompetencia;
	}

	public void setDatacompetencia(String datacompetencia) {
		this.datacompetencia = datacompetencia;
	}

	public List<RegistroSigtap> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroSigtap> registros) {
		this.registros = registros;
	}

	public List<OcupacaoSigtap> getOcupacoes() {
		return ocupacoes;
	}

	public void setOcupacoes(List<OcupacaoSigtap> ocupacoes) {
		this.ocupacoes = ocupacoes;
	}

	public List<CidSigtap> getCids() {
		return cids;
	}

	public void setCids(List<CidSigtap> cids) {
		this.cids = cids;
	}

}
