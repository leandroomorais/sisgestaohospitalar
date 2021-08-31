package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifrn.sisgestaohospitalar.enums.CaraterAtendimento;
import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;

@Entity
@DynamicUpdate
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String responsavel;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID numeroRegistro;

	@OneToOne
	private Cidadao cidadao;

	@NotNull(message = "É necessário Informar o Tipo de Serviço para o Atendimento")
	private TipoServico condutaTipoServico;

	private CondutaCidadao condutaCidadao;

	private LocalDateTime dataEntrada;

	private Status status;

	private CaraterAtendimento caraterAtendimento;

	@NotNull(message = "É necessário selecionar o Profissional de destino")
	@ManyToOne
	private Profissional profissionalDestino;
	
	@ManyToMany
	@JoinTable(name = "atendimento_uso_medicamento", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_uso_medicamento"))
	private List<UsoMedicamento> usoMedicamentos;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_historico_status", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_historico"))
	private List<HistoricoStatus> historicoStatus;

	@ManyToMany
	@JoinTable(name = "atendimento_procedimento", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_rel_procedimento"))
	private List<RelAtendimentoProcedimento> atendimentoProcedimentos;

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

	public UUID getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(UUID numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
	}

	public TipoServico getCondutaTipoServico() {
		return condutaTipoServico;
	}

	public void setCondutaTipoServico(TipoServico condutaTipoServico) {
		this.condutaTipoServico = condutaTipoServico;
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

	public List<RelAtendimentoProcedimento> getAtendimentoProcedimentos() {
		return atendimentoProcedimentos;
	}

	public void setAtendimentoProcedimentos(List<RelAtendimentoProcedimento> atendimentoProcedimentos) {
		this.atendimentoProcedimentos = atendimentoProcedimentos;
	}

	public Triagem getTriagem() {
		return triagem;
	}

	public void setTriagem(Triagem triagem) {
		this.triagem = triagem;
	}

}
