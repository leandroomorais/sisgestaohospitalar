package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifrn.sisgestaohospitalar.enums.CodigoRaca;
import com.ifrn.sisgestaohospitalar.enums.EstadoCivil;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.model.Usuario;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.service.CidadaoCadsusService;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.exception.DataNascimentoMaiorQueDataAtualException;

@Controller
@RequestMapping("/cidadao")
public class CidadaoController {

	@Autowired
	private CidadaoService cidadaoService;

	@Autowired
	private CidadaoCadsusService cadsusService;

	@Autowired
	private CidadaoRepository cidadaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping("/adicionar")
	public ModelAndView cadastrar(Cidadao cidadao, Principal principal) {
		ModelAndView mv = new ModelAndView("cidadao/form");
		ModelMap mp = new ModelMap();
		mp.put("racas", CodigoRaca.values());
		mp.put("cidadao", cidadao);
		mp.put("estadosCivil", EstadoCivil.values());
		mp.put("user", usuarioRepository.findByUsername(principal.getName()));
		mv.addAllObjects(mp);
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView saveCidadao(@Valid Cidadao cidadao, BindingResult result, RedirectAttributes attributes,
			Principal principal) {
		cidadao.setCns(cidadao.getCns().replace(".", ""));
		cidadao.setCpf(cidadao.getCpf().replace(".", "").replace("-", ""));
		if (result.hasErrors()) {
			return cadastrar(cidadao, principal).addObject("hasErrors", true);
		}
		try {
			Prontuario prontuario = new Prontuario();
			prontuario.setDataAbertura(LocalDateTime.now());
			cidadao.setProntuario(prontuario);
			prontuario.setCidadao(cidadao);
			cidadaoService.save(cidadao);

		} catch (DataNascimentoMaiorQueDataAtualException e) {
			result.rejectValue("dataNascimento", e.getMessage(), e.getMessage());
			return cadastrar(cidadao, principal);
		}

		attributes.addFlashAttribute("success", "O Cidadão " + cidadao.getNome() + " foi cadastrado na Base Local");
		return new ModelAndView("redirect:/atendimento/adicionar/" + cidadao.getId());
	}

	@PostMapping("/atualizar")
	public ModelAndView atualizaCidadao(@Valid Cidadao cidadao, BindingResult result, Principal principal,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return editar(cidadao.getId(), principal);
		}
		cidadaoService.update(cidadao);
		return new ModelAndView("redirect:/oi");
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhes(@PathVariable("id") Long id, Principal principal) {
		ModelAndView mv = new ModelAndView("cidadao/detalhe");
		Optional<Cidadao> optional = cidadaoRepository.findById(id);
		mv.addObject("cidadao", optional.get());
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		return mv;
	}

	@RequestMapping("/detalhe-admin/{id}")
	public ModelAndView detalhesAdmin(@PathVariable("id") Long id, Principal principal) {
		ModelAndView mv = new ModelAndView("cidadao/detalhe-admin");
		Optional<Cidadao> optional = cidadaoRepository.findById(id);
		mv.addObject("cidadao", optional.get());
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		return mv;
	}

	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id, Principal principal) {
		Optional<Cidadao> optional = cidadaoRepository.findById(id);
		ModelAndView mv = new ModelAndView("cidadao/editar");
		ModelMap mp = new ModelMap();
		mp.put("racas", CodigoRaca.values());
		mp.put("cidadao", optional.get());
		mp.put("user", usuarioRepository.findByUsername(principal.getName()));
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

	@GetMapping("/busca-admin")
	public ModelAndView buscarCidadao(Principal principal, @RequestParam(name = "cns", required = false) String cns,
			@RequestParam(name = "cpf", required = false) String cpf,
			@RequestParam(name = "nome", required = false) String nome) {
		ModelAndView mv = new ModelAndView("cidadao/busca-admin");
		Usuario user = usuarioRepository.findByUsername(principal.getName());
		Optional<Cidadao> optional = null;
		if (cns != null) {
			if (!cns.isEmpty()) {
				String param = cns.replaceAll("\\.|-|/", "");
				optional = cidadaoRepository.findByCns(param);
				alert(mv, optional);

			}
		}
		if (cpf != null) {
			if (!cpf.isEmpty()) {
				String param = cpf.replaceAll("\\.|-|/", "");
				optional = cidadaoRepository.findByCpf(param);
				alert(mv, optional);
			}
		}
		if (nome != null) {
			if (!nome.isEmpty()) {
				List<Cidadao> cidadaos = cidadaoRepository.findByNomeContainingIgnoreCase(nome);
				if (cidadaos.isEmpty()) {
					mv.addObject("warning", "Nenhum resultado encontrado");
				} else {
					mv.addObject("cidadaos", cidadaos);
				}
			}
		}

		Cidadao cidadao = null;

		if (optional != null) {
			if (optional.isPresent()) {
				cidadao = optional.get();
			}
		}

		mv.addObject("cidadao", cidadao);
		mv.addObject("user", user);
		return mv;
	}

	private void alert(ModelAndView mv, Optional<?> optional) {
		if (optional.isEmpty()) {
			mv.addObject("warning", "Nenhum resultado encontrado");
		}
	}
}
