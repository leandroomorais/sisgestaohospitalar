package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class RegistroAdministracao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean administracaoRealizada;
	
	private LocalDateTime dataAdministracao;
	
	@OneToOne
	private Profissional profissionalResponsavel;
	
	@Transient
	private Long idPrescricao;
	
	private String nota;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAdministracaoRealizada() {
		return administracaoRealizada;
	}

	public void setAdministracaoRealizada(boolean administracaoRealizada) {
		this.administracaoRealizada = administracaoRealizada;
	}

	public LocalDateTime getDataAdministracao() {
		return dataAdministracao;
	}

	public void setDataAdministracao(LocalDateTime dataAdministracao) {
		this.dataAdministracao = dataAdministracao;
	}

	public Profissional getProfissionalResponsavel() {
		return profissionalResponsavel;
	}

	public void setProfissionalResponsavel(Profissional profissionalResponsavel) {
		this.profissionalResponsavel = profissionalResponsavel;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Long getIdPrescricao() {
		return idPrescricao;
	}

	public void setIdPrescricao(Long idPrescricao) {
		this.idPrescricao = idPrescricao;
	}

	@Override
	public String toString() {
		return "RegistroAdministracao [id=" + id + ", administracaoRealizada=" + administracaoRealizada
				+ ", dataAdministracao=" + dataAdministracao + ", profissionalResponsavel=" + profissionalResponsavel
				+ ", idPrescricao=" + idPrescricao + ", nota=" + nota + "]";
	}
	
	
	
}
