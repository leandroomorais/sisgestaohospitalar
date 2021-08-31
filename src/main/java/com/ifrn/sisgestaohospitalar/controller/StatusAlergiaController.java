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
import com.ifrn.sisgestaohospitalar.dto.StatusAlergiaDTO;
import com.ifrn.sisgestaohospitalar.model.Alergia;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.model.StatusAlergia;
import com.ifrn.sisgestaohospitalar.repository.AlergiaRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;
import com.ifrn.sisgestaohospitalar.repository.StatusAlergiaRepository;
import com.ifrn.sisgestaohospitalar.service.StatusAlergiaService;
import com.ifrn.sisgestaohospitalar.service.exception.DataInicialMaiorQueDataFinalException;

@Controller
@RequestMapping("/status-alergia")
public class StatusAlergiaController {

	@Autowired
	private StatusAlergiaService statusAlergiaService;
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	@Autowired
	private StatusAlergiaRepository statusAlergiaRepository;
	@Autowired
	private AlergiaRepository alergiaRepository;

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@Valid StatusAlergia statusAlergia, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		try {
			Optional<Prontuario> optional = prontuarioRepository.findById(statusAlergia.getIdProntuario());
			if (optional.isPresent()) {
				Alergia alergia = statusAlergia.getAlergia();
				if (statusAlergia.getAlergia().getDataCadastro() == null) {
					alergia.setDataCadastro(LocalDateTime.now());
				}
				alergiaRepository.saveAndFlush(alergia);
				statusAlergiaService.save(statusAlergia);
				Prontuario prontuario = optional.get();
				prontuario.getStatusAlergias().add(statusAlergia);
				prontuarioRepository.saveAndFlush(prontuario);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		} catch (DataInicialMaiorQueDataFinalException e) {
			errors.put("dataInicial", e.getMessage());
			return ResponseEntity.unprocessableEntity().body(errors);
		}

	}

	@GetMapping("/alergias/{id}")
	public ResponseEntity<?> dataTables(@PathVariable("id") Long id) {
		Optional<Prontuario> optional = prontuarioRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getStatusAlergias());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/editar/{id}")
	public ResponseEntity<?> editarAlergia(@PathVariable("id") Long id) {
		Optional<StatusAlergia> optional = statusAlergiaRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/editar")
	public ResponseEntity<?> editar(@Valid StatusAlergiaDTO statusAlergiaDTO, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<StatusAlergia> optional = statusAlergiaRepository.findById(statusAlergiaDTO.getId());
		if (optional.isPresent()) {
			StatusAlergia statusAlergia = optional.get();
			statusAlergia.setDataInicio(statusAlergiaDTO.getDataInicio());
			statusAlergia.setDataFim(statusAlergiaDTO.getDataFim());
			statusAlergia.setSituacaoCondicao(statusAlergiaDTO.getSituacaoCondicao());
			Optional<Alergia> optionalAlergia = alergiaRepository.findById(statusAlergiaDTO.getAlergia().getId());
			Alergia alergia = optionalAlergia.get();
			alergia.setNome(statusAlergiaDTO.getAlergia().getNome());
			alergia.setCid(statusAlergiaDTO.getAlergia().getCid());
			alergiaRepository.saveAndFlush(alergia);
			statusAlergiaRepository.saveAndFlush(statusAlergia);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
