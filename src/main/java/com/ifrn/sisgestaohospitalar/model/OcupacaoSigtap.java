package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A classe <code>OcupacaoSigtap</code> representa os objetos do tipo
 * OcupacaoSigtap e contém os atributos da tabela de Ocupações da Sigtap SUS.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
public class OcupacaoSigtap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String codigoocupacao;

	private String nomeocupacao;

	/** Getters and setters */

	public String getCodigoocupacao() {
		return codigoocupacao;
	}

	public void setCodigoocupacao(String codigoocupacao) {
		this.codigoocupacao = codigoocupacao;
	}

	public String getNomeocupacao() {
		return nomeocupacao;
	}

	public void setNomeocupacao(String nomeocupacao) {
		this.nomeocupacao = nomeocupacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
