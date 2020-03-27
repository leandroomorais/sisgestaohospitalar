package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.CitizenUser;

@Repository
public interface CitizenUserRepository extends JpaRepository<CitizenUser, Long> {
	
	/** Retorna o CitizenUser a partir do HealthcardNumber (Número do Cartão de Saúde)
	 * @param healthcardnumber
	 * @return CitizenUser */
	public CitizenUser findByHealthcardnumber(String healthcardnumber);

}
