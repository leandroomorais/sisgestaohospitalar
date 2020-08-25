package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.enums.TipoServico;
import com.ifrn.sisgestaohospitalar.model.AdministracaoMedicamento;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.AdministracaoMedicamentoService;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProcedimentoSigtapService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;

/**
 * A classe Controller <code>AdministracaoMedicamentoController</code> possui os
 * métodos de controle para acesso da página principal do Módulo de
 * Administracao de Medicamentos, Adição de Cidadão a Fila de Atendimento e
 * Visualização da Fila de Atendimento
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Controller
@RequestMapping("/administracao-medicamento")
public class AdministracaoMedicamentoController {

	@Autowired
	EstabelecimentoService estabelecimentoService;

	@Autowired
	GuiaAtendimentoService guiaAtendimentoService;

	@Autowired
	AdministracaoMedicamentoService administracaoMedicamentoService;

	@Autowired
	ProfissionalService profissionalService;

	@Autowired
	ProcedimentoSigtapService procedimentoSigtapService;
	
	List<ProcedimentoSigtap> procedimentos = new ArrayList<ProcedimentoSigtap>();

	/**
	 * Direciona o usuário para a Lista de Cidadãos que estão aguardando Atendimento
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping("/cidadaos-atendimento")
	public ModelAndView listarStatusAddAtd(Principal principal) {
		ModelAndView mv = new ModelAndView("administracaoMedicamento/list-guiaAtendimento");
		List<GuiaAtendimento> listGuiaAtendimento = new ArrayList<GuiaAtendimento>();
		List<GuiaAtendimento> guiasAtendimento = guiaAtendimentoService
				.findByTipoServico(TipoServico.AdminMedicamentos);
		if (guiasAtendimento != null) {

			for (GuiaAtendimento gVerm : guiasAtendimento) {
				if (gVerm.getClassificacaoDeRisco() != null && gVerm.getClassificacaoDeRisco().equals("VERMELHO")) {
					listGuiaAtendimento.add(gVerm);
				}
			}

			for (GuiaAtendimento bLar : guiasAtendimento) {
				if (bLar.getClassificacaoDeRisco() != null && bLar.getClassificacaoDeRisco().equals("LARANJA")) {
					listGuiaAtendimento.add(bLar);
				}
			}

			for (GuiaAtendimento bAma : guiasAtendimento) {
				if (bAma.getClassificacaoDeRisco() != null && bAma.getClassificacaoDeRisco().equals("AMARELO")) {
					listGuiaAtendimento.add(bAma);
				}
			}

			for (GuiaAtendimento bVerd : guiasAtendimento) {
				if (bVerd.getClassificacaoDeRisco() != null && bVerd.getClassificacaoDeRisco().equals("VERDE")) {
					listGuiaAtendimento.add(bVerd);
				}
			}

			for (GuiaAtendimento bAzu : guiasAtendimento) {
				if (bAzu.getClassificacaoDeRisco() != null && bAzu.getClassificacaoDeRisco().equals("AZUL")) {
					listGuiaAtendimento.add(bAzu);
				}
			}
		}
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("user", user);
		mv.addObject("guiasAtendimento", guiasAtendimento);
		mv.addObject("navItem1", true);
		return mv;
	}

	/**
	 * Direciona o usuário para o formulário de atendimento da Administração de
	 * Medicamentos
	 * 
	 * @param id
	 * @param administracaoMedicamento
	 * @param principal
	 * @return
	 */
	@GetMapping("/realizar-atendimento/{id}")
	public ModelAndView addAdminMedicamento(@PathVariable("id") Long id,
			AdministracaoMedicamento administracaoMedicamento, Principal principal) {
		procedimentos.clear();
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		GuiaAtendimento guiaAtendimento = guiaAtendimentoService.findOne(id);
		System.out.println("ID DA GUIA DE ATENDIEMTO>" + guiaAtendimento.getId().toString());
		administracaoMedicamento.setGuiaAtendimento(guiaAtendimento);
		System.out.println("ID DA GUIA SETADA>" + administracaoMedicamento.getGuiaAtendimento().getId().toString());
		ModelAndView mv = new ModelAndView("administracaoMedicamento/form-administracaoMedicamento");
		List<Profissional> profissionais = new ArrayList<Profissional>();
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.MEDICO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("user", user);
		mv.addObject("guiaAtendimento", guiaAtendimento);
		mv.addObject("administracaoMedicamento", administracaoMedicamento);
		mv.addObject("profissionais", profissionais);
		try {
			if(guiaAtendimento.getAtendimentomedico() != null) {
				mv.addObject("medicamentos", guiaAtendimento.getAtendimentomedico().getMedicamentos());
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@PostMapping("/salvar-adminMedicamento")
	public ModelAndView save(@Valid AdministracaoMedicamento administracaoMedicamento,
			@RequestParam(value = "optionsRadios", required = false) TipoServico tipoServico, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return addAdminMedicamento(administracaoMedicamento.getGuiaAtendimento().getId(), administracaoMedicamento,
					principal);
		}
		if (administracaoMedicamento.getDestinocidadao().equals("0")) {
			administracaoMedicamento.getGuiaAtendimento().setStatusAtendimento(StatusAtendimento.FINALIZADO);

		}
		
		administracaoMedicamento.setProcedimentos(procedimentos);

		GuiaAtendimento guiaAtendimento = administracaoMedicamento.getGuiaAtendimento();
		guiaAtendimento.setTipoServico(tipoServico);
		guiaAtendimentoService.save(guiaAtendimento);

		String username = principal.getName();
		Profissional profissional = profissionalService.findByCpf(username);

		administracaoMedicamento.setProfissional(profissional);
		administracaoMedicamento.setData(LocalDate.now());
		administracaoMedicamento.setHora(LocalTime.now());
		administracaoMedicamentoService.save(administracaoMedicamento);
		return listarStatusAddAtd(principal);
	}
	
	@PostMapping("/add-procedimento")
	@ResponseBody
	public void addProcedimento(ProcedimentoSigtap procedimentoSigtap) {
		ProcedimentoSigtap prSigtap = procedimentoSigtapService.findOne(procedimentoSigtap.getId());
		procedimentos.add(prSigtap);
		imprimeLista();
	}
	
	@DeleteMapping("/delete-procedimento")
	@ResponseBody
	public void deleteProcedimento(ProcedimentoSigtap procedimentoSigtap) {
		for(int i = 0; i < procedimentos.size(); i++) {
			if(procedimentos.get(i).getId().equals(procedimentoSigtap.getId())) {
				procedimentos.remove(i);
			}
		}
		imprimeLista();
	}
	
	@GetMapping("/imprimir")
	@ResponseBody
	public void imprimeLista() {
		if(procedimentos.isEmpty()) {
			System.out.println("### A lista está vazia ###");
		}
		for(int i = 0; i < procedimentos.size(); i++) {
			System.out.println("PROCEDIMENTO DA LISTA> " + procedimentos.get(i).getNomeprocedimento());
		}
	}

}
