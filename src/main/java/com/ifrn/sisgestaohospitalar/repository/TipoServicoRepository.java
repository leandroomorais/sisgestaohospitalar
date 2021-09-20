package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.TipoServico;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {

	TipoServico findByNome(String nome);

}
