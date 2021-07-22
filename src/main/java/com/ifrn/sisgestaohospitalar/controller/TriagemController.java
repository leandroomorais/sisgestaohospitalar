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
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.Triagem;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProcedimentoSigtapService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.service.TriagemService;


@Controller
@RequestMapping("/triagem")
public class TriagemController {

	@Autowired
	private TriagemService triagemService;

	@Autowired
	private GuiaAtendimentoService guiaAtendimentoService;

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ProcedimentoSigtapService procedimentoSigtapService;

	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	List<ProcedimentoSigtap> procedimentos = new ArrayList<ProcedimentoSigtap>();

	/**
	 * Direciona o usuário para a página com a lista de Cidadãos para serem
	 * atendidos
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping("/cidadaos-atendimento")
	public ModelAndView listStatusAddTri(Principal principal) {
		ModelAndView mv = new ModelAndView("triagem/list-guiaAtendimento");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("user", user);
		mv.addObject("guiasAtendimento", guiaAtendimentoService.findByTipoServico(TipoServico.Triagem));
		mv.addObject("navItem1", true);
		return mv;
	}

	/**
	 * Direciona o usuário para o formulário de Triagem
	 * 
	 * @param id
	 * @param triagem
	 * @param principal
	 * @return
	 */
	@GetMapping("/realizar-triagem/{id}")
	public ModelAndView addTriagem(@PathVariable("id") Long id, Triagem triagem, Principal principal) {
		procedimentos.clear();
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		GuiaAtendimento guiaAtendimento = guiaAtendimentoService.findOne(id);
		Profissional profissional = profissionalService.findByCpf(username);
		triagem.setGuiaatendimento(guiaAtendimento);
		triagem.setProfissional(profissional);
		List<Profissional> profissionais = new ArrayList<Profissional>();
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.MEDICO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		profissionais.addAll(profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		ModelAndView mv = new ModelAndView("triagem/form-triagem");
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
		mv.addObject("user", user);
		mv.addObject("profissional", profissional);
		mv.addObject("triagem", triagem);
		mv.addObject("profissionais", profissionais);
		mv.addObject("guiaAtendimento", guiaAtendimento);
		mv.addObject("navItem1", true);
		return mv;
	}

	/**
	 * @param triagem
	 * @param result
	 * @param ids_procedimentos
	 * @param principal
	 * @return
	 */
	@PostMapping("/salvar-triagem")
	public ModelAndView save(@Valid Triagem triagem, BindingResult result,
			@RequestParam(value = "classificacaoDeRisco", required = false) String classificacaoDeRisco,
			@RequestParam(value = "optionsRadios", required = false) TipoServico tipoServico, Principal principal) {

		GuiaAtendimento guiaAtendimento = triagem.getGuiaatendimento();

		if (result.hasErrors()) {
			return addTriagem(guiaAtendimento.getId(), triagem, principal);
		}

		if (triagem.getDestinocidadao().equals("0")) {
			triagem.getGuiaatendimento().setStatusAtendimento(StatusAtendimento.FINALIZADO);
		}

		triagem.getGuiaatendimento().setProfissionaldestino(triagem.getProfissionaldestino());
		triagem.setProcedimentos(procedimentos);
		guiaAtendimento.setClassificacaoDeRisco(classificacaoDeRisco);
		guiaAtendimento.setTipoServico(tipoServico);
		guiaAtendimentoService.save(guiaAtendimento);
		String username = principal.getName();
		Profissional profissional = profissionalService.findByCpf(username);
		triagem.setData(LocalDate.now());
		triagem.setHora(LocalTime.now());
		triagem.setProfissional(profissional);
		triagemService.save(triagem);
		procedimentos.clear();
		return listStatusAddTri(principal).addObject("navItem1", true);
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
