package com.ifrn.sisgestaohospitalar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.ArquivoBPARepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.service.ArquivoBPAService;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.utils.EscritorTXT;

@Controller
@RequestMapping("/administracao")
public class AdministracaoController {

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	private ArquivoBPAService arquivoBpaService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ArquivoBPARepository arquivoBPARepository;

	EscritorTXT escritorTXT = new EscritorTXT();

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

	@RequestMapping("/")
	public ModelAndView inicio(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/dashboard-admin");
		String username = principal.getName();
		List<Profissional> profissionais = profissionalService.findAll();
		mv.addObject("estabelecimentos", estabelecimentoService.findAll());
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		mv.addObject("profissionais", profissionais);
		mv.addObject("medicos", profissionalService.findByTipoprofissional(TipoProfissional.MEDICO));
		mv.addObject("enfermeiros", profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		mv.addObject("tecnicos", profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		return mv;

	}

	@RequestMapping("/gerarbpa")
	public ModelAndView gerarbpa(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/gerarbpa");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		return mv;
	}

	@GetMapping("/exportarbpa")
	public ModelAndView exportarbpa(Principal principal, @RequestParam("competencia") String competencia)
			throws IOException {
		ModelAndView mv = new ModelAndView("administrador/gerarbpa");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		if (arquivoBpaService.findByCompetencia(competencia.replace("-", "")) != null) {
			return mv.addObject("erro", "O arquivo BPA para a competência selecionada já foi gerado.");
		}
		String[] periodo = competencia.split("-");
		ArquivoBPA arquivoBPA = arquivoBpaService.processarArquivoBPA(Integer.parseInt(periodo[0]),
				Integer.parseInt(periodo[1]));
		if (arquivoBPA != null) {
			File file = escritorTXT.geraArquivo(arquivoBPA);
			if (file != null) {
				arquivoBPA.setNomeArquivo(file.getName());
				arquivoBPA.setLink(file.getCanonicalPath());
				arquivoBPARepository.save(arquivoBPA);
				return listarBpa(principal).addObject("sucesso", "O arquivo BPA para o SIA SUS foi gerado.");
			}
			return mv.addObject("erro", "Ocorreu um erro ao tentar gerar");

		}

		return mv.addObject("erro", "Ocorreu um erro ao tentar gerar");

	}

	@GetMapping("/listar-bpa")
	public ModelAndView listarBpa(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/list-arquivobpa");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		mv.addObject("arquivosBpa", arquivoBpaService.findAll());
		return mv;
	}

	@GetMapping("/download/{id}")
	public HttpEntity<byte[]> download(@PathVariable("id") Long id) throws IOException {
		ArquivoBPA arquivoBPA = arquivoBpaService.findOne(id);

		byte[] arquivo = Files.readAllBytes(Paths.get(arquivoBPA.getLink()));
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "attachment;filename=\"" + arquivoBPA.getNomeArquivo() + "\"");
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);
		return entity;
	}

	private String getDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataatual = LocalDate.now();
		return dataatual.format(formatter);
	}

}
