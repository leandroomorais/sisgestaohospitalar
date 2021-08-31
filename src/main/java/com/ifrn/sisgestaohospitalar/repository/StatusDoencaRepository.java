package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.StatusDoenca;

@Repository
public interface StatusDoencaRepository extends JpaRepository<StatusDoenca, Long> {

}
