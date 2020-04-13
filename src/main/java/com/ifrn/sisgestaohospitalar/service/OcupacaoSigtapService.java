package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.OcupacaoSigtap;
import com.ifrn.sisgestaohospitalar.repository.OcupacaoSigtapRepository;

/**
 * A classe <code>OcupacaoSigtapService</code> implementa os métodos da
 * Interface OcupacaoSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class OcupacaoSigtapService {

	@Autowired
	private OcupacaoSigtapRepository repository;

	/**
	 * Salva os objetos do tipo OcupacaoSigtap
	 * 
	 * @param ocupacaoSigtap
	 */
	public void save(OcupacaoSigtap ocupacaoSigtap) {
		repository.saveAndFlush(ocupacaoSigtap);
	}

	/**
	 * Deleta os objetos do tipo OcupacaoSigtap a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de objetos do tipo OcupacaoSigtap
	 * 
	 * @return List<OcupacaoSigtap>
	 */
	public List<OcupacaoSigtap> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto OcupacaoSigtap a partir do Id
	 * 
	 * @param id
	 * @return OcupacaoSigtap
	 */
	public OcupacaoSigtap findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna o objeto do tipo OcupacaoSigtap a partir do Código da Ocupação
	 * 
	 * @param codigoocupacao
	 * @return OcupacaoSigtap
	 */
	public OcupacaoSigtap findByCodigoocupacao(String codigoocupacao) {
		return repository.findByCodigoocupacao(codigoocupacao);
	}

}
