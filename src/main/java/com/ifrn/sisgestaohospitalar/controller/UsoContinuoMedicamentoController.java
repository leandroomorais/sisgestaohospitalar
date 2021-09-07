package com.ifrn.sisgestaohospitalar.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/medicamento-continuo")
public class UsoContinuoMedicamentoController {

	@Autowired
	private ProntuarioRepository prontuarioRepository;

	@GetMapping("/medicamentos/{id}")
	public ResponseEntity<?> dataTables(@PathVariable("id") Long id) {
		Optional<Prontuario> optional = prontuarioRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getUsoContinuoMedicamentos());
		}
		return ResponseEntity.notFound().build();
	}

}
