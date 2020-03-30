package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.MedicalCare;

@Repository
public interface MedicalCareRepository extends JpaRepository<MedicalCare, Long> {

}
