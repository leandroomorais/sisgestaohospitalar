package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.utils.Raca;

/**
 * A classe Controller <code>RecepcaoController</code> possui os métodos de
 * controle para acesso da página principal do Módulo de Recepção, Adição de
 * Cidadão a Fila de Atendimento e Visualização da Fila de Atendimento
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Controller
@RequestMapping("/recepcao")
public class RecepcaoController {

	@Autowired
	GuiaAtendimentoService guiatendimentoService;

	@Autowired
	CidadaoService cidadaoService;

	@Autowired
	ProfissionalService profissionalService;

	public Profissional user;

	/**
	 * Direciona o usuário para o formulário de Adição de Cidadão Fila de
	 * Atendimento
	 * 
	 * @param cidadao
	 * @param principal
	 * @return ModelAndView
	 */
	@RequestMapping("/adicionar-cidadao")
	public ModelAndView addCidadao(Cidadao cidadao, Principal principal) {
		ModelAndView mv = new ModelAndView("cidadao/form-cidadao");

		String username = principal.getName();

		user = profissionalService.findByCpf(username);
		
		List<String> listaRaca = Raca.listaCodigoRacaNome;
		Collections.sort(listaRaca);
		mv.addObject("listaRaca", listaRaca);
		mv.addObject("paciente", cidadao);
		mv.addObject("user", user);
		return mv;
	}

	/**
	 * Salva o Cidadão no Banco de Dados
	 * 
	 * @param cidadao
	 * @param result
	 * @param principal
	 * @return ModelAndView
	 * @throws ParseException 
	 */
	@PostMapping("/salvar-cidadao")
	public ModelAndView saveCidadao(@Valid Cidadao cidadao, BindingResult result, Principal principal) throws ParseException {
		if (result.hasErrors()) {
			return addCidadao(cidadao, principal);
		}

		String cns = cidadao.getCns();
		
		String datanascimento;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		datanascimento = cidadao.getDatanascimento().toString().replace('-', '/');
		System.out.println(datanascimento);
		
		Date data;

		cns = cns.replace(".", "");
		
		cidadao.setCns(cns);
		cidadao.setDatanascimento(data = dateFormat.parse(datanascimento));
		

		GuiaAtendimento guiaAtendimento = new GuiaAtendimento();

		cidadaoService.save(cidadao);

		guiaAtendimento.setCidadao(cidadao);

		guiatendimentoService.save(guiaAtendimento);

		return addGuiaAtendimento(guiaAtendimento, principal);
	}

	/**
	 * Direciona o usuário para adição de dados complementares do Cidadão e início
	 * do preenchimento da Guia de Atendimento
	 * 
	 * @param guiaAtendimento
	 * @param principal
	 * @return ModelAndView
	 */
	@RequestMapping("/adicionar-guia-atendimento")
	public ModelAndView addGuiaAtendimento(GuiaAtendimento guiaAtendimento, Principal principal) {
		ModelAndView mv = new ModelAndView("guiaAtendimento/form-guiaatendimento");
		mv.addObject("boletim", guiaAtendimento);
		mv.addObject("boletins", guiatendimentoService.findByStatusatendimento(StatusAtendimento.AGUARDANDOTRIAGEM));
		mv.addObject("profissionais", profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		String username = principal.getName();
		user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		return mv;
	}

	/**
	 * Salva a guia de Atendimento do Cidadão
	 * 
	 * @param guiaAtendimento
	 * @param result
	 * @param principal
	 * @return ModelAndView
	 */
	@PostMapping("/salvar-guia-atendimento")
	public ModelAndView saveGuiaAtendimento(GuiaAtendimento guiaAtendimento, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return addGuiaAtendimento(guiaAtendimento, principal);
		}

		guiaAtendimento.setStatusatendimento(StatusAtendimento.AGUARDANDOTRIAGEM);

		guiatendimentoService.save(guiaAtendimento);

		return listarStatusAtd(principal);
	}

	/**
	 * Retorna a fila de atendimento de acordo com o Status
	 * 
	 * @param principal
	 * @return ModelAndView
	 */
	@RequestMapping("/listar-status")
	public ModelAndView listarStatusAtd(Principal principal) {
		ModelAndView mv = new ModelAndView("boletim/boletim-list");
		String username = principal.getName();
		user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("boletins", guiatendimentoService.findByStatusatendimento(StatusAtendimento.AGUARDANDOTRIAGEM));

		return mv;
	}

	/**
	 * Edita a Guia de Atendimento selecionada a partir do Id
	 * 
	 * @param id
	 * @param principal
	 * @param guiaAtendimento
	 * @return ModelAndView
	 */
	@GetMapping("/editar/{id}")
	public ModelAndView editarCidadao(@PathVariable("id") Long id, Principal principal,
			GuiaAtendimento guiaAtendimento) {

		return addGuiaAtendimento(guiatendimentoService.findOne(id), principal);
	}
}
