package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.MedicalCare;
import com.ifrn.sisgestaohospitalar.repository.MedicalCareRepository;


@Service
public class MedicalCareService {

	@Autowired
	private MedicalCareRepository repository;

	public List<MedicalCare> findAll() {
		return repository.findAll();
	}

	public MedicalCare findOne(Long id) {
		return repository.getOne(id);
	}

	public void save(MedicalCare medicalCare) {
		repository.saveAndFlush(medicalCare);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
