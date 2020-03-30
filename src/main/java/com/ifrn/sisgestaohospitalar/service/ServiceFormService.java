package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.enums.AttendanceStatus;
import com.ifrn.sisgestaohospitalar.model.ServiceForm;
import com.ifrn.sisgestaohospitalar.repository.ServiceFormRepository;



@Service
public class ServiceFormService {
	
	@Autowired
	private ServiceFormRepository repository;
	
	
	public ServiceForm findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void save(ServiceForm serviceForm) {
		repository.saveAndFlush(serviceForm);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<ServiceForm> listAll(){
		return repository.findAll();
	}
	
	public List<ServiceForm> findByStatus(AttendanceStatus status){
		return repository.findByStatus(status);
	}

}
