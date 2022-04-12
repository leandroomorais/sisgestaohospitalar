package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Consulta;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ConsultaRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.utils.Datas;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private ProfissionalRepository profissionalRepository;


	@GetMapping("/verificar/{idAtendimento}")
	public ResponseEntity<?> verificar(@PathVariable("idAtendimento") Long idAtendimento, Principal principal) {
		Atendimento atendimento = atendimentoRepository.getOne(idAtendimento);
		for (Consulta consulta : atendimento.getConsultas()) {
			if (consulta.getProfissional().getCpf().equals(principal.getName())) {
				return ResponseEntity.ok().body(consulta);
			}
		}
		return ResponseEntity.notFound().build();
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
			Profissional profissional = profissionalRepository.findByCpf(principal.getName());
			consulta.setProfissional(profissional);
			consulta.setDataRegistro(LocalDateTime.now());
			if (atendimento.getSinaisVitais().isEmpty()) {
				atendimento.setSinaisVitais(new ArrayList<>());
			}
			consulta.getSinaisVitais().setUltimaAtualizacao(LocalDateTime.now());
			if (!atendimento.getSinaisVitais().contains(consulta.getSinaisVitais())) {
				atendimento.getSinaisVitais().add(consulta.getSinaisVitais());
			}
			if (atendimento.getConsultas().isEmpty()) {
				atendimento.setConsultas(new ArrayList<>());
			}
			atendimento.getConsultas().add(consulta);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
