package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoDetalhe;

@Repository
public interface ProcedimentoDetalheRepository extends JpaRepository<ProcedimentoDetalhe, Long> {

	@Query("select p from ProcedimentoDetalhe p where p.codigoProcedimento = :codigoProcedimento and p.codigoDetalhe = '048'")
	ProcedimentoDetalhe exigeCid(@Param("codigoProcedimento") Long codigoProcedimento);

	@Query("select p from ProcedimentoDetalhe p where p.codigoProcedimento = :codigoProcedimento and p.codigoDetalhe = '012'")
	ProcedimentoDetalhe exigeIdade(@Param("codigoProcedimento") Long codigoProcedimento);

}
