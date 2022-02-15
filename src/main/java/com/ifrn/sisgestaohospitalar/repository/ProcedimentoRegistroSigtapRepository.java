package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoRegistroSigtap;

@Repository
public interface ProcedimentoRegistroSigtapRepository extends JpaRepository<ProcedimentoRegistroSigtap, Long> {

	@Query("select p from ProcedimentoRegistroSigtap p where p.codigoProcedimento = :codigoProcedimento and(p.codigoRegistro = '01' or p.codigoRegistro = '02')")
	List<ProcedimentoRegistroSigtap> buscaTipoRegistroBPA(@Param("codigoProcedimento") Long codigoProcedimento);

	ProcedimentoRegistroSigtap findByCodigoRegistro(String codigoRegistro);

}
