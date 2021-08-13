package com.ifrn.sisgestaohospitalar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.model.Comorbidade;
import com.ifrn.sisgestaohospitalar.repository.ComorbidadeRepository;
import com.ifrn.sisgestaohospitalar.service.ComorbidadeService;
import com.ifrn.sisgestaohospitalar.service.exception.ComorbidadeMesmoNomeJaCadastradoException;

@Controller
@RequestMapping("/comorbidade")
public class ComorbidadeController {
	
	@Autowired
	private ComorbidadeRepository comorbidadeRepository;
	@Autowired
	private ComorbidadeService comorbidadeService;
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@Valid Comorbidade comorbidade, BindingResult result){
		if(result.hasErrors()) {
			String erro = "";
			for(FieldError error : result.getFieldErrors()) {
				erro = error.getDefaultMessage();
			}
			return ResponseEntity.unprocessableEntity().body(erro);
		}
		try {
			comorbidadeService.save(comorbidade);
			return ResponseEntity.ok(comorbidade);
		} catch (ComorbidadeMesmoNomeJaCadastradoException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(comorbidadeRepository.findAll());
	}

}
