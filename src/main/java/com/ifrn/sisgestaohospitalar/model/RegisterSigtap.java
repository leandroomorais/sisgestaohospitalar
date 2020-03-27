package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe para objetos do tipo Registro da Tabela Sigtap, onde serão contidos
 * atributos e métodos para o mesmo.
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Entity
public class RegisterSigtap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String coderegister;

	private String numberregister;

	private String datetcompetency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoderegister() {
		return coderegister;
	}

	public void setCoderegister(String coderegister) {
		this.coderegister = coderegister;
	}

	public String getNumberregister() {
		return numberregister;
	}

	public void setNumberregister(String numberregister) {
		this.numberregister = numberregister;
	}

	public String getDatetcompetency() {
		return datetcompetency;
	}

	public void setDatetcompetency(String datetcompetency) {
		this.datetcompetency = datetcompetency;
	}
}
