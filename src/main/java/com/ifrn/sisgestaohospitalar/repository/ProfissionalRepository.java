package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Profissional;

/**
 * A interface <code>CidadaoRepository</code> extende a interface JpaRepository
 * da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	/**
	 * @param cpf
	 * @return Profissional
	 */
	public Profissional findByCpf(String cpf);

	/**
	 * @param cns
	 * @return Profissional
	 */
	public Profissional findByCns(String cns);

}
