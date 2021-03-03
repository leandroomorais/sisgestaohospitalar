package com.ifrn.sisgestaohospitalar.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ImportarXMLCNES")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportarXmlEsus {

	@XmlElement(name = "IDENTIFICACAO")
	public IdentificacaoXmlEsus identificacao;

	/** Getters and setters */

	public IdentificacaoXmlEsus getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(IdentificacaoXmlEsus identificacao) {
		this.identificacao = identificacao;
	}

}
