package com.ifrn.sisgestaohospitalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifrn.sisgestaohospitalar.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByNome(String nome);

}
