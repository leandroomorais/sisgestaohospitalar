package com.ifrn.sisgestaohospitalar.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.FolhaBPAConsolidado;
import com.ifrn.sisgestaohospitalar.model.FolhaBPAIndividualizado;
import com.ifrn.sisgestaohospitalar.model.LinhaBPAConsolidado;
import com.ifrn.sisgestaohospitalar.model.LinhaBPAIndividualizado;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoRegistroSigtap;
import com.ifrn.sisgestaohospitalar.repository.ArquivoBPARepository;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRegistroSigtapRepository;

/**
 * A classe <code>ArquivoBPAService</code> implementa os métodos da Interface
 * ArquivoBPARepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class ArquivoBPAService {

	@Autowired
	private ArquivoBPARepository repository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private ProcedimentoRegistroSigtapRepository registroSigtapRepository;

	@Autowired
	private LinhaBPAIndividualizadoService linhaBpaIndividualizadoService;

	@Autowired
	private LinhaBPAConsolidadoService linhaBPAConsolidadoService;

	private List<AtendimentoProcedimento> procedimentosConsolidados = new ArrayList<>();

	private List<AtendimentoProcedimento> procedimentosIndividualizados = new ArrayList<>();

	public List<AtendimentoProcedimento> getAtendimentoProcedimentos(String ano, String mes) {
		List<AtendimentoProcedimento> atendimentoProcedimentos = new ArrayList<>();
		atendimentoProcedimentos.clear();
		List<Atendimento> atendimentos = atendimentoRepository.findByMes(ano, mes);
		if (atendimentos != null) {
			for (Atendimento atendimento : atendimentos) {
				atendimentoProcedimentos.addAll(atendimento.getAtendimentoProcedimentos());
			}
			return atendimentoProcedimentos;
		}
		return null;
	}

	public void filtraProcedimentos(List<AtendimentoProcedimento> atendimentoProcedimentos) {
		if (!atendimentoProcedimentos.isEmpty()) {
			// Iterando sobre lista de procedimentos do mês
			for (AtendimentoProcedimento atendimentoProcedimento : atendimentoProcedimentos) {
				Long codigoProcedimento = atendimentoProcedimento.getProcedimento().getCodigo();
				// Separando os procedimentos que são individualizados e consolidados
				// simultaneamente
				if (individualizadoEConsolidado(codigoProcedimento)) {
					setProcedimentosConsolidados(atendimentoProcedimento);
				}
				// Separando os procedimentos que são consolidados "exclusivamente"
				if (consolidado(codigoProcedimento)) {
					setProcedimentosConsolidados(atendimentoProcedimento);
				}
				// Separando os procedimentos que são individualizados "exclusivamente"
				if (individualizado(codigoProcedimento)) {
					setProcedimentosIndividualizados(atendimentoProcedimento);
				}
			}
		}
	}

	private void setProcedimentosConsolidados(AtendimentoProcedimento atendimentoProcedimento) {
		procedimentosConsolidados.add(atendimentoProcedimento);
	}

	private void setProcedimentosIndividualizados(AtendimentoProcedimento atendimentoProcedimento) {
		procedimentosIndividualizados.add(atendimentoProcedimento);
	}

	public void clearProcedimentosConsolidados() {
		procedimentosConsolidados.clear();
	}

	public void clearProcedimentosIndividualizados() {
		procedimentosIndividualizados.clear();
	}

	public List<AtendimentoProcedimento> getProcedimentosConsolidados() {
		return procedimentosConsolidados;
	}

	public List<AtendimentoProcedimento> getProcedimentosIndividualizados() {
		return procedimentosIndividualizados;
	}

	private boolean individualizadoEConsolidado(Long codigoProcedimento) {
		List<ProcedimentoRegistroSigtap> procedimentoRegistroSigtaps = registroSigtapRepository
				.buscaTipoRegistroBPA(codigoProcedimento);
		if (procedimentoRegistroSigtaps.size() == 2) {
			return true;
		}
		return false;
	}

	private boolean consolidado(Long codigoProcedimento) {
		List<ProcedimentoRegistroSigtap> procedimentoRegistroSigtaps = registroSigtapRepository
				.buscaTipoRegistroBPA(codigoProcedimento);
		if (procedimentoRegistroSigtaps.size() == 1) {
			if (procedimentoRegistroSigtaps.iterator().next().getCodigoRegistro().equals("01")) {
				return true;
			}
		}
		return false;
	}

	private boolean individualizado(Long codigoProcedimento) {
		List<ProcedimentoRegistroSigtap> procedimentoRegistroSigtaps = registroSigtapRepository
				.buscaTipoRegistroBPA(codigoProcedimento);
		if (procedimentoRegistroSigtaps.size() == 1) {
			if (procedimentoRegistroSigtaps.iterator().next().getCodigoRegistro().equals("02")) {
				return true;
			}
		}
		return false;
	}

	public void processarArquivoBPA(String ano, String mes) {

		ArquivoBPA arquivoBPA = new ArquivoBPA();
		clearProcedimentosIndividualizados();
		clearProcedimentosConsolidados();

		filtraProcedimentos(getAtendimentoProcedimentos(ano, mes));

		FolhaBPAIndividualizado folhaBPAIndividualizado = new FolhaBPAIndividualizado();
		FolhaBPAConsolidado folhaBPAConsolidado = new FolhaBPAConsolidado();

		folhaBPAIndividualizado.setLinhasBPAIndividualizado(new ArrayList<>());
		folhaBPAConsolidado.setLinhasBPAConsolidado(new ArrayList<>());

		int numeroFolhaConsolidado = 1;
		int numeroLinhaConsolidado = 0;

		int numeroFolhaIndividualizado = 1;
		int numeroLinhaIndividualizado = 0;

		for (LinhaBPAConsolidado linhaBPAConsolidado : linhaBPAConsolidadoService
				.getLinhasBPAConsolidado(getProcedimentosConsolidados())) {
			numeroLinhaConsolidado++;
			folhaBPAConsolidado.setNumero(numeroFolhaConsolidado);
			linhaBPAConsolidado.setNumeroFolha(Integer.toString(numeroLinhaIndividualizado));
			linhaBPAConsolidado.setNumeroLinha(Integer.toString(numeroLinhaConsolidado));
			folhaBPAConsolidado.getLinhasBPAConsolidado().add(linhaBPAConsolidado);
			if (numeroLinhaConsolidado == 20) {
				numeroFolhaConsolidado++;
				numeroLinhaConsolidado = 0;
				folhaBPAConsolidado = new FolhaBPAConsolidado();
			}
			arquivoBPA.setFolhasBPAConsolidado(new ArrayList<>());
			arquivoBPA.getFolhasBPAConsolidado().add(folhaBPAConsolidado);
		}

		for (LinhaBPAIndividualizado linhaBPAIndividualizado : linhaBpaIndividualizadoService
				.getLinhasBPAIndividualizado(getProcedimentosIndividualizados())) {
			numeroLinhaIndividualizado++;
			folhaBPAIndividualizado.setNumero(numeroFolhaIndividualizado);
			linhaBPAIndividualizado.setNumeroFolha(Integer.toString(numeroFolhaIndividualizado));
			linhaBPAIndividualizado.setNumeroLinha(Integer.toString(numeroLinhaIndividualizado));
			folhaBPAIndividualizado.getLinhasBPAIndividualizado().add(linhaBPAIndividualizado);
			if (numeroLinhaIndividualizado == 20) {
				numeroFolhaIndividualizado++;
				numeroLinhaIndividualizado = 0;
				folhaBPAIndividualizado = new FolhaBPAIndividualizado();
			}
			arquivoBPA.setFolhasBPAIndividualizado(new ArrayList<>());
			arquivoBPA.getFolhasBPAIndividualizado().add(folhaBPAIndividualizado);
		}

		arquivoBPA.setQtdFolhas(Integer.toString(
				arquivoBPA.getFolhasBPAIndividualizado().size() + arquivoBPA.getFolhasBPAConsolidado().size()));

		save(arquivoBPA);
	}

	public List<LinhaBPAIndividualizado> retornoIndividualizado(String ano, String mes) {
		clearProcedimentosIndividualizados();
		filtraProcedimentos(getAtendimentoProcedimentos(ano, mes));
		return linhaBpaIndividualizadoService.getLinhasBPAIndividualizado(getProcedimentosIndividualizados());
	}

	public List<LinhaBPAConsolidado> retornoConsolidado(String ano, String mes) {
		clearProcedimentosConsolidados();
		filtraProcedimentos(getAtendimentoProcedimentos(ano, mes));
		return linhaBPAConsolidadoService.getLinhasBPAConsolidado(getProcedimentosConsolidados());
	}

	public void save(ArquivoBPA arquivoBPA) {
		repository.saveAndFlush(arquivoBPA);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<ArquivoBPA> findAll() {
		return repository.findAll();
	}

	public ArquivoBPA findOne(Long id) {
		return repository.getOne(id);
	}

	public ArquivoBPA findByCompetencia(String competencia) {
		return repository.findByCompetencia(competencia);
	}

}
