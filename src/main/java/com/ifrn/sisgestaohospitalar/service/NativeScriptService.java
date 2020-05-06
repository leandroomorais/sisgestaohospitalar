package com.ifrn.sisgestaohospitalar.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class NativeScriptService {

	@PersistenceContext
	private EntityManager entityManager;

	public void execute(String sql) {
		entityManager.createNativeQuery(sql).executeUpdate();
	}

}
