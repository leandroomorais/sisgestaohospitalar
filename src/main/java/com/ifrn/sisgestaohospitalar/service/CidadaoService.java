package com.ifrn.sisgestaohospitalar.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.service.exception.DataNascimentoMaiorQueDataAtualException;

@Service
public class CidadaoService {

	@Autowired
	private CidadaoRepository repository;

	/**
	 * Salva os objetos do tipo Cidadao
	 * 
	 * @param cidadao
	 */
	public void save(Cidadao cidadao) {
		if (cidadao.getDataNascimento().isAfter(LocalDate.now())) {
			throw new DataNascimentoMaiorQueDataAtualException("A data de nascimento é maior que a data atual");
		}
		repository.saveAndFlush(cidadao);
	}

	public void update(Cidadao cidadao) {
		repository.saveAndFlush(cidadao);
	}

}
