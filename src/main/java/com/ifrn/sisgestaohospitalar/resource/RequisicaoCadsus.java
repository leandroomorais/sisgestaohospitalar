package com.ifrn.sisgestaohospitalar.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.jhemarcos.cns.CnsDatasus;
import br.com.jhemarcos.conexao.ConexaoDatasusImpl;
import br.com.jhemarcos.models.DadosDemograficosImpl;

/**
 * A classe <code>RequisicaoCadsus</code> contém métodos para as requisições ao
 * Sistema Cadsus para a pesquisa de Cidadãos a partir no CNS ou CPF, usando a
 * API com interface para clientes em JAVA para integração server-side com o
 * DATASUS desenvolvida por Jean Marcos disponível em:
 * {@link #clone("https://github.com/jhemarcos/cns-api")}
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Component
public class RequisicaoCadsus {

	/**
	 * Implementa uma nova conexão com o CADSUS
	 * 
	 * @return ConexaoDatasusImp
	 */
	@Bean
	public static ConexaoDatasusImpl conexaoDatasusImpl() {
		return new ConexaoDatasusImpl("CADSUS.CNS.PDQ.PUBLICO", "kUXNmiiii#RDdlOELdoe00966", false);
	}

	/**
	 * @param conexaoDatasusImpl
	 * @return CnsDatasus
	 */
	@Bean
	public static CnsDatasus cnsDatasus(ConexaoDatasusImpl conexaoDatasusImpl) {
		return new CnsDatasus(conexaoDatasusImpl);
	}

	/**
	 * Retorna a resposta com dados do Cidadão no serviço do Cadsus a partir do CNS
	 * 
	 * @param cnsDatasus
	 * @param param
	 */
	@Bean
	public static String respostaXmlCns(CnsDatasus cnsDatasus, String param) {
		DadosDemograficosImpl dadosDemograficosImpl = (DadosDemograficosImpl) cnsDatasus.buscaPorCNS(param);
		String resposta = dadosDemograficosImpl.getRespostaXml();
		return resposta;
	}

	/**
	 * * Retorna a resposta com dados do Cidadão no serviço do Cadsus a partir do
	 * CPF
	 * 
	 * @param cnsDatasus
	 * @param param
	 * @return
	 */
	@Bean
	public static String respostaXmlCpf(CnsDatasus cnsDatasus, String param) {
		DadosDemograficosImpl dadosDemograficosImpl = (DadosDemograficosImpl) cnsDatasus.buscaPorCPF(param);
		String resposta = dadosDemograficosImpl.getRespostaXml();
		return resposta;
	}

}
