package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoRegistroSigtap;

@Repository
public interface ProcedimentoRegistroSigtapRepository extends JpaRepository<ProcedimentoRegistroSigtap, Long> {

}
