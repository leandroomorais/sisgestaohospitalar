package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.StatusAlergia;

@Repository
public interface StatusAlergiaRepository extends JpaRepository<StatusAlergia, Long> {

}