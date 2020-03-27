package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.RegisterSigtap;
import com.ifrn.sisgestaohospitalar.repository.RegisterSigtapRepository;

/**
 * Classe que implementa os Métodos da Interface RegisterSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 *
 */

@Service
public class RegisterSigtapService {

	@Autowired
	private RegisterSigtapRepository repository;

	/**
	 * Salva o Registro da Sigtap
	 * @param registerSigtap
	 */
	public void save(RegisterSigtap registerSigtap) {
		repository.save(registerSigtap);
	}

	/**
	 * Retorna o Registro a partir do id
	 * @param id
	 * @return RegisterSigtap
	 */
	public RegisterSigtap findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Deleta o Registro a partir do id
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a Lista dos Registros
	 * @return List<RegisterSigtap>
	 */
	public List<RegisterSigtap> listAll() {
		return repository.findAll();
	}

}
