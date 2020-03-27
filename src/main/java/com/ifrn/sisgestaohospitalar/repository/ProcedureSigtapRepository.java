package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedureSigtap;

@Repository
public interface ProcedureSigtapRepository extends JpaRepository<ProcedureSigtap, Long> {

	/**
	 * Método que retorna Lista de String contendo o nome do procedimento
	 * correspondente ao parâmetro pesquisado "keyword"
	 * @param keyword
	 * @return
	 */
	@Query("SELECT numberprocedure FROM ProcedureSigtap where numberprocedure like  %:keyword%")
	public List<String> search(@Param("keyword") String keyword);

	/**
	 * Método quue retorna a lista de Procedimentos da Tabela Sigtap a partir do
	 * Número do procedimento
	 * @param numberprocedure
	 * @return
	 */
	@Query
	public List<ProcedureSigtap> findByNumberprocedure(String numberprocedure);

}
