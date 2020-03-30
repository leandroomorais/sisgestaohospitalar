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
 * Classe para objetos do tipo Procedimento da Tabela Sigtap, onde serão
 * contidos atributos e métodos para o mesmo.
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Entity
public class ProcedureSigtap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String codeprocedure;

	private String nameprocedure;

	private char typecomplexity;

	private char typesex;

	private int qtmaximumexecution;

	private int qtstaydays;

	private int qtpoints;

	private int vlminimumage;

	private int vlmaximumage;

	private int vlSh;

	private int vlSa;

	private int vlSp;

	private String codefinancing;

	private String coderubric;

	private int qtlenghtstay;

	private String datecompetency;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="proceduresigtap_register_relationship", 
	joinColumns= {@JoinColumn(name="procedure_id")}, 
	inverseJoinColumns= {@JoinColumn(name="register_id")})
	private List<RegisterSigtap> registersSigtap;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="proceduresigtap_occupation_relationship", 
	joinColumns= {@JoinColumn(name="procedure_id")}, 
	inverseJoinColumns= {@JoinColumn(name="occupation_id")})
	private List<OccupationSigtap> occupationsSigtap;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="proceduresigtap_cid_relationship", 
	joinColumns= {@JoinColumn(name="procedure_id")}, 
	inverseJoinColumns= {@JoinColumn(name="cid_id")})
	private List<CidSigtap> cidsSigtap;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeprocedure() {
		return codeprocedure;
	}

	public void setCodeprocedure(String codeprocedure) {
		this.codeprocedure = codeprocedure;
	}

	public String getNameprocedure() {
		return nameprocedure;
	}

	public void setNameprocedure(String nameprocedure) {
		this.nameprocedure = nameprocedure;
	}

	public char getTypecomplexity() {
		return typecomplexity;
	}

	public void setTypecomplexity(char typecomplexity) {
		this.typecomplexity = typecomplexity;
	}

	public char getTypesex() {
		return typesex;
	}

	public void setTypesex(char typesex) {
		this.typesex = typesex;
	}

	public int getQtmaximumexecution() {
		return qtmaximumexecution;
	}

	public void setQtmaximumexecution(int qtmaximumexecution) {
		this.qtmaximumexecution = qtmaximumexecution;
	}

	public int getQtstaydays() {
		return qtstaydays;
	}

	public void setQtstaydays(int qtstaydays) {
		this.qtstaydays = qtstaydays;
	}

	public int getQtpoints() {
		return qtpoints;
	}

	public void setQtpoints(int qtpoints) {
		this.qtpoints = qtpoints;
	}

	public int getVlminimumage() {
		return vlminimumage;
	}

	public void setVlminimumage(int vlminimumage) {
		this.vlminimumage = vlminimumage;
	}

	public int getVlmaximumage() {
		return vlmaximumage;
	}

	public void setVlmaximumage(int vlmaximumage) {
		this.vlmaximumage = vlmaximumage;
	}

	public int getVlSh() {
		return vlSh;
	}

	public void setVlSh(int vlSh) {
		this.vlSh = vlSh;
	}

	public int getVlSa() {
		return vlSa;
	}

	public void setVlSa(int vlSa) {
		this.vlSa = vlSa;
	}

	public int getVlSp() {
		return vlSp;
	}

	public void setVlSp(int vlSp) {
		this.vlSp = vlSp;
	}

	public String getCodefinancing() {
		return codefinancing;
	}

	public void setCodefinancing(String codefinancing) {
		this.codefinancing = codefinancing;
	}

	public String getCoderubric() {
		return coderubric;
	}

	public void setCoderubric(String coderubric) {
		this.coderubric = coderubric;
	}

	public int getQtlenghtstay() {
		return qtlenghtstay;
	}

	public void setQtlenghtstay(int qtlenghtstay) {
		this.qtlenghtstay = qtlenghtstay;
	}

	public String getDatecompetency() {
		return datecompetency;
	}

	public void setDatecompetency(String datecompetency) {
		this.datecompetency = datecompetency;
	}

	public List<RegisterSigtap> getRegistersSigtap() {
		return registersSigtap;
	}

	public void setRegistersSigtap(List<RegisterSigtap> registersSigtap) {
		this.registersSigtap = registersSigtap;
	}

	public List<OccupationSigtap> getOccupationsSigtap() {
		return occupationsSigtap;
	}

	public void setOccupationsSigtap(List<OccupationSigtap> occupationsSigtap) {
		this.occupationsSigtap = occupationsSigtap;
	}

	public List<CidSigtap> getCidsSigtap() {
		return cidsSigtap;
	}

	public void setCidsSigtap(List<CidSigtap> cidsSigtap) {
		this.cidsSigtap = cidsSigtap;
	}

}
