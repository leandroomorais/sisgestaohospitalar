package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**Classe para objetos do tipo Cid (Código Internacional de Doenças) da Tabela Sigtap, onde serão contidos atributos e métodos para o mesmo.
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Entity
public class CidSigtap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String codecid;
	
	private String numbercid;
	
	private char typeAgravo;
	
	private char typeSexo;
	
	private char typeEstadio;
	
	private int valueCamposIrradiados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodecid() {
		return codecid;
	}

	public void setCodecid(String codecid) {
		this.codecid = codecid;
	}

	public String getNumbercid() {
		return numbercid;
	}

	public void setNumbercid(String numbercid) {
		this.numbercid = numbercid;
	}

	public char getTypeAgravo() {
		return typeAgravo;
	}

	public void setTypeAgravo(char typeAgravo) {
		this.typeAgravo = typeAgravo;
	}

	public char getTypeSexo() {
		return typeSexo;
	}

	public void setTypeSexo(char typeSexo) {
		this.typeSexo = typeSexo;
	}

	public char getTypeEstadio() {
		return typeEstadio;
	}

	public void setTypeEstadio(char typeEstadio) {
		this.typeEstadio = typeEstadio;
	}

	public int getValueCamposIrradiados() {
		return valueCamposIrradiados;
	}

	public void setValueCamposIrradiados(int valueCamposIrradiados) {
		this.valueCamposIrradiados = valueCamposIrradiados;
	}
}
