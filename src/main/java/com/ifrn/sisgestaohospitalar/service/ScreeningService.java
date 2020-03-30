package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Screening;
import com.ifrn.sisgestaohospitalar.repository.ScreeningRepository;

@Service
public class ScreeningService {
	
	@Autowired
	private ScreeningRepository repository;
	

	public Screening findOne(Long id) { 
		return repository.getOne(id); 
	}

	public void save(Screening screening) {
		repository.saveAndFlush(screening); 
	}

	public void delete(Long id) { 
		repository.deleteById(id); 
	}

	public List<Screening> listAll(){ 
		return repository.findAll(); 
	}

}
