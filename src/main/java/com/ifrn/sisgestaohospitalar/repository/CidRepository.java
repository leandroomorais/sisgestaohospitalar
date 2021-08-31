package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Cid;

@Repository
public interface CidRepository extends JpaRepository<Cid, Long> {

	Cid findByCodigo(String codigo);

	List<Cid> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

}
