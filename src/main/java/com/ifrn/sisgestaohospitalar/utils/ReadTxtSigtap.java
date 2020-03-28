package com.ifrn.sisgestaohospitalar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.CidSigtap;
import com.ifrn.sisgestaohospitalar.model.OccupationSigtap;
import com.ifrn.sisgestaohospitalar.model.ProcedureSigtap;
import com.ifrn.sisgestaohospitalar.model.RegisterSigtap;
import com.ifrn.sisgestaohospitalar.repository.CidSigtapRepository;
import com.ifrn.sisgestaohospitalar.repository.OccupationSigtapRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedureSigtapRepository;
import com.ifrn.sisgestaohospitalar.repository.RegisterSigtapRepository;

/**
 * Classe que implementa os métodos para a leitura dos Arquivos TXT da Tabela de
 * Procedimentos e OPM do SUS - SIGTAP
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */

@Service
public class ReadTxtSigtap {

	@Autowired
	private ProcedureSigtapRepository procedureSigtapRepository;

	@Autowired
	private RegisterSigtapRepository registerSigtapRepository;

	@Autowired
	private CidSigtapRepository cidSigtapRepository;

	@Autowired
	private OccupationSigtapRepository occupationSigtapRepository;

	/**
	 * Realiza a leitura do arquivo TXT e o relacionamento entre a tabela de
	 * procedimentos (ProcedureSigtap) e a tabela de Instrumento de Registro
	 * (RegisterSigtap)
	 * 
	 * @param fileRelationshipProcedure_Register
	 * @throws IOException
	 */
	public void relationshipProcedure_Register(String fileRelationshipProcedure_Register) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileRelationshipProcedure_Register),
				Charset.forName("ISO-8859-1"));
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String newLine = new String(line.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = newLine.substring(0, 10);
			String CO_REGISTRO = newLine.substring(10, 12);
			String DT_COMPETENCIA = newLine.substring(12, 18);

			ProcedureSigtap procedure = procedureSigtapRepository.findByCodeprocedure(CO_PROCEDIMENTO);
			RegisterSigtap registro = registerSigtapRepository.findByCoderegister(CO_REGISTRO);

			procedure.getRegistersSigtap().add(registro);

			if (procedure.getDatecompetency().equals(DT_COMPETENCIA)) {
				procedureSigtapRepository.saveAndFlush(procedure);
			}
		}
	}

	/**
	 * Realiza a leitura do arquivo TXT e o relacionamento entre a tabela de
	 * procedimentos (ProcedureSigtap) e a tabela do Código Internacional de Doenças
	 * CID (CidSigtap)
	 * 
	 * @param fileRelationshipProcedure_Cid
	 * @throws IOException
	 */
	public void relationshipProcedure_Cid(String fileRelationshipProcedure_Cid) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileRelationshipProcedure_Cid),
				Charset.forName("ISO-8859-1"));
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String newLine = new String(line.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = newLine.substring(0, 10);
			String CO_CID = newLine.substring(10, 14);
			String DT_COMPETENCIA = newLine.substring(15, 21);

			ProcedureSigtap procedure = procedureSigtapRepository.findByCodeprocedure(CO_PROCEDIMENTO);
			CidSigtap cid = cidSigtapRepository.findByCodecid(CO_CID);

			procedure.getCidsSigtap().add(cid);

			if (procedure.getDatecompetency().equals(DT_COMPETENCIA)) {
				procedureSigtapRepository.saveAndFlush(procedure);
			} else {
				System.out.println("Erro");
			}
		}

	}

	/**
	 * Realiza a leitura do arquivo TXT e o relacionamento entre a tabela de
	 * procedimentos (ProcedureSigtap) e a tabela de Ocupações CBO
	 * (OccupationSigtap)
	 * 
	 * @param fileRelationshipProcedure_Occupation
	 * @throws IOException
	 */
	public void relationshipProcedure_Occupation(String fileRelationshipProcedure_Occupation) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileRelationshipProcedure_Occupation),
				Charset.forName("ISO-8859-1"));
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String newLine = new String(line.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = newLine.substring(0, 10);
			String CO_OCUPACAO = newLine.substring(10, 16);
			String DT_COMPETENCIA = newLine.substring(16, 22);

			ProcedureSigtap procedure = procedureSigtapRepository.findByCodeprocedure(CO_PROCEDIMENTO);
			OccupationSigtap occupation = occupationSigtapRepository.findByCodeoccupation(CO_OCUPACAO);

			procedure.getOccupationsSigtap().add(occupation);

			if (procedure.getDatecompetency().equals(DT_COMPETENCIA)) {
				procedureSigtapRepository.saveAndFlush(procedure);
			} else {
				System.out.println("Erro");
			}
		}

	}

	/**
	 * Realiza a leitura do arquivo TXT que contém os Procedimento da Sigtap do SUS
	 * e persiste no Banco de Dados
	 * 
	 * @param fileProcedure
	 * @throws IOException
	 */
	public void readTxtAndSaveProcedures(String fileProcedure) throws IOException {

		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileProcedure),
				Charset.forName("ISO-8859-1"));
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String newLine = new String(line.getBytes("UTF-8"));

			String CO_PROCEDIMENTO = newLine.substring(0, 10);
			String NO_PROCEDIMENTO = newLine.substring(10, 260);
			char TP_COMPLEXIDADE = newLine.charAt(260);
			char TP_SEXO = newLine.charAt(261);
			int QT_MAXIMA_EXECUCAO = Integer.parseInt(newLine.substring(262, 266));
			int QT_DIAS_PERMANENCIA = Integer.parseInt(newLine.substring(266, 270));
			int QT_PONTOS = Integer.parseInt(newLine.substring(270, 274));
			int VL_IDADE_MINIMA = Integer.parseInt(newLine.substring(274, 278));
			int VL_IDADE_MAXIMA = Integer.parseInt(newLine.substring(278, 282));
			int VL_SH = Integer.parseInt(newLine.substring(282, 292));
			int VL_SA = Integer.parseInt(newLine.substring(292, 302));
			int VL_SP = Integer.parseInt(newLine.substring(302, 312));
			String CO_FINANCIAMENTO = newLine.substring(312, 314);
			String CO_RUBRICA = newLine.substring(314, 320);
			int QT_TEMPO_PERMANENCIA = Integer.parseInt(newLine.substring(320, 323));
			String DT_COMPETENCIA = newLine.substring(324, 330);

			ProcedureSigtap procedure = new ProcedureSigtap();

			procedure.setCodeprocedure(CO_PROCEDIMENTO);
			procedure.setNumberprocedure(NO_PROCEDIMENTO);
			procedure.setTypecomplexity(TP_COMPLEXIDADE);
			procedure.setTypesex(TP_SEXO);
			procedure.setQtmaximumexecution(QT_MAXIMA_EXECUCAO);
			procedure.setQtstaydays(QT_DIAS_PERMANENCIA);
			procedure.setQtpoints(QT_PONTOS);
			procedure.setVlminimumage(VL_IDADE_MINIMA);
			procedure.setVlmaximumage(VL_IDADE_MAXIMA);
			procedure.setVlSh(VL_SH);
			procedure.setVlSa(VL_SA);
			procedure.setVlSp(VL_SP);
			procedure.setCodefinancing(CO_FINANCIAMENTO);
			procedure.setCoderubric(CO_RUBRICA);
			procedure.setQtlenghtstay(QT_TEMPO_PERMANENCIA);
			procedure.setDatecompetency(DT_COMPETENCIA);

			procedureSigtapRepository.save(procedure);

		}
	}

	/**
	 * Realiza a leitura do Arquivo TXT que contém os instrumentos de Registro da
	 * Sigtap e persiste no Banco de Dados
	 * 
	 * @param fileRegister
	 * @throws IOException
	 */
	public void readTxtAndSaveRegister(String fileRegister) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileRegister), Charset.forName("ISO-8859-1"));

		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String newLine = new String(line.getBytes("UTF-8"));

			String CO_REGISTRO = newLine.substring(0, 2);
			String NO_REGISTRO = newLine.substring(2, 52);
			String DT_COMPETENCIA = newLine.substring(52, 58);

			RegisterSigtap registro = new RegisterSigtap();

			registro.setCoderegister(CO_REGISTRO);
			registro.setNumberregister(NO_REGISTRO);
			registro.setDatetcompetency(DT_COMPETENCIA);

			registerSigtapRepository.save(registro);
		}

	}

	/**
	 * Realiza a leitura do arquivo TXT que contém os dados do Código Internacional
	 * de Doenças CID e persiste no Banco de Dados
	 * 
	 * @param fileCid
	 * @throws IOException
	 */
	public void readAndSaveCid(String fileCid) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileCid), Charset.forName("ISO-8859-1"));

		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String newLine = new String(line.getBytes("UTF-8"));

			String CO_CID = newLine.substring(0, 4);
			String NO_CID = newLine.substring(4, 104);
			char TP_AGRAVO = newLine.charAt(104);
			char TP_SEXO = newLine.charAt(105);
			char TP_ESTADIO = newLine.charAt(106);
			int VL_CAMPOS_IRRADIADOS = Integer.parseInt(newLine.substring(107, 111));

			CidSigtap cid = new CidSigtap();

			cid.setCodecid(CO_CID);
			cid.setNumbercid(NO_CID);
			cid.setTypeAgravo(TP_AGRAVO);
			cid.setTypeSexo(TP_SEXO);
			cid.setTypeEstadio(TP_ESTADIO);
			cid.setValueCamposIrradiados(VL_CAMPOS_IRRADIADOS);

			cidSigtapRepository.save(cid);

		}

	}

	/**
	 * Realiza a Leitura do Arquivo TXT que contém os dados da Ocupação (CBO) e
	 * persiste no Banco de Dados
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void readAndSaveOccupation(String file) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(file), Charset.forName("ISO-8859-1"));

		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String newLine = new String(line.getBytes("UTF-8"));

			String CO_OCUPACAO = newLine.substring(0, 6);
			String NO_OCUPACAO = newLine.substring(6, 156);

			OccupationSigtap occupation = new OccupationSigtap();
			occupation.setCodeoccupation(CO_OCUPACAO);
			occupation.setNumberoccupation(NO_OCUPACAO);

			occupationSigtapRepository.save(occupation);
		}

	}

}
