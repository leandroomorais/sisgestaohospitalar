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

	@Query(nativeQuery = true, value = "select * from procedimento p inner join procedimento_ocupacao po on p.codigo = po.codigo_procedimento where p.codigo = :codigoProcedimento and po.codigo_ocupacao = :codigoOcupacao group by p.codigo")
	ProcedimentoOcupacao verificaCBO(@Param("codigoProcedimento") Long codigoProcedimento,
			@Param("codigoOcupacao") String codigoOcupacao);
}
