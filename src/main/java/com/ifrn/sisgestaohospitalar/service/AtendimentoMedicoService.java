package com.ifrn.sisgestaohospitalar.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.AtendimentoMedico;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoMedicoRepository;

/**
 * A classe <code>AtendimentoMedicoService</code> implementa os métodos da
 * Interface AtendimentoMedicoRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class AtendimentoMedicoService {

	@Autowired
	private AtendimentoMedicoRepository repository;

	/**
	 * Salva os objetos do tipo AtendimentoMedico
	 * 
	 * @param atendimentoMedico
	 */
	public void save(AtendimentoMedico atendimentoMedico) {
		repository.saveAndFlush(atendimentoMedico);
	}

	/**
	 * Deleta os objetos do tipo AtendimentoMedico a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a lista de objetos do tipo AtendimentoMedico
	 * 
	 * @return List<AtendimentoMedico>
	 */
	public List<AtendimentoMedico> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto AtendimentoMedico a partir do Id
	 * 
	 * @param id
	 * @return Triagem
	 */
	public AtendimentoMedico findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna a lista de Atendimentos Médicos a partir da data
	 * 
	 * @param data
	 * @return List<AtendimentoMedico>
	 */
	public List<AtendimentoMedico> findByData(Date data) {
		return repository.findByData(data);
	}

}
