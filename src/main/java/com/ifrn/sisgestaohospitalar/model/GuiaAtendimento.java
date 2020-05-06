package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;

/**
 * A classe <code>GuiaAtendimento</code> representa os objetos do tipo Guia de
 * atendimento e contém seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
public class GuiaAtendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int numeroregistro;

	private String responsavel;

	private StatusAtendimento statusatendimento;

	private Date data;

	/**
	 * Relacionamento entre os objetos GuiaAtendimento e Cidadao
	 */
	@ManyToOne
	@JoinTable(name = "guiaatendimento_cidadao")
	private Cidadao cidadao;

	/**
	 * Relacionamento entre os objetos GuiaAtendimento e Triagem
	 */
	@OneToOne(mappedBy = "guiaatendimento")
	private Triagem triagem;

	/**
	 * Relacionamento entre os objetos GuiaAtendimento e AtendimentoMedico
	 */
	@OneToOne(mappedBy = "guiaatendimento")
	private AtendimentoMedico atendimentomedico;

	/**
	 * Relacionamento entre os objetos GuiaAtendimento e Profissional
	 */
	@ManyToOne
	@JoinTable(name = "guiaatendimento_profissional")
	private Profissional profissional;

	/** Getters and Setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroregistro() {
		return numeroregistro;
	}

	public void setNumeroregistro(int numeroregistro) {
		this.numeroregistro = numeroregistro;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public StatusAtendimento getStatusatendimento() {
		return statusatendimento;
	}

	public void setStatusatendimento(StatusAtendimento statusatendimento) {
		this.statusatendimento = statusatendimento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public Triagem getTriagem() {
		return triagem;
	}

	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}

	public AtendimentoMedico getAtendimentomedico() {
		return atendimentomedico;
	}

	public void setAtendimentomedico(AtendimentoMedico atendimentomedico) {
		this.atendimentomedico = atendimentomedico;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

}
