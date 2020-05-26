package com.ifrn.sisgestaohospitalar.repository;

import java.util.Date;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.TokenRedefinicao;
import com.ifrn.sisgestaohospitalar.model.Profissional;

/**
 * A interface <code>TokenRedefinicaoRepository</code> extende a interface
 * JpaRepository da API JPA e seus respectivos métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Repository
public interface TokenRedefinicaoRepository extends JpaRepository<TokenRedefinicao, Long> {

	/**
	 * @param token
	 * @return TokenRedefinicao
	 */
	TokenRedefinicao findByToken(String token);

	/**
	 * @param profissional
	 * @return TokenRedefinicao
	 */
	TokenRedefinicao findByProfissional(Profissional profissional);

	/**
	 * @param now
	 * @return Stream<TokenRedefinicao>
	 */
	Stream<TokenRedefinicao> findAllByDataExpiracaoLessThan(Date now);

	/**
	 * Deleta os Tokens Expirados
	 * 
	 * @param now
	 */
	@Modifying
	@Query("delete from TokenRedefinicao t where t.dataExpiracao <= ?1")
	void deleteAllExpiredSince(Date now);

}
