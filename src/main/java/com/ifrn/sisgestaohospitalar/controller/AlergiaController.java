package com.ifrn.sisgestaohospitalar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifrn.sisgestaohospitalar.repository.AlergiaRepository;

@Controller
@RequestMapping("/alergia")
public class AlergiaController {

	@Autowired
	private AlergiaRepository alergiaRepository;

	@GetMapping("/buscar")
	public ResponseEntity<?> search(@Param("term") String term) {
		return ResponseEntity.ok(alergiaRepository.findByNomeIgnoreCaseContaining(term));
	}

}
