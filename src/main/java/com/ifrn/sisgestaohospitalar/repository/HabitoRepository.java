package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Habito;

@Repository
public interface HabitoRepository extends JpaRepository<Habito, Long> {
	
	Habito findByNomeIgnoreCaseContaining(String nome);

}
