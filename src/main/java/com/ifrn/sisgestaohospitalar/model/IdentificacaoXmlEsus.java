package com.ifrn.sisgestaohospitalar.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * A classe <code>IdentificacaoXmlEsus</code> representa os objetos do tipo IdentificacaoXmlEsus contendo
 * seus atributos e métodos e funcionando como classe auxiliar na leitura do arquivo XML do ESUS
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class IdentificacaoXmlEsus {
	
	@XmlElementWrapper(name = "PROFISSIONAIS")
	@XmlElement(name = "DADOS_PROFISSIONAIS")
	List<Profissional> profissionais = new ArrayList<Profissional>();
	
	@XmlElementWrapper(name = "ESTABELECIMENTOS")
	@XmlElement(name = "DADOS_GERAIS_ESTABELECIMENTOS")
	List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
	
	/**Getters and setters*/

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

	public List<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}

	public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}
	
}
