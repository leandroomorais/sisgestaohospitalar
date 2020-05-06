package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Role;

/**
 * A interface <code>RoleRepository</code> extende a interface JpaRepository da
 * API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * @param nome
	 * @return Role
	 */
	public Role findByNome(String nome);

}
