package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoDetalhe;

@Repository
public interface ProcedimentoDetalheRepository extends JpaRepository<ProcedimentoDetalhe, Long> {

}
