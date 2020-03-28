package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe para objetos do tipo Ciap2 (Classificação Internacional da Atenção
 * Primária) onde serão contidos atributos e métodos para o mesmo.
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */

@Entity
public class Ciap2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String codeciap2;

	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeciap2() {
		return codeciap2;
	}

	public void setCodeciap2(String codeciap2) {
		this.codeciap2 = codeciap2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
