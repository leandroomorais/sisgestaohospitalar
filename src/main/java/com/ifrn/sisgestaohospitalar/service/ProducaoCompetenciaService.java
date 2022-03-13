package com.ifrn.sisgestaohospitalar.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.dto.InfoProducaoDTO;
import com.ifrn.sisgestaohospitalar.dto.ProducaoCompetenciaProvisorioDTO;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;

@Service
public class ProducaoCompetenciaService {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	public ProducaoCompetenciaProvisorioDTO getProducaoProvisoria() {
		LocalDateTime competenciaAtual = LocalDateTime.now();
		List<Atendimento> atendimentos = atendimentoRepository.findByMes(competenciaAtual.getYear(),
				competenciaAtual.getMonthValue());
		if (atendimentos != null) {
			List<AtendimentoProcedimento> atendimentosProcedimento = new ArrayList<>();
			ProducaoCompetenciaProvisorioDTO producaoCompetenciaProvisorioDTO = new ProducaoCompetenciaProvisorioDTO();
			producaoCompetenciaProvisorioDTO.setInfoProducaoDTOs(new ArrayList<>());
			atendimentos.stream().forEach(a -> {
				a.getAtendimentoProcedimentos().stream().forEach(ap -> {
					atendimentosProcedimento.add(ap);
				});
			});

			atendimentosProcedimento.stream().collect(Collectors.groupingBy(AtendimentoProcedimento::getProcedimento))
					.forEach((procedimento, lista) -> {
						InfoProducaoDTO infoProducaoDTO = new InfoProducaoDTO();
						infoProducaoDTO.setNomeProcedimento(procedimento.getNome());
						infoProducaoDTO.setCodigo(Long.toString(procedimento.getCodigo()));
						int qtd = lista.stream().collect(Collectors.summingInt(AtendimentoProcedimento::getQuantidade));
						infoProducaoDTO.setQtd(qtd);
						infoProducaoDTO.setValorUnitario(procedimento.getVlsa());
						BigDecimal qtdBig = new BigDecimal(qtd);
						infoProducaoDTO.setValor(procedimento.getVlsa().multiply(qtdBig));
						producaoCompetenciaProvisorioDTO.getInfoProducaoDTOs().add(infoProducaoDTO);
					});

			producaoCompetenciaProvisorioDTO.setValorTotal(producaoCompetenciaProvisorioDTO.getInfoProducaoDTOs()
					.stream().map(InfoProducaoDTO::getValor).reduce(BigDecimal.ZERO, BigDecimal::add));

			return producaoCompetenciaProvisorioDTO;
		}
		return null;
	}

}
