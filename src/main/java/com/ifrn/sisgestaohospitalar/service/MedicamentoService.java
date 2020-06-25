package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Medicamento;
import com.ifrn.sisgestaohospitalar.repository.MedicamentoRepository;

@Service
public class MedicamentoService {
	
	@Autowired
	private MedicamentoRepository repository;
	
	public void save(Medicamento medicamento) {
		repository.saveAndFlush(medicamento);
	}

}
