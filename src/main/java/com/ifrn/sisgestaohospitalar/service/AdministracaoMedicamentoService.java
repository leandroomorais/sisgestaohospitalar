package com.ifrn.sisgestaohospitalar.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.AdministracaoMedicamento;
import com.ifrn.sisgestaohospitalar.repository.AdministracaoMedicamentoRepository;

/**
 * A classe <code>AdministracaoMedicamentoService</code> implementa os métodos
 * da Interface AdministracaoMedicamentoRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class AdministracaoMedicamentoService {

	@Autowired
	private AdministracaoMedicamentoRepository repository;

	/**
	 * Salva os objetos do tipo AdministracaoMedicamento
	 * 
	 * @param administracaoMedicamento
	 */
	public void save(AdministracaoMedicamento administracaoMedicamento) {
		repository.saveAndFlush(administracaoMedicamento);
	}

	/**
	 * Deleta os objetos do tipo AdministracaoMedicamento a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de objetos do tipo AdministracaoMedicamento
	 * 
	 * @return List<AdministracaoMedicamento>
	 */
	public List<AdministracaoMedicamento> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto AdministracaoMedicamento a partir do Id
	 * 
	 * @param id
	 * @return Triagem
	 */
	public AdministracaoMedicamento findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna a lista de Administração de Medicamentos a partir da data
	 * 
	 * @param data
	 * @return List<AdministracaoMedicamento>
	 */
	public List<AdministracaoMedicamento> findByData(LocalDate data) {
		return repository.findByData(data);
	}

}
