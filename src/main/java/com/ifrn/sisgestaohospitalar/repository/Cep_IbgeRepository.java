package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Cep_Ibge;

@Repository
public interface Cep_IbgeRepository extends JpaRepository<Cep_Ibge, Long> {

	Cep_Ibge findByCep(Long cep);
}
