package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Alergia;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long> {

}
