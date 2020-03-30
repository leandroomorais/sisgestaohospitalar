package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.MedicationAdministration;
import com.ifrn.sisgestaohospitalar.repository.MedicationAdministrationRepository;


@Service
public class MedicationAdministrationService {
	
	@Autowired
	private MedicationAdministrationRepository repository;
	
	public List<MedicationAdministration> findAll() {
		return repository.findAll();
	}
	
	public MedicationAdministration findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void save(MedicationAdministration medicationAdministration) {
		repository.saveAndFlush(medicationAdministration);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
