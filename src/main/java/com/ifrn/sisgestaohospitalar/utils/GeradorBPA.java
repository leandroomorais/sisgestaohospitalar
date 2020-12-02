package com.ifrn.sisgestaohospitalar.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.model.BPAIndividualizado;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.RegistroSigtap;
import com.ifrn.sisgestaohospitalar.service.ArquivoBPAService;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;

@Service
public class GeradorBPA {

	@Autowired
	private GuiaAtendimentoService guiaAtendimentoService;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	private ArquivoBPAService arquivoBPAService;

	public void geradorBPA(String mes, String cnes) throws IOException {

		String diretorio = System.getProperty("user.dir") + "/Bpa";
		String mesAux[] = mes.split("-");
		String extensaoArquivo = extensaoDoArquivo(mesAux[1]);

		File BPATEXT = new File(diretorio, "PA" + mes + "." + extensaoArquivo);
		FileWriter writer = new FileWriter(BPATEXT, false);
		PrintWriter printWriter = new PrintWriter(writer);

		int contNumeroFolhaBPAI = 0;
		int contLinhasBPAI = 0;
		int linhasAuxTriagem = 0;
		int linhasAuxAtdMedico = 0;
		int linhasAuxAdmMedicamentos = 0;

		long somaProcedimentos = 0;
		long qtdProcedimentos = 0;

		Estabelecimento estabelecimento = estabelecimentoService.findByCnes(cnes);

		LocalDate dataInicial = (LocalDate.parse(mesAux[0] + "-" + mesAux[1] + "-01"));
		LocalDate dataFinal = (LocalDate.now().withMonth(Integer.parseInt(mesAux[1])));

		ArquivoBPA arquivoBPA = new ArquivoBPA();
		
		

		// For para a quantidade de procedimentos, linhas e folhas do BPAI
		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getTriagem() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							for (Lotacao lotacao : guiaAtendimento.getTriagem().getProfissional().getLotacoes()) {
								somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
								qtdProcedimentos++;
								contNumeroFolhaBPAI = 1;
							}
						}
					}
				}
			}

			if (guiaAtendimento.getAtendimentomedico() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAtendimentomedico()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							for (Lotacao lotacao : guiaAtendimento.getAtendimentomedico().getProfissional()
									.getLotacoes()) {
								somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
								qtdProcedimentos++;
								contNumeroFolhaBPAI = 2;
							}
						}
					}
				}
			}

			if (guiaAtendimento.getAdministracaomedicamento() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							for (Lotacao lotacao : guiaAtendimento.getAdministracaomedicamento().getProfissional()
									.getLotacoes()) {
								somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
								qtdProcedimentos++;
								contNumeroFolhaBPAI = 3;
							}
						}
					}
				}
			}
		}
		
		System.out.println("###########################################################");
		System.out.println("SOMA PROCEDIMENTOs: " + somaProcedimentos + "QTD PROCEDIMENTOS: " + qtdProcedimentos);

		BPATEXT.createNewFile();
		
		int qtdLinhas = (int) (qtdProcedimentos + 1);

		arquivoBPA.setCabecalhoHeader("01");
		arquivoBPA.setIndicadorHeader("#BPA#");
		arquivoBPA.setCompetencia(mes.replace("-", ""));
		arquivoBPA.setQtdLinhas(Integer.toString(qtdLinhas));
		arquivoBPA.setQtdFolhas(Integer.toString(contNumeroFolhaBPAI));
		arquivoBPA.setControleDominio(campoControle(somaProcedimentos, qtdProcedimentos));
		arquivoBPA.setOrgaoResponsavel("SMS DE CIDADE MODELO");
		arquivoBPA.setSiglaOrgaoResponsavel("SMSCM");
		arquivoBPA.setCnpjOrgaoResponsavel(estabelecimento.getCnpj());
		arquivoBPA.setOrgaoDestino("SMS DE CIDADE MODELO");
		arquivoBPA.setIncadorOrgao("M");
		arquivoBPA.setVersaoSistema("SGH-1.0");

		printWriter.println(arquivoBPA.toString());

		// For para escrita dos BPAI
		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getTriagem() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							for (Lotacao lotacao : guiaAtendimento.getTriagem().getProfissional().getLotacoes()) {
								BPAIndividualizado bpaIndividualizado = new BPAIndividualizado();
								linhasAuxTriagem++;
								bpaIndividualizado.setLinhaIdenti("03");
								bpaIndividualizado.setCnes(estabelecimento.getCnes());
								bpaIndividualizado.setCompetencia(mes.replace("-", ""));
								bpaIndividualizado
										.setCnsProfissional(guiaAtendimento.getTriagem().getProfissional().getCns());
								bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
								bpaIndividualizado.setDataAtendimento(guiaAtendimento.getTriagem().getData());
								bpaIndividualizado.setNumeroFolha("01");
								bpaIndividualizado.setNumeroLinha(Integer.toString(linhasAuxTriagem));
								bpaIndividualizado.setCodigoProcedimento(procedimentoSigtap.getCodigoprocedimento());
								bpaIndividualizado.setCnsPaciente(guiaAtendimento.getCidadao().getCns());
								bpaIndividualizado.setSexoPaciente(guiaAtendimento.getCidadao().getSexo());
								bpaIndividualizado.setCodigoIbge(guiaAtendimento.getCidadao().getCodigoibgemunicipio());
								bpaIndividualizado.setCid("");
								bpaIndividualizado.setIdade(Integer.toString(guiaAtendimento.getCidadao().getIdade()));
								bpaIndividualizado.setQtdProcedimento("01");
								bpaIndividualizado.setCaraterAtendimento("01");
								bpaIndividualizado.setNumeroAutorizacao("");
								bpaIndividualizado.setOrigemInformacao("BPA");
								bpaIndividualizado.setNomePaciente(guiaAtendimento.getCidadao().getNome());
								bpaIndividualizado.setDataNascimento(guiaAtendimento.getCidadao().getDatanascimento());
								bpaIndividualizado
										.setRacaCor(Integer.toString(guiaAtendimento.getCidadao().getCodigoraca()));
								if (guiaAtendimento.getCidadao().getCodigoraca() != 05) {
									bpaIndividualizado.setEtnia("");
								} else {
									bpaIndividualizado
											.setEtnia(Integer.toString(guiaAtendimento.getCidadao().getCodigoetnia()));
								}
								bpaIndividualizado.setNacionalidade(
										Integer.toString(guiaAtendimento.getCidadao().getCodigonacionalidade()));
								bpaIndividualizado.setCodigoServico("");
								bpaIndividualizado.setCodigoClassificacao("");
								bpaIndividualizado.setCodigoSequenciaEquipe("");
								bpaIndividualizado.setCodigoAreaEquipe("");
								bpaIndividualizado.setCodigoCnpjEmpresa("");
								bpaIndividualizado.setCepPaciente(guiaAtendimento.getCidadao().getCep());
								bpaIndividualizado.setLogradouroPaciente(
										Integer.toString(guiaAtendimento.getCidadao().getCodigologradouro()));
								bpaIndividualizado.setEnderecoPaciente(guiaAtendimento.getCidadao().getEndereco());
								bpaIndividualizado
										.setComplementoEndereco(guiaAtendimento.getCidadao().getComplementoendereco());
								bpaIndividualizado.setNumeroEndereco(guiaAtendimento.getCidadao().getNumeroendereco());
								bpaIndividualizado.setBairroEndereco(guiaAtendimento.getCidadao().getBairro());
								bpaIndividualizado.setTelefonePaciente(guiaAtendimento.getCidadao().getTelefone());
								bpaIndividualizado.setEmailPaciente(guiaAtendimento.getCidadao().getEmail());
								bpaIndividualizado.setIdentificacaoEquipe("");
								bpaIndividualizado.setFim("");
								printWriter.println(bpaIndividualizado.toString());
							}
						}
					}
				}
			}

			if (guiaAtendimento.getAtendimentomedico() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAtendimentomedico()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							for (Lotacao lotacao : guiaAtendimento.getAtendimentomedico().getProfissional()
									.getLotacoes()) {
								BPAIndividualizado bpaIndividualizado = new BPAIndividualizado();
								linhasAuxAtdMedico++;
								bpaIndividualizado.setLinhaIdenti("03");
								bpaIndividualizado.setCnes(estabelecimento.getCnes());
								bpaIndividualizado.setCompetencia(mes.replace("-", ""));
								bpaIndividualizado.setCnsProfissional(
										guiaAtendimento.getAtendimentomedico().getProfissional().getCns());
								bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
								bpaIndividualizado.setDataAtendimento(guiaAtendimento.getAtendimentomedico().getData());
								bpaIndividualizado.setNumeroFolha("01");
								bpaIndividualizado.setNumeroLinha(Integer.toString(linhasAuxAtdMedico));
								bpaIndividualizado.setCodigoProcedimento(procedimentoSigtap.getCodigoprocedimento());
								bpaIndividualizado.setCnsPaciente(guiaAtendimento.getCidadao().getCns());
								bpaIndividualizado.setSexoPaciente(guiaAtendimento.getCidadao().getSexo());
								bpaIndividualizado.setCodigoIbge(guiaAtendimento.getCidadao().getCodigoibgemunicipio());
								bpaIndividualizado.setCid("");
								bpaIndividualizado.setIdade(Integer.toString(guiaAtendimento.getCidadao().getIdade()));
								bpaIndividualizado.setQtdProcedimento("01");
								bpaIndividualizado.setCaraterAtendimento("01");
								bpaIndividualizado.setNumeroAutorizacao("");
								bpaIndividualizado.setOrigemInformacao("BPA");
								bpaIndividualizado.setNomePaciente(guiaAtendimento.getCidadao().getNome());
								bpaIndividualizado.setDataNascimento(guiaAtendimento.getCidadao().getDatanascimento());
								bpaIndividualizado
										.setRacaCor(Integer.toString(guiaAtendimento.getCidadao().getCodigoraca()));
								if (guiaAtendimento.getCidadao().getCodigoraca() != 05) {
									bpaIndividualizado.setEtnia("");
								} else {
									bpaIndividualizado
											.setEtnia(Integer.toString(guiaAtendimento.getCidadao().getCodigoetnia()));
								}
								bpaIndividualizado.setNacionalidade(
										Integer.toString(guiaAtendimento.getCidadao().getCodigonacionalidade()));
								bpaIndividualizado.setCodigoServico("");
								bpaIndividualizado.setCodigoClassificacao("");
								bpaIndividualizado.setCodigoSequenciaEquipe("");
								bpaIndividualizado.setCodigoAreaEquipe("");
								bpaIndividualizado.setCodigoCnpjEmpresa("");
								bpaIndividualizado.setCepPaciente(guiaAtendimento.getCidadao().getCep());
								bpaIndividualizado.setLogradouroPaciente(
										Integer.toString(guiaAtendimento.getCidadao().getCodigologradouro()));
								bpaIndividualizado.setEnderecoPaciente(guiaAtendimento.getCidadao().getEndereco());
								bpaIndividualizado
										.setComplementoEndereco(guiaAtendimento.getCidadao().getComplementoendereco());
								bpaIndividualizado.setNumeroEndereco(guiaAtendimento.getCidadao().getNumeroendereco());
								bpaIndividualizado.setBairroEndereco(guiaAtendimento.getCidadao().getBairro());
								bpaIndividualizado.setTelefonePaciente(guiaAtendimento.getCidadao().getTelefone());
								bpaIndividualizado.setEmailPaciente(guiaAtendimento.getCidadao().getEmail());
								bpaIndividualizado.setIdentificacaoEquipe("");
								bpaIndividualizado.setFim("");
								printWriter.println(bpaIndividualizado.toString());
							}
						}
					}
				}
			}

			if (guiaAtendimento.getAdministracaomedicamento() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							for (Lotacao lotacao : guiaAtendimento.getAdministracaomedicamento().getProfissional()
									.getLotacoes()) {
								BPAIndividualizado bpaIndividualizado = new BPAIndividualizado();
								linhasAuxAdmMedicamentos++;
								bpaIndividualizado.setLinhaIdenti("03");
								bpaIndividualizado.setCnes(estabelecimento.getCnes());
								bpaIndividualizado.setCompetencia(mes.replace("-", ""));
								bpaIndividualizado.setCnsProfissional(
								guiaAtendimento.getAdministracaomedicamento().getProfissional().getCns());
								bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
								bpaIndividualizado.setDataAtendimento(guiaAtendimento.getData());
								bpaIndividualizado.setNumeroFolha("01");
								bpaIndividualizado.setNumeroLinha(Integer.toString(linhasAuxAdmMedicamentos));
								bpaIndividualizado.setCodigoProcedimento(procedimentoSigtap.getCodigoprocedimento());
								bpaIndividualizado.setCnsPaciente(guiaAtendimento.getCidadao().getCns());
								bpaIndividualizado.setSexoPaciente(guiaAtendimento.getCidadao().getSexo());
								bpaIndividualizado.setCodigoIbge(guiaAtendimento.getCidadao().getCodigoibgemunicipio());
								bpaIndividualizado.setCid("");
								bpaIndividualizado.setIdade(Integer.toString(guiaAtendimento.getCidadao().getIdade()));
								bpaIndividualizado.setQtdProcedimento("01");
								bpaIndividualizado.setCaraterAtendimento("01");
								bpaIndividualizado.setNumeroAutorizacao("");
								bpaIndividualizado.setOrigemInformacao("BPA");
								bpaIndividualizado.setNomePaciente(guiaAtendimento.getCidadao().getNome());
								bpaIndividualizado.setDataNascimento(guiaAtendimento.getCidadao().getDatanascimento());
								bpaIndividualizado
										.setRacaCor(Integer.toString(guiaAtendimento.getCidadao().getCodigoraca()));
								if (guiaAtendimento.getCidadao().getCodigoraca() != 05) {
									bpaIndividualizado.setEtnia("");
								} else {
									bpaIndividualizado
											.setEtnia(Integer.toString(guiaAtendimento.getCidadao().getCodigoetnia()));
								}
								bpaIndividualizado.setNacionalidade(
										Integer.toString(guiaAtendimento.getCidadao().getCodigonacionalidade()));
								bpaIndividualizado.setCodigoServico("");
								bpaIndividualizado.setCodigoClassificacao("");
								bpaIndividualizado.setCodigoSequenciaEquipe("");
								bpaIndividualizado.setCodigoAreaEquipe("");
								bpaIndividualizado.setCodigoCnpjEmpresa("");
								bpaIndividualizado.setCepPaciente(guiaAtendimento.getCidadao().getCep());
								bpaIndividualizado.setLogradouroPaciente(
										Integer.toString(guiaAtendimento.getCidadao().getCodigologradouro()));
								bpaIndividualizado.setEnderecoPaciente(guiaAtendimento.getCidadao().getEndereco());
								bpaIndividualizado
										.setComplementoEndereco(guiaAtendimento.getCidadao().getComplementoendereco());
								bpaIndividualizado.setNumeroEndereco(guiaAtendimento.getCidadao().getNumeroendereco());
								bpaIndividualizado.setBairroEndereco(guiaAtendimento.getCidadao().getBairro());
								bpaIndividualizado.setTelefonePaciente(guiaAtendimento.getCidadao().getTelefone());
								bpaIndividualizado.setEmailPaciente(guiaAtendimento.getCidadao().getEmail());
								bpaIndividualizado.setIdentificacaoEquipe("");
								bpaIndividualizado.setFim("");
								printWriter.println(bpaIndividualizado.toString());
								// arquivoBPA.getBpasIndividualizado().add(bpaIndividualizado);
							}
						}
					}
				}
			}
		}

		arquivoBPA.setGerado(true);
		arquivoBPA.setDataGeracao(LocalDate.now());
		arquivoBPA.setHoraGeracao(LocalDateTime.now());
		arquivoBPA.setLink(BPATEXT.getAbsolutePath());
		arquivoBPA.setNomeArquivo(BPATEXT.getName());

		arquivoBPAService.save(arquivoBPA);
		printWriter.flush();

		printWriter.close();
	}

	public String campoControle(Long somaProcedimentos, Long quantidadeProcedimentos) {
		long soma = somaProcedimentos + quantidadeProcedimentos;
		long resto = soma % 1111;
		long resultado = resto + 1111;
		return Long.toString(resultado);
	}

	public String extensaoDoArquivo(String mes) {
		if (mes.equals("01")) {
			return "JAN";
		} else if (mes.equals("02")) {
			return "FEV";
		} else if (mes.equals("03")) {
			return "MAR";
		} else if (mes.equals("04")) {
			return "ABR";
		} else if (mes.equals("05")) {
			return "MAI";
		} else if (mes.equals("06")) {
			return "JUN";
		} else if (mes.equals("07")) {
			return "JUL";
		} else if (mes.equals("08")) {
			return "AGO";
		} else if (mes.equals("09")) {
			return "SET";
		} else if (mes.equals("10")) {
			return "OUT";
		} else if (mes.equals("11")) {
			return "NOV";
		} else if (mes.equals("12")) {
			return "DEZ";
		} else
			return "txt";
	}

}
