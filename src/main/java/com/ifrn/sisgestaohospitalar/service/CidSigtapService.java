package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.CidSigtap;
import com.ifrn.sisgestaohospitalar.repository.CidSigtapRepository;

/**
 * Classe que implementa os Métodos da Interface CidSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Service
public class CidSigtapService {

	@Autowired
	private CidSigtapRepository repository;

	/**
	 * Salva o CidSigtap
	 * 
	 * @param cidSigtap
	 */
	public void save(CidSigtap cidSigtap) {
		repository.saveAndFlush(cidSigtap);
	}

	/**
	 * Deleta o CidSigtap a partir o id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna o CidSigtap a partir do id
	 * 
	 * @param id
	 * @return CidSigtap
	 */
	public CidSigtap findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna a lista de Cids
	 * 
	 * @return List<CidSigtap>
	 */
	public List<CidSigtap> listAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o CidSigtap a partir do seu código
	 * 
	 * @param codecid
	 * @return CidSigtap
	 */
	public CidSigtap findByCodecid(String codecid) {
		return repository.findByCodecid(codecid);
	}

}
