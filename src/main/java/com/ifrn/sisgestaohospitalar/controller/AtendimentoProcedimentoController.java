package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Cid;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.service.ProcedimentoOcupacapService;
import com.ifrn.sisgestaohospitalar.utils.Datas;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoProcedimentoRepository;

@Controller
@RequestMapping("/atendimento-procedimento")
public class AtendimentoProcedimentoController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	@Autowired
	private AtendimentoProcedimentoRepository atendimentoProcedimentoRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository;
	@Autowired
	private ProcedimentoOcupacapService procedimentoOcupacapService;
	@Autowired
	private CidRepository cidRepository;

	private Datas datas = new Datas();

	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarProcedimentos(@Valid AtendimentoProcedimento atendimentoProcedimento,
			BindingResult result, Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		Optional<Atendimento> optional = atendimentoRepository
				.findById(atendimentoProcedimento.getAtendimento().getId());
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			Profissional profissional = profissionalRepository.findByCpf(principal.getName());
			atendimentoProcedimento.setProfissional(profissional);
			atendimentoProcedimento.setIdadeNoAtendimento(datas.getIdade(atendimento.getCidadao().getDataNascimento(),
					atendimento.getDataEntrada().toLocalDate()));
			if (!procedimentoOcupacapService.verificaCboProcedimento(atendimentoProcedimento, profissional)) {
				String message = "CBO incompatível para este procedimento!";
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
			}
			atendimentoProcedimento.setCboProfissional(procedimentoOcupacapService.getOcupacao(profissional));
			for (AtendimentoProcedimento atendimentoProcedimentoAux : atendimento.getAtendimentoProcedimentos()) {
				if (atendimentoProcedimento.getProcedimento().getCodigo()
						.equals(atendimentoProcedimentoAux.getProcedimento().getCodigo())
						&& atendimentoProcedimento.getProfissional().getCpf().equals(profissional.getCpf())) {
					atendimentoProcedimentoAux.setQuantidade(
							atendimentoProcedimentoAux.getQuantidade() + atendimentoProcedimento.getQuantidade());
					atendimentoProcedimentoRepository.saveAndFlush(atendimentoProcedimentoAux);
					return ResponseEntity.ok().build();

				}
			}
			atendimento.getAtendimentoProcedimentos().add(atendimentoProcedimento);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/excluir/{idAtendimento}/{idAtendimentoProcedimento}")
	public ResponseEntity<?> excluir(@PathVariable("idAtendimento") Long idAtendimento,
			@PathVariable("idAtendimentoProcedimento") Long idAtendimentoProcedimento, Principal principal) {
		Optional<Atendimento> optionalAtendimento = atendimentoRepository.findById(idAtendimento);
		if (optionalAtendimento.isPresent()) {
			Atendimento atendimento = optionalAtendimento.get();
			Optional<AtendimentoProcedimento> optionalRelAtendimentoProcedimento = atendimentoProcedimentoRepository
					.findById(idAtendimentoProcedimento);
			if (optionalRelAtendimentoProcedimento.isPresent()) {
				AtendimentoProcedimento atendimentoProcedimento = optionalRelAtendimentoProcedimento.get();
				if (!atendimentoProcedimento.getProfissional().getCpf().equals(principal.getName())) {
					String msg = "Procedimento realizado por: " + atendimentoProcedimento.getProfissional().getNome()
							+ ". Não é possível excluir";
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
				}
				atendimento.getAtendimentoProcedimentos().remove(atendimentoProcedimento);
				atendimentoRepository.saveAndFlush(atendimento);
				atendimentoProcedimentoRepository.deleteById(idAtendimentoProcedimento);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/buscarporid/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<AtendimentoProcedimento> optional = atendimentoProcedimentoRepository.findById(id);

		if (optional.get().getCodigoCid() != null) {
			Cid cid = cidRepository.findByCodigoIgnoreCaseContaining(optional.get().getCodigoCid());
			if (cid != null) {
				return ResponseEntity.ok(cid);
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@GetMapping("/adicionarcidaoatendprocedimento/{idAtendimentoProcedimento}/{idCid}")
	public ResponseEntity<?> adicionarCidAoAtendProcedimento(
			@PathVariable("idAtendimentoProcedimento") Long idAtendimentoProcedimento,
			@PathVariable("idCid") Long idCid, Principal principal) {

		Optional<Cid> optionalCid = cidRepository.findById(idCid);
		Optional<AtendimentoProcedimento> optionalAtendimentoProcedimento = atendimentoProcedimentoRepository
				.findById(idAtendimentoProcedimento);

		if (optionalCid.isPresent() && optionalAtendimentoProcedimento.isPresent()) {
			optionalAtendimentoProcedimento.get().setCodigoCid(optionalCid.get().getCodigo());
			atendimentoProcedimentoRepository.saveAndFlush(optionalAtendimentoProcedimento.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
