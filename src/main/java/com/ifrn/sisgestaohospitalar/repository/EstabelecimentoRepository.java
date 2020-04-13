package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;

/**
 * A interface <code>EstabelecimentoRepository</code> extende a interface
 * JpaRepository da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

	/**
	 * @param cnes
	 * @return Estabelecimento
	 */
	public Estabelecimento findByCnes(String cnes);

}
