package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.SinaisVitais;

@Repository
public interface SinaisVitaisRepository extends JpaRepository<SinaisVitais, Long> {

}
