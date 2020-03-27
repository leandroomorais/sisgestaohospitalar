package com.ifrn.sisgestaohospitalar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.ProcedureSigtap;
import com.ifrn.sisgestaohospitalar.repository.ProcedureSigtapRepository;

/**
 * Classe que implementa os Métodos da Interface ProcedureSigtapRepository
 * 
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 *
 */
@Service
public class ProcedureSigtapService {

	@Autowired
	private ProcedureSigtapRepository repository;

	/**
	 * Salva o Procedimento
	 * @param procedureSigtap
	 */
	public void save(ProcedureSigtap procedureSigtap) {
		repository.saveAndFlush(procedureSigtap);
	}

	/**
	 * Deleta o procedimento a partir do id
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna o Proceidmento a partir do Id
	 * @param id
	 * @return ProcedureSigtap
	 */
	public ProcedureSigtap findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna a lista de Procedimentos
	 * @return List<ProcedureSigtap>
	 */
	public List<ProcedureSigtap> listAll() {
		return repository.findAll();
	}

	/**
	 * Retorna a lista de Strings com os nomes dos procedimentos a partir do
	 * parâmetro de pesquisa - keyword - usado para o autocomplete
	 * @param keyword
	 * @return List<String>
	 */
	public List<String> search(String keyword) {
		return repository.search(keyword);
	}

}
