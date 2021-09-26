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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.dto.PrescricaoDTO;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Prescricao;
import com.ifrn.sisgestaohospitalar.model.RegistroAdministracao;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.PrescricaoRepository;

@Controller
@RequestMapping("/prescricao")
public class PrescricaoController {

	@Autowired
	private PrescricaoRepository prescricaoRepository;
	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> editarPrescricao(@PathVariable("id") Long id) {
		Optional<Prescricao> optional = prescricaoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/excluir/{idAtendimento}/{idPrescricao}")
	public ResponseEntity<?> excluirPrescricao(@PathVariable("idAtendimento") Long idAtendimento,
			@PathVariable("idPrescricao") Long idPrescricao) {
		Atendimento atendimento = atendimentoRepository.getOne(idAtendimento);
		Prescricao prescricao = prescricaoRepository.getOne(idPrescricao);
		atendimento.getPrescricoes().remove(prescricao);
		atendimentoRepository.saveAndFlush(atendimento);
		prescricaoRepository.delete(prescricao);
		return ResponseEntity.ok().build();

	}
	
	@PostMapping("/registro")
	public ResponseEntity<?> salvarRegistro(@Valid RegistroAdministracao registroAdministracao, BindingResult result) {
		System.out.println(registroAdministracao.toString());
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Prescricao> optional = prescricaoRepository.findById(registroAdministracao.getIdPrescricao());
		if (optional.isPresent()) {
			Prescricao prescricao = optional.get();
			registroAdministracao.setDataAdministracao(LocalDateTime.now());
			prescricao.getRegistrosAdministracao().add(registroAdministracao);
			prescricaoRepository.saveAndFlush(prescricao);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/editar")
	public ResponseEntity<?> editar(@Valid PrescricaoDTO prescricaoDTO, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Prescricao> optional = prescricaoRepository.findById(prescricaoDTO.getId());
		if (optional.isPresent()) {
			Prescricao prescricao = optional.get();
			prescricao.setMedicamento(prescricaoDTO.getMedicamento());
			prescricao.setAdministracaoNoAtendimento(prescricaoDTO.isAdministracaoNoAtendimento());
			prescricao.setDoseUnica(prescricaoDTO.isDoseUnica());
			prescricao.setUsoContinuo(prescricaoDTO.isUsoContinuo());
			prescricao.setOrientacoes(prescricaoDTO.getOrientacoes());
			prescricao.setPosologia(prescricaoDTO.getPosologia());
			prescricao.setQuantidade(prescricaoDTO.getQuantidade());
			prescricao.setViaAdministracao(prescricaoDTO.getViaAdministracao());
			prescricaoRepository.saveAndFlush(prescricao);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
