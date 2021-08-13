package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Triagem;

@Repository
public interface TriagemRepository extends JpaRepository<Triagem, Long> {

}
