package com.ifrn.sisgestaohospitalar.jobs;

import java.text.ParseException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ifrn.sisgestaohospitalar.utils.ReadXml;

/**Classe Initiator que contém métodos que inicializam funções juntamente 
 * com a inicialização da aplicação
 * @author Leandro Morais
 *
 */
@Component
public class Initiator implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ReadXml readXml;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub

		String cnes = "2380633";
		String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS21_241360.xml";
		
		readXmlEsus(cnes, file);

	
	}
	
	/**Método que realiza a leitura do arquivo XML
	 * @param cnes
	 * @param file*/
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
