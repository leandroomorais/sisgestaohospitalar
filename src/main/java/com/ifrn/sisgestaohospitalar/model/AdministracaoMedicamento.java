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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


/**
 * A classe <code>AdministracaoMedicamento</code> representa os objetos do tipo
 * Administração de Medicamento e contém seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
public class AdministracaoMedicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String observacoes;

	private boolean realizado;

	private String destinocidadao;

	@DateTimeFormat(iso = ISO.DATE, pattern = "")
	@Column(name = "data", columnDefinition = "DATE")
	private LocalDate data;

	private LocalTime hora;

	@OneToOne
	@JoinColumn(name = "guiaatendimento_id")
	public GuiaAtendimento guiaatendimento;

	@ManyToOne
	@JoinTable(name = "administracaomedicamento_profissional")
	private Profissional profissional;

	@LazyCollection(value = LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "admnistracaomedicamento_procedimento")
	private List<ProcedimentoSigtap> procedimentos;

	@OneToOne
	@JoinColumn(name = "profissionaldestino_id")
	public Profissional profissionaldestino;

	/** Getters e setters **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public String getDestinocidadao() {
		return destinocidadao;
	}

	public void setDestinocidadao(String destinocidadao) {
		this.destinocidadao = destinocidadao;
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

	public GuiaAtendimento getGuiaAtendimento() {
		return guiaatendimento;
	}

	public void setGuiaAtendimento(GuiaAtendimento guiaAtendimento) {
		this.guiaatendimento = guiaAtendimento;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public List<ProcedimentoSigtap> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoSigtap> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Profissional getProfissionaldestino() {
		return profissionaldestino;
	}

	public void setProfissionaldestino(Profissional profissionaldestino) {
		this.profissionaldestino = profissionaldestino;
	}

}
