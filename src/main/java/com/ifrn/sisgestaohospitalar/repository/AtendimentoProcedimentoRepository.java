package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;

@Repository
public interface AtendimentoProcedimentoRepository extends JpaRepository<AtendimentoProcedimento, Long> {

}
