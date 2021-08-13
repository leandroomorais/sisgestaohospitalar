package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Prontuario;
@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long>{

}
