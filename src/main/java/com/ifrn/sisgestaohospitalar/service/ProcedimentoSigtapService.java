package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoSigtapRepository;

/**
 * A classe <code>ProcedimentoSigtapService</code> implementa os métodos da
 * Interface ProcedimentoSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Service
public class ProcedimentoSigtapService {

	@Autowired
	private ProcedimentoSigtapRepository repository;

	/**
	 * Salva os objetos do tipo ProcedimentoSigtap
	 * 
	 * @param procedimentoSigtap
	 */
	public void save(ProcedimentoSigtap procedimentoSigtap) {
		repository.saveAndFlush(procedimentoSigtap);
	}

	/**
	 * Deleta os objetos do Tipo ProcedimentoSigtap a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de Objetos do Tipo ProcedimentoSigtap
	 * 
	 * @return List<ProcedimentoSigtap>
	 */
	public List<ProcedimentoSigtap> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto ProcedimentoSigtap a partir do Id
	 * 
	 * @param id
	 * @return ProcedimentoSigtap
	 */
	public ProcedimentoSigtap findOne(Long id) {
		return repository.getOne(id);
	}
	
	

}
