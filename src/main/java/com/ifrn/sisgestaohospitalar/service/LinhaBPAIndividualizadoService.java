package com.ifrn.sisgestaohospitalar.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
import com.ifrn.sisgestaohospitalar.model.LinhaBPAIndividualizado;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;

@Service
public class LinhaBPAIndividualizadoService {

	@Autowired
	private ArquivoBPAService arquivoBPAService;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	DateTimeFormatter formaterCompetencia = DateTimeFormatter.ofPattern("yyyyMM");
	DateTimeFormatter formaterDataAtendimento = DateTimeFormatter.ofPattern("yyyyMMdd");

	public List<LinhaBPAIndividualizado> geraBPAIndividualizado(String mes) {
		List<LinhaBPAIndividualizado> linhasBPAIndividualizado = new ArrayList<>();
		arquivoBPAService.filtraProcedimentos(arquivoBPAService.getAtendimentoProcedimentos(mes));
		for (AtendimentoProcedimento atendimentoProcedimento : arquivoBPAService.getProcedimentosIndividualizados()) {
			LinhaBPAIndividualizado linhaBPAIndividualizado = new LinhaBPAIndividualizado();
			linhaBPAIndividualizado.setLinhaIdenti("3");
			linhaBPAIndividualizado.setCnes(getCnes());
			linhaBPAIndividualizado.setCompetencia(
					atendimentoProcedimento.getAtendimento().getDataEntrada().format(formaterCompetencia));
			linhaBPAIndividualizado.setCnsProfissional(atendimentoProcedimento.getProfissional().getCns());
			linhaBPAIndividualizado.setCboProfissional(getCboProfissional(atendimentoProcedimento.getProfissional()));
			linhaBPAIndividualizado.setDataAtendimento(
					atendimentoProcedimento.getAtendimento().getDataEntrada().format(formaterDataAtendimento));
			

		}
		return linhasBPAIndividualizado;
	}

	private String getCnes() {
		return estabelecimentoRepository.findAll().get(0).getCnes();
	}

	private String getCboProfissional(Profissional profissional) {
		String cboProfissional = null;
		for (Lotacao lotacao : profissional.getLotacoes()) {
			if (lotacao.getCnes().equals(getCnes())) {
				cboProfissional = lotacao.getCodigoCBO();
			}
		}
		return cboProfissional;
	}
}
