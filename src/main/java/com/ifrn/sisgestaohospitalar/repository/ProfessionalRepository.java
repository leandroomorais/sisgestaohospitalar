package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.enums.ProfessionalType;
import com.ifrn.sisgestaohospitalar.model.Professional;


@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
	
	/** Retorna Professional a partir do CPF */
	public Professional findByCpf(String cpf);
	
	/**Retorna Lista de Professional a partir do Tipo*/
	public List<Professional> findByProfessionaltype(ProfessionalType professionaltype);
	
	/**Retorna o Professional a partir do email*/
	public Professional findByEmail(String email);

}
