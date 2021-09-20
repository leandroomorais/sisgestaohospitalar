package com.ifrn.sisgestaohospitalar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Cid;
import com.ifrn.sisgestaohospitalar.model.Ocupacao;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.model.RegistroSigtap;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoCid;
import com.ifrn.sisgestaohospitalar.repository.CidRepository;
import com.ifrn.sisgestaohospitalar.repository.OcupacaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.RegistroSigtapRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoCidRepository;

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
	private ProcedimentoRepository procedimentoRepository;

	@Autowired
	private RegistroSigtapRepository registroSigtapRepository;

	@Autowired
	private CidRepository cidRepository;

	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	@Autowired
	private ProcedimentoCidRepository procedimentoCidRepository;

	/**
	 * Este método realiza a leitura do arquivo TXT que contém o relacionamento
	 * entre a tabela de Procedimento e a tabela RegistroSigtap e persiste as
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
			Long CO_PROCEDIMENTO = Long.getLong(novaLinha.substring(0, 10));
			String CO_REGISTRO = novaLinha.substring(10, 12);
			String DT_COMPETENCIA = novaLinha.substring(12, 18);
			Procedimento procedimentoSigtap = procedimentoRepository.findByCodigo(CO_PROCEDIMENTO);
			RegistroSigtap registroSigtap = registroSigtapRepository.findByCodigo(CO_REGISTRO);
			procedimentoSigtap.getRegistros().add(registroSigtap);

			if (procedimentoSigtap.getDataCompetencia().equals(DT_COMPETENCIA)) {
				procedimentoRepository.saveAndFlush(procedimentoSigtap);
			}
		}
	}

	/**
	 * Este método realiza a leitura do arquivo TXT que contém o relacionamento
	 * entre a tabela de Procedimento e a tabela Cid e persiste as informações no
	 * Banco de Dados
	 * 
	 * @param arquivoRelacionamentoProcedimento_Cid
	 * @throws IOException
	 */
	public void relacionamentoProcedimento_Cid(String arquivoRelacionamentoProcedimento_Cid) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoRelacionamentoProcedimento_Cid),
				Charset.forName("ISO-8859-1"));
		String linha;
		
		List<ProcedimentoCid> procedimentoCids = new ArrayList<>();
		
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			Long CO_PROCEDIMENTO = Long.parseLong(novaLinha.substring(0, 10));
			String CO_CID = novaLinha.substring(10, 14);
			String DT_COMPETENCIA = novaLinha.substring(15, 21);
			ProcedimentoCid procedimentoCid = new ProcedimentoCid();
			procedimentoCid.setCodigoProcedimento(CO_PROCEDIMENTO);
			procedimentoCid.setCodigoCid(CO_CID);
			procedimentoCids.add(procedimentoCid);
		}
		procedimentoCidRepository.saveAll(procedimentoCids);
	}

	/**
	 * Este método realiza a leitura do arquivo que contém o relacionamento entre a
	 * tabela Procedimento e Cid e persite as informações no Banco de Dados
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

			Long CO_PROCEDIMENTO = Long.parseLong(novaLinha.substring(0, 10));
			String CO_OCUPACAO = novaLinha.substring(10, 16);
			String DT_COMPETENCIA = novaLinha.substring(16, 22);

			Procedimento procedimentoSigtap = procedimentoRepository.findByCodigo(CO_PROCEDIMENTO);
			Ocupacao ocupacaoSigtap = ocupacaoRepository.findByCodigo(CO_OCUPACAO);

			procedimentoSigtap.getOcupacoes().add(ocupacaoSigtap);

			if (procedimentoSigtap.getDataCompetencia().equals(DT_COMPETENCIA)) {
				procedimentoRepository.saveAndFlush(procedimentoSigtap);
			} else {
				System.out.println("Erro");
			}
		}
	}

	/**
	 * Este método realiza a leitura do arquivo TXT de Procedimentos da Tabela
	 * Sigtap e preenche a tabela Procedimento do Banco de Dados
	 * 
	 * @param arquivoProcedimentos
	 * @throws IOException
	 */
	public void lerTxtProcedimentos(String arquivoProcedimentos) throws IOException {

		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoProcedimentos),
				Charset.forName("ISO-8859-1"));
		String linha;
		List<Procedimento> procedimentos = new ArrayList<>();
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			Long CO_PROCEDIMENTO = Long.parseLong(novaLinha.substring(0, 10));
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
			Procedimento procedimentoSigtap = new Procedimento();
			procedimentoSigtap.setCodigo(CO_PROCEDIMENTO);
			procedimentoSigtap.setNome(NO_PROCEDIMENTO);
			procedimentoSigtap.setTipoComplexidade(TP_COMPLEXIDADE);
			procedimentoSigtap.setTipoSexo(TP_SEXO);
			procedimentoSigtap.setQtdMaximaExecucao(QT_MAXIMA_EXECUCAO);
			procedimentoSigtap.setQtdDiasPermanencia(QT_DIAS_PERMANENCIA);
			procedimentoSigtap.setQtdPontos(QT_PONTOS);
			procedimentoSigtap.setVlIdadeMinina(VL_IDADE_MINIMA);
			procedimentoSigtap.setVlIdadeMaxima(VL_IDADE_MAXIMA);
			procedimentoSigtap.setVlsh(VL_SH);
			procedimentoSigtap.setVlsa(VL_SA);
			procedimentoSigtap.setVlsp(VL_SP);
			procedimentoSigtap.setCodigoFinanciamento(CO_FINANCIAMENTO);
			procedimentoSigtap.setCodigoRubrica(CO_RUBRICA);
			procedimentoSigtap.setQtdTempoPermanencia(QT_TEMPO_PERMANENCIA);
			procedimentoSigtap.setDataCompetencia(DT_COMPETENCIA);
			procedimentos.add(procedimentoSigtap);
		}
		procedimentoRepository.saveAll(procedimentos);
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

		List<RegistroSigtap> registroSigtaps = new ArrayList<>();
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			String CO_REGISTRO = novaLinha.substring(0, 2);
			String NO_REGISTRO = novaLinha.substring(2, 52);
			String DT_COMPETENCIA = novaLinha.substring(52, 58);
			RegistroSigtap registroSigtap = new RegistroSigtap();
			registroSigtap.setCodigo(CO_REGISTRO);
			registroSigtap.setNome(NO_REGISTRO);
			registroSigtap.setDataCompetencia(DT_COMPETENCIA);
			registroSigtaps.add(registroSigtap);
		}
		registroSigtapRepository.saveAll(registroSigtaps);
	}

	/**
	 * Este método realiza a leitura do arquivo TXT Cid da Tabela Sigtap e preenche
	 * a tabela Cid do Banco de Dados
	 * 
	 * @param arquivoCid
	 * @throws IOException
	 */
	public void lerTxtCid(String arquivoCid) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoCid), Charset.forName("ISO-8859-1"));
		String linha;
		List<Cid> cids = new ArrayList<>();
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			String CO_CID = novaLinha.substring(0, 4);
			String NO_CID = novaLinha.substring(4, 104);
			char TP_AGRAVO = novaLinha.charAt(104);
			char TP_SEXO = novaLinha.charAt(105);
			char TP_ESTADIO = novaLinha.charAt(106);
			int VL_CAMPOS_IRRADIADOS = Integer.parseInt(novaLinha.substring(107, 111));
			Cid cidSigtap = new Cid();
			cidSigtap.setCodigo(CO_CID);
			cidSigtap.setNome(NO_CID.trim());
			cidSigtap.setTipoAgravo(TP_AGRAVO);
			cidSigtap.setTipoSexo(TP_SEXO);
			cidSigtap.setTipoEstadio(TP_ESTADIO);
			cidSigtap.setValorCamposIrradiados(VL_CAMPOS_IRRADIADOS);
			cids.add(cidSigtap);
		}

		cidRepository.saveAll(cids);
	}

	/**
	 * Este método realiza a leitura do arquivo TXT Ocupacao da Tabela Sigtap e
	 * preenche a tabela Ocupacao do Banco de Dados
	 * 
	 * @param arquivoOcupacao
	 * @throws IOException
	 */
	public void lerTxtOcupacao(String arquivoOcupacao) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoOcupacao),
				Charset.forName("ISO-8859-1"));
		String linha = bufferedReader.readLine();
		List<Ocupacao> ocupacaos = new ArrayList<>();
		while ((bufferedReader.readLine()) != null) {

			StringBuilder novaLinha = new StringBuilder(linha);
			String CO_OCUPACAO = novaLinha.substring(0, 6);
			String NO_OCUPACAO = novaLinha.substring(6, 156);
			Ocupacao ocupacaoSigtap = new Ocupacao();
			ocupacaoSigtap.setCodigo(CO_OCUPACAO);
			ocupacaoSigtap.setNome(NO_OCUPACAO);
		}
		ocupacaoRepository.saveAll(ocupacaos);
	}
}
