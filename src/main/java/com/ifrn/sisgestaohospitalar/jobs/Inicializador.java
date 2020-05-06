package com.ifrn.sisgestaohospitalar.jobs;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ifrn.sisgestaohospitalar.utils.LeitorTxtSigtap;
import com.ifrn.sisgestaohospitalar.utils.LeitorXlsCiap2;
import com.ifrn.sisgestaohospitalar.utils.LeitorXmlEsus;

import jxl.read.biff.BiffException;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private LeitorXmlEsus leitorXmlEsus;

	@Autowired
	private LeitorTxtSigtap leitorTxtSigtap;
	
	@Autowired
	private LeitorXlsCiap2 leitorXlsCiap2;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		String cnes = "2380633";
		String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS21_241360.xml";

		try {
			leitorXmlEsus.lerXmlEsus(file, cnes);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String urlProcedimentos = System.getProperty("user.dir") + "/SigtapSUS/tb_procedimento.txt";

		String urlRegistros = System.getProperty("user.dir") + "/SigtapSUS/tb_registro.txt";

		String urlCid = System.getProperty("user.dir") + "/SigtapSUS/tb_cid.txt";

		String urlOcupacao = System.getProperty("user.dir") + "/SigtapSUS/tb_ocupacao.txt";

		String urlRelationProced_Registro = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_registro.txt";

		String urlRelationProced_Cid = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_cid.txt";

		String urlRelationProced_Ocupacao = System.getProperty("user.dir")
				+ "/SigtapSUS/rl_procedimento_ocupacaoteste.txt";

		try {
			//leitorTxtSigtap.lerTxtCid(urlCid);
			leitorTxtSigtap.lerTxtProcedimentos(urlProcedimentos);
			//leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
			leitorTxtSigtap.lerTxtRegistro(urlRegistros);

			//leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);
			//leitorTxtSigtap.relacionamentoProcedimento_Ocupacao(urlRelationProced_Ocupacao);
			leitorTxtSigtap.relacionamentoProcedimento_Registro(urlRelationProced_Registro);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String urlCiap2 = System.getProperty("user.dir") + "/CIAP2/CIAP_CID_revisado_16_8_2016_xls.xls";
		try {
			leitorXlsCiap2.lerCiap2(urlCiap2);
		} catch (BiffException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
