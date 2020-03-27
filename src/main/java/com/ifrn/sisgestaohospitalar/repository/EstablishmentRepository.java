package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Establishment;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
	
	/**Método que recupera dados do Estabelecimento a partir do Código CNES
	 * @param cnes
	 * @return Establishment*/
	public Establishment findByCnes(String cnes);

}
