package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.MedicationAdministration;

@Repository
public interface MedicationAdministrationRepository extends JpaRepository<MedicationAdministration, Long> {

}
