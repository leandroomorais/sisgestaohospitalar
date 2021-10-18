package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.ifrn.sisgestaohospitalar.model.Exame;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.ExameRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/exame")
public class ExameController {

	@Autowired
	private ExameRepository exameRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	List<Procedimento> procedimentos = new ArrayList<Procedimento>();

	@PostMapping("/")
	public ResponseEntity<?> exame(@Valid Exame exame, BindingResult result, Principal principal) {
		System.out.println("chegou aqui -------------------------------");
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Prontuario> optional = prontuarioRepository.findById(exame.getProntuario().getId());
		if (optional.isPresent()) {
			Prontuario prontuario = optional.get();
			exame.setDataRegistro(LocalDateTime.now());
			exame.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			exame.setProcedimentos(procedimentos);
			prontuario.getExames().add(exame);
			prontuarioRepository.saveAndFlush(prontuario);
			procedimentos.clear();
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/procedimentos/")
	public ResponseEntity<?> procedimentosexame(@Valid Procedimento procedimento, BindingResult result,
			Principal principal) {
		System.out.println("chegou aqui -------------------------------");
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Procedimento procedimentook = procedimentoRepository.findByCodigo(procedimento.getCodigo());
		if (procedimentook != null) {
			procedimentos.add(procedimentook);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/listarprocedimentos/")
	public ResponseEntity<?> listarprocedimentosexame() {
		System.out.println("aqui");
		// if (!procedimentos.isEmpty()) {
		// System.out.println("aqui no if");
		return ResponseEntity.ok().body(procedimentos);

		// }
		// System.out.println("aqui fora do if");
		// return ResponseEntity.badRequest().build();

	}

	@GetMapping("/excluirprocedimentos/{idProcedimentoExame}")
	public ResponseEntity<?> excluirprocedimentosexame(@PathVariable("idProcedimentoExame") Long id) {
		for (Procedimento proc : procedimentos) {
			if (proc.getCodigo().equals(id)) {
				procedimentos.remove(proc);
				return ResponseEntity.ok().body(procedimentos);
			} else {
			}
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/listar/atendimento/{idAtendimento}")
	public ResponseEntity<?> exames(@PathVariable("idAtendimento") Long id) {
		// Atendimento atendimento = atendimentoRepository.getOne(id);
		System.out.println("aqui");
		// if (atendimento != null) {
		System.out.println("aqui no if");
		List<Exame> exames = exameRepository.findAll();
		for (Exame e : exames) {
			System.out.println("aqui no if" + e.getNomeexame());
		}
		return ResponseEntity.ok().body(exames);
		// }
		// System.out.println("aqui fora if");
		// return ResponseEntity.badRequest().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getExame(@PathVariable("id") Long id) {
		Optional<Exame> optional = exameRepository.findById(id);
		if (optional.isPresent()) {
			Exame exame = optional.get();
			return ResponseEntity.ok().body(exame);
		}
		return ResponseEntity.badRequest().build();
	}

}