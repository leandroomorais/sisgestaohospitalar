package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	Profissional findByCpf(String cpf);

	Profissional findByCns(String cns);

	List<Profissional> findByTipoProfissional(TipoProfissional tipoProfissional);

	Profissional findByEmail(String email);

	@Query(value = "select * from profissional p where p.nome " + " like concat('%', :name, '%')", nativeQuery = true)
	Profissional findByNome(@Param("name") String nome);

	@Query(nativeQuery = true, value = "select * from profissional p inner join profissional_lotacao pl on p.id = pl.profissional_id inner join lotacao lt on pl.lotacao_id = lt.id where (lt.codigocbo = '223505' or lt.codigocbo = '225125' or lt.codigocbo = '322205' or lt.codigocbo = '322230')")
	List<Profissional> searchSelectOptions();

}
