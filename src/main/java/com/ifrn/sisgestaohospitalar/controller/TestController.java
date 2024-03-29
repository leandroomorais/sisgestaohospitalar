package com.ifrn.sisgestaohospitalar.controller;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.repository.ArquivoBPARepository;
import com.ifrn.sisgestaohospitalar.service.ArquivoBPAService;
import com.ifrn.sisgestaohospitalar.utils.EscritorTXT;

import groovy.cli.Option;

@Controller
@RequestMapping("/teste")
public class TestController {

	@Autowired
	private ArquivoBPAService arquivoBPAService;

	@Autowired
	private ArquivoBPARepository arquivoBPARepository;

	EscritorTXT escritorTXT = new EscritorTXT();

	@GetMapping("/{mes}")
	public ResponseEntity<?> getAtendimentos(@PathVariable("mes") String mes) {
		String[] periodo = mes.split("-");
		
		// arquivoBPAService.processarArquivoBPA(Integer.parseInt(periodo[0]),
		// Integer.parseInt(periodo[1]))
		return ResponseEntity.ok().body(
				arquivoBPAService.processarArquivoBPA(Integer.parseInt(periodo[0]), Integer.parseInt(periodo[1])));
	}

	@GetMapping("/apagar")
	public ResponseEntity<?> deletar() {
		arquivoBPARepository.deleteAll();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/arquivo/{id}")
	public ResponseEntity<?> pegarArquivo(@PathVariable("id") Long id) {
		return ResponseEntity.ok(arquivoBPARepository.findById(id));
	}

}
