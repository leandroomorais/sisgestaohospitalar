package com.ifrn.sisgestaohospitalar.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;

/**
 * A interface <code>GuiaAtendimentoRepository</code> extende a interface
 * JpaRepository da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface GuiaAtendimentoRepository extends JpaRepository<GuiaAtendimento, Long> {

	public List<GuiaAtendimento> findByStatusAtendimento(StatusAtendimento statusAtendimento);
	
	public List<GuiaAtendimento> findByTipoServico(TipoServico tipoServico);

	public List<GuiaAtendimento> findByData(LocalDate data);
	
	@Query(value = "SELECT g FROM GuiaAtendimento g WHERE g.data BETWEEN :dataInicial AND :dataFinal")
	public List<GuiaAtendimento> findByPeriodo(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal")LocalDate dataFinal);

}
