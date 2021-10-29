package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Exame;
import com.ifrn.sisgestaohospitalar.model.Procedimento;

public interface ExameRepository extends JpaRepository<Exame, Long> {
	
	List<Exame> findByAtendimento(Atendimento atendimento);
	
	//@Query("select p.codigo, p.nome from exame e inner join exame_procedimentos ep on(e.id = ep.id_exame) \r\n"
		//	+ "	inner join procedimento p on(ep.id_procedimento = p.codigo) where e.id = id")
	//List<Procedimento> findByExame(Long id);
	//@Query("select e from Exame e where e.atendimento_id = id")
	//List<Exame> findByCodigoAtendimento(Long id);

	//select p.codigo, p.nome from exame e inner join exame_procedimentos ep on(e.id = ep.id_exame) 
	//inner join procedimento p on(ep.id_procedimento = p.codigo) where e.id =
}
