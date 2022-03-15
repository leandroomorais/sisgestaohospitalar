package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
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

import com.ifrn.sisgestaohospitalar.enums.CaraterAtendimento;
import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
import com.ifrn.sisgestaohospitalar.enums.MomentoColeta;
import com.ifrn.sisgestaohospitalar.enums.SituacaoCondicao;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.Triagem;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ClassificacaoDeRiscoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.TipoServicoRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.service.TriagemDataTablesService;

@Controller
@RequestMapping("/triagem")
public class TriagemController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private TipoServicoRepository tipoServicoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClassificacaoDeRiscoRepository classificacaoDeRiscoRepository;

	@RequestMapping("/verificar/{atendimentoId}")
	public ResponseEntity<?> verificaTriagem(@PathVariable("atendimentoId") Long id) {
		Atendimento atendimento = atendimentoRepository.getOne(id);
		Triagem triagem = atendimento.getTriagem();
		if (triagem != null) {
			triagem.setClassificacaoDeRisco(atendimento.getClassificacaoDeRisco());
			return ResponseEntity.ok().body(triagem);
		}
		return ResponseEntity.badRequest().build();
	}

	@RequestMapping("/adicionar/{atendimentoId}")
	public ModelAndView cadastrar(@PathVariable("atendimentoId") Long id, Triagem triagem,
			RedirectAttributes attributes, Principal principal) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			Profissional profissional = profissionalRepository.findByCpf(principal.getName());
			atendimento.setStatus(Status.EMATENDIMENTO);
			atendimentoRepository.saveAndFlush(atendimento);
			triagem.setAtendimento(atendimento);
			triagem.setInicioTriagem(LocalDateTime.now());
			triagem.setProfissional(profissional);
			ModelAndView mv = new ModelAndView("triagem/form-triagem");
			mv.addObject("atendimento", triagem.getAtendimento());
			mv.addObject("momentosColeta", MomentoColeta.values());
			mv.addObject("classificacoesRisco", classificacaoDeRiscoRepository.findAll());
			mv.addObject("situacoesCondicao", SituacaoCondicao.values());
			mv.addObject("profissionais", profissionalRepository.searchSelectOptions());
			mv.addObject("tipoServicos", tipoServicoRepository.findAll());
			mv.addObject("condutasCidadao", CondutaCidadao.values());
			mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
			return mv;
		} else {
			attributes.addFlashAttribute("erro", "Atendimento não localizado");
			return new ModelAndView("redirect:/triagem/listar");
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@Valid Triagem triagem, BindingResult result, Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Atendimento atendimento = atendimentoRepository.getOne(triagem.getAtendimento().getId());
		if (atendimento != null) {
			triagem.setFimTriagem(LocalDateTime.now());
			triagem.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			triagem.getSinaisVitais().setUltimaAtualizacao(LocalDateTime.now());
			atendimento.setTriagem(triagem);
			atendimento.setClassificacaoDeRisco(triagem.getClassificacaoDeRisco());
			atendimento.setCaraterAtendimento(CaraterAtendimento.ELETIVO);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/datatables/server")
	public ResponseEntity<?> dataTables(HttpServletRequest request) {
		Map<String, Object> data = new TriagemDataTablesService().execute(atendimentoRepository, tipoServicoRepository,
				request);
		return ResponseEntity.ok(data);
	}

	@GetMapping("/listar")
	public ModelAndView listar(Principal principal) {
		ModelAndView mv = new ModelAndView("triagem/listar-atendimento");
		mv.addObject("statusAtendimentos", Status.values());
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		return mv;
	}

	@RequestMapping("/procedimentos/{id}")
	public ResponseEntity<?> procedimentosTriagem(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getAtendimentoProcedimentos());
		}
		return ResponseEntity.badRequest().build();

	}

}
