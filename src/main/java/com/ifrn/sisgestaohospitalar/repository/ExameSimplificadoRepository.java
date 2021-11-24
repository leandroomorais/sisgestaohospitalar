package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ExameSimplificado;

@Repository
public interface ExameSimplificadoRepository extends JpaRepository<ExameSimplificado, Long> {

}
