package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.TipoServico;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	@Query("select a from Atendimento a join a.tipoServicos tp where tp.nome != 'Triagem' and a.status != 4 and a.status != 3 and a.status != 2 group by a")
	Page<Atendimento> findAtendimentos(Pageable pageable);

	List<Atendimento> findByCidadao(Cidadao cidadao);

	List<Atendimento> findByStatus(Status status);

	Page<Atendimento> findByTipoServicos(Pageable pageable, TipoServico tipoServico);

	@Query("select a from Atendimento a where a.cidadao.nome like %:search% or a.profissionalDestino like %:search%")
	Page<Atendimento> findByCidadaoOrProfissionalOrTipoServico(@Param("search") String search, Pageable pageable);

	@Query("select a from Atendimento a where year(a.dataEntrada) = :ano and month(a.dataEntrada) = :mes")
	List<Atendimento> findByMes(@Param("ano") int ano, @Param("mes") int mes);

	@Query(value = "SELECT * FROM atendimento a WHERE date(a.data_entrada) BETWEEN :data1 AND :data2", nativeQuery = true)
	List<Atendimento> findByDataEntradaBetween(@Param("data1") String data1, @Param("data2") String data2);

	@Query(value = "SELECT * FROM atendimento a WHERE date(a.data_entrada) = :dataEntrada", nativeQuery = true)
	List<Atendimento> findByDataEntrada(@Param("dataEntrada") String dataEntrada);

}
