package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Atestado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500)
	private String texto;

	@Positive(message = "O período não pode ser igual o inferior a 0")
	private int periodo;

	private boolean autorizaImpressaoCid;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atestado_cid", joinColumns = @JoinColumn(name = "id_atestado"), inverseJoinColumns = @JoinColumn(name = "id_cid"))
	private List<Cid> cids;

	@NotNull
	@JsonIgnore
	@OneToOne
	private Atendimento atendimento;

	@NotNull
	@JsonIgnore
	@OneToOne
	private Prontuario prontuario;

	private LocalDateTime dataRegistro;

	@OneToOne
	private Profissional profissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public boolean isAutorizaImpressaoCid() {
		return autorizaImpressaoCid;
	}

	public void setAutorizaImpressaoCid(boolean autorizaImpressaoCid) {
		this.autorizaImpressaoCid = autorizaImpressaoCid;
	}

	public List<Cid> getCids() {
		return cids;
	}

	public void setCids(List<Cid> cids) {
		this.cids = cids;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

}
