package com.ifrn.sisgestaohospitalar.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Cidadao;

@Repository
public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {

	/**
	 * @param cns
	 * @return Cidadao
	 */
	public Optional<Cidadao> findByCns(String cns);

	/**
	 * @param cpf
	 * @return Cidadao
	 */
	public Optional<Cidadao> findByCpf(String cpf);

	/**
	 * @param nome
	 * @return Cidadao
	 */
	public Optional<Cidadao> findByNomeIgnoreCase(String nome);
	
	public Optional<Cidadao> findByNomeAndDatanascimento(String nome, LocalDate datanascimento);

}
