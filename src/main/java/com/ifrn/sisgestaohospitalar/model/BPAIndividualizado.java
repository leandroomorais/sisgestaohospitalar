package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BPAIndividualizado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String linhaIdenti;
	
	private String cnes;
	
	private String competencia;
	
	private String cnsProfissional;
	
	private String cboProfissional;
	
	private String dataAtendimento;
	
	private String numeroFolha;
	
	private String numeroLinha;
	
	private String codigoProcedimento;
	
	private String cnsPaciente;
	
	private String sexoPaciente;
	
	private String codigoIbge;
	
	private String cid;
	
	private String idade;
	
	private String qtdProcedimento;
	
	private String caraterAtendimento;
	
	private String numeroAutorizacao;
	
	private String origemInformacao;
	
	private String nomePaciente;
	
	private String dataNascimento;
	
	private String racaCor;
	
	private String etnia;
	
	private String nacionalidade;
	
	private String codigoServico;
	
	private String codigoClassificacao;
	
	private String codigoSequenciaEquipe;
	
	private String codigoAreaEquipe;
	
	private String codigoCnpjEmpresa;
	
	private String cepPaciente;
	
	private String logradouroPaciente;
	
	private String enderecoPaciente;
	
	private String complementoEndereco;
	
	private String numeroEndereco;
	
	private String bairroEndereco;
	
	private String telefonePaciente;
	
	private String emailPaciente;
	
	private String identificacaoEquipe;
	
	private String fim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLinhaIdenti() {
		return linhaIdenti;
	}

	public void setLinhaIdenti(String linhaIdenti) {
		this.linhaIdenti = String.format("%-2.2s", linhaIdenti);
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = String.format("%-7.7s", cnes);
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = String.format("%-6.6s", competencia);
	}

	public String getCnsProfissional() {
		return cnsProfissional;
	}

	public void setCnsProfissional(String cnsProfissional) {
		this.cnsProfissional = String.format("%-15.15s", cnsProfissional);
	}

	public String getCboProfissional() {
		return cboProfissional;
	}

	public void setCboProfissional(String cboProfissional) {
		this.cboProfissional = String.format("%-6.6s", cboProfissional);
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = String.format("%-8.8s", dataAtendimento);
	}

	public String getNumeroFolha() {
		return numeroFolha;
	}

	public void setNumeroFolha(String numeroFolha) {
		this.numeroFolha = String.format("%03d", new Object[] {Integer.parseInt(numeroFolha)});
	}

	public String getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(String numeroLinha) {
		this.numeroLinha = String.format("%02d", new Object[] {Integer.parseInt(numeroLinha)});
	}

	public String getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(String codigoProcedimento) {
		this.codigoProcedimento = String.format("%-10.10s", codigoProcedimento);
	}

	public String getCnsPaciente() {
		return cnsPaciente;
	}

	public void setCnsPaciente(String cnsPaciente) {
		this.cnsPaciente = String.format("%-15.15s", cnsPaciente);
	}

	public String getSexoPaciente() {
		return sexoPaciente;
	}

	public void setSexoPaciente(String sexoPaciente) {
		this.sexoPaciente = String.format("%-1.1s", sexoPaciente);
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = String.format("%-6.6s", codigoIbge);
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = String.format("%-4.4s", cid);
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = String.format("%03d", new Object[] {Integer.parseInt(idade)});
	}

	public String getQtdProcedimento() {
		return qtdProcedimento;
	}

	public void setQtdProcedimento(String qtdProcedimento) {
		this.qtdProcedimento = String.format("%06d", new Object[] {Integer.parseInt(qtdProcedimento)});
	}

	public String getCaraterAtendimento() {
		return caraterAtendimento;
	}

	public void setCaraterAtendimento(String caraterAtendimento) {
		this.caraterAtendimento =  String.format("%02d", new Object[] {Integer.parseInt(caraterAtendimento)});
	}

	public String getNumeroAutorizacao() {
		return numeroAutorizacao;
	}

	public void setNumeroAutorizacao(String numeroAutorizacao) {
		this.numeroAutorizacao = String.format("%-13.13s", numeroAutorizacao);
	}

	public String getOrigemInformacao() {
		return origemInformacao;
	}

	public void setOrigemInformacao(String origemInformacao) {
		this.origemInformacao = String.format("%-3.3s", origemInformacao);
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = String.format("%-30.30s", nomePaciente);
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = String.format("%-8.8s", dataNascimento);
	}

	public String getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(String racaCor) {
		this.racaCor = String.format("%02d", new Object[] {Integer.parseInt(racaCor)});
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = String.format("%-4.4s", etnia);
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = String.format("%03d", new Object[] {Integer.parseInt(nacionalidade)});
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = String.format("%-3.3s", codigoServico);
	}

	public String getCodigoClassificacao() {
		return codigoClassificacao;
	}

	public void setCodigoClassificacao(String codigoClassificacao) {
		this.codigoClassificacao = String.format("%-3.3s", codigoClassificacao);
	}

	public String getCodigoSequenciaEquipe() {
		return codigoSequenciaEquipe;
	}

	public void setCodigoSequenciaEquipe(String codigoSequenciaEquipe) {
		this.codigoSequenciaEquipe = String.format("%-8.8s", codigoSequenciaEquipe);
	}
	

	public String getCodigoAreaEquipe() {
		return codigoAreaEquipe;
	}

	public void setCodigoAreaEquipe(String codigoAreaEquipe) {
		this.codigoAreaEquipe = String.format("%-4.4s", codigoAreaEquipe);
	}

	public String getCodigoCnpjEmpresa() {
		return codigoCnpjEmpresa;
	}

	public void setCodigoCnpjEmpresa(String codigoCnpjEmpresa) {
		this.codigoCnpjEmpresa = String.format("%-14.14s", codigoCnpjEmpresa);
	}

	public String getCepPaciente() {
		return cepPaciente;
	}

	public void setCepPaciente(String cepPaciente) {
		this.cepPaciente = String.format("%-8.8s", cepPaciente);
	}

	public String getLogradouroPaciente() {
		return logradouroPaciente;
	}

	public void setLogradouroPaciente(String logradouroPaciente) {
		this.logradouroPaciente = String.format("%03d", new Object[] {Integer.parseInt(logradouroPaciente)});;
	}

	public String getEnderecoPaciente() {
		return enderecoPaciente;
	}

	public void setEnderecoPaciente(String enderecoPaciente) {
		this.enderecoPaciente = String.format("%-30.30s", enderecoPaciente);
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = String.format("%-10.10s", complementoEndereco);
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = String.format("%-5.5s", numeroEndereco);
	}

	public String getBairroEndereco() {
		return bairroEndereco;
	}

	public void setBairroEndereco(String bairroEndereco) {
		this.bairroEndereco = String.format("%-30.30s", bairroEndereco);
	}

	public String getTelefonePaciente() {
		return telefonePaciente;
	}

	public void setTelefonePaciente(String telefonePaciente) {
		this.telefonePaciente = String.format("%-11.11s", telefonePaciente);
	}

	public String getEmailPaciente() {
		return emailPaciente;
	}

	public void setEmailPaciente(String emailPaciente) {
		this.emailPaciente = String.format("%-40.40s", emailPaciente);
	}

	public String getIdentificacaoEquipe() {
		return identificacaoEquipe;
	}

	public void setIdentificacaoEquipe(String identificacaoEquipe) {
		this.identificacaoEquipe = String.format("%-10.10s", identificacaoEquipe);
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = String.format("%-2.2s", fim);
	}

	@Override
	public String toString() {
		return linhaIdenti + cnes + competencia  + cnsProfissional  + cboProfissional 
				+ dataAtendimento  + numeroFolha  + numeroLinha  + codigoProcedimento + cnsPaciente
				+ sexoPaciente + codigoIbge  + cid  + idade +  qtdProcedimento 			+ caraterAtendimento + " " + numeroAutorizacao + " " + origemInformacao + " " + nomePaciente + " "
				+ dataNascimento + racaCor + etnia + nacionalidade + codigoServico 
				+ codigoClassificacao + codigoSequenciaEquipe + codigoAreaEquipe + codigoCnpjEmpresa
				+ cepPaciente + logradouroPaciente + enderecoPaciente + complementoEndereco
				+ numeroEndereco + bairroEndereco + telefonePaciente + emailPaciente 
				+ identificacaoEquipe;
	}
	
	
	



	
	
	

}
