package com.ifrn.sisgestaohospitalar.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.AdministracaoMedicamento;

/**
 * A interface <code>AdministracaoMedicamentoRepository</code> extende a
 * interface JpaRepository da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface AdministracaoMedicamentoRepository extends JpaRepository<AdministracaoMedicamento, Long> {

	/**
	 * @param data
	 * @return List<AdministracaoMedicamento>
	 */
	public List<AdministracaoMedicamento> findByData(LocalDate data);

}
