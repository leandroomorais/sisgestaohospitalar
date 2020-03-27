package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.OccupationSigtap;
import com.ifrn.sisgestaohospitalar.repository.OccupationSigtapRepository;

/**
 * Classe que implementa os Métodos da Interface OccupationSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 *
 */
@Service
public class OccupationSigtapService {

	@Autowired
	private OccupationSigtapRepository repository;

	/**
	 * Salva a Ocupaçao 
	 * @param occupationSigtap
	 */
	public void save(OccupationSigtap occupationSigtap) {
		repository.saveAndFlush(occupationSigtap);
	}

	/**
	 * Retorna a Ocupação a partir do id
	 * @param id
	 * @return OccupatioSigtap
	 */
	public OccupationSigtap findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Deleta a Ocupação a partir do id
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de Ocupações
	 * @return List<OccupationSigtap>
	 */
	public List<OccupationSigtap> listAll() {
		return repository.findAll();
	}

	/**
	 * Retorna a ocupação a partir do seu código
	 * @param codeoccupation
	 * @return OccupationSigtap
	 */
	public OccupationSigtap findByCodeoccupation(String codeoccupation) {
		return repository.findByCodeoccupation(codeoccupation);
	}

}
