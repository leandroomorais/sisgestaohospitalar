package com.ifrn.sisgestaohospitalar.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifrn.sisgestaohospitalar.dto.AtendimentoDTO;
import com.ifrn.sisgestaohospitalar.enums.ClassificacaoDeRisco;
import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
import com.ifrn.sisgestaohospitalar.enums.MomentoColeta;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.HistoricoStatus;
import com.ifrn.sisgestaohospitalar.model.SinaisVitais;
import com.ifrn.sisgestaohospitalar.model.Triagem;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.service.AtendimentoService;
import com.ifrn.sisgestaohospitalar.service.TriagemDataTablesService;
import com.ifrn.sisgestaohospitalar.service.TriagemService;
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaAdicionadoNaFilaException;

@Controller
@RequestMapping("/triagem")
public class TriagemController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private TriagemService triagemService;

	@RequestMapping("/adicionar/{atendimentoId}")
	public ModelAndView cadastrar(@PathVariable("atendimentoId") Long id, Triagem triagem,
			RedirectAttributes attributes) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			atendimento.setStatus(Status.EMATENDIMENTO);
			atendimentoRepository.saveAndFlush(atendimento);
			triagem.setAtendimento(atendimento);
			triagem.setInicioTriagem(LocalDateTime.now());
			ModelAndView mv = new ModelAndView("triagem/form-triagem");
			mv.addObject("atendimento", triagem.getAtendimento());
			mv.addObject("momentosColeta", MomentoColeta.values());
			mv.addObject("classificacoesRisco", ClassificacaoDeRisco.values());
			mv.addObject("profissionais", profissionalRepository.findAll());
			mv.addObject("tipoServicos", TipoServico.values());
			mv.addObject("condutasCidadao", CondutaCidadao.values());
			return mv;
		} else {
			attributes.addFlashAttribute("erro", "Atendimento não localizado");
			return new ModelAndView("redirect:/triagem/listar");
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@Valid Triagem triagem, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		try {
			Optional<Atendimento> optional = atendimentoRepository.findById(triagem.getAtendimento().getId());
			if (optional.isPresent()) {
				Atendimento atendimento = optional.get();
				
				if (triagem.getAtendimento().getCondutaTipoServico() != TipoServico.INATIVO) {
					atendimento.setStatus(Status.AGUARDANDOATENDIMENTO);
				}
				if (triagem.getAtendimento().getCondutaTipoServico().equals(TipoServico.INATIVO)
						&& triagem.getAtendimento().getStatus() != Status.NAOAGUARDOU) {
					atendimento.setStatus(Status.FINALIZADO);
				}
				
				atendimento.setCondutaTipoServico(triagem.getAtendimento().getCondutaTipoServico());
				atendimento.setCondutaCidadao(triagem.getAtendimento().getCondutaCidadao());
				
				triagem.setAtendimento(atendimento);
				triagem.setFimTriagem(LocalDateTime.now());
				
				atendimento.setTriagem(triagem);
				
				HistoricoStatus historicoStatus = new HistoricoStatus();
				historicoStatus.setStatus(triagem.getAtendimento().getStatus());
				historicoStatus.setTipoServico(triagem.getAtendimento().getCondutaTipoServico());
				historicoStatus.setUltimaAtualizacao(LocalDateTime.now());

				triagemService.save(triagem);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		} catch (CidadaoJaAdicionadoNaFilaException e) {
			errors.put(e.getLocalizedMessage(), e.getMessage());
			return ResponseEntity.unprocessableEntity().body(errors);
		}
	}

	@GetMapping("/datatables/server")
	public ResponseEntity<?> dataTables(HttpServletRequest request) {
		Map<String, Object> data = new TriagemDataTablesService().execute(atendimentoRepository, request);
		return ResponseEntity.ok(data);
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("triagem/listar-atendimento");
		mv.addObject("statusAtendimentos", Status.values());
		return mv;
	}

}
