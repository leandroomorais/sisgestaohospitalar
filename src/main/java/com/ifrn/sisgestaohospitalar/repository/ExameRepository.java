package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ifrn.sisgestaohospitalar.enums.StatusExame;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Exame;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.model.Prontuario;

public interface ExameRepository extends JpaRepository<Exame, Long> {
	
	List<Exame> findByAtendimento(Atendimento atendimento);
	
	List<Exame> findByStatus(StatusExame status);
	
	List<Exame> findByProntuarioOrderByDataSolicitacaoDesc(Prontuario prontuario);
}
