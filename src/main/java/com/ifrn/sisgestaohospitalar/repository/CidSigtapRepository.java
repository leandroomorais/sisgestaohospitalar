package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.CidSigtap;

/**
 * A interface <code>CidSigtapRepository</code> extende a interface JpaRepository
 * da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface CidSigtapRepository extends JpaRepository<CidSigtap, Long> {
	
	/**
	 * @param codigocid
	 * @return CidSigtap
	 */
	public CidSigtap findByCodigocid(String codigocid);

}
