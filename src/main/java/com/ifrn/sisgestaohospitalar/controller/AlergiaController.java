//package com.ifrn.sisgestaohospitalar.controller;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ifrn.sisgestaohospitalar.model.Alergia;
//import com.ifrn.sisgestaohospitalar.model.Prontuario;
//import com.ifrn.sisgestaohospitalar.repository.AlergiaRepository;
//import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;
//
//@Controller
//@RequestMapping("/alergia")
//public class AlergiaController {
//	@Autowired
//	private ProntuarioRepository prontuarioRepository;
//	@Autowired
//	private AlergiaRepository alergiaRepository;
//
//	@PostMapping("/salvar")
//	public ResponseEntity<?> salvar(@Valid Alergia alergia, BindingResult result) {
//		Map<String, String> errors = new HashMap<>();
//		if (result.hasErrors()) {
//			for (FieldError error : result.getFieldErrors()) {
//				errors.put(error.getField(), error.getDefaultMessage());
//			}
//			return ResponseEntity.unprocessableEntity().body(errors);
//		}
//		Optional<Prontuario> optional = prontuarioRepository.findById(alergia.getIdProntuario());
//		if (optional.isPresent()) {
//			Prontuario prontuario = optional.get();
//			alergia.setDataCadastro(LocalDateTime.now());
//			prontuario.getAlergias().add(alergia);
//			prontuarioRepository.saveAndFlush(prontuario);
//			return ResponseEntity.ok(alergia);
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//	@GetMapping("/listar/{id}")
//	public ResponseEntity<?> listarAlergia(@PathVariable("id") Long id) {
//		if (id != null) {
//			Optional<Prontuario> optional = prontuarioRepository.findById(id);
//			if (optional.isPresent()) {
//				Prontuario prontuario = optional.get();
//				return ResponseEntity.ok(prontuario.getAlergias());
//			}
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//	@GetMapping("/excluir/{id}/{idAlergia}")
//	public ResponseEntity<?> excluirAlergia(@PathVariable("id") Long id, @PathVariable("idAlergia") Long idAlergia) {
//		Optional<Prontuario> optional = prontuarioRepository.findById(id);
//		Optional<Alergia> optional2 = alergiaRepository.findById(idAlergia);
//		if (optional.isPresent()) {
//			Prontuario prontuario = optional.get();
//			Alergia alergia = optional2.get();
//			prontuarioRepository.saveAndFlush(prontuario);
//			return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//	@GetMapping("/alergias/{id}")
//	public String listaAlergia(@PathVariable("id") Long id, ModelMap model) {
//		Optional<Prontuario> optional = prontuarioRepository.findById(id);
//		if (optional.isPresent()) {
//			Prontuario prontuario = optional.get();
//			model.addAttribute("alergias", prontuario.getAlergias());
//		}
//		return "triagem/lista-alergia";
//	}
//}
