package com.ifrn.sisgestaohospitalar.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaCadastradoException;
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
		Optional<Cidadao> optional = repository.findByCpf(cidadao.getCpf());
		if(optional.isPresent()) {
			throw new CidadaoJaCadastradoException("Já existe um Cidadão cadastrado com este mesmo CPF");
		}
		if(cidadao.getDatanascimento().isAfter(LocalDate.now())) {
			throw new DataNascimentoMaiorQueDataAtualException("A data de nascimento é maior que a data atual");
		}
		repository.save(cidadao);
	}



}
