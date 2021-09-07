package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.RelAtendimentoProcedimento;

@Repository
public interface RelAtendimentoProcedimentoRepository extends JpaRepository<RelAtendimentoProcedimento, Long> {

}
