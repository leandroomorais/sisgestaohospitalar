package com.ifrn.sisgestaohospitalar.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.ifrn.sisgestaohospitalar.enums.CaraterAtendimento;
import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.HistoricoStatus;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.RelAtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Triagem;

public class AtendimentoDTO {

	private Long id;

	private String responsavel;

	private UUID numeroRegistro;

	@NotNull(message = "É necessário Informar o Tipo de Serviço para o Atendimento")
	private TipoServico condutaTipoServico;

	private CondutaCidadao condutaCidadao;

	private LocalDateTime dataEntrada;

	private Status status;

	private CaraterAtendimento caraterAtendimento;

	@NotNull(message = "É necessário selecionar o Profissional de destino")
	private Profissional profissionalDestino;

	private List<HistoricoStatus> historicoStatus;

	private List<RelAtendimentoProcedimento> atendimentoProcedimentos;

	@Valid
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
