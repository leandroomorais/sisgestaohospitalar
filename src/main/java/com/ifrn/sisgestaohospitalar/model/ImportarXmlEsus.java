package com.ifrn.sisgestaohospitalar.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A classe <code>ImportarXmlEsus</code> representa os objetos do tipo
 * ImportarXmlEsus é uma classe auxiliar para a leitura do arquivo XML do ESUS
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@XmlRootElement(name = "ImportarXMLCNES")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportarXmlEsus {

	@XmlElement(name = "IDENTIFICACAO")
	public IdentificacaoXmlEsus identificacao;
	
	/**Getters and setters*/

	public IdentificacaoXmlEsus getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(IdentificacaoXmlEsus identificacao) {
		this.identificacao = identificacao;
	}

}
