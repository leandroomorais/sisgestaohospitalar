package com.ifrn.sisgestaohospitalar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.GuiaAtendimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.ArquivoBPAService;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.service.TriagemService;
import com.ifrn.sisgestaohospitalar.utils.GeradorBPA;
//import com.ifrn.sisgestaohospitalar.utils.EscritorTXT;
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
	CidadaoService cidadaoService;

	@Autowired
	GeradorBPA geradorBpa;
	
	@Autowired
	ArquivoBPAService arquivoBpaService;

	@Autowired
	TriagemService triagemService;

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads/";


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
		mv.addObject("totalatd", guiaatendimentoService.findByData(LocalDate.now()));
		mv.addObject("urgencias", getUrgencias());
		return mv;

	}

	@RequestMapping("/cadastrar-profissional")
	public ModelAndView add(Profissional profissional, Principal principal) {
		ModelAndView mv = new ModelAndView("profissional/form-profissional");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("profissional", profissional);
		mv.addObject("user", user);
		return mv;
	}

	@PostMapping("/salvar-profissional")
	public ModelAndView save(@Valid Profissional profissional, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return add(profissional, principal);
		}
		profissional.setPassword(new BCryptPasswordEncoder().encode("sgh" + profissional.getCpf()));
		profissional.setEnabled(true);

		String name = profissional.getNome();
		String[] name1 = name.split(" ");
		String firstname = name1[0].toString() + " " + name1[1].toString();
		profissional.setFirstname(firstname);
		profissional.setUsername(profissional.getCpf());

		profissionalService.save(profissional);
		return profissionalList(principal).addObject("sucesso", "O cadastro do Profissional foi realizado.");
	}

	@GetMapping("/profissional-edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id, Principal principal) {
		return add(profissionalService.findOne(id), principal);
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, Principal principal) {
		profissionalService.delete(id);
		return profissionalList(principal);
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

	@RequestMapping("/listar-profissional")
	public ModelAndView profissionalList(Principal principal) {
		ModelAndView mv = new ModelAndView("profissional/list-profissional");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("profissionais", profissionalService.findAll());
		return mv;
	}

	@RequestMapping("/profissional-detalhe/{id}")
	public ModelAndView profissionalDetalhe(Principal principal, @PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("profissional/profissional-details");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("profissional", profissionalService.findOne(id));
		return mv;
	}

	@RequestMapping("meu-perfil/{id}")
	public ModelAndView meuPerfil(Principal principal, @PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("profissional/perfil-admin-detalhes");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("profissional", profissionalService.findOne(id));
		return mv;
	}
	
	
	@RequestMapping("/buscar-atendimento")
	public ModelAndView buscarGuiaAtendimento(Principal principal) {
		ModelAndView mv = new ModelAndView("guiaAtendimento/buscar-guiaAtendimento");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("guiasAtendimentos",null);
		return mv;
	}
	
	@GetMapping("/buscar-data")
	public ModelAndView buscarData(Principal principal, @RequestParam("data") String data) {
		if(guiaatendimentoService.findByData(LocalDate.parse(data)).isEmpty()) {
			return buscarGuiaAtendimento(principal).addObject("erro","Nenhum atendimento encontrado para esta data");
		}
		return buscarGuiaAtendimento(principal).addObject("guiasAtendimentos", guiaatendimentoService.findByData(LocalDate.parse(data)));
	}
	
	@GetMapping("/buscar-periodo")
	public ModelAndView buscarData(Principal principal, @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) {
		int compare = LocalDate.parse(dataFinal).compareTo(LocalDate.parse(dataInicial));
		if(compare < 0) {
			return buscarGuiaAtendimento(principal).addObject("erro","A data Inicial não deve se superior a data final");
		}
		if(guiaatendimentoService.findByPeriodo(LocalDate.parse(dataFinal), LocalDate.parse(dataFinal)).isEmpty()) {
			return buscarGuiaAtendimento(principal).addObject("erro","Nenhum atendimento encontrado para esta data");
		}
		return buscarGuiaAtendimento(principal).addObject("guiasAtendimentos", guiaatendimentoService.findByPeriodo(LocalDate.parse(dataFinal), LocalDate.parse(dataFinal)));
	}
	
	@GetMapping("/atendimento-detalhe/{id}")
	public ModelAndView guiaAtendimentoDetalhe(Principal principal, @PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("guiaAtendimento/detalhe-guiaAtendimento-admin");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("guiaAtendimento", guiaatendimentoService.findOne(id));
		return mv;
	}
	
	@GetMapping("/buscar-cidadaos")
	public ModelAndView buscarCidadao(Principal principal) {
		ModelAndView mv = new ModelAndView("cidadao/buscar-cidadao");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		Cidadao cidadao = new Cidadao();
		mv.addObject("cidadao", cidadao);
		mv.addObject("user", user);
		return mv;
	}
	
	@GetMapping("/buscar-cidadao-cns")
	public ModelAndView buscarCidadaoCns(Principal principal, @RequestParam("cns") String cns) {
		cns.replace(".", "");
		if(cidadaoService.findByCns(cns) == null) {
			return buscarCidadao(principal).addObject("erro","Nenhum Cidadão foi encontrado com este CNS");
		}
		return buscarCidadao(principal).addObject("cidadao",cidadaoService.findByCns(cns));
	}
	
	@GetMapping("/buscar-cidadao-cpf")
	public ModelAndView buscarCidadaoCpf(Principal principal, @RequestParam("cpf") String cpf) {
		cpf.replace(".", "");
		if(cidadaoService.findByCpf(cpf) == null) {
			return buscarCidadao(principal).addObject("erro","Nenhum Cidadão foi encontrado com este CPF");
		}
		return buscarCidadao(principal).addObject("cidadao",cidadaoService.findByCpf(cpf));
	}
	
	@GetMapping("/buscar-cidadao-nome")
	public ModelAndView buscarCidadaoNome(Principal principal, @RequestParam("nome") String nome) {
		nome.replace(".", "");
		if(cidadaoService.findByNome(nome) == null) {
			return buscarCidadao(principal).addObject("erro","Nenhum Cidadão foi encontrado com este Nome");
		}
		return buscarCidadao(principal).addObject("cidadao",cidadaoService.findByNome(nome));
	}
	
	@GetMapping("/cidadao-detalhe/{id}")
	public ModelAndView cidadaoDetalhe(Principal principal, @PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("cidadao/detalhe-cidadao-admin");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("cidadao", cidadaoService.findOne(id));
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

	@GetMapping("/exportarbpa")
	public ModelAndView exportarbpa(Principal principal, @RequestParam("mes") String mes) throws IOException {
		ModelAndView mv = new ModelAndView("administrador/gerarbpa");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		if(arquivoBpaService.findByCompetencia(mes.replace("-", "")) != null) {
			return mv.addObject("erro","O arquivo BPA para a competência selecionada já foi gerado.");
		}
		geradorBpa.geradorBPA(mes, "2380633");
		mv.addObject("sucesso", "O arquivo BPA para o SIA SUS foi gerado.");
		return mv;
	}
	
	@GetMapping("/listar-bpa")
	public ModelAndView listarBpa(Principal principal) {
		ModelAndView mv = new ModelAndView("administrador/list-arquivobpa");
		String username = principal.getName();
		Profissional user = profissionalService.findByCpf(username);
		mv.addObject("user", user);
		mv.addObject("arquivosBpa", arquivoBpaService.findAll());
		return mv;
	}
	
	@GetMapping("/download/{id}")
	public HttpEntity<byte[]> download(@PathVariable("id") Long id) throws IOException{
		ArquivoBPA arquivoBPA = arquivoBpaService.findOne(id);
		
		byte[] arquivo = Files.readAllBytes(Paths.get(arquivoBPA.getLink()));
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add("Content-Disposition", "attachment;filename=\""+ arquivoBPA.getNomeArquivo() +"\"");
	    HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo,httpHeaders);
	    return entity;
	}

	private String getDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataatual = LocalDate.now();
		return dataatual.format(formatter);
	}

	private int getUrgencias() {
		int i = 0;
		try {
			for (GuiaAtendimento guiaAtendimento : guiaatendimentoService.findByData(LocalDate.now())) {
				if (guiaAtendimento.getClassificacaoDeRisco().equals("VERMELHO")) {
					i++;
				}
			}
		} catch (NullPointerException exception) {
			exception.printStackTrace();
		}

		return i;
	}
}
