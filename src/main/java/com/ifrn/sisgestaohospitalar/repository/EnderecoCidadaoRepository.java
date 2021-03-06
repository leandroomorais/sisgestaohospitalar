package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.EnderecoCidadao;

@Repository
public interface EnderecoCidadaoRepository extends JpaRepository<EnderecoCidadao, Long> {

}
