package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cid {

	@Id
	private String codigo;

	private String nome;

	private char tipoAgravo;

	private char tipoSexo;

	private char tipoEstadio;

	private int valorCamposIrradiados;

	/** Getters and setters */

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getTipoAgravo() {
		return tipoAgravo;
	}

	public void setTipoAgravo(char tipoAgravo) {
		this.tipoAgravo = tipoAgravo;
	}

	public char getTipoSexo() {
		return tipoSexo;
	}

	public void setTipoSexo(char tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	public char getTipoEstadio() {
		return tipoEstadio;
	}

	public void setTipoEstadio(char tipoEstadio) {
		this.tipoEstadio = tipoEstadio;
	}

	public int getValorCamposIrradiados() {
		return valorCamposIrradiados;
	}

	public void setValorCamposIrradiados(int valorCamposIrradiados) {
		this.valorCamposIrradiados = valorCamposIrradiados;
	}

}
