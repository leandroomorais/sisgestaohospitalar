package com.ifrn.sisgestaohospitalar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.repository.ExameRepository;
import com.ifrn.sisgestaohospitalar.repository.ResultadoExameRepository;

@Controller
@RequestMapping("/resultadoexame")
public class ResultadoExameController {
	
	@Autowired
	private ResultadoExameRepository resultadoexameRepository;
	@Autowired
	private ExameRepository exameRepository;

}
