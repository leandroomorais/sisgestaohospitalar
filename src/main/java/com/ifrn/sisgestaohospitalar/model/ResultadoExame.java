package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ResultadoExame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private LocalDateTime dataRealizacao;
	
	private LocalDateTime dataResultado;
	
	@ManyToOne
	@JoinColumn(name = "exame_id")
	private Exame exame;
	
	@ManyToOne
	@JoinColumn(name = "procedimento_id")
	private Procedimento procedimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(LocalDateTime dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public LocalDateTime getDataResultado() {
		return dataResultado;
	}

	public void setDataResultado(LocalDateTime dataResultado) {
		this.dataResultado = dataResultado;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
}
