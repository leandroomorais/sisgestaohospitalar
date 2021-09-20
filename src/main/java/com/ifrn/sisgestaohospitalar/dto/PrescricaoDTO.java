package com.ifrn.sisgestaohospitalar.dto;

import java.time.LocalDateTime;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.ifrn.sisgestaohospitalar.model.Medicamento;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.model.SituacaoPrescricao;
import com.ifrn.sisgestaohospitalar.model.ViaAdministracao;

public class PrescricaoDTO {

	private Long id;

	@NotNull(message = "Selecione o medicamento")
	private Medicamento medicamento;

	@NotNull(message = "Selecione a via de administração")
	private ViaAdministracao viaAdministracao;

	@NotBlank(message = "É necessário preencher a posologia")
	private String posologia;

	private boolean administracaoNoAtendimento;

	private String orientacoes;

	private boolean doseUnica;

	private boolean usoContinuo;
	
	@Transient
	private Long idAtendimento;
	
	private Prontuario prontuario;
		
	private int quantidade;

	private LocalDateTime data;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public ViaAdministracao getViaAdministracao() {
		return viaAdministracao;
	}

	public void setViaAdministracao(ViaAdministracao viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public boolean isAdministracaoNoAtendimento() {
		return administracaoNoAtendimento;
	}

	public void setAdministracaoNoAtendimento(boolean administracaoNoAtendimento) {
		this.administracaoNoAtendimento = administracaoNoAtendimento;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

	public boolean isDoseUnica() {
		return doseUnica;
	}

	public void setDoseUnica(boolean doseUnica) {
		this.doseUnica = doseUnica;
	}

	public boolean isUsoContinuo() {
		return usoContinuo;
	}

	public void setUsoContinuo(boolean usoContinuo) {
		this.usoContinuo = usoContinuo;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PrescricaoDTO [id=" + id + ", medicamento=" + medicamento.getId() + ", viaAdministracao=" + viaAdministracao.getId()
				+ ", posologia=" + posologia + ", administracaoNoAtendimento=" + administracaoNoAtendimento
				+ ", orientacoes=" + orientacoes + ", doseUnica=" + doseUnica + ", usoContinuo=" + usoContinuo
				+ ", idAtendimento=" + idAtendimento + ", prontuario=" + prontuario + ", quantidade=" + quantidade + ", data=" + data + "]";
	}
	
	
	
}
