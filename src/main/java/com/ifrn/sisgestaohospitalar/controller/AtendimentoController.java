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
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Diagnostico;
import com.ifrn.sisgestaohospitalar.model.HistoricoStatus;
import com.ifrn.sisgestaohospitalar.model.Prescricao;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.TipoServicoRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.repository.ViaAdministracaoRepository;
import com.ifrn.sisgestaohospitalar.service.AtendimentoDataTablesService;
import com.ifrn.sisgestaohospitalar.service.AtendimentoService;
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

	@GetMapping("/tabela")
	public String mostrarTabela() {
		return "";
	}

	@GetMapping("/datatables/server")
	public ResponseEntity<?> dataTables(HttpServletRequest request) {
		Map<String, Object> data = new AtendimentoDataTablesService().execute(atendimentoRepository, request);
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
		mv.addObject("profissionais", profissionalRepository.findAll());
		return mv;
	}

	@RequestMapping("/atender/{atendimentoId}")
	public ModelAndView atendimento(@PathVariable("atendimentoId") Long atendimentoId, Principal principal) {
		Optional<Atendimento> optional = atendimentoRepository.findById(atendimentoId);
		ModelAndView mv = new ModelAndView("atendimento/form-atendimento");
		if (optional.isPresent()) {
			mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
			mv.addObject("atendimento", optional.get());
			mv.addObject("tipoServicos", tipoServicoRepository.findAll());
			mv.addObject("profissionais", profissionalRepository.findAll());
			mv.addObject("viasAdministracao", viaAdministracaoRepository.findAll());
		}
		return mv;

	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Atendimento atendimento, BindingResult result, RedirectAttributes attributes,
			Principal principal) {
		if (result.hasErrors()) {
			return cadastrar(atendimento.getCidadao().getId(), atendimento, principal);
		}
		try {
			if (atendimento.getDataEntrada() == null) {
				atendimento.setDataEntrada(LocalDateTime.now());
			}
			if (atendimento.getStatus() != null) {
				atendimento.setStatus(atendimento.getStatus());
			}
			atendimento.setStatus(Status.AGUARDANDOATENDIMENTO);
			HistoricoStatus historicoStatus = new HistoricoStatus();
			historicoStatus.setStatus(atendimento.getStatus());
			historicoStatus.setTipoServicos(atendimento.getTipoServicos());
			historicoStatus.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			historicoStatus.setUltimaAtualizacao(LocalDateTime.now());
			List<HistoricoStatus> listHistoricoStatus = new ArrayList<>();
			listHistoricoStatus.add(historicoStatus);
			atendimento.setHistoricoStatus(listHistoricoStatus);
			atendimentoService.save(atendimento);
		} catch (CidadaoJaAdicionadoNaFilaException e) {
			result.rejectValue("cidadao", e.getMessage(), e.getMessage());
			return cadastrar(atendimento.getCidadao().getId(), atendimento, principal);
		}

		attributes.addFlashAttribute("success",
				"O Cidadão " + atendimento.getCidadao().getNome()
						+ " foi adicionado a lista de atendimentos para o(s) serviço(s): "
						+ atendimento.tiposServicosToString());
		return new ModelAndView("redirect:/atendimento/listar");
	}

	@RequestMapping("/detalhar/{id}")
	public ModelAndView detalhar(@PathVariable("id") Long id, Principal principal) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			ModelAndView mv = new ModelAndView("atendimento/detalhe-atendimento");
			mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
			mv.addObject("atendimento", optional.get());
			mv.addObject("historicosStatus", optional.get().getHistoricoStatus());
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

	@RequestMapping("/procedimentos-triagem/{id}")
	public ResponseEntity<?> procedimentosTriagem(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getAtendimentoProcedimentos());
		}
		return ResponseEntity.badRequest().build();

	}

	@PostMapping("/diagnostico")
	public ResponseEntity<?> diagnostico(@Valid Diagnostico diagnostico, Principal principal) {
		Optional<Atendimento> optional = atendimentoRepository.findById(diagnostico.getIdAtendimento());
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			diagnostico.setLocalDateTime(LocalDateTime.now());
			diagnostico.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			atendimento.getDiagnostico().add(diagnostico);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@RequestMapping("/diagnosticos/{id}")
	public ResponseEntity<?> diagnosticos(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getDiagnostico());
		}
		return ResponseEntity.badRequest().build();

	}

	@PostMapping("/prescricao")
	public ResponseEntity<?> prescricao(@Valid Prescricao prescricao, BindingResult result, Principal principal) {
		System.out.println(prescricao);
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}

		Optional<Atendimento> optional = atendimentoRepository.findById(prescricao.getIdAtendimento());
		if (optional.isPresent()) {
			Atendimento atendimento = optional.get();
			prescricao.setData(LocalDateTime.now());
			prescricao.setProfissional(profissionalRepository.findByCpf(principal.getName()));
			atendimento.getPrescricoes().add(prescricao);
			atendimentoRepository.saveAndFlush(atendimento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@RequestMapping("/prescricoes/{id}")
	public ResponseEntity<?> prescricoes(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get().getPrescricoes());
		}
		return ResponseEntity.badRequest().build();

	}

}
