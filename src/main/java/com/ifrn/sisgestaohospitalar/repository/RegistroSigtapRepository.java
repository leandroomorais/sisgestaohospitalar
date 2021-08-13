package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.RegistroSigtap;

@Repository
public interface RegistroSigtapRepository extends JpaRepository<RegistroSigtap, Long> {
	
	RegistroSigtap findByCodigo(String codigo);

}
