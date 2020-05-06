package com.ifrn.sisgestaohospitalar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.CidSigtap;
import com.ifrn.sisgestaohospitalar.model.OcupacaoSigtap;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;
import com.ifrn.sisgestaohospitalar.model.RegistroSigtap;
import com.ifrn.sisgestaohospitalar.repository.CidSigtapRepository;
import com.ifrn.sisgestaohospitalar.repository.OcupacaoSigtapRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoSigtapRepository;
import com.ifrn.sisgestaohospitalar.repository.RegistroSigtapRepository;

/**
 * A classe <code>LeitorTxtSigtap</code> é um utilitário que contém métodos para
 * a leitura e persistência de dados dos arquivos TXT da Tabela de Procedimentos
 * do SUS - SIGTAP.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class LeitorTxtSigtap {

	@Autowired
	private ProcedimentoSigtapRepository procedimentoSigtapRepository;

	@Autowired
	private RegistroSigtapRepository registroSigtapRepository;

	@Autowired
	private CidSigtapRepository cidSigtapRepository;

	@Autowired
	private OcupacaoSigtapRepository ocupacaoSigtapRepository;

	/**
	 * Este método realiza a leitura do arquivo TXT que contém o relacionamento
	 * entre a tabela de ProcedimentoSigtap e a tabela RegistroSigtap e persiste as
	 * informações no Banco de Dados
	 * 
	 * @param arquivoRelacionamentoProcedimento_Registro
	 * @throws IOException
	 */
	public void relacionamentoProcedimento_Registro(String arquivoRelacionamentoProcedimento_Registro)
			throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoRelacionamentoProcedimento_Registro),
				Charset.forName("ISO-8859-1"));
		String linha;

		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = novaLinha.substring(0, 10);
			String CO_REGISTRO = novaLinha.substring(10, 12);
			String DT_COMPETENCIA = novaLinha.substring(12, 18);

			ProcedimentoSigtap procedimentoSigtap = procedimentoSigtapRepository
					.findByCodigoprocedimento(CO_PROCEDIMENTO);
			RegistroSigtap registroSigtap = registroSigtapRepository.findByCodigoregistro(CO_REGISTRO);

			procedimentoSigtap.getRegistros().add(registroSigtap);

			if (procedimentoSigtap.getDatacompetencia().equals(DT_COMPETENCIA)) {
				procedimentoSigtapRepository.saveAndFlush(procedimentoSigtap);
			}
		}
	}

	/**
	 * Este método realiza a leitura do arquivo TXT que contém o relacionamento
	 * entre a tabela de ProcedimentoSigtap e a tabela CidSigtap e persiste as
	 * informações no Banco de Dados
	 * 
	 * @param arquivoRelacionamentoProcedimento_Cid
	 * @throws IOException
	 */
	public void relacionamentoProcedimento_Cid(String arquivoRelacionamentoProcedimento_Cid) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoRelacionamentoProcedimento_Cid),
				Charset.forName("ISO-8859-1"));
		String linha;

		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = novaLinha.substring(0, 10);
			String CO_CID = novaLinha.substring(10, 14);
			String DT_COMPETENCIA = novaLinha.substring(15, 21);

			ProcedimentoSigtap procedimentoSigtap = procedimentoSigtapRepository
					.findByCodigoprocedimento(CO_PROCEDIMENTO);
			CidSigtap cidSigtap = cidSigtapRepository.findByCodigocid(CO_CID);

			procedimentoSigtap.getCids().add(cidSigtap);

			if (procedimentoSigtap.getDatacompetencia().equals(DT_COMPETENCIA)) {
				procedimentoSigtapRepository.saveAndFlush(procedimentoSigtap);
			} else {
				System.out.println("Erro");
			}
		}

	}

	/**
	 * Este método realiza a leitura do arquivo que contém o relacionamento entre a
	 * tabela ProcedimentoSigtap e CidSigtap e persite as informações no Banco de
	 * Dados
	 * 
	 * @param arquivoRelacionamentoProcedimento_Ocupacao
	 * @throws IOException
	 */
	public void relacionamentoProcedimento_Ocupacao(String arquivoRelacionamentoProcedimento_Ocupacao)
			throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoRelacionamentoProcedimento_Ocupacao),
				Charset.forName("ISO-8859-1"));
		String linha;

		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = novaLinha.substring(0, 10);
			String CO_OCUPACAO = novaLinha.substring(10, 16);
			String DT_COMPETENCIA = novaLinha.substring(16, 22);

			ProcedimentoSigtap procedimentoSigtap = procedimentoSigtapRepository
					.findByCodigoprocedimento(CO_PROCEDIMENTO);
			OcupacaoSigtap ocupacaoSigtap = ocupacaoSigtapRepository.findByCodigoocupacao(CO_OCUPACAO);

			procedimentoSigtap.getOcupacoes().add(ocupacaoSigtap);

			if (procedimentoSigtap.getDatacompetencia().equals(DT_COMPETENCIA)) {
				procedimentoSigtapRepository.saveAndFlush(procedimentoSigtap);
			} else {
				System.out.println("Erro");
			}

		}

	}

	/**
	 * Este método realiza a leitura do arquivo TXT de Procedimentos da Tabela
	 * Sigtap e preenche a tabela ProcedimentoSigtap do Banco de Dados
	 * 
	 * @param arquivoProcedimentos
	 * @throws IOException
	 */
	public void lerTxtProcedimentos(String arquivoProcedimentos) throws IOException {

		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoProcedimentos),
				Charset.forName("ISO-8859-1"));
		String linha;

		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = novaLinha.substring(0, 10);
			String NO_PROCEDIMENTO = novaLinha.substring(10, 260);
			char TP_COMPLEXIDADE = novaLinha.charAt(260);
			char TP_SEXO = novaLinha.charAt(261);
			int QT_MAXIMA_EXECUCAO = Integer.parseInt(novaLinha.substring(262, 266));
			int QT_DIAS_PERMANENCIA = Integer.parseInt(novaLinha.substring(266, 270));
			int QT_PONTOS = Integer.parseInt(novaLinha.substring(270, 274));
			int VL_IDADE_MINIMA = Integer.parseInt(novaLinha.substring(274, 278));
			int VL_IDADE_MAXIMA = Integer.parseInt(novaLinha.substring(278, 282));
			int VL_SH = Integer.parseInt(novaLinha.substring(282, 292));
			int VL_SA = Integer.parseInt(novaLinha.substring(292, 302));
			int VL_SP = Integer.parseInt(novaLinha.substring(302, 312));
			String CO_FINANCIAMENTO = novaLinha.substring(312, 314);
			String CO_RUBRICA = novaLinha.substring(314, 320);
			int QT_TEMPO_PERMANENCIA = Integer.parseInt(novaLinha.substring(320, 323));
			String DT_COMPETENCIA = novaLinha.substring(324, 330);

			ProcedimentoSigtap procedimentoSigtap = new ProcedimentoSigtap();

			procedimentoSigtap.setCodigoprocedimento(CO_PROCEDIMENTO);
			procedimentoSigtap.setNomeprocedimento(NO_PROCEDIMENTO);
			procedimentoSigtap.setTipocomplexidade(TP_COMPLEXIDADE);
			procedimentoSigtap.setTiposexo(TP_SEXO);
			procedimentoSigtap.setQtdmaximaexecucao(QT_MAXIMA_EXECUCAO);
			procedimentoSigtap.setQtddiaspermanencia(QT_DIAS_PERMANENCIA);
			procedimentoSigtap.setQtdpontos(QT_PONTOS);
			procedimentoSigtap.setVlidademinina(VL_IDADE_MINIMA);
			procedimentoSigtap.setVlidademaxima(VL_IDADE_MAXIMA);
			procedimentoSigtap.setVlsh(VL_SH);
			procedimentoSigtap.setVlsa(VL_SA);
			procedimentoSigtap.setVlsp(VL_SP);
			procedimentoSigtap.setCodigofinanciamento(CO_FINANCIAMENTO);
			procedimentoSigtap.setCodigorubrica(CO_RUBRICA);
			procedimentoSigtap.setQtdtempopermanencia(QT_TEMPO_PERMANENCIA);
			procedimentoSigtap.setDatacompetencia(DT_COMPETENCIA);

			procedimentoSigtapRepository.save(procedimentoSigtap);

		}
	}

	/**
	 * Este método realiza a leitura do arquivo TXT Registro da Tabela Sigtap e
	 * preenche a tabela RegistroSigtap do Banco de Dados
	 * 
	 * @param arquivoRegistro
	 * @throws IOException
	 */
	public void lerTxtRegistro(String arquivoRegistro) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoRegistro),
				Charset.forName("ISO-8859-1"));

		String linha;

		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));

			String CO_REGISTRO = novaLinha.substring(0, 2);
			String NO_REGISTRO = novaLinha.substring(2, 52);
			String DT_COMPETENCIA = novaLinha.substring(52, 58);

			RegistroSigtap registroSigtap = new RegistroSigtap();

			registroSigtap.setCodigoregistro(CO_REGISTRO);
			registroSigtap.setNomeregistro(NO_REGISTRO);
			registroSigtap.setDatacompetencia(DT_COMPETENCIA);

			registroSigtapRepository.save(registroSigtap);
		}

	}

	/**
	 * Este método realiza a leitura do arquivo TXT Cid da Tabela Sigtap e preenche
	 * a tabela CidSigtap do Banco de Dados
	 * 
	 * @param arquivoCid
	 * @throws IOException
	 */
	public void lerTxtCid(String arquivoCid) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoCid), Charset.forName("ISO-8859-1"));

		String linha;

		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));

			String CO_CID = novaLinha.substring(0, 4);
			String NO_CID = novaLinha.substring(4, 104);
			char TP_AGRAVO = novaLinha.charAt(104);
			char TP_SEXO = novaLinha.charAt(105);
			char TP_ESTADIO = novaLinha.charAt(106);
			int VL_CAMPOS_IRRADIADOS = Integer.parseInt(novaLinha.substring(107, 111));

			CidSigtap cidSigtap = new CidSigtap();

			cidSigtap.setCodigocid(CO_CID);
			cidSigtap.setNomecid(NO_CID);
			cidSigtap.setTipoagravo(TP_AGRAVO);
			cidSigtap.setTiposexo(TP_SEXO);
			cidSigtap.setTipoestadio(TP_ESTADIO);
			cidSigtap.setValorcamposirradiados(VL_CAMPOS_IRRADIADOS);

			cidSigtapRepository.save(cidSigtap);

		}

	}

	/**
	 * Este método realiza a leitura do arquivo TXT Ocupacao da Tabela Sigtap e
	 * preenche a tabela OcupacaoSigtap do Banco de Dados
	 * 
	 * @param arquivoOcupacao
	 * @throws IOException
	 */
	public void lerTxtOcupacao(String arquivoOcupacao) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoOcupacao),
				Charset.forName("ISO-8859-1"));

		String linha = bufferedReader.readLine();

		OcupacaoSigtap ocupacaoSigtap = new OcupacaoSigtap();

		while ((bufferedReader.readLine()) != null) {

			StringBuilder novaLinha = new StringBuilder(linha);

			String CO_OCUPACAO = novaLinha.substring(0, 6);
			String NO_OCUPACAO = novaLinha.substring(6, 156);

			ocupacaoSigtap.setCodigoocupacao(CO_OCUPACAO);
			ocupacaoSigtap.setNomeocupacao(NO_OCUPACAO);

			ocupacaoSigtapRepository.save(ocupacaoSigtap);
		}
	}
}
