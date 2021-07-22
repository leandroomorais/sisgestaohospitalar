package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.sisgestaohospitalar.enums.CodigoRaca;
import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;
import com.ifrn.sisgestaohospitalar.service.CidadaoCadsusService;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaAdicionadoNaFilaException;
import com.ifrn.sisgestaohospitalar.service.exception.CidadaoJaCadastradoException;

@Controller
@RequestMapping("/recepcao")
public class RecepcaoController {

	private static final Logger logger = LoggerFactory.getLogger(RecepcaoController.class);

	@Autowired
	GuiaAtendimentoService guiatendimentoService;

	@Autowired
	CidadaoService cidadaoService;

	@Autowired
	CidadaoRepository cidadaoRepository;

	@Autowired
	ProfissionalService profissionalService;

	@Autowired
	EstabelecimentoService estabelecimentoService;

	@Autowired
	CidadaoCadsusService cadsusService;

	@Autowired
	LogradouroRepository logradouroRepository;

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
		mv.addObject("cidadao", cidadao);
		mv.addObject("user", user);
		mv.addObject("hasErrors", false);
		mv.addObject("navItem1", true);
		mv.addObject("racas", CodigoRaca.values());
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
	public ModelAndView saveCidadao(@Valid Cidadao cidadao, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return addCidadao(cidadao, principal).addObject("hasErrors", true);
		}
		try {
			cidadaoService.save(cidadao);
		} catch (CidadaoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			result.rejectValue("datanascimento", e.getMessage(), e.getMessage());
			return addCidadao(cidadao, principal);
		}

		GuiaAtendimento guiaAtendimento = new GuiaAtendimento();
		guiaAtendimento.setCidadao(cidadao);
		return addGuiaAtendimento(guiaAtendimento, principal).addObject("navItem1", true).addObject("success",
				"O Cidadão " + guiaAtendimento.getCidadao().getNome() + " foi adicionado a Base Local");
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
		mv.addObject("tipoServicos", TipoServico.values());
		List<Profissional> profissionais = new ArrayList<Profissional>();
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.MEDICO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		mv.addObject("profissionais", profissionais);
		String username = principal.getName();
		user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("navItem1", true);
		mv.addObject("edit", false);
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
	public ModelAndView saveGuiaAtendimento(@Valid GuiaAtendimento guiaAtendimento, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return addGuiaAtendimento(guiaAtendimento, principal);
		}
		try {
			String username = principal.getName();
			guiaAtendimento.setProfissional(profissionalService.findByCpf(username));
			guiaAtendimento.setData(LocalDate.now());
			guiaAtendimento.setHora(LocalTime.now());
			guiaAtendimento.setNumeroregistro(geradorNumero());
			if (guiaAtendimento.getTipoServico() != TipoServico.Triagem) {
				guiaAtendimento.setClassificacaoDeRisco("AZUL");
			}
			if (guiaAtendimento.getStatusAtendimento() == null) {
				guiaAtendimento.setStatusAtendimento(StatusAtendimento.AGUARDANDOATENDIMENTO);
			}

			if (guiaAtendimento.getStatusAtendimento().equals(StatusAtendimento.NAOAGUARDOU)) {
				guiaAtendimento.setTipoServico(TipoServico.Inativo);
			}

			guiatendimentoService.save(guiaAtendimento);

		} catch (CidadaoJaAdicionadoNaFilaException e) {
			result.rejectValue("cidadao", e.getMessage(), e.getMessage());
			return addGuiaAtendimento(guiaAtendimento, principal);
		}
		return listarStatusAtd(principal).addObject("navItem1", true).addObject("navItem2", false).addObject("success",
				"O Cidadão " + guiaAtendimento.getCidadao().getNome()
						+ " foi adicionado a fila de atendimento para o serviço: "
						+ guiaAtendimento.getTipoServico().getDescricao());
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
		mv.addObject("guiasAtendimento", guiatendimentoService.listaAtendimentos());
		mv.addObject("statusAtendimentos", StatusAtendimento.values());
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
		return addGuiaAtendimento(guiatendimentoService.findOne(id), principal).addObject("edit", true);
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

	@GetMapping("/excluir/{id}")
	public ModelAndView excluirGuia(@PathVariable("id") Long id, Principal principal, GuiaAtendimento guiaAtendimento) {
		ModelAndView mv = new ModelAndView("guiaAtendimento/detalhe-guiaAtendimento");
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("guiaAtendimento", guiatendimentoService.findOne(id));
		String username = principal.getName();
		user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		guiatendimentoService.delete(id);
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
