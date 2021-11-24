package com.ifrn.sisgestaohospitalar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.repository.GrupoExameRepository;

@Controller
@RequestMapping("/grupo-exame")
public class GrupoExameController {

	@Autowired
	private GrupoExameRepository grupoExameRepository;

	@GetMapping("/")
	public ResponseEntity<?> listarGrupos() {
		return ResponseEntity.ok().body(grupoExameRepository.findAll());
	}

}
