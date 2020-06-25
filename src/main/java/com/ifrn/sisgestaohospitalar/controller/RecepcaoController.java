package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;

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

	@Autowired
	EstabelecimentoService estabelecimentoService;

	// Contador para Gerador de Número de Registro
	int i = 0;

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
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("paciente", cidadao);
		mv.addObject("user", user);
		mv.addObject("hasErrors", false);
		mv.addObject("navItem1", true);
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
	public ModelAndView saveCidadao(@Valid Cidadao cidadao, BindingResult result, Principal principal)
			throws ParseException {
		if (result.hasErrors()) {
			return addCidadao(cidadao, principal).addObject("hasErrors", true);
		}
		cidadao.setIdade(idade(cidadao.getDatanascimento()));
		String cns = cidadao.getCns();
		cns = cns.replace(".", "");
		cidadao.setCns(cns);
		cidadaoService.save(cidadao);
		GuiaAtendimento guiaAtendimento = new GuiaAtendimento();
		guiaAtendimento.setCidadao(cidadao);
		return addGuiaAtendimento(guiaAtendimento, principal).addObject("navItem1", true);
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
		ModelAndView mv = new ModelAndView("guiaAtendimento/form-guiaAtendimento");
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("guiaAtendimento", guiaAtendimento);
		List<Profissional> profissionais = new ArrayList<Profissional>();
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.MEDICO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		mv.addObject("profissionais", profissionais);
		String username = principal.getName();
		user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("navItem1", true);
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
	public ModelAndView saveGuiaAtendimento(GuiaAtendimento guiaAtendimento, @RequestParam("optionsRadios") TipoServico tipoServico, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return addGuiaAtendimento(guiaAtendimento, principal);
		}

		String username = principal.getName();
		guiaAtendimento.setProfissional(profissionalService.findByCpf(username));
		guiaAtendimento.setData(LocalDate.now());
		guiaAtendimento.setHora(LocalTime.now());
		guiaAtendimento.setNumeroregistro(geradorNumero());
		guiaAtendimento.setTipoServico(tipoServico);
		if(tipoServico != TipoServico.EscutaInicial) {
			guiaAtendimento.setClassificacaoDeRisco("AZUL");
		}
		
		guiatendimentoService.save(guiaAtendimento);
		return listarStatusAtd(principal).addObject("navItem1", true).addObject("navItem2", false);
	}

	/**
	 * Retorna a fila de atendimento de acordo com o Status
	 * 
	 * @param principal
	 * @return ModelAndView
	 */
	@RequestMapping("/listar-status")
	public ModelAndView listarStatusAtd(Principal principal) {
		ModelAndView mv = new ModelAndView("guiaAtendimento/list-guiaAtendimento");
		String username = principal.getName();
		user = profissionalService.findByCpf(username);
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("user", user);
		mv.addObject("guiasAtendimento", guiatendimentoService.teste(StatusAtendimento.FINALIZADO));
		mv.addObject("navItem2", true);
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
	public ModelAndView editarGuia(@PathVariable("id") Long id, Principal principal, GuiaAtendimento guiaAtendimento) {

		return addGuiaAtendimento(guiatendimentoService.findOne(id), principal);
	}

	/**
	 * Detalha a Guia de Atendimento selecionada a partir do Id
	 * 
	 * @param id
	 * @param principal
	 * @param guiaAtendimento
	 * @return ModelAndView
	 */
	@GetMapping("/detalhar/{id}")
	public ModelAndView detalharGuia(@PathVariable("id") Long id, Principal principal,
			GuiaAtendimento guiaAtendimento) {
		ModelAndView mv = new ModelAndView("guiaAtendimento/detalhe-guiaAtendimento");
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("guiaAtendimento", guiatendimentoService.findOne(id));
		String username = principal.getName();
		user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		return mv;
	}

	/**
	 * Retorna a idade do Cidadão a partir da data de nascimento e data atual
	 * 
	 * @param datanascimento
	 * @return
	 */
	public int idade(final LocalDate datanascimento) {
		final LocalDate dataAtual = LocalDate.now();
		final Period periodo = Period.between(datanascimento, dataAtual);
		return periodo.getYears();
	}

	/**
	 * Gera o número de Registro da Guia de Atendimento
	 * 
	 * @param guiaAtendimento
	 * @return
	 */
	public String geradorNumero() {
		LocalDate localDate = LocalDate.now();
		String ano = Integer.toString(localDate.getYear());
		String mes = Integer.toString(localDate.getMonthValue());
		i++;
		String id = Integer.toString(i);
		return "0" + id + "0" + mes + ano;
	}
}
