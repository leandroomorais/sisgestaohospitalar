package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

/**
 * A classe <code>AtendimentoMedico</code> representa os objetos do tipo
 * Atendimento Médico e contém seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Entity
public class AtendimentoMedico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 500)
	@NotBlank(message = "Por favor, preencha a Hipotese Diagnóstica")
	private String hipoteseDiagnostica;

	private String historiaclinica;

	private String examefisico;

	private String evolucaoMedica;

	private String medicamentocidadao;

	private String medicamentohospitalar;

	private Date data;

	@NotBlank(message = "Por favor, informe o destino do Paciente")
	private String destinocidadao;

	/**
	 * Relacionamento entre os objetos AtendimentoMedico e GuiaAtendimento
	 */
	@OneToOne
	@JoinColumn(name = "guiaatendimento_id")
	private GuiaAtendimento guiaatendimento;

	/**
	 * Relacionamento entre os objetos AtendimentoMedico e Profissional
	 */
	@OneToOne
	@JoinColumn(name = "profissionalexecutante_id")
	private Profissional profissionalexecutante;

	/**
	 * Relacionamento entre os objetos AtendimentoMédico e ProcedimentoSigtap
	 */
	@ManyToMany
	@JoinTable(name = "atendimentomedico_procedimentos")
	private List<ProcedimentoSigtap> procedimentos;

	/** Getters and Setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHipoteseDiagnostica() {
		return hipoteseDiagnostica;
	}

	public void setHipoteseDiagnostica(String hipoteseDiagnostica) {
		this.hipoteseDiagnostica = hipoteseDiagnostica;
	}

	public String getHistoriaclinica() {
		return historiaclinica;
	}

	public void setHistoriaclinica(String historiaclinica) {
		this.historiaclinica = historiaclinica;
	}

	public String getExamefisico() {
		return examefisico;
	}

	public void setExamefisico(String examefisico) {
		this.examefisico = examefisico;
	}

	public String getEvolucaoMedica() {
		return evolucaoMedica;
	}

	public void setEvolucaoMedica(String evolucaoMedica) {
		this.evolucaoMedica = evolucaoMedica;
	}

	public String getMedicamentocidadao() {
		return medicamentocidadao;
	}

	public void setMedicamentocidadao(String medicamentocidadao) {
		this.medicamentocidadao = medicamentocidadao;
	}

	public String getMedicamentohospitalar() {
		return medicamentohospitalar;
	}

	public void setMedicamentohospitalar(String medicamentohospitalar) {
		this.medicamentohospitalar = medicamentohospitalar;
	}

	public String getDestinocidadao() {
		return destinocidadao;
	}

	public void setDestinocidadao(String destinocidadao) {
		this.destinocidadao = destinocidadao;
	}

	public GuiaAtendimento getGuiaatendimento() {
		return guiaatendimento;
	}

	public void setGuiaatendimento(GuiaAtendimento guiaatendimento) {
		this.guiaatendimento = guiaatendimento;
	}

	public Profissional getProfissionalexecutante() {
		return profissionalexecutante;
	}

	public void setProfissionalexecutante(Profissional profissionalexecutante) {
		this.profissionalexecutante = profissionalexecutante;
	}

	public List<ProcedimentoSigtap> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoSigtap> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
