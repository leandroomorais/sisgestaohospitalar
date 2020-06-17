package com.ifrn.sisgestaohospitalar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.repository.GuiaAtendimentoRepository;

/**
 * A classe <code>GuiaAtendimentoService</code> implementa os métodos da
 * Interface GuiaAtendimentoRepository
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class GuiaAtendimentoService {

	@Autowired
	private GuiaAtendimentoRepository repository;

	/**
	 * Salva os objetos do tipo GuiaAtendimento
	 * 
	 * @param guiaAtendimento
	 */
	public void save(GuiaAtendimento guiaAtendimento) {
		repository.saveAndFlush(guiaAtendimento);
	}

	/**
	 * Deleta os objetos do tipo GuiaAtendimento a partir do Id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a Lista de objetos do tipo GuiaAtendimento
	 * 
	 * @return List<GuiaAtendimento>
	 */
	public List<GuiaAtendimento> findAll() {
		return repository.findAll();
	}

	/**
	 * Retorna o objeto do tipo GuiaAtendimento a partir do Id
	 * 
	 * @param id
	 * @return GuiaAtendimento
	 */
	public GuiaAtendimento findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Retorna a lista de Guias de Atendimento de acordo com o Status
	 * 
	 * @param statusAtendimento
	 * @return List<GuiaAtendimento>
	 */
	public List<GuiaAtendimento> findByStatusatendimento(StatusAtendimento statusAtendimento) {
		return repository.findByStatusatendimento(statusAtendimento);
	}

	/**
	 * Retorna a lista de Guias de Atendimento de acordo com o Status
	 * 
	 * @param data
	 * @return List<GuiaAtendimento>
	 */
	public List<GuiaAtendimento> findByData(Date data) {
		return repository.findByData(data);
	}

	public List<GuiaAtendimento> teste(StatusAtendimento statusAtendimento) {
		List<GuiaAtendimento> atendimentos = new ArrayList<GuiaAtendimento>();
		for (GuiaAtendimento guiaAtendimento : repository.findAll()) {
			if (guiaAtendimento.getStatusatendimento() != statusAtendimento) {
				atendimentos.add(guiaAtendimento);
			}
		}
		return atendimentos;
	}

}
