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
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Consulta;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@GetMapping("/verificar/{id}")
	public ResponseEntity<?> verificar(@PathVariable("id") Long id) {
		Atendimento atendimento = atendimentoRepository.getOne(id);
		Consulta consulta = atendimento.getConsulta();
		if (consulta != null) {
			return ResponseEntity.ok().body(consulta);
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/")
	public ResponseEntity<?> salvar(@Valid Consulta consulta, BindingResult result, Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Optional<Atendimento> optional = atendimentoRepository.findById(consulta.getAtendimento().getId());
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			consulta.setDataRegistro(LocalDateTime.now());
			consulta.getAvaliacao().getSinaisVitais().setUltimaAtualizacao(LocalDateTime.now());
			atendimento.setConsulta(consulta);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
