package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {
	
	List<Exame> findByAtendimento(Atendimento atendimento);
	
	//@Query("select e from Exame e where e.atendimento_id = id")
	//List<Exame> findByCodigoAtendimento(Long id);

}
