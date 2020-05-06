package com.ifrn.sisgestaohospitalar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ifrn.sisgestaohospitalar.model.Ciap2;
import com.ifrn.sisgestaohospitalar.repository.Ciap2Repository;

/**
 * A classe <code>Ciap2Service</code> implementa os métodos da Interface
 * Ciap2Repository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
public class Ciap2Service {

	@Autowired
	private Ciap2Repository repository;

	/**
	 * Salva os objetos do tipo Ciap2
	 * 
	 * @param Ciap2
	 */
	public void save(Ciap2 ciap2) {
		repository.saveAndFlush(ciap2);
	}

	/**
	 * Deleta os objetos do tipo RegistroSigtap a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a Lista de objetos do tipo Ciap2
	 * 
	 * @return List<Ciap2>
	 */
	public List<Ciap2> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto do tipo Ciap2 a partir do Id
	 * 
	 * @param id
	 * @return Ciap2
	 */
	public Ciap2 findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * @param keyword
	 * @return List<String>
	 */
	public List<String> search(String keyword) {
		return repository.search(keyword);
	}

}
