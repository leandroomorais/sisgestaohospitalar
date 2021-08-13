package com.ifrn.sisgestaohospitalar.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
import com.ifrn.sisgestaohospitalar.model.ImportarXmlEsus;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;

/**
 * A classe <code>LeitorXmlEsus</code> é um utilitário que contém métodos para a
 * leitura e persistência de dados do arquivo XML do Sistema ESUS
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class LeitorXmlEsus {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * O método lerXmlEsus ler o arquivo XML ESUS e persiste as informações no Banco
	 * de Dados
	 * 
	 * @param file
	 * @param cnes
	 * @throws JAXBException
	 * @throws ParseException
	 */
	public void lerXmlEsus(String file, String cnes) throws JAXBException, ParseException {

		JAXBContext jaxbContext = JAXBContext.newInstance(ImportarXmlEsus.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ImportarXmlEsus importarXmlEsus = (ImportarXmlEsus) unmarshaller.unmarshal(new File(file));

		for (Profissional profissional : importarXmlEsus.getIdentificacao().getProfissionais()) {
			for (Lotacao lotacaoProfissional : profissional.getLotacoes()) {
				if (lotacaoProfissional.getCnes().equals(cnes)
						&& profissionalRepository.findByCpf(profissional.getCpf()) == null) {
					String[] name = profissional.getNome().split(" ");
					String nomeAbrev = name[0].toString() + " " + name[1].toString();
					profissional.setNomeAbrev(nomeAbrev);
					profissional.setAtivo(true);
//					if (lotacaoProfissional.getCodigoCBO().equals("225125")) {
//						profissional.setTipoprofissional(TipoProfissional.MEDICO);
//						Role role = roleService.findByNome("MED");
//						profissional.getRole().add(role);
//					} else if (lotacaoProfissional.getCodigocbo().equals("223505")) {
//						profissional.setTipoprofissional(TipoProfissional.ENFERMEIRO);
//						Role role = roleService.findByNome("ENF");
//						profissional.getRole().add(role);
//					} else if (lotacaoProfissional.getCodigocbo().equals("322205")) {
//						profissional.setTipoprofissional(TipoProfissional.TECNICO);
//						Role role = roleService.findByNome("TEC");
//						profissional.getRole().add(role);
//					} else if (lotacaoProfissional.getCodigocbo().equals("123105")) {
//						profissional.setTipoprofissional(TipoProfissional.ADMINISTRADOR);
//						Role role = roleService.findByNome("ADM");
//						profissional.getRole().add(role);
//					}

				}

				profissionalRepository.saveAndFlush(profissional);
			}
		}

		for (Estabelecimento estabelecimento : importarXmlEsus.getIdentificacao().getEstabelecimentos()) {
			if (estabelecimento.getCnes().equals(cnes)
					&& estabelecimentoRepository.findByCnes(estabelecimento.getCnes()) == null) {
				estabelecimentoRepository.saveAndFlush(estabelecimento);
			}
		}
	}

}
