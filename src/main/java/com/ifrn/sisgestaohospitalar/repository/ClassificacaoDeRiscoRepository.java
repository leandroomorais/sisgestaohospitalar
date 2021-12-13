package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ClassificacaoDeRisco;

@Repository
public interface ClassificacaoDeRiscoRepository extends JpaRepository<ClassificacaoDeRisco, Long> {

	ClassificacaoDeRisco findBynome(String nome);

}
