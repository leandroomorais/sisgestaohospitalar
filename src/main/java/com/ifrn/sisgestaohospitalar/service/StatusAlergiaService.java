package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.StatusAlergia;
import com.ifrn.sisgestaohospitalar.repository.StatusAlergiaRepository;
import com.ifrn.sisgestaohospitalar.service.exception.DataInicialMaiorQueDataFinalException;

@Service
public class StatusAlergiaService {

	@Autowired
	private StatusAlergiaRepository repository;

	public void save(StatusAlergia statusAlergia) {
		if (statusAlergia.getDataFim() != null) {
			if (statusAlergia.getDataInicio().isAfter(statusAlergia.getDataFim())) {
				throw new DataInicialMaiorQueDataFinalException("A data inicial não pode ser maior que a data final");
			}
		}
		repository.saveAndFlush(statusAlergia);
	}
}
