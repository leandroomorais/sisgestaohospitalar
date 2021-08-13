package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
	Estado findBySigla(String sigla);

}
