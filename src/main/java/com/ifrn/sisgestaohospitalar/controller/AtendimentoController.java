package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ifrn.sisgestaohospitalar.dto.AtendimentoDTO;
import com.ifrn.sisgestaohospitalar.enums.CaraterAtendimento;
import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
import com.ifrn.sisgestaohospitalar.enums.MomentoColeta;
import com.ifrn.sisgestaohospitalar.enums.SituacaoCondicao;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.HistoricoAtendimento;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ClassificacaoDeRiscoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.TipoServicoRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.repository.ViaAdministracaoRepository;
import com.ifrn.sisgestaohospitalar.service.AtendimentoDataTablesService;
import com.ifrn.sisgestaohospitalar.service.AtendimentoRiscoDataTablesService;
import com.ifrn.sisgestaohospitalar.service.AtendimentoService;
import com.ifrn.sisgestaohospitalar.service.HistoricoAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaAdicionadoNaFilaException;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {
	
	@Autowired
	private CidadaoRepository cidadaoRepository;

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private ViaAdministracaoRepository viaAdministracaoRepository;

	@Autowired
	private TipoServicoRepository tipoServicoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private HistoricoAtendimentoService historicoAtendimentoService;

	@Autowired
	private ClassificacaoDeRiscoRepository classificacaoDeRiscoRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> atendimento(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/datatables/server")
	public ResponseEntity<?> dataTables(HttpServletRequest request) {
		Map<String, Object> data = new AtendimentoDataTablesService().execute(atendimentoRepository, request);
		return ResponseEntity.ok(data);
	}

	@GetMapping("/datatables-risco/server")
	public ResponseEntity<?> dataTablesRisco(HttpServletRequest request) {
		Map<String, Object> data = new AtendimentoRiscoDataTablesService().execute(atendimentoRepository, request);
		System.out.println("Aqui");
		System.out.println(new AtendimentoRiscoDataTablesService().execute(atendimentoRepository, request).toString());
		return ResponseEntity.ok(data);
	}

	@RequestMapping("/adicionar/{cidadaoId}")
	public ModelAndView cadastrar(@PathVariable("cidadaoId") Long cidadaoId, Atendimento atendimento,
			Principal principal) {
		ModelAndView mv = new ModelAndView("atendimento/form-cadastrar");
		Optional<Cidadao> optional = cidadaoRepository.findById(cidadaoId);
		atendimento.setCidadao(optional.get());
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		mv.addObject("atendimento", atendimento);
		mv.addObject("tipoServicos", tipoServicoRepository.findAll());
		mv.addObject("profissionais", profissionalRepository.searchSelectOptions());
		return mv;
	}

	@RequestMapping("/atender/{atendimentoId}")
	public ModelAndView atendimento(@PathVariable("atendimentoId") Long atendimentoId, Principal principal) {
		Optional<Atendimento> optional = atendimentoRepository.findById(atendimentoId);
		ModelAndView mv = new ModelAndView("atendimento/form-atendimento");
		if (optional.isPresent()) {
			mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
			mv.addObject("atendimento", optional.get());
			mv.addObject("situacoesCondicao", SituacaoCondicao.values());
			mv.addObject("tipoServicos", tipoServicoRepository.findAll());
			mv.addObject("momentosColeta", MomentoColeta.values());
			mv.addObject("profissionais", profissionalRepository.searchSelectOptions());
			mv.addObject("viasAdministracao", viaAdministracaoRepository.findAll());
			mv.addObject("condutasCidadao", CondutaCidadao.values());
			mv.addObject("tiposAtendimento", CaraterAtendimento.values());
			Atendimento atendimento = optional.get();
			atendimento.setStatus(Status.EMATENDIMENTO);
			atendimento.getHistoricosAtendimento().add(historicoAtendimentoService.criaHistoricoAtendimento(
					"INICIO ATENDIMENTO", null, atendimento.getStatus(), null, principal, null, null));
			atendimentoRepository.saveAndFlush(atendimento);
		}
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Atendimento atendimento, BindingResult result, RedirectAttributes attributes,
			Principal principal) {
		if (result.hasErrors()) {
			return cadastrar(atendimento.getCidadao().getId(), atendimento, principal);
		}

		if (atendimento.getTipoServicos().isEmpty()) {
			result.rejectValue("tipoServicos", "Não pode ser nulo", "Selecione o(s) serviço(s) para este atendimento");
			return cadastrar(atendimento.getCidadao().getId(), atendimento, principal);
		}

		try {
			if (atendimento.getDataEntrada() == null) {
				atendimento.setDataEntrada(LocalDateTime.now());
			}
			if (atendimento.getStatus() != null) {
				atendimento.setStatus(atendimento.getStatus());
			}
			atendimento.setClassificacaoDeRisco(classificacaoDeRiscoRepository.getOne((long) 6));
			atendimento.setStatus(Status.AGUARDANDOATENDIMENTO);
			atendimento.setHistoricosAtendimento(new ArrayList<HistoricoAtendimento>());
			atendimento.getHistoricosAtendimento()
					.add(historicoAtendimentoService.criaHistoricoAtendimento("ADICIONADO A LISTA DE ATENDIMENTOS",
							atendimento.getCondutaCidadao(), atendimento.getStatus(), atendimento.getTipoServicos(),
							principal, atendimento.getProfissionalDestino(), null));
			atendimentoService.save(atendimento);
		} catch (CidadaoJaAdicionadoNaFilaException e) {
			result.rejectValue("cidadao", e.getMessage(), e.getMessage());
			return cadastrar(atendimento.getCidadao().getId(), atendimento, principal);
		}

		attributes.addFlashAttribute("success",
				"O Cidadão " + atendimento.getCidadao().getNome() + "foi adicionado a lista de atendimentos");
		return new ModelAndView("redirect:/atendimento/listar");
	}

	@PostMapping("/finalizar")
	public ResponseEntity<?> finalizarAtendimento(@Valid AtendimentoDTO atendimentoDTO, BindingResult result,
			Principal principal) {
		Map<String, String> errors = new HashMap<>();

		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		
		if (atendimentoDTO.getCondutaCidadao() == null) {
			errors.put("atendimento.condutaCidadao", "É necessário informar a conduta do cidadão");
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.ALTAEPISODIOAPOSPRESCRICAO)
				&& atendimentoDTO.getTipoServicos().isEmpty()) {
			errors.put("tipoServicos", "É necessário selecionar o tipo de serviço");
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.OBSERVACAO)
				&& atendimentoDTO.getTempoObservacao() <= 0) {
			errors.put("tempoObservacao", "O tempo de observação não pode ser menor ou igual a 0");
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		if (atendimentoDTO.getCaraterAtendimento() == null) {
			errors.put("atendimento.tipoAtendimento", "Selecione o tipo do atendimento");
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Atendimento> optional = atendimentoRepository.findById(atendimentoDTO.getId());
		
		if(!optional.get().getAtendimentoProcedimentos().isEmpty()) {
			List<AtendimentoProcedimento> atendimentos = optional.get().getAtendimentoProcedimentos();
			for(AtendimentoProcedimento a : atendimentos) {
				if(a.getProcedimento().getCodigo() == 203010035 && a.getCodigoCid() == null) {
					String msg = "O procedimento "+ a.getProcedimento().getNome() +" requer a adição de um CID compatível, retorne em Procedimentos para adicionar";
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
				}
				if(a.getProcedimento().getCodigo() == 203020030 && a.getCodigoCid() == null) {
					String msg = "O procedimento "+ a.getProcedimento().getNome() +" requer a adição de um CID compatível, retorne em Procedimentos para adicionar";
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
				}
			}
		}
		
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			atendimento.setTipoServicos(atendimentoDTO.getTipoServicos());
			atendimento.setCondutaCidadao(atendimentoDTO.getCondutaCidadao());
			atendimento.setProfissionalDestino(atendimentoDTO.getProfissionalDestino());
			atendimento.setTempoObservacao(atendimentoDTO.getTempoObservacao());
			atendimento.setCaraterAtendimento(atendimentoDTO.getCaraterAtendimento());
			if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.UBS)
					|| atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.LIBERADO)
					|| atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.TRANSFERIDO)) {
				atendimento.getTipoServicos().clear();
				atendimento.getTipoServicos().add(tipoServicoRepository.findByNome("Inativo"));
				atendimento.setStatus(Status.FINALIZADO);
			}
			if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.OBSERVACAO)) {
				atendimento.setStatus(Status.OBSERVACAO);
			}
			if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.ALTAEPISODIOAPOSPRESCRICAO)) {
				atendimento.setStatus(Status.AGUARDANDOATENDIMENTO);
			}
			if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.NAOAGUARDOUATENDIMENTO)) {
				atendimento.getTipoServicos().clear();
				atendimento.getTipoServicos().add(tipoServicoRepository.findByNome("Inativo"));
				atendimento.setStatus(Status.NAOAGUARDOU);
			}
			atendimento.getHistoricosAtendimento()
					.add(historicoAtendimentoService.criaHistoricoAtendimento("FIM ATENDIMENTO",
							atendimento.getCondutaCidadao(), atendimento.getStatus(), atendimento.getTipoServicos(),
							principal, atendimento.getProfissionalDestino(), null));
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/finalizar/triagem")
	public ResponseEntity<?> finalizarAtendimentoTriagem(@Valid AtendimentoDTO atendimentoDTO, BindingResult result,
			Principal principal) {
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		if (atendimentoDTO.getCondutaCidadao() == null && atendimentoDTO.getTipoServicos().isEmpty()) {
			errors.put("tipoServicos", "Selecione o(s) serviço(s) para o atendimento");
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Atendimento> optional = atendimentoRepository.findById(atendimentoDTO.getId());
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			atendimento.setTipoServicos(atendimentoDTO.getTipoServicos());
			atendimento.setCondutaCidadao(atendimentoDTO.getCondutaCidadao());
			atendimento.setProfissionalDestino(atendimentoDTO.getProfissionalDestino());
			if (atendimentoDTO.getCondutaCidadao() != null) {
				if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.LIBERADO)
						|| atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.UBS)) {
					atendimento.setStatus(Status.FINALIZADO);
					atendimento.getTipoServicos().clear();
					atendimento.getTipoServicos().add(tipoServicoRepository.findByNome("Inativo"));
				}
			}

			if (atendimentoDTO.getCondutaCidadao() != null) {
				if (atendimentoDTO.getCondutaCidadao().equals(CondutaCidadao.NAOAGUARDOUATENDIMENTO)) {
					atendimento.setStatus(Status.NAOAGUARDOU);
					atendimento.getTipoServicos().clear();
					atendimento.getTipoServicos().add(tipoServicoRepository.findByNome("Inativo"));
				}
			}

			if (atendimentoDTO.getTipoServicos() != null && !atendimentoDTO.getTipoServicos().isEmpty()
					&& atendimentoDTO.getCondutaCidadao() == null) {
				atendimento.setStatus(Status.AGUARDANDOATENDIMENTO);
			}

			atendimento.getHistoricosAtendimento()
					.add(historicoAtendimentoService.criaHistoricoAtendimento("FIM TRIAGEM",
							atendimento.getCondutaCidadao(), atendimento.getStatus(), atendimento.getTipoServicos(),
							principal, atendimento.getProfissionalDestino(), null));

			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@RequestMapping("/detalhar/{id}")
	public ModelAndView detalhar(@PathVariable("id") Long id, Principal principal) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			ModelAndView mv = new ModelAndView("atendimento/detalhe-atendimento");
			mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
			mv.addObject("atendimento", optional.get());
			return mv;
		}
		return listar(principal).addObject("erro", "Atendimento não localizado");
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id, Principal principal) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		return cadastrar(optional.get().getCidadao().getId(), optional.get(), principal);
	}

	@RequestMapping("/listar")
	public ModelAndView listar(Principal principal) {
		ModelAndView mv = new ModelAndView("atendimento/listar-atendimento");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		mv.addObject("statusAtendimentos", Status.values());
		return mv;
	}

	@RequestMapping("/listar-atendimentos")
	public ModelAndView listarPorRisco(Principal principal) {
		ModelAndView mv = new ModelAndView("atendimento/listar-atendimento-risco");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		mv.addObject("statusAtendimentos", Status.values());
		return mv;
	}

}
