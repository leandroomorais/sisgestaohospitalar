package com.ifrn.sisgestaohospitalar.service;

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
import com.ifrn.sisgestaohospitalar.utils.Datas;

@Service
public class LinhaBPAConsolidadoService {

	@Autowired
	private ProcedimentoDetalheRepository procedimentoDetalheRepository;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	Datas datas = new Datas();

	public List<LinhaBPAConsolidado> getLinhasBPAConsolidado(List<AtendimentoProcedimento> procedimentosConsolidados) {
		if (procedimentosConsolidados.isEmpty()) {
			return new ArrayList<LinhaBPAConsolidado>();
		} else {
			List<LinhaBPAConsolidado> linhasBpaConsolidado = new ArrayList<>();
			String CNES = getCnes();
			procedimentosConsolidados.stream().collect(Collectors.groupingBy(AtendimentoProcedimento::getProcedimento))
					.forEach((procedimento, lista) -> {
						if (exigeIdade(procedimento)) {
							lista.stream()
									.collect(Collectors.groupingBy(AtendimentoProcedimento::getIdadeNoAtendimento))
									.forEach((k, v) -> {
										v.stream()
												.collect(Collectors
														.groupingBy(AtendimentoProcedimento::getCboProfissional))
												.forEach((cbo, list) -> {
													String COMPETENCIA = list.get(0).getAtendimento().getDataEntrada()
															.format(datas.competencia());
													LinhaBPAConsolidado linhaBPAConsolidado = new LinhaBPAConsolidado();
													linhaBPAConsolidado.setLinhaIdenti("02");
													linhaBPAConsolidado.setCnes(CNES);
													linhaBPAConsolidado.setCompetencia(COMPETENCIA);
													linhaBPAConsolidado.setCboProfissional(cbo);
													linhaBPAConsolidado.setCodigoProcedimento(
															getCodigoProcedimento(procedimento.getCodigo()));
													linhaBPAConsolidado.setIdade(Integer.toString(k));
													linhaBPAConsolidado.setQuantidade(
															Integer.toString(getQuantidadeProcedimentos(list)));
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
										linhaBPAConsolidado.setCnes(CNES);
										linhaBPAConsolidado.setCompetencia("202203"); // Lembrar de alterar
										linhaBPAConsolidado.setCboProfissional(k);
										linhaBPAConsolidado
												.setCodigoProcedimento(getCodigoProcedimento(procedimento.getCodigo()));
										linhaBPAConsolidado.setIdade("0");
										linhaBPAConsolidado
												.setQuantidade(Integer.toString(getQuantidadeProcedimentos(v)));
										linhaBPAConsolidado.setOrigem("BPA");
										linhaBPAConsolidado.setFim(" ");
										linhasBpaConsolidado.add(linhaBPAConsolidado);
									});

						}
					});
			return linhasBpaConsolidado;
		}

	}

	private int getQuantidadeProcedimentos(List<AtendimentoProcedimento> atendimentoProcedimentos) {
		return atendimentoProcedimentos.stream().collect(Collectors.summingInt(AtendimentoProcedimento::getQuantidade));
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
