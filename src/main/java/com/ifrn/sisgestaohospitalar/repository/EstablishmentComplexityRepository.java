package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.EstablishmentComplexity;

@Repository
public interface EstablishmentComplexityRepository extends JpaRepository<EstablishmentComplexity, Long> {

}
