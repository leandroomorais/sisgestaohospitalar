package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.ResultadoExame;
import com.ifrn.sisgestaohospitalar.repository.ResultadoExameRepository;
import com.ifrn.sisgestaohospitalar.service.exception.DataInicialMaiorQueDataFinalException;

@Service
public class ResultadoExameService {

	@Autowired
	private ResultadoExameRepository repository;

	public void save(ResultadoExame resultadoExame) {
		if (resultadoExame.getDataRealizacao().isAfter(resultadoExame.getDataResultado())) {
			throw new DataInicialMaiorQueDataFinalException(
					"A data de realização do exame não pode ser maior que a data do resultado");
		}
		repository.saveAndFlush(resultadoExame);
	}

}
