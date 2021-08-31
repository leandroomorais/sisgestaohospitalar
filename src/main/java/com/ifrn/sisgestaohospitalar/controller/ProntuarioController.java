package com.ifrn.sisgestaohospitalar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/prontuario")
public class ProntuarioController {
	
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	
}
