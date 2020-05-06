package com.ifrn.sisgestaohospitalar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Triagem;

/**
 * A interface <code>TriagemRepository</code> extende a interface JpaRepository
 * da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Repository
public interface TriagemRepository extends JpaRepository<Triagem, Long> {

	/**
	 * @param classificacaoderisco
	 * @return List<Triagem>
	 */
	public List<Triagem> findByClassificacaoderisco(String classificacaoderisco);
	
	/**
	 * @param data
	 * @return
	 */
	public List<Triagem> findByData(Date data);

}
