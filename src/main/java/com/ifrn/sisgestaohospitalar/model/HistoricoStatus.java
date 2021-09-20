package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.ifrn.sisgestaohospitalar.enums.Status;

@Entity
public class HistoricoStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Status status;

	private LocalDateTime ultimaAtualizacao;
	
	@ManyToMany
	@JoinTable(name = "historico_servicos", joinColumns = @JoinColumn(name = "id_historico"), inverseJoinColumns = @JoinColumn(name = "id_servico"))
	private List<TipoServico> tipoServicos;
	
	@OneToOne
	@JoinColumn(name = "status_profissional")
	private Profissional profissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}

	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	
}
