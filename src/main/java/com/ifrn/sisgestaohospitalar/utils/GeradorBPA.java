package com.ifrn.sisgestaohospitalar.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import com.ifrn.sisgestaohospitalar.model.Triagem;
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

	List<GuiaAtendimento> guiasBPAC = new ArrayList<GuiaAtendimento>();
	List<GuiaAtendimento> guiasBPAI = new ArrayList<GuiaAtendimento>();
	List<ProcedimentoSigtap> procedimentoSigtapIndividualizado = new ArrayList<ProcedimentoSigtap>();

	public void geradorBPA(String mes, String cnes) throws IOException {

		String diretorio = System.getProperty("user.dir") + "/Bpa";
		String mesAux[] = mes.split("-");
		String extensaoArquivo = extensaoDoArquivo(mesAux[1]);

		File BPATEXT = new File(diretorio, "FILEBPA" + mes + "." + extensaoArquivo);
		FileWriter writer = new FileWriter(BPATEXT, false);
		PrintWriter printWriter = new PrintWriter(writer);

		int contProcedimentosBPAC = 0;
		int contProcedimentosBPAI = 0;
		int contNumeroFolhaBPAC = 0;
		int contNumeroFolhaBPAI = 0;
		int contLinhasBPAC = 0;
		int contLinhasBPAI = 0;

		Estabelecimento estabelecimento = estabelecimentoService.findByCnes(cnes);

		LocalDate dataInicial = (LocalDate.parse(mesAux[0] + "-" + mesAux[1] + "-01"));
		LocalDate dataFinal = (LocalDate.now().withMonth(Integer.parseInt(mesAux[1])));

		ArquivoBPA arquivoBPA = new ArquivoBPA();

		BPATEXT.createNewFile();
		arquivoBPA.setCabecalhoHeader("01");
		arquivoBPA.setIndicadorHeader("#BPA#");
		arquivoBPA.setCompetencia(mes.replace("-", ""));
		arquivoBPA.setQtdLinhas(Integer.toString(guiasBPAI.size()));
		arquivoBPA.setQtdFolhas("01");
		arquivoBPA.setControleDominio("1111");
		arquivoBPA.setOrgaoResponsavel("SMS DE CIDADE MODELO");
		arquivoBPA.setSiglaOrgaoResponsavel("SMSCM");
		arquivoBPA.setCnpjOrgaoResponsavel(estabelecimento.getCnpj());
		arquivoBPA.setOrgaoDestino("SMS DE CIDADE MODELO");
		arquivoBPA.setIncadorOrgao("M");
		arquivoBPA.setVersaoSistema("SGH-1.0");
		

		printWriter.println(arquivoBPA.toString());

		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getAtendimentomedico() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAtendimentomedico()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("01")) {
							guiasBPAC.add(guiaAtendimento);
							System.out.println("BPAC> " + procedimentoSigtap.getNomeprocedimento());
						}
					}
				}
			}

		}

		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getAtendimentomedico() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAtendimentomedico()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("01")) {
							guiasBPAC.add(guiaAtendimento);
							System.out.println("BPAC> " + procedimentoSigtap.getNomeprocedimento());
						}
					}
				}
			}

		}

		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getAdministracaomedicamento() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("01")) {
							guiasBPAC.add(guiaAtendimento);
							System.out.println("BPAC> " + procedimentoSigtap.getNomeprocedimento());
						}
					}
				}
			}

		}

		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getTriagem() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							guiasBPAI.add(guiaAtendimento);
							procedimentoSigtapIndividualizado.add(procedimentoSigtap);
							System.out.println("BPAI> " + procedimentoSigtap.getNomeprocedimento());
						}
					}
				}
			}

		}

		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getAtendimentomedico() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAtendimentomedico()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							guiasBPAI.add(guiaAtendimento);
							procedimentoSigtapIndividualizado.add(procedimentoSigtap);
							System.out.println("BPAI> " + procedimentoSigtap.getNomeprocedimento());
						}
					}
				}
			}

		}

		for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInicial, dataFinal)) {
			if (guiaAtendimento.getAdministracaomedicamento() != null) {
				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento()
						.getProcedimentos()) {
					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
						if (registroSigtap.getCodigoregistro().equals("02")) {
							guiasBPAI.add(guiaAtendimento);
							procedimentoSigtapIndividualizado.add(procedimentoSigtap);
							System.out.println("BPAI> " + procedimentoSigtap.getNomeprocedimento());
						}
					}
				}
			}

		}
		
		for(GuiaAtendimento guiaAtendimento : guiasBPAI) {
			for(ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
				for(Lotacao lotacao : guiaAtendimento.getTriagem().getProfissional().getLotacoes()) {
					contLinhasBPAI++;
					if (contLinhasBPAI >= 20) {
						contNumeroFolhaBPAI++;
						contLinhasBPAI = 0;
					}

					BPAIndividualizado bpaIndividualizado = new BPAIndividualizado();
					bpaIndividualizado.setLinhaIdenti("03");
					bpaIndividualizado.setCnes(estabelecimento.getCnes());
					bpaIndividualizado.setCompetencia(mes.replace("-", ""));
					bpaIndividualizado.setCnsProfissional(guiaAtendimento.getTriagem().getProfissional().getCns());
					bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
					bpaIndividualizado
							.setDataAtendimento(guiaAtendimento.getTriagem().getData().toString().replace("-", ""));
					bpaIndividualizado.setNumeroFolha(Integer.toString(contNumeroFolhaBPAI));
					bpaIndividualizado.setNumeroLinha(Integer.toString(contLinhasBPAI));
					bpaIndividualizado.setCodigoProcedimento(procedimentoSigtap.getCodigoprocedimento());
					bpaIndividualizado.setCnsPaciente(guiaAtendimento.getCidadao().getCns());
					bpaIndividualizado.setSexoPaciente(guiaAtendimento.getCidadao().getSexo());
					bpaIndividualizado.setCodigoIbge(guiaAtendimento.getCidadao().getCodigoibgemunicipio());
					bpaIndividualizado.setCid("");
					bpaIndividualizado.setIdade(Integer.toString(guiaAtendimento.getCidadao().getIdade()));
					bpaIndividualizado.setQtdProcedimento("1");
					bpaIndividualizado.setCaraterAtendimento("01");
					bpaIndividualizado.setNumeroAutorizacao("");
					bpaIndividualizado.setOrigemInformacao("BPA");
					bpaIndividualizado.setNomePaciente(guiaAtendimento.getCidadao().getNome());
					bpaIndividualizado.setDataNascimento(
							guiaAtendimento.getCidadao().getDatanascimento().toString().replace("-", ""));
					bpaIndividualizado.setRacaCor(Integer.toString(guiaAtendimento.getCidadao().getCodigoraca()));
					bpaIndividualizado.setEtnia("");
					bpaIndividualizado
							.setNacionalidade(Integer.toString(guiaAtendimento.getCidadao().getCodigonacionalidade()));
					bpaIndividualizado.setCodigoServico("");
					bpaIndividualizado.setCodigoClassificacao("");
					bpaIndividualizado.setCodigoSequenciaEquipe("");
					bpaIndividualizado.setCodigoAreaEquipe("");
					bpaIndividualizado.setCodigoCnpjEmpresa("");
					bpaIndividualizado.setCepPaciente(guiaAtendimento.getCidadao().getCep());
					bpaIndividualizado.setLogradouroPaciente(
							Integer.toString(guiaAtendimento.getCidadao().getCodigologradouro()));
					bpaIndividualizado.setEnderecoPaciente(guiaAtendimento.getCidadao().getEndereco());
					bpaIndividualizado.setComplementoEndereco(guiaAtendimento.getCidadao().getComplementoendereco());
					bpaIndividualizado.setNumeroEndereco(guiaAtendimento.getCidadao().getNumeroendereco());
					bpaIndividualizado.setBairroEndereco(guiaAtendimento.getCidadao().getBairro());
					bpaIndividualizado.setTelefonePaciente(guiaAtendimento.getCidadao().getTelefone());
					bpaIndividualizado.setEmailPaciente(guiaAtendimento.getCidadao().getEmail());
					bpaIndividualizado.setIdentificacaoEquipe("");

					printWriter.println(bpaIndividualizado.toString());
					
				}
			}
		}
		
		
