package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoOcupacao;

@Repository
public interface ProcedimentoOcupacaoRepository extends JpaRepository<ProcedimentoOcupacao, Long> {
	List<ProcedimentoOcupacao> findByCodigoProcedimento(Long codigoProcedimento);

	@Query(nativeQuery = true, value = "INSERT INTO procedimento_ocupacao()")
	public void testsalve(@Param("id") Long id, @Param("codigoProcedimento") Long codigoProcedimento,
			@Param("codigoOcupacap") String codigoOcupacao, @Param("competencia") String competencia);
}
