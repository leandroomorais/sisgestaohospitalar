package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;

@Repository
public interface ArquivoBPARepository extends JpaRepository<ArquivoBPA, Long> {
	
	public ArquivoBPA findByCompetencia(String competencia);
	
	public ArquivoBPA findByGerado(boolean gerado);

}