//		for (GuiaAtendimento guiaAtendimento : guiasBPAI) {
//			for (Lotacao lotacao : guiaAtendimento.getTriagem().getProfissional().getLotacoes()) {
//				for (ProcedimentoSigtap procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
//
//					contLinhasBPAI++;
//					if (contLinhasBPAI >= 20) {
//						contNumeroFolhaBPAI++;
//						contLinhasBPAI = 0;
//					}
//
//					BPAIndividualizado bpaIndividualizado = new BPAIndividualizado();
//					bpaIndividualizado.setLinhaIdenti("03");
//					bpaIndividualizado.setCnes(estabelecimento.getCnes());
//					bpaIndividualizado.setCompetencia(mes.replace("-", ""));
//					bpaIndividualizado.setCnsProfissional(guiaAtendimento.getTriagem().getProfissional().getCns());
//					bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
//					bpaIndividualizado
//							.setDataAtendimento(guiaAtendimento.getTriagem().getData().toString().replace("-", ""));
//					bpaIndividualizado.setNumeroFolha(Integer.toString(contNumeroFolhaBPAI));
//					bpaIndividualizado.setNumeroLinha(Integer.toString(contLinhasBPAI));
//					bpaIndividualizado.setCodigoProcedimento(procedimentoSigtap.getCodigoprocedimento());
//					bpaIndividualizado.setCnsPaciente(guiaAtendimento.getCidadao().getCns());
//					bpaIndividualizado.setSexoPaciente(guiaAtendimento.getCidadao().getSexo());
//					bpaIndividualizado.setCodigoIbge(guiaAtendimento.getCidadao().getCodigoibgemunicipio());
//					bpaIndividualizado.setCid("");
//					bpaIndividualizado.setIdade(Integer.toString(guiaAtendimento.getCidadao().getIdade()));
//					bpaIndividualizado.setQtdProcedimento("1");
//					bpaIndividualizado.setCaraterAtendimento("01");
//					bpaIndividualizado.setNumeroAutorizacao("");
//					bpaIndividualizado.setOrigemInformacao("BPA");
//					bpaIndividualizado.setNomePaciente(guiaAtendimento.getCidadao().getNome());
//					bpaIndividualizado.setDataNascimento(
//							guiaAtendimento.getCidadao().getDatanascimento().toString().replace("-", ""));
//					bpaIndividualizado.setRacaCor(Integer.toString(guiaAtendimento.getCidadao().getCodigoraca()));
//					bpaIndividualizado.setEtnia("");
//					bpaIndividualizado
//							.setNacionalidade(Integer.toString(guiaAtendimento.getCidadao().getCodigonacionalidade()));
//					bpaIndividualizado.setCodigoServico("");
//					bpaIndividualizado.setCodigoClassificacao("");
//					bpaIndividualizado.setCodigoSequenciaEquipe("");
//					bpaIndividualizado.setCodigoAreaEquipe("");
//					bpaIndividualizado.setCodigoCnpjEmpresa("");
//					bpaIndividualizado.setCepPaciente(guiaAtendimento.getCidadao().getCep());
//					bpaIndividualizado.setLogradouroPaciente(
//							Integer.toString(guiaAtendimento.getCidadao().getCodigologradouro()));
//					bpaIndividualizado.setEnderecoPaciente(guiaAtendimento.getCidadao().getEndereco());
//					bpaIndividualizado.setComplementoEndereco(guiaAtendimento.getCidadao().getComplementoendereco());
//					bpaIndividualizado.setNumeroEndereco(guiaAtendimento.getCidadao().getNumeroendereco());
//					bpaIndividualizado.setBairroEndereco(guiaAtendimento.getCidadao().getBairro());
//					bpaIndividualizado.setTelefonePaciente(guiaAtendimento.getCidadao().getTelefone());
//					bpaIndividualizado.setEmailPaciente(guiaAtendimento.getCidadao().getEmail());
//					bpaIndividualizado.setIdentificacaoEquipe("");
//
//					printWriter.println(bpaIndividualizado.toString());
//				}
//			}
//
//		}
		
		arquivoBPA.setGerado(true);
		arquivoBPA.setDataGeracao(LocalDate.now());
		arquivoBPA.setHoraGeracao(LocalDateTime.now());
		arquivoBPA.setLink(BPATEXT.getAbsolutePath());
		arquivoBPA.setNomeArquivo(BPATEXT.getName());
		
		arquivoBPAService.save(arquivoBPA);
		printWriter.flush();

		printWriter.close();

