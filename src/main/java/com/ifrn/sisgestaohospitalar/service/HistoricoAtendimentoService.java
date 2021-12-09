package com.ifrn.sisgestaohospitalar.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.HistoricoAtendimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.TipoServico;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;

@Service
public class HistoricoAtendimentoService {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	public HistoricoAtendimento criaHistoricoAtendimento(String descricao, CondutaCidadao condutaCidadao, Status status,
			List<TipoServico> tipoServicos, Principal principal, Profissional profissionalDestino,
			Profissional profissionalResponsavel) {
		HistoricoAtendimento historicoAtendimento = new HistoricoAtendimento();
		if (principal != null) {
			Profissional profissional = profissionalRepository.findByCpf(principal.getName());
			historicoAtendimento.setProfissional(profissional);
		} else {
			historicoAtendimento.setProfissional(profissionalResponsavel);
		}
		historicoAtendimento.setDescricao(descricao);
		historicoAtendimento.setCondutaCidadao(condutaCidadao);
		historicoAtendimento.setStatus(status);
		historicoAtendimento.setTipoServicos(tipoServicos);
		historicoAtendimento.setLocalDateTime(LocalDateTime.now());
		historicoAtendimento.setProfissionalDestino(profissionalDestino);
		return historicoAtendimento;
	}

}
