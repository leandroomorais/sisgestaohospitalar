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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

/**
 * A classe <code>Triagem</code> representa os objetos do tipo Triagem e contém
 * seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Entity
public class Triagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String motivo;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "É necessário preencher o campo Motivo da Consulta")
	private String descricaomotivo;

	private String pressaoarterial;

	private String temperaturacorporal;

	private String peso;

	private String altura;

	private String imc;

	private String glicemiacapilar;

	private String momentocoleta;

	private String frequenciacardiaca;

	private String saturacaooxigenio;

	private String frequenciarespiratoria;

	private String perimetrocefalico;

	private String classificacaoderisco;

	private String usomedicamentos;

	private String alergiamedicamentos;

	private boolean tabagismo;

	private boolean etilismo;

	private boolean drogaslicitas;

	private String outroshabitos;

	private boolean hipertensaoarterialsistemica;

	private boolean doençameningocócica;

	private boolean avc;

	private boolean linfangioliomiomatose;

	private boolean insuficienciarenal;

	private boolean dpoc;

	private boolean asma;

	private boolean dlp;

	private boolean insuficienciacardiacacongestiva;

	private boolean epilepsia;

	private boolean caneoplasia;

	private String outrasdoenças;

	private Date data;

	@NotBlank(message = "Por favor, informe o destino do Cidadão")
	private String destinocidadao;

	/**
	 * Relacionamento entre os objetos Triagem e GuiaAtendimento
	 */
	@OneToOne
	@JoinColumn(name = "guiaatendimento_id")
	private GuiaAtendimento guiaatendimento;

	/**
	 * Relacionamento entre os objetos Triagem e ProcedimentoSigtap
	 */
	@ManyToMany
	@JoinTable(name = "triagem_procedimentos")
	private List<ProcedimentoSigtap> procedimentos;

	/**
	 * Relacionamento entre os objetos Triagem e Profissional
	 */
	@OneToOne
	@JoinColumn(name = "profissionalexecutante_id")
	private Profissional profissionalexecutante;

	/** Getters and Setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDescricaomotivo() {
		return descricaomotivo;
	}

	public void setDescricaomotivo(String descricaomotivo) {
		this.descricaomotivo = descricaomotivo;
	}

	public String getPressaoarterial() {
		return pressaoarterial;
	}

	public void setPressaoarterial(String pressaoarterial) {
		this.pressaoarterial = pressaoarterial;
	}

	public String getTemperaturacorporal() {
		return temperaturacorporal;
	}

	public void setTemperaturacorporal(String temperaturacorporal) {
		this.temperaturacorporal = temperaturacorporal;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public String getGlicemiacapilar() {
		return glicemiacapilar;
	}

	public void setGlicemiacapilar(String glicemiacapilar) {
		this.glicemiacapilar = glicemiacapilar;
	}

	public String getMomentocoleta() {
		return momentocoleta;
	}

	public void setMomentocoleta(String momentocoleta) {
		this.momentocoleta = momentocoleta;
	}

	public String getFrequenciacardiaca() {
		return frequenciacardiaca;
	}

	public void setFrequenciacardiaca(String frequenciacardiaca) {
		this.frequenciacardiaca = frequenciacardiaca;
	}

	public String getSaturacaooxigenio() {
		return saturacaooxigenio;
	}

	public void setSaturacaooxigenio(String saturacaooxigenio) {
		this.saturacaooxigenio = saturacaooxigenio;
	}

	public String getFrequenciarespiratoria() {
		return frequenciarespiratoria;
	}

	public void setFrequenciarespiratoria(String frequenciarespiratoria) {
		this.frequenciarespiratoria = frequenciarespiratoria;
	}

	public String getPerimetrocefalico() {
		return perimetrocefalico;
	}

	public void setPerimetrocefalico(String perimetrocefalico) {
		this.perimetrocefalico = perimetrocefalico;
	}

	public String getClassificacaoderisco() {
		return classificacaoderisco;
	}

	public void setClassificacaoderisco(String classificacaoderisco) {
		this.classificacaoderisco = classificacaoderisco;
	}

	public String getUsomedicamentos() {
		return usomedicamentos;
	}

	public void setUsomedicamentos(String usomedicamentos) {
		this.usomedicamentos = usomedicamentos;
	}

	public String getAlergiamedicamentos() {
		return alergiamedicamentos;
	}

	public void setAlergiamedicamentos(String alergiamedicamentos) {
		this.alergiamedicamentos = alergiamedicamentos;
	}

	public boolean isTabagismo() {
		return tabagismo;
	}

	public void setTabagismo(boolean tabagismo) {
		this.tabagismo = tabagismo;
	}

	public boolean isEtilismo() {
		return etilismo;
	}

	public void setEtilismo(boolean etilismo) {
		this.etilismo = etilismo;
	}

	public boolean isDrogaslicitas() {
		return drogaslicitas;
	}

	public void setDrogaslicitas(boolean drogaslicitas) {
		this.drogaslicitas = drogaslicitas;
	}

	public String getOutroshabitos() {
		return outroshabitos;
	}

	public void setOutroshabitos(String outroshabitos) {
		this.outroshabitos = outroshabitos;
	}

	public boolean isHipertensaoarterialsistemica() {
		return hipertensaoarterialsistemica;
	}

	public void setHipertensaoarterialsistemica(boolean hipertensaoarterialsistemica) {
		this.hipertensaoarterialsistemica = hipertensaoarterialsistemica;
	}

	public boolean isDoençameningocócica() {
		return doençameningocócica;
	}

	public void setDoençameningocócica(boolean doençameningocócica) {
		this.doençameningocócica = doençameningocócica;
	}

	public boolean isAvc() {
		return avc;
	}

	public void setAvc(boolean avc) {
		this.avc = avc;
	}

	public boolean isLinfangioliomiomatose() {
		return linfangioliomiomatose;
	}

	public void setLinfangioliomiomatose(boolean linfangioliomiomatose) {
		this.linfangioliomiomatose = linfangioliomiomatose;
	}

	public boolean isInsuficienciarenal() {
		return insuficienciarenal;
	}

	public void setInsuficienciarenal(boolean insuficienciarenal) {
		this.insuficienciarenal = insuficienciarenal;
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

	public boolean isInsuficienciacardiacacongestiva() {
		return insuficienciacardiacacongestiva;
	}

	public void setInsuficienciacardiacacongestiva(boolean insuficienciacardiacacongestiva) {
		this.insuficienciacardiacacongestiva = insuficienciacardiacacongestiva;
	}

	public boolean isEpilepsia() {
		return epilepsia;
	}

	public void setEpilepsia(boolean epilepsia) {
		this.epilepsia = epilepsia;
	}

	public boolean isCaneoplasia() {
		return caneoplasia;
	}

	public void setCaneoplasia(boolean caneoplasia) {
		this.caneoplasia = caneoplasia;
	}

	public String getOutrasdoenças() {
		return outrasdoenças;
	}

	public void setOutrasdoenças(String outrasdoenças) {
		this.outrasdoenças = outrasdoenças;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDestinocidadao() {
		return destinocidadao;
	}

	public void setDestinocidadao(String destinocidadao) {
		this.destinocidadao = destinocidadao;
	}

	public GuiaAtendimento getGuiaatendimento() {
		return guiaatendimento;
	}

	public void setGuiaatendimento(GuiaAtendimento guiaatendimento) {
		this.guiaatendimento = guiaatendimento;
	}

	public List<ProcedimentoSigtap> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoSigtap> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Profissional getProfissionalexecutante() {
		return profissionalexecutante;
	}

	public void setProfissionalexecutante(Profissional profissionalexecutante) {
		this.profissionalexecutante = profissionalexecutante;
	}

}
