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
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaAdicionadoNaFilaException;

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
		for (GuiaAtendimento guiaAtendimentoAux : repository.findByCidadao(guiaAtendimento.getCidadao())) {
			if (guiaAtendimentoAux.getCidadao().equals(guiaAtendimento.getCidadao())
					&& guiaAtendimentoAux.getTipoServico().equals(guiaAtendimento.getTipoServico())
					&& guiaAtendimentoAux.getProfissionaldestino().equals(guiaAtendimento.getProfissionaldestino())
					&& guiaAtendimentoAux.getResponsavel().equals(guiaAtendimento.getResponsavel())
					&& guiaAtendimentoAux.getStatusAtendimento() != StatusAtendimento.FINALIZADO) {
				throw new CidadaoJaAdicionadoNaFilaException("O Cidadão " + guiaAtendimentoAux.getCidadao().getNome()
						+ " já foi adicionado a fila de atendimento para o serviço: "
						+ guiaAtendimentoAux.getTipoServico().getDescricao().toUpperCase()
						+ " e ainda não teve o atendimento finalizado");
			}
		}

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

	/**
	 * Retorna as Guias de Atendimento por período de Data
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public List<GuiaAtendimento> findByPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		return repository.findByPeriodo(dataInicial, dataFinal);
	}

	public List<GuiaAtendimento> listaAtendimentos() {
		List<GuiaAtendimento> listaAtendimentos = new ArrayList<>();
		for (GuiaAtendimento guiaAtendimento : repository.findAll()) {
			if (guiaAtendimento.getStatusAtendimento() != StatusAtendimento.NAOAGUARDOU
					&& guiaAtendimento.getStatusAtendimento() != StatusAtendimento.FINALIZADO) {
				listaAtendimentos.add(guiaAtendimento);
			}
		}
		return listaAtendimentos;
	}
}
