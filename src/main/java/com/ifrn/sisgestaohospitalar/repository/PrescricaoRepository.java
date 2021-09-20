package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Prescricao;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {

}
