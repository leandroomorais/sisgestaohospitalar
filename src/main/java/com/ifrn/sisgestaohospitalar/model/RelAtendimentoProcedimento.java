package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ifrn.sisgestaohospitalar.enums.TipoServico;

@Entity
public class RelAtendimentoProcedimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long codigoProcedimento;
	
	private Long idProfissional;
	
	private TipoServico tipoServico;
	
	private String codigoCid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(Long codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}

	public Long getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(Long idProfissional) {
		this.idProfissional = idProfissional;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getCodigoCid() {
		return codigoCid;
	}

	public void setCodigoCid(String codigoCid) {
		this.codigoCid = codigoCid;
	}
	
}
