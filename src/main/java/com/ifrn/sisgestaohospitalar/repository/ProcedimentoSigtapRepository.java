package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;

/**
 * A interface <code>ProcedimentoSigtapRepository</code> extende a interface
 * JpaRepository da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface ProcedimentoSigtapRepository extends JpaRepository<ProcedimentoSigtap, Long> {

	/**
	 * @param codigoprocedimento
	 * @return ProcedimentoSigtap
	 */
	public ProcedimentoSigtap findByCodigoprocedimento(String codigoprocedimento);

}
