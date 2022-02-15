package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.ifrn.sisgestaohospitalar.model.Cid;

@Repository
public interface CidRepository extends JpaRepository<Cid, Long> {

	Cid findByCodigoIgnoreCaseContaining(String codigo);

	@Query("select c from Cid c where c.codigo like %:term% or c.nome like %:term%")
	List<Cid> findByNomeIgnoreCaseContaining(@Param("term") String term, Pageable pageable);
	
	@Query(nativeQuery = true, value ="select c.* from cid c inner join procedimento_cid pc on(c.codigo = pc.codigo_cid) where pc.codigo_procedimento = ?")
	List<Cid> findByCodigoProcedimentoCid(Long codigoProcedimento);

}
