package com.ifrn.sisgestaohospitalar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
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
	public List<GuiaAtendimento> findByStatusAtendimento(StatusAtendimento statusAtendimento) {
		return repository.findByStatusAtendimento(statusAtendimento);
	}
	
	/**
	 * Retorna a lista de Guias de Atendimento de acordo com o Tipo de Serviço
	 * 
	 * @param statusAtendimento
	 * @return List<GuiaAtendimento>
	 */
	public List<GuiaAtendimento> findByTipoServico(TipoServico tipoServico) {
		return repository.findByTipoServico(tipoServico);
	}

	/**
	 * Retorna a lista de Guias de Atendimento de acordo com o Status
	 * 
	 * @param data
	 * @return List<GuiaAtendimento>
	 */
	public List<GuiaAtendimento> findByData(LocalDate data) {
		return repository.findByData(data);
	}

	public List<GuiaAtendimento> teste(StatusAtendimento statusAtendimento) {
		List<GuiaAtendimento> atendimentos = new ArrayList<GuiaAtendimento>();
		for (GuiaAtendimento guiaAtendimento : repository.findAll()) {
			if (guiaAtendimento.getStatusAtendimento() != statusAtendimento) {
				atendimentos.add(guiaAtendimento);
			}
		}
		return atendimentos;
	}
	
	
	/**
	 * Retorna as Guias de Atendimento por período de Data
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public List<GuiaAtendimento> findByPeriodo(LocalDate dataInicial, LocalDate dataFinal){
		return repository.findByPeriodo(dataInicial, dataFinal);
	}

}
