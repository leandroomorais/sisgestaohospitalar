package com.ifrn.sisgestaohospitalar.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.service.TriagemService;
import com.ifrn.sisgestaohospitalar.utils.LeitorXmlEsus;

/**
 * A classe Controller <code>AdministracaoController</code> possui os métodos de
 * controle para acesso da página principal do Módulo do Administrador, cadastro
 * de Profissionais de forma individual, upload e processamento do arquivo .xml
 * do ESUS, consultas e geração de relatórios diversos e do arquivo de
 * exportação para o SIA/SUS.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Controller
@RequestMapping("/administracao")
public class AdministracaoController {

	@Autowired
	private LeitorXmlEsus leitorXml;

	@Autowired
	ProfissionalService profissionalService;

	@Autowired
	EstabelecimentoService estabelecimentoService;

	@Autowired
	GuiaAtendimentoService guiaatendimentoService;

	@Autowired
	TriagemService triagemService;

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

	public String path = System.getProperty("user.dir") + "/Bpa/BPAEXPORT.txt";

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Direciona o usuário para a página principal do Módulo do Administrador
	 * 
	 * @param principal
	 * @return ModelAndView
	 */
	@RequestMapping("/")
	public ModelAndView inicio(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/dashboard-admin");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		List<Profissional> profissionais = profissionalService.findAll();
		mv.addObject("estabelecimentos", estabelecimentoService.findAll());
		mv.addObject("user", user);
		mv.addObject("profissionais", profissionais);
		mv.addObject("medicos", profissionalService.findByTipoprofissional(TipoProfissional.MEDICO));
		mv.addObject("enfermeiros", profissionalService.findByTipoprofissional(TipoProfissional.ENFERMEIRO));
		mv.addObject("tecnicos", profissionalService.findByTipoprofissional(TipoProfissional.TECNICO));
		mv.addObject("dataatual", getDateTime());
		mv.addObject("urgentes", triagemService.findByClassificacaoderisco("VERMELHO"));
		mv.addObject("totalatd", guiaatendimentoService.findAll());
		return mv;

	}

	/**
	 * Direciona o usuário para a página de Cadastro de Profissionais
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/cadastros")
	public ModelAndView cadastros() {
		ModelAndView mv = new ModelAndView("administrador/dashboard_cadastros");
		return mv;
	}

	/**
	 * Direciona o usuário para o formulário de Upload do arquivo .XML do ESUS
	 * 
	 * @param principal
	 * @return ModelAndView
	 */
	@RequestMapping("/form-upload")
	public ModelAndView formUpload(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/form-upload");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);

		return mv;
	}

	/**
	 * Realiza o processamento do arquivo .XML do ESUS
	 * 
	 * @param file
	 * @param cnes
	 * @param principal
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 * @throws ParseException
	 * @return ModelAndView
	 */
	@RequestMapping("/upload")
	public ModelAndView upload(@RequestParam("file") MultipartFile file, @RequestParam("cnes") String cnes,
			Principal principal) throws IOException, JAXBException, ParseException {

		String arquivo = uploadDirectory + file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(arquivo);
		try {
			Files.write(path, bytes);
		} catch (IOException e) {

		}

		ModelAndView mv = formUpload(principal);
		try {
			leitorXml.lerXmlEsus(arquivo, cnes);
			mv.addObject("sucesso", "O arquivo " + file.getOriginalFilename() + " foi processado com sucesso!");
		} catch (JAXBException e) {
			mv.addObject("erro", "Arquivo: " + file.getOriginalFilename() + e.getMessage());
		}
		return mv;
	}

	@RequestMapping("/gerarbpa")
	public ModelAndView gerarbpa(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/gerarbpa");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		return mv;
	}

//	@RequestMapping("/exportarbpa")
//	public ModelAndView exportarbpa(Principal principal, @RequestParam("mes") String mes) throws IOException {
//		ModelAndView mv = new ModelAndView("administrador/gerarbpa");
//		String username = principal.getName();
//		Profissional user = profissionalService.findByCpf(username);
//		mv.addObject("user",user);
//		String mesauxiliar;
//		mesauxiliar = mes.substring(5, 6);
//		writeTXT.escritor(path, mes);
//		mv.addObject("sucesso","O arquivo BPA para o SIA SUS foi gerado.");
//		mv.addObject("href",path);
//		
//		return mv;
//	}
}
