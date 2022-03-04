package com.ifrn.sisgestaohospitalar.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.LinhaBPAConsolidado;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoDetalheRepository;

@Service
public class LinhaBPAConsolidadoService {

	@Autowired
	private ArquivoBPAService arquivoBPAService;
	@Autowired
	private ProcedimentoDetalheRepository procedimentoDetalheRepository;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	DateTimeFormatter formaterCompetencia = DateTimeFormatter.ofPattern("yyyyMM");
	DateTimeFormatter formaterYYYYmmAA = DateTimeFormatter.ofPattern("yyyyMMdd");

	public List<?> getLinhasBPAConsolidado(String mes) {
		List<LinhaBPAConsolidado> linhasBpaConsolidado = new ArrayList<>();
		String cnes = getCnes();
		arquivoBPAService.filtraProcedimentos(arquivoBPAService.getAtendimentoProcedimentos(mes));

		arquivoBPAService.getProcedimentosConsolidados().stream()
				.collect(Collectors.groupingBy(AtendimentoProcedimento::getProcedimento))
				.forEach((procedimento, lista) -> {
					if (exigeIdade(procedimento)) {
						lista.stream().collect(Collectors.groupingBy(AtendimentoProcedimento::getIdadeNoAtendimento))
								.forEach((k, v) -> {
									v.stream()
											.collect(Collectors.groupingBy(AtendimentoProcedimento::getCboProfissional))
											.forEach((cbo, list) -> {
												LinhaBPAConsolidado linhaBPAConsolidado = new LinhaBPAConsolidado();
												linhaBPAConsolidado.setLinhaIdenti("02");
												linhaBPAConsolidado.setCnes(cnes);
												linhaBPAConsolidado.setCompetencia("202203"); // Lembrar de alterar
												linhaBPAConsolidado.setCboProfissional(cbo);
												linhaBPAConsolidado.setCodigoProcedimento(
														getCodigoProcedimento(procedimento.getCodigo()));
												linhaBPAConsolidado.setIdade(Integer.toString(k));
												linhaBPAConsolidado
														.setQuantidade(Integer.toString(getQuantidadeProcedimentos(v)));
												linhaBPAConsolidado.setOrigem("BPA");
												linhaBPAConsolidado.setFim(" ");
												linhasBpaConsolidado.add(linhaBPAConsolidado);

											});
								});
					} else {
						lista.stream().collect(Collectors.groupingBy(AtendimentoProcedimento::getCboProfissional))
								.forEach((k, v) -> {
									LinhaBPAConsolidado linhaBPAConsolidado = new LinhaBPAConsolidado();
									linhaBPAConsolidado.setLinhaIdenti("02");
									linhaBPAConsolidado.setCnes(cnes);
									linhaBPAConsolidado.setCompetencia("202203"); // Lembrar de alterar
																					// depois
									linhaBPAConsolidado.setCboProfissional(k);
									linhaBPAConsolidado
											.setCodigoProcedimento(getCodigoProcedimento(procedimento.getCodigo()));
									linhaBPAConsolidado.setIdade("0");
									linhaBPAConsolidado.setQuantidade(Integer.toString(getQuantidadeProcedimentos(v)));
									linhaBPAConsolidado.setOrigem("BPA");
									linhaBPAConsolidado.setFim(" ");
									// linhasBpaConsolidado.add(linhaBPAConsolidado);
								});

					}
				});
		return linhasBpaConsolidado;

	}

	private int getQuantidadeProcedimentos(List<AtendimentoProcedimento> atendimentoProcedimentos) {
		int quantidade = 0;
		quantidade = atendimentoProcedimentos.stream()
				.collect(Collectors.summingInt(AtendimentoProcedimento::getQuantidade));
		return quantidade;
	}

	private String getCnes() {
		return estabelecimentoRepository.findAll().get(0).getCnes();
	}

	private boolean exigeIdade(Procedimento procedimento) {
		if (procedimentoDetalheRepository.exigeIdade(procedimento.getCodigo()) != null) {
			return true;
		}
		return false;
	}

	private String getCodigoProcedimento(Long codigoProcedimento) {
		return "0" + codigoProcedimento.toString();
	}
}
