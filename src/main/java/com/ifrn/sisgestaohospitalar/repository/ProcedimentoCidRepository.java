package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoCid;

public interface ProcedimentoCidRepository extends JpaRepository<ProcedimentoCid, Long> {
	
	List<ProcedimentoCid> findByCodigoProcedimento(Long codigoProcedimento);

}
