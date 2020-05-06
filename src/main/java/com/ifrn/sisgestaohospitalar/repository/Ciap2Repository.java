package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Ciap2;

/**
 * A interface <code>Ciap2Repository</code> extende a interface JpaRepository da
 * API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface Ciap2Repository extends JpaRepository<Ciap2, Long> {

	public List<String> findByTitulo(String titulo);

	/**
	 * Retorna a lista com os titulos da Ciap2
	 * 
	 * @param keyword
	 * @return List<String>
	 */
	@Query("SELECT titulo FROM Ciap2 where titulo like  %:keyword%")
	public List<String> search(@Param("keyword") String keyword);

}
