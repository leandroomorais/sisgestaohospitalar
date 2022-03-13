package com.ifrn.sisgestaohospitalar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ifrn.sisgestaohospitalar.dto.PreviaProducao;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.repository.ArquivoBPARepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.service.ArquivoBPAService;
import com.ifrn.sisgestaohospitalar.service.ProducaoService;
import com.ifrn.sisgestaohospitalar.utils.EscritorTXT;

@Controller
@RequestMapping("/bpa")
public class ArquivoBPAController {

	@Autowired
	private ArquivoBPAService arquivoBpaService;

	@Autowired
	private ArquivoBPARepository arquivoBPARepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProducaoService producaoService;

	EscritorTXT escritorTXT = new EscritorTXT();

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

	@RequestMapping("/gerarbpa")
	public ModelAndView gerarbpa(Principal principal) {
		ModelAndView mv = new ModelAndView("arquivoBPA/gerarbpa");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		return mv;
	}

	@GetMapping("/exportarbpa")
	public ModelAndView exportarbpa(Principal principal, @RequestParam("competencia") String competencia)
			throws IOException {
		ModelAndView mv = new ModelAndView("arquivoBPA/gerarbpa");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		if (arquivoBpaService.findByCompetencia(competencia.replace("-", "")) != null) {
			return mv.addObject("warning", "O arquivo BPA para a competência selecionada já foi gerado.");
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
		return mv.addObject("warning", "Sem registro de produção ambulatorial para o período selecionado");
	}

	@GetMapping("/listar-bpa")
	public ModelAndView listarBpa(Principal principal) {
		ModelAndView mv = new ModelAndView("arquivoBPA/list-arquivobpa");
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

	@GetMapping("/previa")
	public ModelAndView getProducaoProvisoria(Principal principal) {
		ModelAndView mv = new ModelAndView("/arquivoBPA/previa");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		PreviaProducao previaProducao = producaoService.getPreviaProducao();
		if (previaProducao != null) {
			mv.addObject("previaProducao", previaProducao);
			return mv;
		}
		return mv.addObject("warning", " Sem registros de produção");
	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Long id, Principal principal) {
		ModelAndView mv = new ModelAndView("arquivoBPA/detalhe");
		Optional<ArquivoBPA> optional = arquivoBPARepository.findById(id);
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
		if (optional.isPresent()) {
			mv.addObject("arquivoBPA", optional.get());
			return mv;
		}
		mv.addObject("erro", " Arquivo não encontrado");
		return mv;

	}

}
