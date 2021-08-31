package com.ifrn.sisgestaohospitalar.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaAdicionadoNaFilaException;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository repository;

	@Autowired
	private CidadaoRepository cidadaoRepository;

	public void save(Atendimento atendimento) {
		Optional<Cidadao> optional = cidadaoRepository.findByCpf(atendimento.getCidadao().getCpf());
		if (optional.isPresent()) {
			List<Atendimento> atendimentos = repository.findByCidadao(optional.get());
			for (Atendimento atendimentoAux : atendimentos) {
				if (atendimentoAux.getStatus() != Status.FINALIZADO
						&& atendimentoAux.getCondutaTipoServico().equals(atendimento.getCondutaTipoServico())
						&& atendimentoAux.getProfissionalDestino().equals(atendimento.getProfissionalDestino())
						&& atendimentoAux.getResponsavel().equals(atendimento.getResponsavel())) {
					throw new CidadaoJaAdicionadoNaFilaException("O cidadão " + atendimentoAux.getCidadao().getNome()
							+ " já foi adicionado a lista de atendimentos para o serviço: "
							+ atendimentoAux.getCondutaTipoServico().getDescricao().toUpperCase());
				}
			}
			repository.saveAndFlush(atendimento);
		}
	}

}
