package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Alergia;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long> {

	List<Alergia> findByNomeIgnoreCaseContaining(String nome);

}
