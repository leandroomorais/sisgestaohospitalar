package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Comparator;
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
import com.ifrn.sisgestaohospitalar.model.Antropometria;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/antropometria")
public class AntropometriaController {

	@Autowired
	private ProntuarioRepository prontuarioRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;

	@PostMapping("/")
	public ResponseEntity<?> antropometria(@Valid Antropometria antropometria, BindingResult result,
			Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Optional<Prontuario> optional = prontuarioRepository.findById(antropometria.getProntuario().getId());
		if (optional.isPresent()) {
			Prontuario prontuario = optional.get();
			antropometria.setDataCadastro(LocalDateTime.now());
			antropometria.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			prontuario.getAntropometrias().add(antropometria);
			prontuarioRepository.saveAndFlush(prontuario);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/listar/prontuario/{idProntuario}")
	public ResponseEntity<?> antropometrias(@PathVariable("idProntuario") Long idProntuario) {
		Prontuario prontuario = prontuarioRepository.getOne(idProntuario);
		if (prontuario != null) {
			return ResponseEntity.ok().body(prontuario.getAntropometrias());
		}
		return ResponseEntity.badRequest().build();
	}

}
