package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.EstablishmentComplexity;
import com.ifrn.sisgestaohospitalar.repository.EstablishmentComplexityRepository;

/**
 * Classe que implementa os métodos da Interface
 * EstablishmentComplexityRepository
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Service
public class EstablishmentComplexityService {

	@Autowired
	private EstablishmentComplexityRepository repository;

	/**
	 * Salva a Complexidade do Estabelecimento
	 * @param EstablishmentComplexity
	 */
	public void save(EstablishmentComplexity establishmentComplexity) {
		repository.save(establishmentComplexity);
	}

	/**
	 * Retorna a Complexidade do Estabelecimento a partir do Id
	 * @param id
	 * @return EstablishmentComplexity
	 */
	public EstablishmentComplexity findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna a lista de Complexidade de Estabelecimento
	 * @return List<EstablishmentComplexity>
	 */
	public List<EstablishmentComplexity> listAll() {
		return repository.findAll();
	}

}
