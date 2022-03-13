package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository repository;

	public void save(Atendimento atendimento) {
		repository.saveAndFlush(atendimento);
	}

	Page<Atendimento> orderByRisco(Pageable pageable) {
		List<Atendimento> atendimentos = repository.findAll(pageable).getContent();
		for (Atendimento atendimento : atendimentos) {
			if (atendimento.getStatus().equals(Status.FINALIZADO)) {
				atendimentos.remove(0);
			}
		}
		Page<Atendimento> page = new PageImpl<>(atendimentos);
		return page;
	}

}
