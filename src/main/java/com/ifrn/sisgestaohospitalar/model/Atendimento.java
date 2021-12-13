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

	@OneToOne
	private Cidadao cidadao;

	private CondutaCidadao condutaCidadao;

	private LocalDateTime dataEntrada;

	private Status status;

	private CaraterAtendimento caraterAtendimento;

	private int tempoObservacao;

	@OneToOne
	private ClassificacaoDeRisco classificacaoDeRisco;

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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_historico", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_historico"))
	private List<HistoricoAtendimento> historicosAtendimento;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_procedimentos", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_rel_procedimento"))
	private List<AtendimentoProcedimento> atendimentoProcedimentos;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "atendimento_evolucoes", joinColumns = @JoinColumn(name = "id_atendimento"), inverseJoinColumns = @JoinColumn(name = "id_evolucao"))
	private List<Evolucao> evolucoes;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Triagem triagem;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Consulta consulta;

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

	public int getTempoObservacao() {
		return tempoObservacao;
	}

	public void setTempoObservacao(int tempoObservacao) {
		this.tempoObservacao = tempoObservacao;
	}

	public ClassificacaoDeRisco getClassificacaoDeRisco() {
		return classificacaoDeRisco;
	}

	public void setClassificacaoDeRisco(ClassificacaoDeRisco classificacaoDeRisco) {
		this.classificacaoDeRisco = classificacaoDeRisco;
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

	public List<HistoricoAtendimento> getHistoricosAtendimento() {
		return historicosAtendimento;
	}

	public void setHistoricosAtendimento(List<HistoricoAtendimento> historicosAtendimento) {
		this.historicosAtendimento = historicosAtendimento;
	}

	public List<AtendimentoProcedimento> getAtendimentoProcedimentos() {
		return atendimentoProcedimentos;
	}

	public void setAtendimentoProcedimentos(List<AtendimentoProcedimento> atendimentoProcedimentos) {
		this.atendimentoProcedimentos = atendimentoProcedimentos;
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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

}
