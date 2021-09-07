package com.ifrn.sisgestaohospitalar.controller;

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
import com.ifrn.sisgestaohospitalar.model.RelAtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.RelAtendimentoProcedimentoRepository;

@Controller
@RequestMapping("/atendimento-procedimento")
public class RelAtendimentoProcedimentoController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	@Autowired
	private RelAtendimentoProcedimentoRepository relAtendimentoProcedimentoRepository;

	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarProcedimentos(@Valid RelAtendimentoProcedimento relAtendimentoProcedimento,
			BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Optional<Atendimento> optional = atendimentoRepository.findById(relAtendimentoProcedimento.getIdAtendimento());
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			atendimento.getAtendimentoProcedimentos().add(relAtendimentoProcedimento);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().body(atendimento);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/excluir/{idAtendimento}/{idAtendimentoProcedimento}")
	public ResponseEntity<?> excluir(@PathVariable("idAtendimento") Long idAtendimento,
			@PathVariable("idAtendimentoProcedimento") Long idAtendimentoProcedimento) {
		Optional<Atendimento> optionalAtendimento = atendimentoRepository.findById(idAtendimento);
		if (optionalAtendimento.isPresent()) {
			Atendimento atendimento = optionalAtendimento.get();
			Optional<RelAtendimentoProcedimento> optionalRelAtendimentoProcedimento = relAtendimentoProcedimentoRepository
					.findById(idAtendimentoProcedimento);
			if (optionalRelAtendimentoProcedimento.isPresent()) {
				RelAtendimentoProcedimento atendimentoProcedimento = optionalRelAtendimentoProcedimento.get();
				atendimento.getAtendimentoProcedimentos().remove(atendimentoProcedimento);
				atendimentoRepository.saveAndFlush(atendimento);
				relAtendimentoProcedimentoRepository.deleteById(idAtendimentoProcedimento);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
