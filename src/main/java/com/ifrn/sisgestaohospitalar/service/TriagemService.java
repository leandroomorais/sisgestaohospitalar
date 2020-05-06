package com.ifrn.sisgestaohospitalar.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Triagem;
import com.ifrn.sisgestaohospitalar.repository.TriagemRepository;

/**
 * A classe <code>TriagemService</code> implementa os métodos da Interface
 * EstabelecimentoRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class TriagemService {

	@Autowired
	private TriagemRepository repository;

	/**
	 * Salva os objetos do tipo Triagem
	 * 
	 * @param triagem
	 */
	public void save(Triagem triagem) {
		repository.saveAndFlush(triagem);
	}

	/**
	 * Deleta os objetos do tipo Triagem a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de objetos do tipo Estabelecimento
	 * 
	 * @return List<Triagem>
	 */
	public List<Triagem> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto Triagem a partir do Id
	 * 
	 * @param id
	 * @return Triagem
	 */
	public Triagem findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna a lista de Triagens de acordo com a Classificação de Risco
	 * 
	 * @param classificacaoderisco
	 * @return List<Triagem>
	 */
	public List<Triagem> findByClassificacaoderisco(String classificacaoderisco) {
		return repository.findByClassificacaoderisco(classificacaoderisco);
	}

	/**
	 * Retorna a lista de Triagens de acordo com a data
	 * 
	 * @param data
	 * @return List<Triagem>
	 */
	public List<Triagem> findByData(Date data) {
		return repository.findByData(data);
	}

}
