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


@Entity
public class MedicationAdministration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date date;
	
	private String note;
	
	private boolean concluded;
	
	@OneToOne
	@JoinColumn(name = "serviceform_id")
	public ServiceForm serviceForm;
	
	@ManyToOne
	@JoinTable(name = "medicationadministration_professional")
	private Professional professional;
	
	@ManyToMany
	@JoinTable(name = "medicationadministration_procedure")
	private List<ProcedureSigtap> proceduresSigtap;
	
	@OneToOne
	@JoinColumn(name = "profissionalperfomer_id")
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isConcluded() {
		return concluded;
	}

	public void setConcluded(boolean concluded) {
		this.concluded = concluded;
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
