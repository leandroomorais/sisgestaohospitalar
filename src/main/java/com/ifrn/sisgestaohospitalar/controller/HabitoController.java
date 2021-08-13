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

import com.ifrn.sisgestaohospitalar.model.Habito;
import com.ifrn.sisgestaohospitalar.repository.HabitoRepository;
import com.ifrn.sisgestaohospitalar.service.HabitoService;
import com.ifrn.sisgestaohospitalar.service.exception.HabitoMesmoNomeJaCadastradoException;

@Controller
@RequestMapping("/habito")
public class HabitoController {

	@Autowired
	private HabitoRepository habitoRepository;

	@Autowired
	private HabitoService habitoService;

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@Valid Habito habito, BindingResult result) {
		if (result.hasErrors()) {
			String erro = "";
			for (FieldError error : result.getFieldErrors()) {
				erro = error.getDefaultMessage();
			}
			return ResponseEntity.unprocessableEntity().body(erro);
		}
		try {
			habitoService.save(habito);
			return ResponseEntity.ok(habito);
		} catch (HabitoMesmoNomeJaCadastradoException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(habitoRepository.findAll());
	}
}
