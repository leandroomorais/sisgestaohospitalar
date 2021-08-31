package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.FormaFarmaceutica;

@Repository
public interface FormaFarmaceuticaRepository extends JpaRepository<FormaFarmaceutica, Long> {

}
