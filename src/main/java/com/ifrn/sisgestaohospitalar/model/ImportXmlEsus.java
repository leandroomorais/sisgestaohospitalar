package com.ifrn.sisgestaohospitalar.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe auxiliar para a Leitura do Arquivo XML do ESUS
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da aplicação
 */

@XmlRootElement(name = "ImportarXMLCNES")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportXmlEsus {

	/** Mapeia os elementos da TAG Identificação do XML */
	@XmlElement(name = "IDENTIFICACAO")
	public IdentificationXmlEsus identificationXmlEsus;

	public IdentificationXmlEsus getIdentificationXmlEsus() {
		return identificationXmlEsus;
	}

	public void setIdentificationXmlEsus(IdentificationXmlEsus identificationXmlEsus) {
		this.identificationXmlEsus = identificationXmlEsus;
	}
}
