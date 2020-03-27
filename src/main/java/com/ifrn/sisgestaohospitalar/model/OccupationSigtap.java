package com.ifrn.sisgestaohospitalar.model;

/**Classe para objetos do tipo Ocupação da Tabela Sigtap, onde serão contidos atributos e métodos para o mesmo.
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OccupationSigtap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String codeoccupation;

	private String numberoccupation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeoccupation() {
		return codeoccupation;
	}

	public void setCodeoccupation(String codeoccupation) {
		this.codeoccupation = codeoccupation;
	}

	public String getNumberoccupation() {
		return numberoccupation;
	}

	public void setNumberoccupation(String numberoccupation) {
		this.numberoccupation = numberoccupation;
	}

}
