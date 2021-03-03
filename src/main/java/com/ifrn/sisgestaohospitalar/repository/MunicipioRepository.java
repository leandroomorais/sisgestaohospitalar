package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Municipio;


@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
	
	public List<Municipio> findBynomeMunicipioSiglaUFIgnoreCaseContaining(String nomeMunicipioSiglaUF);
	public Municipio findBycodigoIBGE(Long codigoIBGE);
	

}
