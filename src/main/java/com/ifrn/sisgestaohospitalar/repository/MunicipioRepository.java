package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
	
	Municipio findBycodigoIBGE(Long codigoIBGE);
	List<Municipio> findByNomeMunicipioSiglaUFIgnoreCaseContaining(String nome);

}
