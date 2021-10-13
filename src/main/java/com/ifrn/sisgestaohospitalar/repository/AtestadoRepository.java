package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Atestado;

@Repository
public interface AtestadoRepository extends JpaRepository<Atestado, Long> {
	List<Atestado> findByAtendimento(Atendimento atendimento);
}
