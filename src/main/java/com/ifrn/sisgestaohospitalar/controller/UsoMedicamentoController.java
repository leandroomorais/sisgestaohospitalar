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
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.model.UsoContinuoMedicamento;
import com.ifrn.sisgestaohospitalar.model.UsoMedicamento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/uso-medicamento")
public class UsoMedicamentoController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	@Autowired
	private ProntuarioRepository prontuarioRepository;

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@Valid UsoMedicamento usoMedicamento, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		usoMedicamento.toString();

		Optional<Atendimento> optionalAtendimento = atendimentoRepository.findById(usoMedicamento.getIdAtendimento());
		if (optionalAtendimento.isPresent()) {
			Atendimento atendimento = optionalAtendimento.get();
			if (usoMedicamento.isUsoContinuo()) {
				Optional<Prontuario> optionalProntuario = prontuarioRepository
						.findById(usoMedicamento.getIdProntuario());
				UsoContinuoMedicamento usoContinuoMedicamento = new UsoContinuoMedicamento();
				usoContinuoMedicamento.setMedicamento(usoMedicamento.getMedicamento());
				usoContinuoMedicamento.setNota(usoMedicamento.getNota());
				usoContinuoMedicamento.setDataCadastro(LocalDateTime.now());
				Prontuario prontuario = optionalProntuario.get();
				prontuario.getUsoContinuoMedicamentos().add(usoContinuoMedicamento);
				prontuarioRepository.saveAndFlush(prontuario);
				return ResponseEntity.ok().build();
			}
			usoMedicamento.setDataCadastro(LocalDateTime.now());
			atendimento.getUsoMedicamentos().add(usoMedicamento);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/medicamentos/{id}")
	public ResponseEntity<?> dataTables(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getUsoMedicamentos());
		}
		return ResponseEntity.notFound().build();
	}

}
