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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Prescricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Selecione o medicamento")
	@OneToOne
	private Medicamento medicamento;

	@NotNull(message = "Selecione a via de administração")
	@OneToOne
	private ViaAdministracao viaAdministracao;

	@NotBlank(message = "É necessário preencher a posologia")
	private String posologia;

	private boolean administracaoNoAtendimento;

	private String orientacoes;

	private boolean doseUnica;

	private boolean usoContinuo;

	@JsonIgnore
	@OneToOne
	private Atendimento atendimento;

	@JsonIgnore
	@OneToOne
	private Prontuario prontuario;

	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "prescricao_registro_administracao", joinColumns = @JoinColumn(name = "id_prescricao"), inverseJoinColumns = @JoinColumn(name = "id_registro_administracao"))
	private List<RegistroAdministracao> registrosAdministracao;

	@Positive(message = "A quatidade não pode ser igual ou inferior a 0")
	private int quantidade;

	private LocalDateTime dataRegistro;

	@OneToOne
	private Profissional profissional;
	
	private boolean prescricaoExternabool = false;
	
	@JsonIgnore
	@OneToOne(mappedBy = "prescricao")
	private PrescricaoExterna prescricaoExterna;

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

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public List<RegistroAdministracao> getRegistrosAdministracao() {
		return registrosAdministracao;
	}

	public void setRegistrosAdministracao(List<RegistroAdministracao> registrosAdministracao) {
		this.registrosAdministracao = registrosAdministracao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public PrescricaoExterna getPrescricaoExterna() {
		return prescricaoExterna;
	}

	public void setPrescricaoExterna(PrescricaoExterna prescricaoExterna) {
		this.prescricaoExterna = prescricaoExterna;
	}

	public boolean isPrescricaoExternabool() {
		return prescricaoExternabool;
	}

	public void setPrescricaoExternabool(boolean prescricaoExternabool) {
		this.prescricaoExternabool = prescricaoExternabool;
	}

	
}
