package com.ifrn.sisgestaohospitalar.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.model.Atestado;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Exame;
import com.ifrn.sisgestaohospitalar.model.Prescricao;
import com.ifrn.sisgestaohospitalar.model.Prontuario;
import com.ifrn.sisgestaohospitalar.repository.AtestadoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidadaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ExameRepository;
import com.ifrn.sisgestaohospitalar.repository.PrescricaoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.ProntuarioRepository;
import com.ifrn.sisgestaohospitalar.service.JasperService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private JasperService jasperService;

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private AtestadoRepository atestadoRepository;

	@Autowired
	private PrescricaoRepository prescricaoRepository;

	@Autowired
	private ExameRepository exameRepository;

	@Autowired
	private CidadaoRepository cidadaoRepository;

	@Autowired
	private ProntuarioRepository prontuarioRepository;

	@GetMapping("/atestado/{id}")
	public void imprimeAtestado(@PathVariable("id") Long id, HttpServletResponse response, Principal principal)
			throws IOException {
		Optional<Atestado> optional = atestadoRepository.findById(id);
		if (optional.isPresent()) {
			jasperService.addParams("SUB_REPORT_DIR",
					jasperService.getJasperDiretorio().concat(jasperService.getJasperPrefixo()).concat("atestadoSub")
							.concat(jasperService.getJasperSufixo()));
			jasperService.addParams("ID_ATESTADO", id);
			jasperService.addParams("USER_NAME", profissionalRepository.findByCpf(principal.getName()).getNome());
			byte[] bytes = jasperService.exportarPDF("atestado");
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-disposition",
					"inline; filename=atestado-" + optional.get().getAtendimento().getCidadao().getNome() + ".pdf");
			response.getOutputStream().write(bytes);
		}
	}

	@GetMapping("/prescricao/{id}")
	public void imprimePrescricao(@PathVariable("id") Long id, HttpServletResponse response, Principal principal)
			throws IOException {
		Optional<Prescricao> optional = prescricaoRepository.findById(id);
		if (optional.isPresent()) {
			jasperService.addParams("ID_PRESCRICAO", id);
			jasperService.addParams("USER_NAME", profissionalRepository.findByCpf(principal.getName()).getNome());
			byte[] bytes = jasperService.exportarPDF("prescricao");
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-disposition",
					"inline; filename=receita-" + optional.get().getAtendimento().getCidadao().getNome() + ".pdf");
			response.getOutputStream().write(bytes);
		}
	}

	@GetMapping("/exame/{id}")
	public void imprimeSolicitacaoExame(@PathVariable("id") Long id, HttpServletResponse response, Principal principal)
			throws IOException {
		Optional<Exame> optional = exameRepository.findById(id);
		if (optional.isPresent()) {
			jasperService.addParams("SUB_REPORT_DIR",
					jasperService.getJasperDiretorio().concat(jasperService.getJasperPrefixo()).concat("exameSub")
							.concat(jasperService.getJasperSufixo()));
			jasperService.addParams("ID_EXAME", id);
			jasperService.addParams("USER_NAME", profissionalRepository.findByCpf(principal.getName()).getNome());
			byte[] bytes = jasperService.exportarPDF("exame");
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-disposition",
					"inline; filename=solicitacao-" + optional.get().getAtendimento().getCidadao().getNome() + ".pdf");
			response.getOutputStream().write(bytes);
		}
	}

	@GetMapping("/cidadao/{id}")
	public void imprimeCadastroCidadao(@PathVariable("id") Long id, HttpServletResponse response, Principal principal)
			throws IOException {
		Optional<Cidadao> optional = cidadaoRepository.findById(id);
		if (optional.isPresent()) {
			jasperService.addParams("ID_CIDADAO", id);
			jasperService.addParams("USER_NAME", profissionalRepository.findByCpf(principal.getName()).getNome());
			byte[] bytes = jasperService.exportarPDF("cidadao");
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-disposition", "inline; filename=cadastro-" + optional.get().getNome() + ".pdf");
			response.getOutputStream().write(bytes);
		}
	}

	@GetMapping("/prontuario/{id}")
	public void imprimirProntuario(@PathVariable("id") Long id, HttpServletResponse response, Principal principal)
			throws IOException {
		Optional<Prontuario> optional = prontuarioRepository.findById(id);
		if (optional.isPresent()) {
			jasperService.addParams("ID_PRONTUARIO", id);
			jasperService.addParams("SUB_ALERGIAS", jasperService.getJasperDiretorio() + "report-prontuario-alergia-sub"
					+ jasperService.getJasperSufixo());
			jasperService.addParams("SUB_CONDICOES", jasperService.getJasperDiretorio()
					+ "report-prontuario-codicoes-sub" + jasperService.getJasperSufixo());
			jasperService.addParams("SUB_ANTROPOMETRIA", jasperService.getJasperDiretorio()
					+ "report-prontuario-antropometria-sub" + jasperService.getJasperSufixo());
			jasperService.addParams("SUB_ATENDIMENTOS", jasperService.getJasperDiretorio()
					+ "report-prontuario-antendimentos-sub" + jasperService.getJasperSufixo());
			jasperService.addParams("SUB_PRESCRICOES", jasperService.getJasperDiretorio()
					+ "report-prontuario-prescricoes" + jasperService.getJasperSufixo());
			jasperService.addParams("SUB_EXAMES", jasperService.getJasperDiretorio()
					+ "report-prontuario-antendimentos-exames" + jasperService.getJasperSufixo());
			jasperService.addParams("SUB_ATESTADOS", jasperService.getJasperDiretorio() + "report-prontuario-atestados"
					+ jasperService.getJasperSufixo());
			jasperService.addParams("SUB_EXAMES_PROCEDIMENTOS", jasperService.getJasperDiretorio()
					+ "report-prontuario-antendimentos-exames-procedimentos-sub" + jasperService.getJasperSufixo());

			jasperService.addParams("USER_NAME", profissionalRepository.findByCpf(principal.getName()).getNome());

			byte[] bytes = jasperService.exportarPDF("prontuario");
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader("Content-disposition", "inline; filename=prontuario.pdf");
			response.getOutputStream().write(bytes);

		}
	}

}
