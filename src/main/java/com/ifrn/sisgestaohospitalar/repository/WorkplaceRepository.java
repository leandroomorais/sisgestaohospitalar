package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Workplace;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

	/**
	 * Recupera a lista de Local de Trabalho e Profissional a partir do CBO
	 * @param codecbo
	 * @return List<Workplace>
	 */
	@Query("select w for Workplace w where w.codecbo = ?1")
	public List<Workplace> findByCodecbo(String codecbo);

}
