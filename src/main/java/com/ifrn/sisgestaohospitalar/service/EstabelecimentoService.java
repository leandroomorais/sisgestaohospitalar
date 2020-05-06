package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;

/**
 * A classe <code>EstabelecimentoService</code> implementa os métodos da
 * Interface EstabelecimentoRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Service
public class EstabelecimentoService {

	@Autowired
	private EstabelecimentoRepository repository;

	/**
	 * Salva os objetos do tipo Estabelecimento
	 * 
	 * @param estabelecimento
	 */
	public void save(Estabelecimento estabelecimento) {
		repository.saveAndFlush(estabelecimento);
	}

	/**
	 * Deleta os objetos do tipo Estabelecimento a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de objetos do tipo Estabelecimento
	 * 
	 * @return List<Estabelecimento>
	 */
	public List<Estabelecimento> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto Estabelecimento a partir do Id
	 * 
	 * @param id
	 * @return Estabelecimento
	 */
	public Estabelecimento findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna o objeto Estabelecimento a partir do CNES
	 * 
	 * @param cnes
	 * @return
	 */
	public Estabelecimento findByCnes(String cnes) {
		return repository.findByCnes(cnes);
	}

}
