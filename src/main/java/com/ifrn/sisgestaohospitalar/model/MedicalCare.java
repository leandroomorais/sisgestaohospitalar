package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class MedicalCare {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date date;
	
	private String diagnostichypothesis;
	
	private String medicalhistory;
	
	private String physicalexam;
	
	private String medicalevolution;
	
	private String medicament;
	
	@NotBlank(message = "Por favor, informe o destino do Paciente")
	private String destinycitzenuser;
	
	@OneToOne
	@JoinColumn(name = "serviceform_id")
	public ServiceForm serviceForm;
	
	@ManyToOne
	@JoinTable(name = "medicalcare_professional")
	private Professional professional;
	
	@ManyToMany
	@JoinTable(name = "atendimento_procedimento")
	private List<ProcedureSigtap> proceduresSigtap;
	
	@OneToOne
	@JoinColumn(name = "professionalperfomer_id")
	public Professional professionalperformer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDiagnostichypothesis() {
		return diagnostichypothesis;
	}

	public void setDiagnostichypothesis(String diagnostichypothesis) {
		this.diagnostichypothesis = diagnostichypothesis;
	}

	public String getMedicalhistory() {
		return medicalhistory;
	}

	public void setMedicalhistory(String medicalhistory) {
		this.medicalhistory = medicalhistory;
	}

	public String getPhysicalexam() {
		return physicalexam;
	}

	public void setPhysicalexam(String physicalexam) {
		this.physicalexam = physicalexam;
	}

	public String getMedicalevolution() {
		return medicalevolution;
	}

	public void setMedicalevolution(String medicalevolution) {
		this.medicalevolution = medicalevolution;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String medicament) {
		this.medicament = medicament;
	}

	public String getDestinycitzenuser() {
		return destinycitzenuser;
	}

	public void setDestinycitzenuser(String destinycitzenuser) {
		this.destinycitzenuser = destinycitzenuser;
	}

	public ServiceForm getServiceForm() {
		return serviceForm;
	}

	public void setServiceForm(ServiceForm serviceForm) {
		this.serviceForm = serviceForm;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public List<ProcedureSigtap> getProceduresSigtap() {
		return proceduresSigtap;
	}

	public void setProceduresSigtap(List<ProcedureSigtap> proceduresSigtap) {
		this.proceduresSigtap = proceduresSigtap;
	}

	public Professional getProfessionalperformer() {
		return professionalperformer;
	}

	public void setProfessionalperformer(Professional professionalperformer) {
		this.professionalperformer = professionalperformer;
	}

}
