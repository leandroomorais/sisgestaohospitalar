package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoOcupacao;

@Repository
public interface ProcedimentoOcupacaoRepository extends JpaRepository<ProcedimentoOcupacao, Long> {
	List<ProcedimentoOcupacao> findByCodigoProcedimento(Long codigoProcedimento);
}
