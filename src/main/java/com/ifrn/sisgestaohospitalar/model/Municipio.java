package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Municipio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nomeMunicipio;
	@NotBlank
	private String nomeMunicipioSiglaUF;
	@NotNull
	private Long codigoIBGE;
	@NotNull
	private Long codigoIBGE7;
	@OneToOne
	private Estado estado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getNomeMunicipioSiglaUF() {
		return nomeMunicipioSiglaUF;
	}
	public void setNomeMunicipioSiglaUF(String nomeMunicipioSiglaUF) {
		this.nomeMunicipioSiglaUF = nomeMunicipioSiglaUF;
	}
	public Long getCodigoIBGE() {
		return codigoIBGE;
	}
	public void setCodigoIBGE(Long codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}
	public Long getCodigoIBGE7() {
		return codigoIBGE7;
	}
	public void setCodigoIBGE7(Long codigoIBGE7) {
		this.codigoIBGE7 = codigoIBGE7;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
