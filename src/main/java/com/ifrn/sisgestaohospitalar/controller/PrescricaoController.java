package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
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
import com.ifrn.sisgestaohospitalar.dto.PrescricaoDTO;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Prescricao;
import com.ifrn.sisgestaohospitalar.model.PrescricaoExterna;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.PrescricaoExternaRepository;
import com.ifrn.sisgestaohospitalar.repository.PrescricaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;

@Controller
@RequestMapping("/prescricao")
public class PrescricaoController {

	@Autowired
	private PrescricaoRepository prescricaoRepository;
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;
	@Autowired
	private ProntuarioRepository prontuarioRepository;
	@Autowired
	private PrescricaoExternaRepository prescricaoExternaRepository;

	@PostMapping("/")
	public ResponseEntity<?> prescricao(@Valid Prescricao prescricao, BindingResult result, Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (prescricao.getViaAdministracao().getProcedimento() == null) {
			errors.put("viaAdministracao", "Selecione a Via de Administração");
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Prontuario> optional = prontuarioRepository.findById(prescricao.getProntuario().getId());
		if (optional.isPresent()) {
			Prontuario prontuario = optional.get();
			prescricao.setDataRegistro(LocalDateTime.now());
			prescricao.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			Prescricao novaPrescricao = prescricaoRepository.save(prescricao);
			prontuario.getPrescricoes().add(novaPrescricao);
			prontuarioRepository.save(prontuario);

			return ResponseEntity.ok().body(novaPrescricao);
		}

		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/listar/atendimento/{idAtendimento}")
	public ResponseEntity<?> prescricoes(@PathVariable("idAtendimento") Long id) {
		Atendimento atendimento = atendimentoRepository.getOne(id);
		if (atendimento != null) {
			return ResponseEntity.ok().body(prescricaoRepository.findByAtendimento(atendimento));
		}
		return ResponseEntity.badRequest().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPrescricao(@PathVariable("id") Long id) {
		Optional<Prescricao> optional = prescricaoRepository.findById(id);
		if (optional.isPresent()) {
			Prescricao prescricao = optional.get();
			return ResponseEntity.ok().body(prescricao);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/excluir/{idProntuario}/{idPrescricao}")
	public ResponseEntity<?> excluirPrescricao(@PathVariable("idProntuario") Long idProntuario,
			@PathVariable("idPrescricao") Long idPrescricao, Principal principal) {
		Prontuario prontuario = prontuarioRepository.getOne(idProntuario);
		Prescricao prescricao = prescricaoRepository.getOne(idPrescricao);
		if (prescricao.getAtendimento().getStatus().equals(Status.FINALIZADO)
				|| !prescricao.getRegistrosAdministracao().isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		if (prescricao.getPrescricaoExterna() != null) {
			prescricaoExternaRepository.delete(prescricao.getPrescricaoExterna());
		}
		prontuario.getPrescricoes().remove(prescricao);
		prontuarioRepository.saveAndFlush(prontuario);
		prescricaoRepository.delete(prescricao);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/editar/{id}")
	public ResponseEntity<?> editarPrescricao(@PathVariable("id") Long id) {
		Optional<Prescricao> optional = prescricaoRepository.findById(id);
		if (optional.isPresent()) {
			Prescricao prescricao = optional.get();
			if (!prescricao.getRegistrosAdministracao().isEmpty()) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			return ResponseEntity.ok().body(prescricao);
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/editar")
	public ResponseEntity<?> editar(@Valid PrescricaoDTO prescricaoDTO, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (prescricaoDTO.getViaAdministracao().getProcedimento() == null) {
			errors.put("viaAdministracao", "Selecione a Via de Administração");
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Optional<Prescricao> optional = prescricaoRepository.findById(prescricaoDTO.getId());
		if (optional.isPresent()) {
			Prescricao prescricao = optional.get();
			if (!prescricao.getRegistrosAdministracao().isEmpty()) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
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

	@PostMapping("/prescricao-externa/")
	public ResponseEntity<?> prescricaoexterna(@Valid PrescricaoExterna prescricaoExterna, BindingResult result,
			Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Prescricao> optionalprescricao = prescricaoRepository
				.findById(prescricaoExterna.getPrescricao().getId());
		if (optionalprescricao.isPresent()) {
			Prescricao prescricao = optionalprescricao.get();
			prescricaoExternaRepository.save(prescricaoExterna);

			Optional<Prontuario> optional = prontuarioRepository.findById(prescricao.getProntuario().getId());
			if (optional.isPresent()) {
				Prontuario prontuario = optional.get();
				prescricao.setDataRegistro(LocalDateTime.now());
				prescricao.setProfissional(profissionalRepository.findByCpf(principal.getName()));
				prescricao.setPrescricaoExternabool(true);
				Prescricao novaPrescricao = prescricaoRepository.save(prescricao);
				prontuario.getPrescricoes().add(novaPrescricao);
				prontuarioRepository.save(prontuario);

				return ResponseEntity.ok().body(novaPrescricao);
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/prescricao-externa/{id}")
	public ResponseEntity<?> getPrescricaoExternaByIdPrescricao(@PathVariable("id") Long id) {

		Optional<Prescricao> prescricao = prescricaoRepository.findById(id);

		if (prescricao.isPresent()) {
			PrescricaoExterna prescricaoexterna = prescricaoExternaRepository.findByPrescricao(prescricao.get());
			if (prescricaoexterna != null) {
				return ResponseEntity.ok().body(prescricaoexterna);
			}
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/prescricao-externa-byid/{id}")
	public ResponseEntity<?> getPrescricaoExternaById(@PathVariable("id") Long id) {

		Optional<PrescricaoExterna> prescricaoExterna = prescricaoExternaRepository.findById(id);

		if (prescricaoExterna.isPresent()) {
			return ResponseEntity.ok().body(prescricaoExterna.get());

		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/prescricao-externa-editar/")
	public ResponseEntity<?> prescricaoexternaeditar(@Valid PrescricaoExterna prescricaoExterna, BindingResult result,
			Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<PrescricaoExterna> optional = prescricaoExternaRepository.findById(prescricaoExterna.getId());
		if (optional.isPresent()) {

			PrescricaoExterna pExterna = optional.get();
			pExterna.setNomeProfissional(prescricaoExterna.getNomeProfissional());
			pExterna.setNumeroRegistro(prescricaoExterna.getNumeroRegistro());
			pExterna.setSiglaUfEmissao(prescricaoExterna.getSiglaUfEmissao());
			pExterna.setDataSolicitacao(prescricaoExterna.getDataSolicitacao());

			return ResponseEntity.ok().body(prescricaoExternaRepository.save(pExterna));
		}
		return ResponseEntity.badRequest().build();
	}

}
