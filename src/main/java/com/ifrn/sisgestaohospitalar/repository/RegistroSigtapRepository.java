package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.RegistroSigtap;

/**
 * A interface <code>RegistroSigtapRepository</code> extende a interface
 * JpaRepository da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface RegistroSigtapRepository extends JpaRepository<RegistroSigtap, String> {

	/**
	 * @param codigoregistro
	 * @return RegistroSigtap
	 */
	public RegistroSigtap findByCodigoregistro(String codigoregistro);

}
