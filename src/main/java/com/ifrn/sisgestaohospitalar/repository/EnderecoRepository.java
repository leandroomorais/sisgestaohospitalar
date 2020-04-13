package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Endereco;
/**
 * A interface <code>EnderecoRepository</code> extende a interface JpaRepository
 * da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
