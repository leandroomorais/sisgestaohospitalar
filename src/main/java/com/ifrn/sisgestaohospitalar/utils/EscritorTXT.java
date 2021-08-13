//package com.ifrn.sisgestaohospitalar.utils;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
//import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
//import com.ifrn.sisgestaohospitalar.model.Procedimento;
//import com.ifrn.sisgestaohospitalar.model.RegistroSigtap;
//import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
//import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
//
//import net.bytebuddy.asm.Advice.Local;
//
//@Service
//public class EscritorTXT {
//
//	@Autowired
//	private GuiaAtendimentoService guiaAtendimentoService;
//
//	@Autowired
//	private EstabelecimentoService estabelecimentoService;
//
//	private BufferedWriter buffWrite;
//	
//	
//	
//	
//	public List<GuiaAtendimento> getGuiasBPAI(String mes) throws NullPointerException{
//		String mesAux[] = mes.split("-");
//		LocalDate dataInical = (LocalDate.parse(mesAux[0]+"-"+mesAux[1]+"-01"));
//		LocalDate dataFinal = (LocalDate.now().withMonth(Integer.parseInt(mesAux[1])));
//		List<GuiaAtendimento> guiasBPAI = new ArrayList<GuiaAtendimento>();
//		for(GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInical, dataFinal)) {
//			for(Procedimento procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
//				for(RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//					if(registroSigtap.getCodigoregistro().equals("01")) {
//						guiasBPAI.add(guiaAtendimento);
//					}
//				}
//			}
//		}
//		for(GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInical, dataFinal)) {
//			for(Procedimento procedimentoSigtap : guiaAtendimento.getAtendimentomedico().getProcedimentos()) {
//				for(RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//					if(registroSigtap.getCodigoregistro().equals("01")) {
//						guiasBPAI.add(guiaAtendimento);
//					}
//				}
//			}
//		}
//		for(GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInical, dataFinal)) {
//			for(Procedimento procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento().getProcedimentos()) {
//				for(RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//					if(registroSigtap.getCodigoregistro().equals("01")) {
//						guiasBPAC.add(guiaAtendimento);
//					}
//				}
//		}
//		}
//		return guiasBPAI;
//	}
//
//	public List<GuiaAtendimento> getGuiasBPAC(String mes) throws NullPointerException{
//		String mesAux[] = mes.split("-");
//	LocalDate dataInical = (LocalDate.parse(mesAux[0]+"-"+mesAux[1]+"-01"));
//		LocalDate dataFinal = (LocalDate.now().withMonth(Integer.parseInt(mesAux[1])));
//		List<GuiaAtendimento> guiasBPAC = new ArrayList<GuiaAtendimento>();
//		for(GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInical, dataFinal)) {
//			for(Procedimento procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
//				for(RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//					if(registroSigtap.getCodigoregistro().equals("01")) {
//						guiasBPAC.add(guiaAtendimento);
//					}
//				}
//			}
//		}
//		for(GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInical, dataFinal)) {
//			for(Procedimento procedimentoSigtap : guiaAtendimento.getAtendimentomedico().getProcedimentos()) {
//				for(RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//					if(registroSigtap.getCodigoregistro().equals("01")) {
//						guiasBPAC.add(guiaAtendimento);
//					}
//				}
//			}
//		}
//		for(GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findByPeriodo(dataInical, dataFinal)) {
//			for(Procedimento procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento().getProcedimentos()) {
//				for(RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//					if(registroSigtap.getCodigoregistro().equals("01")) {
//						guiasBPAC.add(guiaAtendimento);
//				}
//				}
//			}
//		}
//	return guiasBPAC;
//	}
//	
//	public void escreveTxt(String linha, String path) throws IOException {
//		buffWrite = new BufferedWriter(new FileWriter(path));
//		buffWrite.append(linha);
////		buffWrite.close();
////	}
//
//	
//	public void escritor(String path, String mes) throws IOException {
//
//		buffWrite = new BufferedWriter(new FileWriter(path));
//
//		int somaProcedimentos = 0;
//
//		List<GuiaAtendimento> guiasBPAC = new ArrayList<GuiaAtendimento>();
//		List<GuiaAtendimento> guiasBPAI = new ArrayList<GuiaAtendimento>();
//
//		Estabelecimento estabelecimento = estabelecimentoService.findByCnes("2380633");
//		
//		try {
//
//			for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findAll()) {
//				for (Procedimento procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
//					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//						if (registroSigtap.getCodigoregistro().equals("01")) {
//							guiasBPAC.add(guiaAtendimento);
//							somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
//							somaProcedimentos++;
//						}
//					}
//				}
//			}
//
//			for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findAll()) {
//				for (Procedimento procedimentoSigtap : guiaAtendimento.getAtendimentomedico()
//						.getProcedimentos()) {
//					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//						if (registroSigtap.getCodigoregistro().equals("01")) {
//							guiasBPAC.add(guiaAtendimento);
//							somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
//							somaProcedimentos++;
//						}
//					}
//				}
//			}
//
//			for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findAll()) {
//				for (Procedimento procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento()
//						.getProcedimentos()) {
//					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//						if (registroSigtap.getCodigoregistro().equals("01")) {
//							guiasBPAC.add(guiaAtendimento);
//							somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
//							somaProcedimentos++;
//						}
//					}
//				}
//			}
//
//			for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findAll()) {
//				for (Procedimento procedimentoSigtap : guiaAtendimento.getTriagem().getProcedimentos()) {
//					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//						if (registroSigtap.getCodigoregistro().equals("02")) {
//							guiasBPAI.add(guiaAtendimento);
//							somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
//							somaProcedimentos++;
//						}
//					}
//				}
//			}
//
//			for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findAll()) {
//				for (Procedimento procedimentoSigtap : guiaAtendimento.getAtendimentomedico()
//						.getProcedimentos()) {
//					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//						if (registroSigtap.getCodigoregistro().equals("02")) {
//							guiasBPAI.add(guiaAtendimento);
//							somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
//							somaProcedimentos++;
//						}
//					}
//				}
//			}
//
//			for (GuiaAtendimento guiaAtendimento : guiaAtendimentoService.findAll()) {
//				for (Procedimento procedimentoSigtap : guiaAtendimento.getAdministracaomedicamento()
//						.getProcedimentos()) {
//					for (RegistroSigtap registroSigtap : procedimentoSigtap.getRegistros()) {
//						if (registroSigtap.getCodigoregistro().equals("02")) {
//							guiasBPAI.add(guiaAtendimento);
//							somaProcedimentos += Integer.parseInt(procedimentoSigtap.getCodigoprocedimento());
//							somaProcedimentos++;
//						}
//					}
//				}
//			}
//
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//
//		String cbchdr = "01"; // Indicador de Linha do Header
//		String Cbchdr = "#BPA"; // Indicado de Início do Cabeçalho
//		String cbcmvm = mes.replace("-", ""); // Ano e Mês do Processamento da Produção
//		int numLinhas = guiasBPAC.size() + guiasBPAI.size() + 1; // Número de Linhas de BPA gravadas
//		
//		String cbclin = String.format("%06d", new Object[] {numLinhas}); // Número de Linhas de BPA gravadas
//		String cbcflh = String.format("%06d", new Object[] {numLinhas}); // Número de folhas de BPA gravadas
//		int resto = somaProcedimentos % 1111;
//		int resultado = resto + 1111;
//		String cbccontrole = Integer.toString(resultado); // Código de campo de controle
//		String cbcrsp = String.format("%-30.30s", "SMS SEVERIANO MELO"); // Estabelecimento Responsável pelo Envio da
//																			// produção
//		String cbcsgl = String.format("%-6.6s", "SMSSM"); // Sigla do Estabelecimento Responsável pela Digitação
//		String cbccnpj = String.format("%-14.14s", estabelecimento.getCnpj()); // Sigla do Estabelecimento Responsável
//																				// pela Digitação
//		String cbcdestino = String.format("%-40.40s", "SMS SEVERIANO MELO"); // Estabelecimento Responsável pelo Envio
//																				// da produção
//		String cbcdestinoin = "M";
//		String cbcversao = String.format("%-10.10s", "1.0"); // Versão do Sistema
//
//		String linha01 = cbchdr + Cbchdr + cbcmvm + cbclin + cbcflh + resultado + cbcrsp + cbcsgl + cbccnpj
//				+ cbcdestino + cbcdestinoin + cbcversao;
//
//		buffWrite.append(linha01);
//		
//		String prdident = "02";
//		String prdcnes = estabelecimento.getCnes();
//		
//		Iterator<GuiaAtendimento> iterator = guiasBPAC.iterator();
//		while (iterator.hasNext()) {
//			Object object = iterator.next();
//			int frequency = Collections.frequency(guiasBPAC, object);
//		}
//		
//		
//		
//		
//	}
//
//}
