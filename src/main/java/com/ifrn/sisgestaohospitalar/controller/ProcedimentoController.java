package com.ifrn.sisgestaohospitalar.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.model.Exame;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;

@Controller
@RequestMapping("/procedimento")
public class ProcedimentoController {

	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	@GetMapping("/buscar-codigo")
	public ResponseEntity<?> findByCodigo(@Param("search") Long id) {
		Optional<Procedimento> optional = procedimentoRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> search(@Param("term") String term){
		return ResponseEntity.ok(procedimentoRepository.findByNome(term));
	}

	@GetMapping("/buscarexame")
	public ResponseEntity<?> searchExame(@Param("term") String term){
		return ResponseEntity.ok(procedimentoRepository.findByNomeExame(term));
	}
	
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<?> getProcedimento(@PathVariable("codigo") Long codigo) {
		Procedimento procedimento = procedimentoRepository.findByCodigo(codigo);
		if (procedimento != null) {
			return ResponseEntity.ok().body(procedimento);
		}

		return ResponseEntity.badRequest().build();
	}

}
