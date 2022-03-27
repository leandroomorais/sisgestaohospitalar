package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Ocupacao;

@Repository
public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long> {

	Ocupacao findByCodigo(String codigo);

	Ocupacao findByNome(String nome);

	@Query(value = "SELECT p.* FROM ocupacao p WHERE p.nome LIKE %:nome%", nativeQuery = true)
	List<Ocupacao> findByNomeAutocomplete(@Param("nome") String nome);

}
