package com.ifrn.sisgestaohospitalar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.repository.ArquivoBPARepository;

/**
 * A classe <code>ArquivoBPAService</code> implementa os métodos
 * da Interface ArquivoBPARepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class ArquivoBPAService {

	@Autowired
	private ArquivoBPARepository repository;

	public void save(ArquivoBPA arquivoBPA) {
		repository.saveAndFlush(arquivoBPA);
	}


	public void delete(Long id) {
		repository.deleteById(id);
	}


	public List<ArquivoBPA> findAll() {
		return repository.findAll();
	}

	public ArquivoBPA findOne(Long id) {
		return repository.getOne(id);
	}
	
	public ArquivoBPA findByCompetencia(String competencia) {
		return repository.findByCompetencia(competencia);
	}

}
