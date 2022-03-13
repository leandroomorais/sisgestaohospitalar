package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;

@Controller
@RequestMapping("/administracao")
public class AdministracaoController {

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping("/")
	public ModelAndView inicio(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/dashboard-admin");
		List<Profissional> profissionais = profissionalService.findAll();
		mv.addObject("estabelecimentos", estabelecimentoService.findAll());
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		mv.addObject("profissionais", profissionais);
		mv.addObject("medicos", profissionalService.findByTipoprofissional(TipoProfissional.MEDICO));
		mv.addObject("enfermeiros", profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		mv.addObject("tecnicos", profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		return mv;

	}

}
