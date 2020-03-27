package com.ifrn.sisgestaohospitalar.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**Classe auxiliar para a Leitura do Arquivo XML do ESUS
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da aplicação
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class IdentificationXmlEsus {
	
	/**Mapeia a Lista de Profissionais do Arquivo XML*/
	@XmlElementWrapper(name = "PROFISSIONAIS")
	@XmlElement(name = "DADOS_PROFISSIONAIS")
	List<Professional> professionals = new ArrayList<Professional>();
	
	/**Mapeia a Lista de Dados dos estabelecimentos do Arquivo XML*/
	@XmlElementWrapper(name = "ESTABELECIMENTOS")
	@XmlElement(name = "DADOS_GERAIS_ESTABELECIMENTOS")
	List<Establishment> establishments = new ArrayList<Establishment>();

	public List<Professional> getProfessionals() {
		return professionals;
	}

	public void setProfessionals(List<Professional> professionals) {
		this.professionals = professionals;
	}

	public List<Establishment> getEstablishments() {
		return establishments;
	}

	public void setEstablishments(List<Establishment> establishments) {
		this.establishments = establishments;
	}

}
