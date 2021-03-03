package com.ifrn.sisgestaohospitalar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ifrn.sisgestaohospitalar.model.Logradouro;
import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;

@Controller
@RequestMapping("/logradouro")
public class LogradouroController {

	@Autowired
	private LogradouroRepository logradouroRepository;

	@GetMapping
	public ResponseEntity<List<Logradouro>> autoCompletarPorNome(@RequestParam("term") String descLogradouro) {
		return ResponseEntity.ok(logradouroRepository.findBydescLogradouroIgnoreCaseContaining(descLogradouro));
	}

}
