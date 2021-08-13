package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Comorbidade;

@Repository
public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Long> {
	Comorbidade findByNomeIgnoreCaseContaining(String nome);
}
