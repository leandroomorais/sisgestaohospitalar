package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.StatusDoenca;
import com.ifrn.sisgestaohospitalar.repository.StatusDoencaRepository;
import com.ifrn.sisgestaohospitalar.service.exception.DataInicialMaiorQueDataFinalException;

@Service
public class StatusDoencaService {

	@Autowired
	private StatusDoencaRepository repository;

	public void save(StatusDoenca statusDoenca) {
		if (statusDoenca.getDataFim() != null) {
			if (statusDoenca.getDataInicio().isAfter(statusDoenca.getDataFim())) {
				throw new DataInicialMaiorQueDataFinalException("A data inicial não pode ser maior que a data final");
			}
		}
		repository.saveAndFlush(statusDoenca);
	}

}
