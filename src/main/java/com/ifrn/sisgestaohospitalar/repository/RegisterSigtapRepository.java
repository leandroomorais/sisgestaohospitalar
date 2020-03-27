package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.RegisterSigtap;

@Repository
public interface RegisterSigtapRepository extends JpaRepository<RegisterSigtap, Long> {

	/**
	 * Método que retorna o Registro (Instrumento de Registro da sigtap) a partir do
	 * Código do Registro
	 * @return RegisterSigtap
	 */
	public RegisterSigtap findByCoderegister(String coderegister);
}
