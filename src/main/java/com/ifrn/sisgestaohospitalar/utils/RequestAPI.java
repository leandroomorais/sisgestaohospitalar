package com.ifrn.sisgestaohospitalar.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.jhemarcos.cns.CnsDatasus;
import br.com.jhemarcos.conexao.ConexaoDatasusImpl;
import br.com.jhemarcos.models.DadosDemograficosImpl;

@Component
public class RequestAPI {
	
	@Bean
	public static ConexaoDatasusImpl conexaoDatasusImpl() {
		return new ConexaoDatasusImpl("CADSUS.CNS.PDQ.PUBLICO", "kUXNmiiii#RDdlOELdoe00966", false);
	}
	
	@Bean
	public static CnsDatasus cnsDatasus(ConexaoDatasusImpl conexaoDatasusImpl) {
		return  new CnsDatasus(conexaoDatasusImpl);
	}
	
	public static String respostaXmlCns(CnsDatasus cnsDatasus, String param) {
		DadosDemograficosImpl dadosDemograficosImpl = (DadosDemograficosImpl) cnsDatasus.buscaPorCNS(param);
		String resposta = dadosDemograficosImpl.getRespostaXml();
		return resposta;
	}
	
}
