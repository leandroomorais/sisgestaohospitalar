package com.ifrn.sisgestaohospitalar.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ifrn.sisgestaohospitalar.enums.Status;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.HistoricoStatus;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
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
	public ModelAndView cadastrar(@PathVariable("cidadaoId") Long cidadaoId, Atendimento atendimento) {
		ModelAndView mv = new ModelAndView("atendimento/form-atendimento");
		Optional<Cidadao> optional = cidadaoRepository.findById(cidadaoId);
		atendimento.setCidadao(optional.get());
		mv.addObject("atendimento", atendimento);
		mv.addObject("tipoServicos", TipoServico.values());
		mv.addObject("profissionais", profissionalRepository.findAll());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Atendimento atendimento, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(atendimento.getCidadao().getId(), atendimento);
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
			historicoStatus.setTipoServico(atendimento.getCondutaTipoServico());
			historicoStatus.setProfissional(null);
			historicoStatus.setUltimaAtualizacao(LocalDateTime.now());
			List<HistoricoStatus> listHistoricoStatus = new ArrayList<>();
			listHistoricoStatus.add(historicoStatus);
			atendimento.setHistoricoStatus(listHistoricoStatus);
			atendimentoService.save(atendimento);
		} catch (CidadaoJaAdicionadoNaFilaException e) {
			result.rejectValue("cidadao", e.getMessage(), e.getMessage());
			return cadastrar(atendimento.getCidadao().getId(), atendimento);
		}

		attributes.addFlashAttribute("success",
				"O Cidadão " + atendimento.getCidadao().getNome()
						+ " foi adicionado a lista de atendimentos para o serviço: "
						+ atendimento.getCondutaTipoServico().getDescricao());
		return new ModelAndView("redirect:/atendimento/listar");
	}

	@RequestMapping("/detalhar/{id}")
	public ModelAndView detalhar(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		ModelAndView mv = new ModelAndView("atendimento/detalhe-atendimento");
		mv.addObject("atendimento", optional.get());
		return mv;

	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Atendimento> optional = atendimentoRepository.findById(id);
		return cadastrar(optional.get().getCidadao().getId(), optional.get());
	}

	@RequestMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("atendimento/listar-atendimento");
		mv.addObject("statusAtendimentos", Status.values());
		return mv;
	}

}
