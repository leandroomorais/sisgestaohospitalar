package com.ifrn.sisgestaohospitalar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.repository.OcupacaoRepository;

@Controller
@RequestMapping("/ocupacao")
public class OcupacaoController {

	@Autowired
	private OcupacaoRepository ocupacaoRepository;

	@GetMapping("/buscar-codigo")
	public ResponseEntity<?> searchCodigo(@Param("term") String term) {
		return ResponseEntity.ok(ocupacaoRepository.findByCodigo(term));
	}

	@GetMapping("/buscar-nome")
	public ResponseEntity<?> searchNome(@Param("term") String term) {
		return ResponseEntity.ok(ocupacaoRepository.findByNome(term));
	}

	@GetMapping("/autocomplete")
	public ResponseEntity<?> autocomplete(@Param("term") String term) {
		return ResponseEntity.ok(ocupacaoRepository.findByNomeAutocomplete(term));
	}

}
