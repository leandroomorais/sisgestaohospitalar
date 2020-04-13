package com.ifrn.sisgestaohospitalar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;

/**
 * A classe <code>CidadaoService</code> implementa os métodos da Interface
 * CidadaoRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class CidadaoService {

	@Autowired
	private CidadaoRepository repository;

	/**
	 * Salva os objetos do tipo Cidadao
	 * 
	 * @param cidadao
	 */
	public void save(Cidadao cidadao) {
		repository.saveAndFlush(cidadao);
	}

	/**
	 * Deleta os objetos do tipo Cidadao a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de todos os objetos do tipo Cidadao
	 * 
	 * @return List<Cidadao>
	 */
	public List<Cidadao> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto Cidadao a partir do Id
	 * 
	 * @param id
	 * @return Cidadao
	 */
	public Cidadao findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna o objeto Cidadao a partir do CNS (Cartão Nacional de Saúde)
	 * 
	 * @param cns
	 * @return Cidadao
	 */
	public Cidadao findByCns(String cns) {
		return repository.findByCns(cns);
	}

	/**
	 * Retorna o cidadao a partir do CPF
	 * 
	 * @param cpf
	 * @return Cidadao
	 */
	public Cidadao findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

}
