package com.ifrn.sisgestaohospitalar.controller;

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

import com.ifrn.sisgestaohospitalar.dto.StatusDoencaDTO;
import com.ifrn.sisgestaohospitalar.model.Doenca;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.model.StatusDoenca;
import com.ifrn.sisgestaohospitalar.repository.DoencaRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;
import com.ifrn.sisgestaohospitalar.repository.StatusDoencaRepository;
import com.ifrn.sisgestaohospitalar.service.StatusDoencaService;
import com.ifrn.sisgestaohospitalar.service.exception.DataInicialMaiorQueDataFinalException;

@Controller
@RequestMapping("/status-doenca")
public class StatusDoencaController {

	@Autowired
	private StatusDoencaService statusDoencaService;
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	@Autowired
	private StatusDoencaRepository statusDoencaRepository;
	@Autowired
	private DoencaRepository doencaRepository;

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@Valid StatusDoenca statusDoenca, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		try {
			Optional<Prontuario> optional = prontuarioRepository.findById(statusDoenca.getIdProntuario());
			if (optional.isPresent()) {
				Doenca doenca = statusDoenca.getDoenca();
				if (statusDoenca.getDoenca().getDataCadastro() == null) {
					doenca.setDataCadastro(LocalDateTime.now());
				}
				doencaRepository.saveAndFlush(doenca);
				statusDoencaService.save(statusDoenca);
				Prontuario prontuario = optional.get();
				prontuario.getStatusDoencas().add(statusDoenca);
				prontuarioRepository.saveAndFlush(prontuario);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		} catch (DataInicialMaiorQueDataFinalException e) {
			errors.put("dataInicial", e.getMessage());
			return ResponseEntity.unprocessableEntity().body(errors);
		}
	}

	@GetMapping("/doencas/{id}")
	public ResponseEntity<?> dataTables(@PathVariable("id") Long id) {
		Optional<Prontuario> optional = prontuarioRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getStatusDoencas());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/editar/{id}")
	public ResponseEntity<?> editarDoenca(@PathVariable("id") Long id) {
		Optional<StatusDoenca> optional = statusDoencaRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/editar")
	public ResponseEntity<?> editar(@Valid StatusDoencaDTO statusDoencaDTO, BindingResult result) {
		System.out.println("Cheguei aqui meu fiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			System.out.println("Result has errors");
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		

		Optional<StatusDoenca> optional = statusDoencaRepository.findById(statusDoencaDTO.getId());
		if (optional.isPresent()) {
			System.out.println("Status DoencaDTO: " + statusDoencaDTO.toString());
			StatusDoenca statusDoenca = optional.get();
			statusDoenca.setDataInicio(statusDoencaDTO.getDataInicio());
			statusDoenca.setDataFim(statusDoencaDTO.getDataFim());
			statusDoenca.setSituacaoCondicao(statusDoencaDTO.getSituacaoCondicao());
			statusDoenca.setNota(statusDoencaDTO.getNota());
			Optional<Doenca> optionalDoenca = doencaRepository.findById(statusDoencaDTO.getDoenca().getId());
			Doenca doenca = optionalDoenca.get();
			doenca.setNome(statusDoencaDTO.getDoenca().getNome());
			doenca.setCid(statusDoencaDTO.getDoenca().getCid());
			doencaRepository.saveAndFlush(doenca);
			statusDoencaRepository.saveAndFlush(statusDoenca);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
