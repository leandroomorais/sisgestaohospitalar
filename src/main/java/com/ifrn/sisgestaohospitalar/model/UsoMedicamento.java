package com.ifrn.sisgestaohospitalar.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class UsoMedicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "É necessário selecionar um Medicamento")
	@OneToOne
	private Medicamento medicamento;
	
	private String nota;
	
	private LocalDateTime dataCadastro;
	
	private boolean usoContinuo;
	
	@Transient
	private Long idAtendimento;
	
	@Transient
	private Long idProntuario;

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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public Long getIdProntuario() {
		return idProntuario;
	}

	public void setIdProntuario(Long idProntuario) {
		this.idProntuario = idProntuario;
	}

	public boolean isUsoContinuo() {
		return usoContinuo;
	}

	public void setUsoContinuo(boolean usoContinuo) {
		this.usoContinuo = usoContinuo;
	}
	
	
}
