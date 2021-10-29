package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {
	
	Procedimento findByCodigo(Long codigo);
	
	@Query("select p from Procedimento p where p.nome like %:search%")
	List<Procedimento> findByNome(@Param("search") String search);
	
	@Query("select p from Procedimento p where p.codigo like '20%' and p.nome like %:search%")
	List<Procedimento> findByNomeExame(@Param("search") String search);
	
//	@Query(value="select p.codigo, p.codigo_financiamento, p.codigo_rubrica, p.data_competencia, p.nome, "
//			+ "p.qtd_dias_permanencia, p.qtd_maxima_execucao, p.qtd_pontos, p.qtd_tempo_permanencia, p.quantidade, "
//			+ "p.tipo_complexidade, p.tipo_sexo, p.vl_idade_maxima, p.vl_idade_minina, p.vlsa, p.vlsh, p.vlsp from exame e inner join exame_procedimentos ep on(e.id = ep.id_exame) "
//			+ "inner join procedimento p on(ep.id_procedimento = p.codigo) where e.id  = :id", nativeQuery=true)
//	List<Procedimento> findByIdExame(@Param("id") Long id);
	
	@Query(value="select p.* from exame e inner join exame_procedimentos ep on(e.id = ep.id_exame) "
			+ "inner join procedimento p on(ep.id_procedimento = p.codigo) where e.id  = :id", nativeQuery=true)
	List<Procedimento> findByIdExame(@Param("id") Long id);
}
