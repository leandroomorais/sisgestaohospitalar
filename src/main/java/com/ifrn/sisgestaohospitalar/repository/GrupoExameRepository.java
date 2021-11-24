package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.GrupoExame;

@Repository
public interface GrupoExameRepository extends JpaRepository<GrupoExame, Long> {

}
