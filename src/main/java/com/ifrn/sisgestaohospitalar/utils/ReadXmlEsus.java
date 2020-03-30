package com.ifrn.sisgestaohospitalar.utils;

import java.io.File;
import java.text.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.enums.ProfessionalType;
import com.ifrn.sisgestaohospitalar.model.Establishment;
import com.ifrn.sisgestaohospitalar.model.ImportXmlEsus;
import com.ifrn.sisgestaohospitalar.model.Professional;
import com.ifrn.sisgestaohospitalar.model.Workplace;
import com.ifrn.sisgestaohospitalar.repository.EstablishmentRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfessionalRepository;

/**
 * Classe que implementa os métodos para a leitura do Arquivo XML
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Service
public class ReadXmlEsus {

	@Autowired
	private ProfessionalRepository professionalRepository;

	@Autowired
	private EstablishmentRepository establishmentRepository;

	/**
	 * Método que realiza a leitura do arquivo XML e salva no Banco de dados as
	 * informações
	 * @param file
	 * @param cnes
	 * @throws JAXBException
	 * @throws ParseException
	 */
	public void readXml(String file, String cnes) throws JAXBException, ParseException {

		JAXBContext jaxbContext = JAXBContext.newInstance(ImportXmlEsus.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ImportXmlEsus importXmlEsus = (ImportXmlEsus) unmarshaller.unmarshal(new File(file));

		for (Professional professional : importXmlEsus.getIdentificationXmlEsus().getProfessionals()) {
			for (Workplace workplace : professional.getWorkplaces()) {
				if (workplace.getCodecnes().equals(cnes)
						&& professionalRepository.findByCpf(professional.getCpf()) == null) {
					professional.setUsername(professional.getCpf());
					professional.setPassword("sgh" + professional.getCpf());
					professional.setStatus(true);
					// professional.setIdcouncil(Integer.parseInt(professional.getIdcouncil()));
					String name = professional.getNameprof();
					String[] name1 = name.split(" ");
					String firstname = name1[0].toString() + " " + name1[1].toString();
					professional.setFirstname(firstname);
					if (workplace.getCodecbo().equals("225125")) {
						professional.setProfessionaltype(ProfessionalType.DOCTOR);
					} else if (workplace.getCodecbo().equals("223505")) {
						professional.setProfessionaltype(ProfessionalType.NURSE);
					} else if (workplace.getCodecbo().equals("322205")) {
						professional.setProfessionaltype(ProfessionalType.TECHNICIAN);
					} else if (workplace.getCodecbo().equals("123105")) {
						professional.setProfessionaltype(ProfessionalType.ADMINISTRATOR);
						;
					}

				}

				professionalRepository.saveAndFlush(professional);
			}
		}

		for (Establishment establishment : importXmlEsus.getIdentificationXmlEsus().getEstablishments()) {
			if (establishment.getCnes().equals(cnes)
					&& establishmentRepository.findByCnes(establishment.getCnes()) == null) {
				establishmentRepository.saveAndFlush(establishment);
			}
		}

	}

}
