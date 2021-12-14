package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Detalhe;

@Repository
public interface DetalheRepository extends JpaRepository<Detalhe, Long> {

}
