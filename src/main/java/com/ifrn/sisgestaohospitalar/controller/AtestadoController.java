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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Atestado;
import com.ifrn.sisgestaohospitalar.model.Cid;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.AtestadoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/atestado")
public class AtestadoController {

	@Autowired
	private AtestadoRepository atestadoRepository;
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	@Autowired
	private CidRepository cidRepository;

	private List<Cid> cidsAtestado = new ArrayList<>();

	@GetMapping("/cid/{id}")
	public ResponseEntity<?> adicionarCid(@PathVariable("id") Long id) {
		Optional<Cid> optional = cidRepository.findById(id);
		if (optional.isPresent()) {
			cidsAtestado.add(optional.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/listar/cids/")
	public ResponseEntity<?> listarCidAtestado() {
		return ResponseEntity.ok().body(cidsAtestado);
	}

//	@GetMapping("/{idProntuario}/{idAtendimento}")
//	public ResponseEntity<?> novoAtestado(@PathVariable("idProntuario") Long idProntuario,
//			@PathVariable("idAtendimento") Long idAtendimento) {
//		Optional<Prontuario> optionalProntuario = prontuarioRepository.findById(idProntuario);
//		Optional<Atendimento> optionalAtendimento = atendimentoRepository.findById(idAtendimento);
//		if (optionalProntuario.isPresent() && optionalProntuario.isPresent()) {
//			Prontuario prontuario = optionalProntuario.get();
//			Atendimento atendimento = optionalAtendimento.get();
//			Atestado atestado = new Atestado();
//			atestado.setProntuario(prontuario);
//			atestado.setAtendimento(atendimento);
//			atestado.setPeriodo(1);
//			atestadoRepository.saveAndFlush(atestado);
//			return ResponseEntity.ok().body(atestado);
//		}
//		return ResponseEntity.badRequest().build();
//	}

	@PostMapping("/")
	public ResponseEntity<?> salvar(@Valid Atestado atestado, BindingResult result, Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Optional<Prontuario> optional = prontuarioRepository.findById(atestado.getProntuario().getId());
		if (optional.isPresent()) {
			Prontuario prontuario = optional.get();
			atestado.setDataRegistro(LocalDateTime.now());
			atestado.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			atestado.setCids(cidsAtestado);
			prontuario.getAtestados().add(atestado);
			prontuarioRepository.saveAndFlush(prontuario);
			cidsAtestado.clear();
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/listar/atendimento/{idAtendimento}")
	public ResponseEntity<?> atestados(@PathVariable("idAtendimento") Long id) {
		Atendimento atendimento = atendimentoRepository.getOne(id);
		if (atendimento != null) {
			return ResponseEntity.ok().body(atestadoRepository.findByAtendimento(atendimento));
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/excluir/{idAtestado}")
	public ResponseEntity<?> excluirAtestado(@PathVariable("idAtestado") Long idAtestado, Principal principal) {
		Atestado atestado = atestadoRepository.getOne(idAtestado);
		Prontuario prontuario = prontuarioRepository.getOne(atestado.getProntuario().getId());
		if (atestado.getAtendimento().getStatus().equals(Status.FINALIZADO)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		prontuario.getAtestados().remove(atestado);
		prontuarioRepository.saveAndFlush(prontuario);
		atestadoRepository.delete(atestado);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/cid/excluir/{id}")
	public ResponseEntity<?> exlcuirCid(@PathVariable("id") Long id) {
		for (Cid cid : cidsAtestado) {
			if (cid.getId().equals(id)) {
				cidsAtestado.remove(cid);
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/limpar/cids/")
	public ResponseEntity<?> limparCidAtestado() {
		cidsAtestado.clear();
		return ResponseEntity.ok().build();
	}


//	@DeleteMapping("/voltar/excluir/{id}")
//	public ResponseEntity<?> excluirAtestadoFuncaoVoltar(@PathVariable("id") Long id) {
//		Atestado atestado = atestadoRepository.getOne(id);
//		if (atestado != null && atestado.getDataRegistro() == null) {
//			atestadoRepository.delete(atestado);
//			return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.badRequest().build();
//	}

}
