package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.UsoMedicamento;

@Repository
public interface UsoMedicamentoRepository extends JpaRepository<UsoMedicamento, Long> {

}
