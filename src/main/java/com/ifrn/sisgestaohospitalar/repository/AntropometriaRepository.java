package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Antropometria;
import com.ifrn.sisgestaohospitalar.model.Atendimento;

@Repository
public interface AntropometriaRepository extends JpaRepository<Antropometria, Long> {

	List<Antropometria> findByAtendimento(Atendimento atendimento);

}
