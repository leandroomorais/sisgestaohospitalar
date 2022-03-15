package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.Ocupacao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.Role;
import com.ifrn.sisgestaohospitalar.model.TipoUsuario;
import com.ifrn.sisgestaohospitalar.model.Usuario;
import com.ifrn.sisgestaohospitalar.repository.OcupacaoRepository;
import com.ifrn.sisgestaohospitalar.repository.RoleRepository;
import com.ifrn.sisgestaohospitalar.repository.TipoUsuarioRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired
	private OcupacaoRepository ocupacaoRepository;

	@RequestMapping("/cadastrar")
	public ModelAndView add(Profissional profissional, Principal principal) {
		ModelAndView mv = new ModelAndView("profissional/form-profissional");
		Usuario user = usuarioRepository.findByUsername(principal.getName());
		mv.addObject("profissional", profissional);
		mv.addObject("user", user);
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView save(@Valid Profissional profissional, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return add(profissional, principal);
		}

		String name = profissional.getNome();
		String[] name1 = name.split(" ");
		String firstname = name1[0].toString() + " " + name1[1].toString();

		profissional.setNomeAbrev(firstname);
		profissional.setCns(profissional.getCns().replaceAll("\\.|-|/", ""));
		profissional.setCpf(profissional.getCpf().replaceAll("\\.|-|/", ""));
		profissional.setNome(profissional.getNome().toUpperCase());

		Usuario usuario = new Usuario();
		usuario.setUsername(profissional.getCpf());
		usuario.setPassword(new BCryptPasswordEncoder().encode("sgh" + profissional.getCpf()));
		usuario.setConcatName(profissional.getNomeAbrev());

		Lotacao lotacao = new Lotacao();
		lotacao.setCnes(profissional.getLotacaoDTO().getCnes());
		lotacao.setCodigoCBO(profissional.getLotacaoDTO().getCodigoCBO());

		profissional.setLotacoes(new ArrayList<>());
		profissional.getLotacoes().add(lotacao);

		if (profissional.getLotacaoDTO().getCodigoCBO().equals("225125")) {
			TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("MEDICO");
			Ocupacao ocupacao = ocupacaoRepository.findByCodigo(profissional.getLotacaoDTO().getCodigoCBO());
			if (ocupacao != null) {
				profissional.setNomeOcupacao(ocupacao.getNome());
			}
			usuario.setTipoUsuario(tipoUsuario);
			usuario.setEnabled(profissional.isAtivo());
			Role role = roleRepository.findByNome(tipoUsuario.getNome());
			usuario.getRole().add(role);
		} else if (profissional.getLotacaoDTO().getCodigoCBO().equals("223505")) {
			TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("ENFERMEIRO");
			Ocupacao ocupacao = ocupacaoRepository.findByCodigo(profissional.getLotacaoDTO().getCodigoCBO());
			if (ocupacao != null) {
				profissional.setNomeOcupacao(ocupacao.getNome());
			}
			usuario.setTipoUsuario(tipoUsuario);
			usuario.setEnabled(profissional.isAtivo());
			Role role = roleRepository.findByNome(tipoUsuario.getNome());
			usuario.getRole().add(role);
		} else if (profissional.getLotacaoDTO().getCodigoCBO().equals("322205")) {
			TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("TECNICO");
			Ocupacao ocupacao = ocupacaoRepository.findByCodigo(profissional.getLotacaoDTO().getCodigoCBO());
			if (ocupacao != null) {
				profissional.setNomeOcupacao(ocupacao.getNome());
			}
			usuario.setEnabled(profissional.isAtivo());
			usuario.setTipoUsuario(tipoUsuario);
			Role role = roleRepository.findByNome(tipoUsuario.getNome());
			usuario.getRole().add(role);
		} else if (profissional.getLotacaoDTO().getCodigoCBO().equals("322230")) {
			TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("AUXILIAR");
			Ocupacao ocupacao = ocupacaoRepository.findByCodigo(profissional.getLotacaoDTO().getCodigoCBO());
			if (ocupacao != null) {
				profissional.setNomeOcupacao(ocupacao.getNome());
			}
			usuario.setEnabled(profissional.isAtivo());
			usuario.setTipoUsuario(tipoUsuario);
			Role role = roleRepository.findByNome(tipoUsuario.getNome());
			usuario.getRole().add(role);
		} else if (profissional.getLotacaoDTO().getCodigoCBO().equals("123105")) {
			TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("ADMINISTRADOR");
			Ocupacao ocupacao = ocupacaoRepository.findByCodigo(profissional.getLotacaoDTO().getCodigoCBO());
			if (ocupacao != null) {
				profissional.setNomeOcupacao(ocupacao.getNome());
			}
			usuario.setEnabled(profissional.isAtivo());
			usuario.setTipoUsuario(tipoUsuario);
			Role role = roleRepository.findByNome(tipoUsuario.getNome());
			usuario.getRole().add(role);
		}

		profissionalService.save(profissional);
		return profissionalList(principal).addObject("sucesso", "Cadastro feito com sucesso");
	}

	@RequestMapping("/listar")
	public ModelAndView profissionalList(Principal principal) {
		ModelAndView mv = new ModelAndView("profissional/list-profissional");
		Usuario user = usuarioRepository.findByUsername(principal.getName());
		mv.addObject("user", user);
		mv.addObject("profissionais", profissionalService.findAll());
		return mv;
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView profissionalDetalhe(Principal principal, @PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("profissional/detalhe-admin");
		Usuario user = usuarioRepository.findByUsername(principal.getName());
		mv.addObject("user", user);
		mv.addObject("profissional", profissionalService.findOne(id));
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id, Principal principal) {
		return add(profissionalService.findOne(id), principal);
	}

}
