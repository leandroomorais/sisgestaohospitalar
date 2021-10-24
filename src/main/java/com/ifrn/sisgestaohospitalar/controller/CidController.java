package com.ifrn.sisgestaohospitalar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifrn.sisgestaohospitalar.repository.CidRepository;

@Controller
@RequestMapping("/cid")
public class CidController {

	@Autowired
	private CidRepository cidRepository;

	Pageable pageable = PageRequest.of(0, 10);

	@GetMapping("/buscar")
	public ResponseEntity<?> search(@Param("term") String term) {
		return ResponseEntity.ok(cidRepository.findByNomeIgnoreCaseContaining(term, pageable));
	}

}
