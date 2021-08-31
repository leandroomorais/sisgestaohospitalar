package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Triagem;
import com.ifrn.sisgestaohospitalar.repository.TriagemRepository;

@Service
public class TriagemService {

	@Autowired
	private TriagemRepository repository;

	public void save(Triagem triagem) {
		repository.saveAndFlush(triagem);
	}

}
