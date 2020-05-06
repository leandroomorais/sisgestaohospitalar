package com.ifrn.sisgestaohospitalar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.AtendimentoMedico;

/**
 * A interface <code>AtendimentoMedicoRepository</code> extende a interface
 * JpaRepository da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Repository
public interface AtendimentoMedicoRepository extends JpaRepository<AtendimentoMedico, Long> {

	/**
	 * @param data
	 * @return List<AtendimentoMedico>
	 */
	public List<AtendimentoMedico> findByData(Date data);

}
