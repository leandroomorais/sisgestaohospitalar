package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Cep_Ibge;

@Repository
public interface Cep_IbgeRepository extends JpaRepository<Cep_Ibge, Long> {
	public List<Cep_Ibge> findBycodigoIBGE(Long codigoIBGE);
	public Cep_Ibge findBycep(Long cep);

}
