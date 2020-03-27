package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.CidSigtap;

@Repository
public interface CidSigtapRepository extends JpaRepository<CidSigtap, Long> {

	/**
	 * Retorna o Cid a partir do seu código
	 * @param codecid
	 * @return CidSigtap
	 */
	public CidSigtap findByCodecid(String codecid);

}
