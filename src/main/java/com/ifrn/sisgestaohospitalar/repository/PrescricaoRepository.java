package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Prescricao;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {

	List<Prescricao> findByAtendimento(Atendimento atendimento);

}
