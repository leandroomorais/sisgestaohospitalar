package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifrn.sisgestaohospitalar.enums.CodigoRaca;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.service.CidadaoCadsusService;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaCadastradoException;

@Controller
@RequestMapping("/cidadao")
public class CidadaoController {

	@Autowired
	private CidadaoService cidadaoService;

	@Autowired
	private CidadaoCadsusService cadsusService;

	@Autowired
	private CidadaoRepository cidadaoRepository;

	@RequestMapping("/adicionar")
	public ModelAndView cadastrar(Cidadao cidadao) {
		ModelAndView mv = new ModelAndView("cidadao/form-cidadao");
		ModelMap mp = new ModelMap();
		mp.put("racas", CodigoRaca.values());
		mp.put("cidadao", cidadao);
		mv.addAllObjects(mp);
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView saveCidadao(@Valid Cidadao cidadao, BindingResult result,
			RedirectAttributes attributes/* , Principal principal */) {
		if (result.hasErrors()) {
			return cadastrar(cidadao/* , principal */).addObject("hasErrors", true);
		}
		try {
			Prontuario prontuario = new Prontuario();
			prontuario.setDataAbertura(LocalDateTime.now());
			cidadao.setProntuario(prontuario);
			prontuario.setCidadao(cidadao);
			cidadaoService.save(cidadao);
		} catch (CidadaoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			result.rejectValue("dataNascimento", e.getMessage(), e.getMessage());
			return cadastrar(cidadao/* , principal */);
		}

		attributes.addFlashAttribute("success", "O Cidadão " + cidadao.getNome() + " foi cadastrado na Base Local");
		return new ModelAndView("redirect:/atendimento/adicionar/" + cidadao.getId());
	}

	@PostMapping("/atualizar")
	public ModelAndView atualizaCidadao(@Valid Cidadao cidadao, BindingResult result/* , Principal principal */) {
		if (result.hasErrors()) {
			return editar(cidadao.getId());
		}
		cidadaoService.update(cidadao);
		return editar(cidadao.getId()).addObject("success", "Os dados foram salvos");
	}

	@RequestMapping("/detalhar/{id}")
	public ModelAndView detalhes(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("cidadao/detalhe-cidadao");
		Optional<Cidadao> optional = cidadaoRepository.findById(id);
		mv.addObject("cidadao", optional.get());
		return mv;
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidadao> optional = cidadaoRepository.findById(id);
		ModelAndView mv = new ModelAndView("cidadao/editar-cidadao");
		ModelMap mp = new ModelMap();
		mp.put("racas", CodigoRaca.values());
		mp.put("cidadao", optional.get());
		mv.addAllObjects(mp);
		return mv;
	}

	@PostMapping("/busca-local")
	public ResponseEntity<?> buscarCidadao(HttpServletRequest httpServletRequest) {
		String cns = httpServletRequest.getParameter("cns").replace(".", "");
		String cpf = httpServletRequest.getParameter("cpf").replace(".", "").replace("-", "");
		String nome = httpServletRequest.getParameter("nome");
		String dataNascimento = httpServletRequest.getParameter("dataNascimento").replace("-", "");
		if (cns.isEmpty() != true) {
			Optional<Cidadao> cidadao = cidadaoRepository.findByCns(cns);
			if (cidadao.isPresent()) {
				return ResponseEntity.ok(cidadao.get());
			}
			return ResponseEntity.notFound().build();
		}
		if (cpf.isEmpty() != true) {
			Optional<Cidadao> cidadao = cidadaoRepository.findByCpf(cpf);
			if (cidadao.isPresent()) {
				return ResponseEntity.ok(cidadao.get());
			}
			return ResponseEntity.notFound().build();
		}
		if (nome.isEmpty() != true && dataNascimento.isEmpty() != true) {
			Optional<Cidadao> cidadao = cidadaoRepository.findByNomeIgnoreCase(nome);
			if (cidadao.isPresent()) {
				return ResponseEntity.ok(cidadao.get());
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/busca-cadsus")
	public ResponseEntity<?> buscarCidadaoCadsus(HttpServletRequest httpServletRequest) {
		String cns = httpServletRequest.getParameter("cns").replace(".", "");
		String cpf = httpServletRequest.getParameter("cpf").replace(".", "").replace("-", "");
		String nome = httpServletRequest.getParameter("nome");
		String dataNascimento = httpServletRequest.getParameter("dataNascimento").replace("-", "");
		if (cns.isEmpty() != true) {
			System.out.println(cns);
			Cidadao cidadao = cadsusService.consultaCNS(cns);
			if (cidadao != null) {
				return ResponseEntity.ok(cidadao);
			}
			return ResponseEntity.notFound().build();
		}
		if (cpf.isEmpty() != true) {
			Cidadao cidadao = cadsusService.consultaCPF(cpf);
			if (cidadao != null) {
				return ResponseEntity.ok(cidadao);
			}
			return ResponseEntity.notFound().build();
		}
		if (nome.isEmpty() != true && dataNascimento.isEmpty() != true) {
			Cidadao cidadao = cadsusService.consultaNOMEeDN(nome, dataNascimento);
			if (cidadao != null) {
				return ResponseEntity.ok(cidadao);
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().build();
	}
}
