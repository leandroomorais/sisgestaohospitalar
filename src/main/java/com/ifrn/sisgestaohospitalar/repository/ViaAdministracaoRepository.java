package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.ViaAdministracao;

@Repository
public interface ViaAdministracaoRepository extends JpaRepository<ViaAdministracao, Long> {

}
