package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.OcupacaoSigtap;

/**
 * A interface <code>OcupacaoSigtapRepository</code> extende a interface JpaRepository
 * da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface OcupacaoSigtapRepository extends JpaRepository<OcupacaoSigtap, Long> {
	
	/**
	 * @param codigoocupacao
	 * @return OcupacaoSigtap
	 */
	public OcupacaoSigtap findByCodigoocupacao(String codigoocupacao);

}
