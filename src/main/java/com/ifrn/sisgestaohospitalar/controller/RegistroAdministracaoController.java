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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifrn.sisgestaohospitalar.model.Prescricao;
import com.ifrn.sisgestaohospitalar.model.RegistroAdministracao;
import com.ifrn.sisgestaohospitalar.repository.PrescricaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;

@Controller
@RequestMapping("/registro-administracao")
public class RegistroAdministracaoController {
	
	@Autowired
	private PrescricaoRepository prescricaoRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@PostMapping("/")
	public ResponseEntity<?> salvarRegistro(@Valid RegistroAdministracao registroAdministracao, BindingResult result,
			Principal principal) {
		System.out.println(registroAdministracao.toString());
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Prescricao> optional = prescricaoRepository.findById(registroAdministracao.getIdPrescricao());
		if (optional.isPresent()) {
			Prescricao prescricao = optional.get();
			registroAdministracao.setProfissionalResponsavel(profissionalRepository.findByCpf(principal.getName()));
			registroAdministracao.setDataAdministracao(LocalDateTime.now());
			prescricao.getRegistrosAdministracao().add(registroAdministracao);
			prescricaoRepository.saveAndFlush(prescricao);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	

}
