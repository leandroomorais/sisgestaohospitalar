package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.CidSigtap;
import com.ifrn.sisgestaohospitalar.repository.CidSigtapRepository;

/**
 * A classe <code>CidSigtapService</code> implementa os métodos da Interface
 * CidSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class CidSigtapService {

	@Autowired
	private CidSigtapRepository repository;

	/**
	 * Salva os objetos do tipo CidSigtap
	 * 
	 * @param cidSigtap
	 */
	public void save(CidSigtap cidSigtap) {
		repository.saveAndFlush(cidSigtap);
	}

	/**
	 * Deleta os objetos do tipo CidSigtap a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de objetos do tipo CidSigtap
	 * 
	 * @return List<CidSigtap>
	 */
	public List<CidSigtap> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto CidSigtap a partir do Id
	 * 
	 * @param id
	 * @return CidSigtap
	 */
	public CidSigtap findOne(Long id) {
		return repository.getOne(id);
	}
	
	

}
