package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Comorbidade;
import com.ifrn.sisgestaohospitalar.repository.ComorbidadeRepository;
import com.ifrn.sisgestaohospitalar.service.exception.ComorbidadeMesmoNomeJaCadastradoException;

@Service
public class ComorbidadeService {
	
	@Autowired
	private ComorbidadeRepository repository;
	
	public void save(Comorbidade comorbidade) {
		Comorbidade comorbidadeAux = repository.findByNomeIgnoreCaseContaining(comorbidade.getNome());
		if(comorbidadeAux != null) {
			throw new ComorbidadeMesmoNomeJaCadastradoException("Uma mesma doença/comorbidade já foi cadastrada com esse nome");
		}
		repository.saveAndFlush(comorbidade);
	}

}
