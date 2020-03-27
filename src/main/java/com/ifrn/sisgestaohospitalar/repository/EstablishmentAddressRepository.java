package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.EstablishmentAddress;

@Repository
public interface EstablishmentAddressRepository extends JpaRepository<EstablishmentAddress, Long>{

}