//
//		for (int i = 0; i < guiasBPAI.size(); i++) {
//
//			contLinhasBPAI++;
//			if (contLinhasBPAI >= 20) {
//				contNumeroFolhaBPAI++;
//				contLinhasBPAI = 0;
//			}
//
//			BPAIndividualizado bpaIndividualizado = new BPAIndividualizado();
//			bpaIndividualizado.setLinhaIdenti("03");
//			bpaIndividualizado.setCnes(estabelecimento.getCnes());
//			bpaIndividualizado.setCompetencia(mes.replace("-", ""));
//			bpaIndividualizado.setCnsProfissional(guiasBPAI.get(i).getTriagem().getProfissional().getCns());
//			bpaIndividualizado.setCboProfissional(
//					guiasBPAI.get(i).getTriagem().getProfissional().getLotacoes().get(i).getCodigocbo());
//			bpaIndividualizado.setDataAtendimento(guiasBPAI.get(i).getTriagem().getData().toString().replace("-", ""));
//			bpaIndividualizado.setNumeroFolha(Integer.toString(contNumeroFolhaBPAI));
//			bpaIndividualizado.setNumeroLinha(Integer.toString(contLinhasBPAI));
//			bpaIndividualizado.setCodigoProcedimento(
//					guiasBPAI.get(i).getTriagem().getProcedimentos().get(i).getCodigoprocedimento());
//			bpaIndividualizado.setCnsPaciente(guiasBPAI.get(i).getCidadao().getCns());
//			bpaIndividualizado.setSexoPaciente(guiasBPAI.get(i).getCidadao().getSexo());
//
//			arquivoBPA.getBpasIndividualizado().add(bpaIndividualizado);
//
//		}
//
//		arquivoBPAService.save(arquivoBPA);

