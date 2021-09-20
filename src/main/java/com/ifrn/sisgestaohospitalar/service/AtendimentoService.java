package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository repository;

	public void save(Atendimento atendimento) {
		repository.saveAndFlush(atendimento);
	}

}
