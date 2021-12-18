package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoOcupacao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoOcupacaoRepository;

@Service
public class ProcedimentoOcupacapService {

	@Autowired
	private ProcedimentoOcupacaoRepository procedimentoOcupacaoRepository;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	public boolean verificaCboProcedimento(AtendimentoProcedimento atendimentoProcedimento, Profissional profissional) {
		ProcedimentoOcupacao procedimentoOcupacao = procedimentoOcupacaoRepository
				.verificaCBO(atendimentoProcedimento.getProcedimento().getCodigo(), getOcupacao(profissional));
		if (procedimentoOcupacao == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getOcupacao(Profissional profissional) {
		for (Lotacao lotacao : profissional.getLotacoes()) {
			for (Estabelecimento estabelecimento : estabelecimentoRepository.findAll()) {
				if (estabelecimento.getCnes().equals(lotacao.getCnes())) {
					return lotacao.getCodigoCBO();
				}
			}

		}
		return null;
	}

}
