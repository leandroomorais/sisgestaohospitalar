package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDate;
import java.time.LocalTime;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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


	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Column(name = "data", columnDefinition = "DATE")
	private LocalDate data;

	private LocalTime hora;

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
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;

	/**
	 * Relacionamento entre os objetos AtendimentoMedico e Profissional
	 */
	@OneToOne
	@JoinColumn(name = "profissionaldestino_id")
	private Profissional profissionaldestino;

	/**
	 * Relacionamento entre os objetos AtendimentoMédico e ProcedimentoSigtap
	 */
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "atendimentomedico_procedimentos")
	private List<ProcedimentoSigtap> procedimentos;
	
	
	/**
	 * Relacionamento entre os objetos AtendimentoMédico e ProcedimentoSigtap
	 */
	@Cascade(CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "atendimentomedico_medicamentos") 
	private List<Medicamento> medicamentos;

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

	public List<ProcedimentoSigtap> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoSigtap> procedimentos) {
		this.procedimentos = procedimentos;
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

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

}
