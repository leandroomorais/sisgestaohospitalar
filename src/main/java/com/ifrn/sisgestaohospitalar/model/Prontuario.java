package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Prontuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "É necessário Cadastrar um Cidadão")
	@OneToOne
	private Cidadao cidadao;
	
	@NotNull(message = "Data de abertura não pode ser nula")
	private LocalDateTime dataAbertura;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_status_doenca", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_status_doenca"))
	private List<StatusDoenca> statusDoencas;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_status_alergia", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_status_alergia"))
	private List<StatusAlergia> statusAlergias;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_uso_continuo_medicamento", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_uso_continuo_medicamento"))
	private List<UsoContinuoMedicamento> usoContinuoMedicamentos;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_habito", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_habito"))
	private List<Habito> habitos;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_antropometria", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_antropometria"))
	private List<Antropometria> antropometrias;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_atendimento", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_atendimento"))
	private List<Atendimento> atendimentos;
	
	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_prescricoes", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_prescricao"))
	private List<Prescricao> prescricoes;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_diagnosticos", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_diagnostico"))
	private List<Diagnostico> diagnostico;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prontuario_atestados", joinColumns = @JoinColumn(name = "id_prontuario"), inverseJoinColumns = @JoinColumn(name = "id_atestado"))
	private List<Atestado> atestados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public List<StatusDoenca> getStatusDoencas() {
		return statusDoencas;
	}

	public void setStatusDoencas(List<StatusDoenca> statusDoencas) {
		this.statusDoencas = statusDoencas;
	}

	public List<StatusAlergia> getStatusAlergias() {
		return statusAlergias;
	}

	public void setStatusAlergias(List<StatusAlergia> statusAlergias) {
		this.statusAlergias = statusAlergias;
	}

	public List<UsoContinuoMedicamento> getUsoContinuoMedicamentos() {
		return usoContinuoMedicamentos;
	}

	public void setUsoContinuoMedicamentos(List<UsoContinuoMedicamento> usoContinuoMedicamentos) {
		this.usoContinuoMedicamentos = usoContinuoMedicamentos;
	}

	public List<Habito> getHabitos() {
		return habitos;
	}

	public void setHabitos(List<Habito> habitos) {
		this.habitos = habitos;
	}

	public List<Antropometria> getAntropometrias() {
		return antropometrias;
	}

	public void setAntropometrias(List<Antropometria> antropometrias) {
		this.antropometrias = antropometrias;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<Prescricao> getPrescricoes() {
		return prescricoes;
	}

	public void setPrescricoes(List<Prescricao> prescricoes) {
		this.prescricoes = prescricoes;
	}

	public List<Diagnostico> getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(List<Diagnostico> diagnostico) {
		this.diagnostico = diagnostico;
	}

	public List<Atestado> getAtestados() {
		return atestados;
	}

	public void setAtestados(List<Atestado> atestados) {
		this.atestados = atestados;
	}

}
