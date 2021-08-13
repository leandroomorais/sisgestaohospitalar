package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	List<Atendimento> findByCidadao(Cidadao cidadao);

	List<Atendimento> findByStatus(Status status);

	Page<Atendimento> findByCondutaTipoServico(TipoServico tipoServico, Pageable pageable);

	@Query("select a from Atendimento a where a.cidadao.nome like %:search% or a.profissionalDestino like %:search% or a.condutaTipoServico like %:search%")
	Page<Atendimento> findByCidadaoOrProfissionalOrTipoServico(@Param("search") String search, Pageable pageable);

}
