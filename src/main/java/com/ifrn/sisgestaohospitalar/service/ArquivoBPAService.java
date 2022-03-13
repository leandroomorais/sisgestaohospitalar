package com.ifrn.sisgestaohospitalar.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.ifrn.sisgestaohospitalar.model.OrgaoResponsavel;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoRegistroSigtap;
import com.ifrn.sisgestaohospitalar.repository.ArquivoBPARepository;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.OrgaoResponsavelRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRegistroSigtapRepository;
import com.ifrn.sisgestaohospitalar.utils.EscritorTXT;

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
	private OrgaoResponsavelRepository orgaoResponsavelRepository;

	@Autowired
	private LinhaBPAIndividualizadoService linhaBpaIndividualizadoService;

	@Autowired
	private LinhaBPAConsolidadoService linhaBPAConsolidadoService;

	private List<AtendimentoProcedimento> procedimentosConsolidados = new ArrayList<>();

	private List<AtendimentoProcedimento> procedimentosIndividualizados = new ArrayList<>();

	EscritorTXT escritorTXT = new EscritorTXT();

	public ArquivoBPA processarArquivoBPA(int ano, int mes) {
		clearProcedimentosConsolidados();
		clearProcedimentosIndividualizados();
		List<AtendimentoProcedimento> atendimentosDoPeriodo = getAtendimentoProcedimentos(ano, mes);
		if (atendimentosDoPeriodo == null || atendimentosDoPeriodo.isEmpty()) {
			return null;
		} else {
			filtraProcedimentos(atendimentosDoPeriodo);
			ArquivoBPA arquivoBPA = new ArquivoBPA();
			arquivoBPA.setFolhasBPAConsolidado(new ArrayList<FolhaBPAConsolidado>());
			arquivoBPA.setFolhasBPAIndividualizado(new ArrayList<FolhaBPAIndividualizado>());
			FolhaBPAConsolidado folhaBPAConsolidado = new FolhaBPAConsolidado();
			FolhaBPAIndividualizado folhaBPAIndividualizado = new FolhaBPAIndividualizado();
			List<LinhaBPAConsolidado> linhasBPAConsolidados = new ArrayList<>();
			List<LinhaBPAIndividualizado> linhasBPAIndividualizado = new ArrayList<>();
			int numerofolhaBpaConsolidado = 1;
			int numerolinhaBpaConsolidado = 0;
			int numeroFolhaBpaIndividualizado = 1;
			int numeroLinhaBpaIndividualizado = 0;
			String cnes = null;

			for (LinhaBPAConsolidado linha : linhaBPAConsolidadoService
					.getLinhasBPAConsolidado(getProcedimentosConsolidados())) {
				numerolinhaBpaConsolidado++;
				folhaBPAConsolidado.setNumero(numerofolhaBpaConsolidado);
				linha.setNumeroFolha(Integer.toString(numerofolhaBpaConsolidado));
				linha.setNumeroLinha(Integer.toString(numerolinhaBpaConsolidado));
				cnes = linha.getCnes();
				linhasBPAConsolidados.add(linha);
				if (numerolinhaBpaConsolidado == 20) {
					numerofolhaBpaConsolidado++;
					numerolinhaBpaConsolidado = 0;
					folhaBPAConsolidado = new FolhaBPAConsolidado();
				}
			}

			folhaBPAConsolidado.setLinhasBPAConsolidado(linhasBPAConsolidados);
			arquivoBPA.getFolhasBPAConsolidado().add(folhaBPAConsolidado);

			for (LinhaBPAIndividualizado linha : linhaBpaIndividualizadoService
					.getLinhasBPAIndividualizado(getProcedimentosIndividualizados())) {
				numeroLinhaBpaIndividualizado++;
				folhaBPAIndividualizado.setNumero(numeroFolhaBpaIndividualizado);
				linha.setNumeroFolha(Integer.toString(numeroFolhaBpaIndividualizado));
				linha.setNumeroLinha(Integer.toString(numeroLinhaBpaIndividualizado));
				linhasBPAIndividualizado.add(linha);
				if (numeroLinhaBpaIndividualizado == 20) {
					numeroFolhaBpaIndividualizado++;
					numeroLinhaBpaIndividualizado = 0;
					folhaBPAIndividualizado = new FolhaBPAIndividualizado();
				}
			}

			folhaBPAIndividualizado.setLinhasBPAIndividualizado(linhasBPAIndividualizado);
			arquivoBPA.getFolhasBPAIndividualizado().add(folhaBPAIndividualizado);

			OrgaoResponsavel orgaoResponsavel = getOrgaoResponsavel();

			arquivoBPA.setCabecalhoHeader("01");
			arquivoBPA.setIndicadorHeader("#BPA#");
			arquivoBPA.setCompetencia(getCompetencia(ano, mes));
			arquivoBPA.setQtdLinhas(Integer.toString(
					getNumeroLinhas(arquivoBPA.getFolhasBPAIndividualizado(), arquivoBPA.getFolhasBPAConsolidado())));
			arquivoBPA.setQtdFolhas(Integer.toString(
					arquivoBPA.getFolhasBPAConsolidado().size() + arquivoBPA.getFolhasBPAIndividualizado().size()));
			arquivoBPA.setControleDominio(
					getCampoControle(arquivoBPA.getFolhasBPAIndividualizado(), arquivoBPA.getFolhasBPAConsolidado()));
			arquivoBPA.setOrgaoResponsavel(orgaoResponsavel.getNomeOrgao());
			arquivoBPA.setSiglaOrgaoResponsavel(orgaoResponsavel.getSigla());
			arquivoBPA.setCnpjOrgaoResponsavel(orgaoResponsavel.getCnpj());
			arquivoBPA.setOrgaoDestino(orgaoResponsavel.getNomeOrgao());
			arquivoBPA.setIncadorOrgao(orgaoResponsavel.getIndicador());
			arquivoBPA.setVersaoSistema("SGH-1.0");
			arquivoBPA.setFim("");
			arquivoBPA.setHoraGeracao(LocalDateTime.now());
			arquivoBPA.setDataGeracao(LocalDate.now());
			arquivoBPA.setGerado(true);
			arquivoBPA.setCnes(cnes);
			arquivoBPA.setValorTotal(getValorTotal(arquivoBPA));
			return arquivoBPA;
		}
	}

	public List<AtendimentoProcedimento> getAtendimentoProcedimentos(int ano, int mes) {
		List<AtendimentoProcedimento> atendimentoProcedimentos = new ArrayList<>();
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
		if (!atendimentoProcedimentos.isEmpty() || atendimentoProcedimentos != null) {
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

	private String getCompetencia(int ano, int mes) {
		return Integer.toString(ano) + String.format("%02d", new Object[] { mes });
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

	private BigDecimal getValorTotal(ArquivoBPA arquivoBPA) {
		List<LinhaBPAConsolidado> linhaBPAConsolidados = new ArrayList<>();
		List<LinhaBPAIndividualizado> linhaBPAIndividualizados = new ArrayList<>();
		arquivoBPA.getFolhasBPAConsolidado().forEach(f -> {
			f.getLinhasBPAConsolidado().forEach(l -> {
				linhaBPAConsolidados.add(l);
			});
		});
		arquivoBPA.getFolhasBPAIndividualizado().forEach(f -> {
			f.getLinhasBPAIndividualizado().forEach(l -> {
				linhaBPAIndividualizados.add(l);
			});
		});

		return linhaBPAConsolidados.stream().map(LinhaBPAConsolidado::getValor).reduce(BigDecimal.ZERO, BigDecimal::add)
				.add(linhaBPAIndividualizados.stream().map(LinhaBPAIndividualizado::getValor).reduce(BigDecimal.ZERO,
						BigDecimal::add));
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

	private int getNumeroLinhas(List<FolhaBPAIndividualizado> folhasBPAIndividualizado,
			List<FolhaBPAConsolidado> folhasBPAConsolidado) {
		int qtdLinhas = 0;
		for (FolhaBPAIndividualizado folhaBPAIndividualizado : folhasBPAIndividualizado) {
			qtdLinhas += folhaBPAIndividualizado.getLinhasBPAIndividualizado().size();
		}
		for (FolhaBPAConsolidado folhaBPAConsolidado : folhasBPAConsolidado) {
			qtdLinhas += folhaBPAConsolidado.getLinhasBPAConsolidado().size();
		}
		return qtdLinhas + 1;
	}

	private OrgaoResponsavel getOrgaoResponsavel() {
		return orgaoResponsavelRepository.findAll().get(0);
	}

	private String getCampoControle(List<FolhaBPAIndividualizado> folhasBPAIndividualizado,
			List<FolhaBPAConsolidado> folhasBPAConsolidado) {
		Long soma = 0L;
		for (FolhaBPAIndividualizado folhaBpaIndividualizado : folhasBPAIndividualizado) {
			for (LinhaBPAIndividualizado linhaBPAIndividualizado : folhaBpaIndividualizado
					.getLinhasBPAIndividualizado()) {
				soma += Long.parseLong(linhaBPAIndividualizado.getQtdProcedimento());
				soma += Long.parseLong(linhaBPAIndividualizado.getCodigoProcedimento());
			}
		}
		for (FolhaBPAConsolidado folhaBPAConsolidado : folhasBPAConsolidado) {
			for (LinhaBPAConsolidado linhaBPAConsolidado : folhaBPAConsolidado.getLinhasBPAConsolidado()) {
				soma += Long.parseLong(linhaBPAConsolidado.getCodigoProcedimento());
				soma += Long.parseLong(linhaBPAConsolidado.getQuantidade());
			}
		}
		Long resto = soma % 1111;
		return Long.toString(Long.sum(resto, 1111));
	}

	public List<LinhaBPAIndividualizado> retornoIndividualizado(int ano, int mes) {
		clearProcedimentosIndividualizados();
		filtraProcedimentos(getAtendimentoProcedimentos(ano, mes));
		return linhaBpaIndividualizadoService.getLinhasBPAIndividualizado(getProcedimentosIndividualizados());
	}

	public List<LinhaBPAConsolidado> retornoConsolidado(int ano, int mes) {
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
