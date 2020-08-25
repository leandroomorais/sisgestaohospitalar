package com.ifrn.sisgestaohospitalar.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.json.JSONArray;
import org.json.JSONObject;
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
import com.ifrn.sisgestaohospitalar.model.AtendimentoMedico;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.Medicamento;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.AtendimentoMedicoService;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProcedimentoSigtapService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;

/**
 * A classe Controller <code>AtendimentoMedicoController</code> possui os
 * métodos de controle para acesso da página principal do Módulo de Atendimento
 * Médico, Adição de Cidadão a Fila de Atendimento e Visualização da Fila de
 * Atendimento
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Controller
@RequestMapping("/atendimento-medico")
public class AtendimentoMedicoController {

	@Autowired
	private AtendimentoMedicoService atendimentoMedicoService;

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	private GuiaAtendimentoService guiaAtendimentoService;

	@Autowired
	private ProcedimentoSigtapService procedimentoSigtapService;
	
	List<ProcedimentoSigtap> procedimentos = new ArrayList<ProcedimentoSigtap>();

	/**
	 * Direciona o usuário a página com a listagem de Cidadãos para atendimento
	 * ordenada conforme Classificação de Risco
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping("/cidadaos-atendimento")
	public ModelAndView listarStatusAddAtd(Principal principal) {
		ModelAndView mv = new ModelAndView("atendimentoMedico/list-guiaAtendimento");
		List<GuiaAtendimento> listGuiaAtendimento = new ArrayList<GuiaAtendimento>();
		List<GuiaAtendimento> guiasAtendimento = guiaAtendimentoService
				.findByTipoServico(TipoServico.AtendimentoMedico);
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
		mv.addObject("guiasAtendimento", listGuiaAtendimento);
		mv.addObject("navItem1",true);
		return mv;
	}

	/**
	 * Direciona o usuário para a Página de Atendimento Médico do Cidadão
	 * 
	 * @param id
	 * @param atendimentoMedico
	 * @param principal
	 * @return
	 */
	@GetMapping("/realizar-atendimento/{id}")
	public ModelAndView addAtdMedico(@PathVariable("id") Long id, AtendimentoMedico atendimentoMedico,
			Principal principal) {
		procedimentos.clear();
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		GuiaAtendimento guiaAtendimento = guiaAtendimentoService.findOne(id);
		atendimentoMedico.setGuiaatendimento(guiaAtendimento);
		ModelAndView mv = new ModelAndView("atendimentoMedico/form-atendimentoMedico");
		List<Profissional> profissionais = new ArrayList<Profissional>();
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("user", user);
		mv.addObject("guiaAtendimento", guiaAtendimento);
		mv.addObject("atendimentoMedico", atendimentoMedico);
		mv.addObject("profissionais", profissionais);
		mv.addObject("navItem1",true);

		try {
			if (guiaAtendimento.getTriagem().getId() != null) {
				mv.addObject("getTriagem", true);
			}
		} catch (NullPointerException e) {
			mv.addObject("getTriagem", false);
		}
		return mv;
	}

	/**
	 * Salva os objetos do tipo Atendimento Médico
	 * 
	 * @param atendimentoMedico
	 * @param ids_procedimentos
	 * @param result
	 * @param principal
	 * @return
	 */
	@PostMapping("/salvar-atendimento")
	public ModelAndView save(@Valid AtendimentoMedico atendimentoMedico,
			@RequestParam("orientacao") String orientacao,
			@RequestParam(value = "medicamento", required = false) String medicamentos,
			@RequestParam(value = "optionsRadios", required = false) TipoServico tipoServico, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return addAtdMedico(atendimentoMedico.getGuiaatendimento().getId(), atendimentoMedico, principal);
		}
		if (atendimentoMedico.getDestinocidadao().equals("0")) {
			atendimentoMedico.getGuiaatendimento().setStatusAtendimento(StatusAtendimento.FINALIZADO);
		}
		
		atendimentoMedico.setProcedimentos(procedimentos);

		List<Medicamento> medicamentos2 = new ArrayList<Medicamento>();

		if (!medicamentos.isEmpty()) {
			System.out.println("String Array Medicamentos> " + medicamentos);
			JSONArray jsonArray = new JSONArray(medicamentos);
			for (int i = 0; i < jsonArray.length(); i++) {
				System.out.println("Objeto Json> " + jsonArray.get(i));
				JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
				System.out.println("JsonObject> " + jsonObject);
				Medicamento medicamento = new Medicamento();
				medicamento.setConcentracao(jsonObject.getString("concentracao"));
				medicamento.setNome(jsonObject.getString("nome"));
				medicamento.setForma(jsonObject.getString("forma"));
				medicamento.setPosologia(jsonObject.getString("posologia"));
				medicamentos2.add(medicamento);

			}
		}

		GuiaAtendimento guiaAtendimento = atendimentoMedico.getGuiaatendimento();
		guiaAtendimento.setTipoServico(tipoServico);
		guiaAtendimentoService.save(guiaAtendimento);
		String username = principal.getName();
		Profissional profissional = profissionalService.findByCpf(username);

		atendimentoMedico.setData(LocalDate.now());
		atendimentoMedico.setHora(LocalTime.now());
		atendimentoMedico.setProfissional(profissional);
		atendimentoMedico.setMedicamentos(medicamentos2);
		atendimentoMedicoService.save(atendimentoMedico);
		return listarStatusAddAtd(principal).addObject("navItem1",true);
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
