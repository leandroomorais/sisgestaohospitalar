package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Diagnostico;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.DiagnosticoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/diagnostico")
public class DiagnosticoController {

	@Autowired
	private ProntuarioRepository prontuarioRepository;

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private DiagnosticoRepository diagnosticoRepository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@PostMapping("/")
	public ResponseEntity<?> diagnostico(@Valid Diagnostico diagnostico, Principal principal, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Prontuario prontuario = prontuarioRepository.getOne(diagnostico.getProntuario().getId());
		if (prontuario != null) {
			diagnostico.setDataRegistro(LocalDateTime.now());
			diagnostico.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			prontuario.getDiagnostico().add(diagnostico);
			prontuarioRepository.saveAndFlush(prontuario);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@RequestMapping("/listar/atendimento/{idAtendimento}")
	public ResponseEntity<?> diagnosticos(@PathVariable("idAtendimento") Long id) {
		Atendimento atendimento = atendimentoRepository.getOne(id);
		if (atendimento != null) {
			List<Diagnostico> diagnosticos = diagnosticoRepository.findByAtendimento(atendimento);
			return ResponseEntity.ok().body(diagnosticos);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{idDiagnostico}")
	public ResponseEntity<?> excluir(@PathVariable("idDiagnostico") Long idDiagnostico, Principal principal) {
		Optional<Diagnostico> optionalDiagnostico = diagnosticoRepository.findById(idDiagnostico);
		if (optionalDiagnostico.isPresent()) {
			Diagnostico diagnostico = optionalDiagnostico.get();
			Prontuario prontuario = prontuarioRepository.getOne(diagnostico.getProntuario().getId());
			if (!diagnostico.getProfissional().getCpf().equals(principal.getName())) {
				String msg = "Diagnóstico cadastrado por: " + diagnostico.getProfissional().getNome()
						+ " . Não é possível excluir";
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
			}

			if (diagnostico.getAtendimento().getStatus().equals(Status.FINALIZADO)) {
				String msg = "Atendimento finalizado. Não é possível excluir.";
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
			}

			prontuario.getDiagnostico().remove(diagnostico);
			prontuarioRepository.saveAndFlush(prontuario);
			diagnosticoRepository.delete(diagnostico);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