//			 for(GuiaAtendimento guiaAtendimento : guiasBPAI) {
//				 BPAIndividualizado bpaIndividualizado = new BPAIndividualizado();
//				 bpaIndividualizado.setLinhaIdenti("03");
//				 bpaIndividualizado.setCnes(estabelecimento.getCnes());
//				 bpaIndividualizado.setCompetencia(mes.replace("-", ""));
//				
//				 for(Lotacao lotacao : guiaAtendimento.getTriagem().getProfissional().getLotacoes()) {
//					 bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
//				 }
//				 
//				 for(Lotacao lotacao : guiaAtendimento.getAtendimentomedico().getProfissional().getLotacoes()) {
//					 bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
//				 }
//				 
//				 for(Lotacao lotacao : guiaAtendimento.getAdministracaomedicamento().getProfissional().getLotacoes()) {
//					 bpaIndividualizado.setCboProfissional(lotacao.getCodigocbo());
//				 }
//				 
//				
//				}

	}

	public String extensaoDoArquivo(String mes) {
		if(mes.equals("01")) {
			return "JAN";
		}else if(mes.equals("02")) {
			return "FEV";
		}else if(mes.equals("03")) {
			return "MAR";
		}else if(mes.equals("04")) {
			return "ABR";
		}else if(mes.equals("05")) {
			return "MAI";
		}else if(mes.equals("06")) {
			return "JUN";
		}else if(mes.equals("07")) {
			return "JUL";
		}else if(mes.equals("08")) {
			return "AGO";
		}else if(mes.equals("09")) {
			return "SET";
		}else if(mes.equals("10")) {
			return "OUT";
		}else if(mes.equals("11")) {
			return "NOV";
		}else if(mes.equals("12")) {
			return "DEZ";
		}else return "txt";
	}

}
