package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Establishment;
import com.ifrn.sisgestaohospitalar.repository.EstablishmentRepository;

/**
 * Classe que implementa os métodos da Interface EstablishmentRepository
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Service
public class EstablishmentService {

	@Autowired
	private EstablishmentRepository repository;

	/**
	 * Salva o Estabelecimento
	 * @param Establishment
	 */
	public void save(Establishment establishment) {
		repository.saveAndFlush(establishment);
	}

	/**
	 * Retorna o Estabelecimento a partir do Id
	 * @param id
	 * @return Establishment
	 */
	public Establishment findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Deleta o Estabelecimento a partir do Id
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de Estabelecimentos
	 * @return List<Establishment>
	 */
	public List<Establishment> listAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o Estabelecimento a partir do Cnes
	 * @param cnes
	 * @return Establishment
	 */
	public Establishment findByCnes(String cnes) {
		return repository.findByCnes(cnes);
	}

}
