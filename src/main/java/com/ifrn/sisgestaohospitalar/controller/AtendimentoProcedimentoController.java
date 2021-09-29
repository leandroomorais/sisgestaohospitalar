package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoProcedimentoRepository;

@Controller
@RequestMapping("/atendimento-procedimento")
public class AtendimentoProcedimentoController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	@Autowired
	private AtendimentoProcedimentoRepository atendimentoProcedimentoRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;

	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarProcedimentos(@Valid AtendimentoProcedimento atendimentoProcedimento,
			BindingResult result, Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Optional<Atendimento> optional = atendimentoRepository.findById(atendimentoProcedimento.getIdAtendimento());
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			atendimentoProcedimento.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			atendimento.getAtendimentoProcedimentos().add(atendimentoProcedimento);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().body(atendimento);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/excluir/{idAtendimento}/{idAtendimentoProcedimento}")
	public ResponseEntity<?> excluir(@PathVariable("idAtendimento") Long idAtendimento,
			@PathVariable("idAtendimentoProcedimento") Long idAtendimentoProcedimento, Principal principal) {
		Optional<Atendimento> optionalAtendimento = atendimentoRepository.findById(idAtendimento);
		if (optionalAtendimento.isPresent()) {
			Atendimento atendimento = optionalAtendimento.get();
			Optional<AtendimentoProcedimento> optionalRelAtendimentoProcedimento = atendimentoProcedimentoRepository
					.findById(idAtendimentoProcedimento);
			if (optionalRelAtendimentoProcedimento.isPresent()) {
				AtendimentoProcedimento atendimentoProcedimento = optionalRelAtendimentoProcedimento.get();
				if (atendimentoProcedimento.getProfissional().getCpf() != principal.getName()) {
					return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
				}
				atendimento.getAtendimentoProcedimentos().remove(atendimentoProcedimento);
				atendimentoRepository.saveAndFlush(atendimento);
				atendimentoProcedimentoRepository.deleteById(idAtendimentoProcedimento);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
