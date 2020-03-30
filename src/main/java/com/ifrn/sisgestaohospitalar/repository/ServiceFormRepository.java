package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.enums.AttendanceStatus;
import com.ifrn.sisgestaohospitalar.model.ServiceForm;

@Repository
public interface ServiceFormRepository extends JpaRepository<ServiceForm, Long> {
	
	public List<ServiceForm> findByStatus(AttendanceStatus status);
}
