package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifrn.sisgestaohospitalar.enums.StatusExame;
import com.ifrn.sisgestaohospitalar.model.Exame;
import com.ifrn.sisgestaohospitalar.model.ResultadoExame;
import com.ifrn.sisgestaohospitalar.repository.ExameRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.ResultadoExameRepository;
import com.ifrn.sisgestaohospitalar.service.ResultadoExameService;

@Controller
@RequestMapping("/resultadoexame")
public class ResultadoExameController {

	@Autowired
	private ResultadoExameService resultadoExameService;
	@Autowired
	private ResultadoExameRepository resultadoexameRepository;
	@Autowired
	private ExameRepository exameRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;

	@PostMapping("/")
	public ResponseEntity<?> resultadoexame(@Valid ResultadoExame resultadoexame, BindingResult result,
			Principal principal) {

		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		resultadoexame.setDataCadastro(LocalDateTime.now());
		resultadoexame.setProfissional(profissionalRepository.findByCpf(principal.getName()));
		resultadoExameService.save(resultadoexame);
		Exame exame = resultadoexame.getExame();
		if (exame.getProcedimentos().size() == exame.getResultados().size()) {
			exame.setStatus(StatusExame.AVALIADO);
			exameRepository.save(exame);
		}
		return ResponseEntity.ok().build();

		// Optional<Prontuario> optional =
		// prontuarioRepository.findById(exame.getProntuario().getId());
//		if (optional.isPresent()) {
//			Prontuario prontuario = optional.get();
//			exame.setDataSolicitacao(LocalDateTime.now());
//			exame.setStatus(StatusExame.SOLICITADO);
//			exame.setProfissional(profissionalRepository.findByCpf(principal.getName()));
//			exame.setProcedimentos(procedimentos);
//			prontuario.getExames().add(exame);
//			prontuarioRepository.saveAndFlush(prontuario);
//			procedimentos.clear();
//
//			return ResponseEntity.ok().build();
//		}
//
//		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/listarprocedimentosexame/{id}")
	public ResponseEntity<?> procedimentosExame(@PathVariable("id") Long id) {
		Optional<Exame> optional = exameRepository.findById(id);
		if (optional.isPresent()) {
			Exame exame = optional.get();
			return ResponseEntity.ok().body(exame.getProcedimentos());
		}

		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getResultadoExame(@PathVariable("id") Long id) {
		Optional<ResultadoExame> optional = resultadoexameRepository.findById(id);
		if (optional.isPresent()) {
			ResultadoExame resultadoexame = optional.get();
			return ResponseEntity.ok().body(resultadoexame);
		}

		return ResponseEntity.badRequest().build();
	}
}
