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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifrn.sisgestaohospitalar.enums.CaraterAtendimento;
import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
import com.ifrn.sisgestaohospitalar.enums.Status;

@Entity
@DynamicUpdate
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String responsavel;

	private String historiaClinia;

	@OneToOne
	private Cidadao cidadao;

	private CondutaCidadao condutaCidadao;

	private LocalDateTime dataEntrada;

	private Status status;

	private CaraterAtendimento caraterAtendimento;

	@NotNull(message = "É necessário selecionar o Profissional de destino")
	@OneToOne
	private Profissional profissionalDestino;

	@NotNull(message = "É necessário selecionar o(s) serviço(s) para o Atendimento")
	@ManyToMany
	@JoinTable(name = "atendimento_servicos", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_servico"))
	private List<TipoServico> tipoServicos;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_uso_medicamentos", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_uso_medicamento"))
	private List<UsoMedicamento> usoMedicamentos;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_historico_status", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_historico"))
	private List<HistoricoStatus> historicoStatus;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_procedimentos", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_rel_procedimento"))
	private List<AtendimentoProcedimento> atendimentoProcedimentos;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_prescricoes", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_prescricao"))
	private List<Prescricao> prescricoes;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_diagnosticos", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_diagnostico"))
	private List<Diagnostico> diagnostico;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_evolucoes", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_evolucao"))
	private List<Evolucao> evolucoes;

	@JsonIgnore
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Triagem triagem;


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getResponsavel() {
		return responsavel;
	}



	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}



	public String getHistoriaClinia() {
		return historiaClinia;
	}



	public void setHistoriaClinia(String historiaClinia) {
		this.historiaClinia = historiaClinia;
	}



	public Cidadao getCidadao() {
		return cidadao;
	}



	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}



	public CondutaCidadao getCondutaCidadao() {
		return condutaCidadao;
	}



	public void setCondutaCidadao(CondutaCidadao condutaCidadao) {
		this.condutaCidadao = condutaCidadao;
	}



	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}



	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public CaraterAtendimento getCaraterAtendimento() {
		return caraterAtendimento;
	}



	public void setCaraterAtendimento(CaraterAtendimento caraterAtendimento) {
		this.caraterAtendimento = caraterAtendimento;
	}



	public Profissional getProfissionalDestino() {
		return profissionalDestino;
	}



	public void setProfissionalDestino(Profissional profissionalDestino) {
		this.profissionalDestino = profissionalDestino;
	}



	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}



	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}



	public List<UsoMedicamento> getUsoMedicamentos() {
		return usoMedicamentos;
	}



	public void setUsoMedicamentos(List<UsoMedicamento> usoMedicamentos) {
		this.usoMedicamentos = usoMedicamentos;
	}



	public List<HistoricoStatus> getHistoricoStatus() {
		return historicoStatus;
	}



	public void setHistoricoStatus(List<HistoricoStatus> historicoStatus) {
		this.historicoStatus = historicoStatus;
	}



	public List<AtendimentoProcedimento> getAtendimentoProcedimentos() {
		return atendimentoProcedimentos;
	}



	public void setAtendimentoProcedimentos(List<AtendimentoProcedimento> atendimentoProcedimentos) {
		this.atendimentoProcedimentos = atendimentoProcedimentos;
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



	public List<Evolucao> getEvolucoes() {
		return evolucoes;
	}



	public void setEvolucoes(List<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}



	public Triagem getTriagem() {
		return triagem;
	}



	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}



	public String tiposServicosToString() {
		String servicos = "";
		for (TipoServico tipoServico : tipoServicos) {
			servicos.concat(tipoServico.getNome());
		}
		return servicos;
	}

}
