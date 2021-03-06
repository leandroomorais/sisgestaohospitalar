package com.ifrn.sisgestaohospitalar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.model.TokenRedefinicao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.TokenRedefinicaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;

/**
 * A classe <code>ProfissionalService</code> implementa os métodos da Interface
 * ProfissionalRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository repository;

	@Autowired
	private TokenRedefinicaoRepository tokenRedefinicaoRepository;

	/**
	 * Salva os objetos do tipo Profissional
	 * 
	 * @param profissional
	 */
	public void save(Profissional profissional) {
		repository.saveAndFlush(profissional);
	}

	/**
	 * Deleta os objetos do tipo Profissional a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de todos os objetos do tipo Profissional
	 * 
	 * @return List<Profissional>
	 */
	public List<Profissional> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto Profissional a partir do Id
	 * 
	 * @param id
	 * @return Profissional
	 */
	public Profissional findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna o objeto Profissional a partir do CNS (Cartão Nacional de Saúde)
	 * 
	 * @param cns
	 * @return Profissional
	 */
	public Profissional findByCns(String cns) {
		return repository.findByCns(cns);
	}

	/**
	 * Retorna o Profissional a partir do CPF
	 * 
	 * @param cpf
	 * @return Profissional
	 */
	public Profissional findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	/**
	 * Retorna a lista de Profissionais de acordo com o tipo
	 * 
	 * @param tipoProfissional
	 * @return List<Profissional>
	 */
	public List<Profissional> findByTipoprofissional(TipoProfissional tipoProfissional) {
		return repository.findByTipoprofissional(tipoProfissional);
	}

	/**
	 * Retorna o Profissional a partir do email
	 * 
	 * @param email
	 * @return Profissional
	 */
	public Profissional findByEmail(String email) {
		return repository.findByEmail(email);
	}

	/**
	 * Retorna o Token para resetar a senha (Password)
	 * 
	 * @param token
	 * @return TokenRedefinicao
	 */
	public TokenRedefinicao getTokenRedefinicao(final String token) {
		return tokenRedefinicaoRepository.findByToken(token);
	}

	/**
	 * Gera o Token de Redefinição de Senha
	 * 
	 * @param profissional
	 * @param token
	 */
	public void gerarTokenRedefinicao(final Profissional profissional, final String token) {
		final TokenRedefinicao meuToken = new TokenRedefinicao(token, profissional);
		tokenRedefinicaoRepository.save(meuToken);
	}

}
