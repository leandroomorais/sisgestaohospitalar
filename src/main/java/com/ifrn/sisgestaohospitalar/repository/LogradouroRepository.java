package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Logradouro;

@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
	
	Logradouro findByCodigo(Long codigo);
	List<Logradouro> findByDescricaoIgnoreCaseContaining(String descricao);

}
