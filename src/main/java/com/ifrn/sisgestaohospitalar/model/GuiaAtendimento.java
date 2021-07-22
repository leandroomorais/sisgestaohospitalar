package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;

@Entity
public class GuiaAtendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String numeroregistro;

	private String responsavel;

	@NotNull(message = "É necessário selecionar o Tipo de Serviço")
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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cidadao_id")
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
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		int i = 0;
		numeroregistro = Integer.toString(i++); 
	}

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

	public AdministracaoMedicamento getAdministracaomedicamento() {
		return administracaomedicamento;
	}

	public void setAdministracaomedicamento(AdministracaoMedicamento administracaomedicamento) {
		this.administracaomedicamento = administracaomedicamento;
	}

}
