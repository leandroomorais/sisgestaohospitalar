package com.ifrn.sisgestaohospitalar.jobs;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ifrn.sisgestaohospitalar.utils.ReadTxtSigtap;
import com.ifrn.sisgestaohospitalar.utils.ReadXml;

/**
 * Classe Initiator que contém métodos que inicializam funções juntamente com a
 * inicialização da aplicação
 * 
 * @author Leandro Morais
 *
 */
@Component
public class Initiator implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ReadXml readXml;

	@Autowired
	private ReadTxtSigtap readTxtSigtap;

	String cnes = "2380633";
	String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS21_241360.xml";
	
	String urlProcedimentos = System.getProperty("user.dir") + "/SigtapSUS/tb_procedimento.txt";
	
	String urlRegistros = System.getProperty("user.dir") + "/SigtapSUS/tb_registro.txt";
	
	String urlCid = System.getProperty("user.dir") + "/SigtapSUS/tb_cid.txt";
	
	String urlOcupacao = System.getProperty("user.dir") + "/SigtapSUS/tb_ocupacao.txt";
	
	String urlRelationProced_Registro = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_registro.txt";
	
	String urlRelationProced_Cid = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_cid.txt";
	
	//String urlRelationProced_Ocupacao = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_ocupacao.txt";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Chamada do Método de Leitura do XML ESUS
		readXmlEsus(cnes, file);
		
		
		//Chamada dos Métodos de Leitura dos Arquivos TXT da Tabela SIGTAP
		try {
			readTxtSigtap.readTxtAndSaveProcedures(urlProcedimentos);
			readTxtSigtap.readTxtAndSaveRegister(urlRegistros);
			readTxtSigtap.readAndSaveCid(urlCid);
			readTxtSigtap.readAndSaveOccupation(urlOcupacao);
			readTxtSigtap.relationshipProcedure_Register(urlRelationProced_Registro);
			//readTxt.relationshipProcediment_Cid(urlRelationProced_Cid);
			//readTxt.relationshipProcediment_Ocupacao(urlRelationProced_Ocupacao);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Método que realiza a leitura do arquivo XML e realiza a persistência no Banco de Dados
	 * 
	 * @param cnes
	 * @param file
	 */
	public void readXmlEsus(String cnes, String file) {
		try {
			readXml.readXml(file, cnes);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
