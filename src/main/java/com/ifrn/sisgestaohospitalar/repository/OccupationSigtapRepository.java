package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.OccupationSigtap;

@Repository
public interface OccupationSigtapRepository extends JpaRepository<OccupationSigtap, Long> {

	/**
	 * Método que retorna a Ocupação (CBO) a partir do código
	 * @param codeoccupation
	 * @return OccupationSigtap
	 */
	public OccupationSigtap findByCodeoccupation(String codeoccupation);

}
