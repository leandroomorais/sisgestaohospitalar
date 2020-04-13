package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.RegistroSigtap;
import com.ifrn.sisgestaohospitalar.repository.RegistroSigtapRepository;

/**
 * A classe <code>RegistroSigtapService</code> implementa os métodos da
 * Interface RegistroSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class RegistroSigtapService {

	@Autowired
	private RegistroSigtapRepository repository;

	/**
	 * Salva os objetos do tipo RegistroSigtap
	 * 
	 * @param registroSigtap
	 */
	public void save(RegistroSigtap registroSigtap) {
		repository.saveAndFlush(registroSigtap);
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
	 * Retorna a Lista de objetos do tipo RegistroSigtap
	 * 
	 * @return List<RegistroSigtap>
	 */
	public List<RegistroSigtap> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto do tipo RegistroSigtap a partir do Id
	 * 
	 * @param id
	 * @return RegistroSigtap
	 */
	public RegistroSigtap findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna o objeto do tipo RegistroSigtap a partir do Codigo do Registro
	 * 
	 * @param codigoregistro
	 * @return RegistroSigtap
	 */
	public RegistroSigtap findByCodigoregistro(String codigoregistro) {
		return repository.findByCodigoregistro(codigoregistro);
	}

}
