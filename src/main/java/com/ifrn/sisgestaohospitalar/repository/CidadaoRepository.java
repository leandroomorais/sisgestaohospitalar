package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Cidadao;

/**
 * A interface <code>CidadaoRepository</code> extende a interface JpaRepository
 * da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {

	/**
	 * @param cns
	 * @return Cidadao
	 */
	public Cidadao findByCns(String cns);

	/**
	 * @param cpf
	 * @return Cidadao
	 */
	public Cidadao findByCpf(String cpf);

}
