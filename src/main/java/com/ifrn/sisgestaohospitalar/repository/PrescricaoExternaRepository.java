package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.PrescricaoExterna;

@Repository
public interface PrescricaoExternaRepository  extends JpaRepository<PrescricaoExterna, Long> {

}
