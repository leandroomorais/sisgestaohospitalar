package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;
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
import javax.validation.constraints.NotBlank;

@Entity
public class Screening {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date date;

	private String bloodpressure;

	private String temperature;

	private String hgt;

	private String timecollection;

	private String fc;

	private String spo2;

	private String fr;

	private String weight;

	private String height;

	private String imc;

	private String cephalicperimeter;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "É necessário preencher o campo Motivo da Consulta")
	private String reasonconsultation;

	private String descriptionconsultation;

	private String riskrating;

	private String medicationinuse;

	private String drugallergy;

	private boolean smoking;

	private boolean alcoholism;

	private boolean legaldrugs;

	private String otherhabits;

	private boolean has;

	private boolean dm;

	private boolean avc;

	private boolean lam;

	private boolean insRenal;

	private boolean dpoc;

	private boolean asma;

	private boolean dlp;

	private boolean icc;

	private boolean epilepsy;

	private boolean caneoplasia;

	private String otherdiseases;

	@NotBlank(message = "Por favor, informe o destino do Paciente")
	private String destinycitzenuser;
	
	@OneToOne
	@JoinColumn(name="serviceform_id")
	public ServiceForm serviceForm;
	
	@ManyToOne
	@JoinTable(name="serviceform_professional")
	private Professional professional;
	
	@ManyToMany
	@JoinTable(name="serviceform_procedure")
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

	public String getBloodpressure() {
		return bloodpressure;
	}

	public void setBloodpressure(String bloodpressure) {
		this.bloodpressure = bloodpressure;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHgt() {
		return hgt;
	}

	public void setHgt(String hgt) {
		this.hgt = hgt;
	}

	public String getTimecollection() {
		return timecollection;
	}

	public void setTimecollection(String timecollection) {
		this.timecollection = timecollection;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getSpo2() {
		return spo2;
	}

	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public String getCephalicperimeter() {
		return cephalicperimeter;
	}

	public void setCephalicperimeter(String cephalicperimeter) {
		this.cephalicperimeter = cephalicperimeter;
	}

	public String getReasonconsultation() {
		return reasonconsultation;
	}

	public void setReasonconsultation(String reasonconsultation) {
		this.reasonconsultation = reasonconsultation;
	}

	public String getDescriptionconsultation() {
		return descriptionconsultation;
	}

	public void setDescriptionconsultation(String descriptionconsultation) {
		this.descriptionconsultation = descriptionconsultation;
	}

	public String getRiskrating() {
		return riskrating;
	}

	public void setRiskrating(String riskrating) {
		this.riskrating = riskrating;
	}

	public String getMedicationinuse() {
		return medicationinuse;
	}

	public void setMedicationinuse(String medicationinuse) {
		this.medicationinuse = medicationinuse;
	}

	public String getDrugallergy() {
		return drugallergy;
	}

	public void setDrugallergy(String drugallergy) {
		this.drugallergy = drugallergy;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public boolean isAlcoholism() {
		return alcoholism;
	}

	public void setAlcoholism(boolean alcoholism) {
		this.alcoholism = alcoholism;
	}

	public boolean isLegaldrugs() {
		return legaldrugs;
	}

	public void setLegaldrugs(boolean legaldrugs) {
		this.legaldrugs = legaldrugs;
	}

	public String getOtherhabits() {
		return otherhabits;
	}

	public void setOtherhabits(String otherhabits) {
		this.otherhabits = otherhabits;
	}

	public boolean isHas() {
		return has;
	}

	public void setHas(boolean has) {
		this.has = has;
	}

	public boolean isDm() {
		return dm;
	}

	public void setDm(boolean dm) {
		this.dm = dm;
	}

	public boolean isAvc() {
		return avc;
	}

	public void setAvc(boolean avc) {
		this.avc = avc;
	}

	public boolean isLam() {
		return lam;
	}

	public void setLam(boolean lam) {
		this.lam = lam;
	}

	public boolean isInsRenal() {
		return insRenal;
	}

	public void setInsRenal(boolean insRenal) {
		this.insRenal = insRenal;
	}

	public boolean isDpoc() {
		return dpoc;
	}

	public void setDpoc(boolean dpoc) {
		this.dpoc = dpoc;
	}

	public boolean isAsma() {
		return asma;
	}

	public void setAsma(boolean asma) {
		this.asma = asma;
	}

	public boolean isDlp() {
		return dlp;
	}

	public void setDlp(boolean dlp) {
		this.dlp = dlp;
	}

	public boolean isIcc() {
		return icc;
	}

	public void setIcc(boolean icc) {
		this.icc = icc;
	}

	public boolean isEpilepsy() {
		return epilepsy;
	}

	public void setEpilepsy(boolean epilepsy) {
		this.epilepsy = epilepsy;
	}

	public boolean isCaneoplasia() {
		return caneoplasia;
	}

	public void setCaneoplasia(boolean caneoplasia) {
		this.caneoplasia = caneoplasia;
	}

	public String getOtherdiseases() {
		return otherdiseases;
	}

	public void setOtherdiseases(String otherdiseases) {
		this.otherdiseases = otherdiseases;
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
