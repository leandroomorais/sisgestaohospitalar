package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String numeroregistro;

	private String responsavel;

	private TipoServico tipoServico;

	private StatusAtendimento statusAtendimento;

	private String classificacaoDeRisco;

	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Column(name = "data", columnDefinition = "DATE")
	private LocalDate data;

	private LocalTime hora;

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

	@OneToOne(mappedBy = "guiaatendimento")
	private AdministracaoMedicamento administracaomedicamento;

	/**
	 * Relacionamento entre os objetos GuiaAtendimento e Profissional
	 */
	@ManyToOne
	@JoinTable(name = "guiaatendimento_profissional")
	private Profissional profissional;

	/**
	 * Relacionamento entre os objetos GuiaAtendimento e Profissional de Destino
	 */
	@ManyToOne
	@JoinTable(name = "guiaatendimento_profissionaldestino")
	private Profissional profissionaldestino;

	/** Getters and Setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroregistro() {
		return numeroregistro;
	}

	public void setNumeroregistro(String numeroregistro) {
		this.numeroregistro = numeroregistro;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
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

	public Profissional getProfissionaldestino() {
		return profissionaldestino;
	}

	public void setProfissionaldestino(Profissional profissionaldestino) {
		this.profissionaldestino = profissionaldestino;
	}

	public String getClassificacaoDeRisco() {
		return classificacaoDeRisco;
	}

	public void setClassificacaoDeRisco(String classificacaoDeRisco) {
		this.classificacaoDeRisco = classificacaoDeRisco;
	}

}
