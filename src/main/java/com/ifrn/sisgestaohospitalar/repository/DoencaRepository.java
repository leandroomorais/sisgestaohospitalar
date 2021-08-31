package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Doenca;

@Repository
public interface DoencaRepository extends JpaRepository<Doenca, Long> {

	List<Doenca> findByNomeIgnoreCaseContaining(String nome);
}
