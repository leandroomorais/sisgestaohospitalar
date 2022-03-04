package com.ifrn.sisgestaohospitalar.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.FolhaBPAIndividualizado;
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

	private List<AtendimentoProcedimento> procedimentosConsolidados = new ArrayList<>();

	private List<AtendimentoProcedimento> procedimentosIndividualizados = new ArrayList<>();

	public List<AtendimentoProcedimento> getAtendimentoProcedimentos(String mes) {
		List<AtendimentoProcedimento> atendimentoProcedimentos = new ArrayList<>();
		atendimentoProcedimentos.clear();
		List<Atendimento> atendimentos = atendimentoRepository.findByMes(mes);
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

	public void processarArquivoBPA(String mes) {
		ArquivoBPA arquivoBPA = new ArquivoBPA();
		FolhaBPAIndividualizado folhaBPAIndividualizado = new FolhaBPAIndividualizado();
		folhaBPAIndividualizado.setLinhasBPAIndividualizado(new ArrayList<>());
		int numeroFolha = 1;
		int numeroLinha = 0;
		for (LinhaBPAIndividualizado linhaBPAIndividualizado : linhaBpaIndividualizadoService
				.getLinhasBPAIndividualizado(mes)) {
			numeroLinha++;
			folhaBPAIndividualizado.setNumero(numeroFolha);
			linhaBPAIndividualizado.setNumeroFolha(Integer.toString(numeroFolha));
			linhaBPAIndividualizado.setNumeroLinha(Integer.toString(numeroLinha));
			folhaBPAIndividualizado.getLinhasBPAIndividualizado().add(linhaBPAIndividualizado);
			if (numeroLinha == 20) {
				numeroFolha++;
				numeroLinha = 0;
				folhaBPAIndividualizado = new FolhaBPAIndividualizado();
			}
			arquivoBPA.setFolhasBPAIndividualizado(new ArrayList<>());
			arquivoBPA.getFolhasBPAIndividualizado().add(folhaBPAIndividualizado);
		}
		save(arquivoBPA);
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
