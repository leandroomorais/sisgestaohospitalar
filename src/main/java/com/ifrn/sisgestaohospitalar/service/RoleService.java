package com.ifrn.sisgestaohospitalar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Role;
import com.ifrn.sisgestaohospitalar.repository.RoleRepository;

/**
 * A classe <code>RoleService</code> implementa os métodos da Interface
 * ProfissionalRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	/**
	 * Retorna a lista com todos os objetos do tipo Role
	 * 
	 * @return
	 */
	public List<Role> findAll() {
		return repository.findAll();
	}

	/**
	 * Salva os objetos do tipo Role
	 * 
	 * @param role
	 * @return
	 */
	public Role save(Role role) {
		return repository.saveAndFlush(role);
	}

	/**
	 * Retorna os objetos do tipo Role a partir do Nome
	 * 
	 * @param nome
	 * @return
	 */
	public Role findByNome(String nome) {
		return repository.findByNome(nome);
	}

}
