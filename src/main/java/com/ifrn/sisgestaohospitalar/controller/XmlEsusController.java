package com.ifrn.sisgestaohospitalar.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.ParseException;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.utils.LeitorXmlEsus;

@RequestMapping("/xml-esus")
@Controller
public class XmlEsusController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

	@Autowired
	private LeitorXmlEsus leitorXmlEsus;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping("/form-upload")
	public ModelAndView formUpload(Principal principal) {
		ModelAndView mv = new ModelAndView("xmlEsus/form-upload");
		mv.addObject("user", usuarioRepository.findByUsername(principal.getName()));
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

		ModelAndView mv = formUpload(principal);

		String arquivo = uploadDirectory + file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		Path path = Paths.get(arquivo);
		try {
			Files.write(path, bytes);
		} catch (IOException e) {

		}

		try {
			leitorXmlEsus.lerXmlEsus(arquivo, cnes);
			mv.addObject("sucesso", "O arquivo " + file.getOriginalFilename() + " foi processado com sucesso!");

		} catch (JAXBException e) {
			return formUpload(principal).addObject("erro", e.getMessage());
		}
		return mv;
	}

}
