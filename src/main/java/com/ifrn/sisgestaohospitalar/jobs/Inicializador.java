//package com.ifrn.sisgestaohospitalar.jobs;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import javax.xml.bind.JAXBException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import com.ifrn.sisgestaohospitalar.enums.CodigoRaca;
//import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;
//import com.ifrn.sisgestaohospitalar.enums.Status;
//import com.ifrn.sisgestaohospitalar.enums.TipoProfissional;
//import com.ifrn.sisgestaohospitalar.enums.TipoServico;
//import com.ifrn.sisgestaohospitalar.model.Atendimento;
//import com.ifrn.sisgestaohospitalar.model.Cep_Ibge;
//import com.ifrn.sisgestaohospitalar.model.Cid;
//import com.ifrn.sisgestaohospitalar.model.Cidadao;
//import com.ifrn.sisgestaohospitalar.model.Comorbidade;
//import com.ifrn.sisgestaohospitalar.model.EnderecoEstabelecimento;
//import com.ifrn.sisgestaohospitalar.model.Estado;
//import com.ifrn.sisgestaohospitalar.model.Habito;
//import com.ifrn.sisgestaohospitalar.model.Endereco;
//import com.ifrn.sisgestaohospitalar.model.Logradouro;
//import com.ifrn.sisgestaohospitalar.model.Municipio;
//import com.ifrn.sisgestaohospitalar.model.Procedimento;
//import com.ifrn.sisgestaohospitalar.model.Profissional;
//import com.ifrn.sisgestaohospitalar.model.HistoricoStatus;
//import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
//import com.ifrn.sisgestaohospitalar.repository.Cep_IbgeRepository;
//import com.ifrn.sisgestaohospitalar.repository.ComorbidadeRepository;
//import com.ifrn.sisgestaohospitalar.repository.EnderecoRepository;
//import com.ifrn.sisgestaohospitalar.repository.EstadoRepository;
//import com.ifrn.sisgestaohospitalar.repository.HabitoRepository;
//import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;
//import com.ifrn.sisgestaohospitalar.repository.MunicipioRepository;
//import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;
//import com.ifrn.sisgestaohospitalar.service.CidadaoService;
//import com.ifrn.sisgestaohospitalar.service.ComorbidadeService;
//import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
//import com.ifrn.sisgestaohospitalar.utils.LeitorTxtSigtap;
//import com.ifrn.sisgestaohospitalar.utils.LeitorXmlEsus;
//import com.ifrn.sisgestaohospitalar.utils.SalvarEstadosEMunicipios;
//import com.ifrn.sisgestaohospitalar.utils.SalvarLogradouros;
//
//import jxl.read.biff.BiffException;
//
//@Component
//public class Inicializador implements ApplicationListener<ContextRefreshedEvent> {
//
//	@Autowired
//	private LeitorXmlEsus leitorXmlEsus;
//
//	@Autowired
//	private LeitorTxtSigtap leitorTxtSigtap;
//
//	// @Autowired
//	// private RoleService roleService;
//
//	@Autowired
//	private CidadaoService cidadaoService;
//
//	@Autowired
//	private ProfissionalService profissionalService;
//
//	// @Autowired
//	// private GuiaAtendimentoService guiaAtendimentoService;
//
//	@Autowired
//	private Cep_IbgeRepository cepIbgeRepository;
//
//	@Autowired
//	private MunicipioRepository municipioRepository;
//
//	@Autowired
//	private LogradouroRepository logradouroRepository;
//
//	@Autowired
//	private EstadoRepository estadoRepository;
//
//	@Autowired
//	private EnderecoRepository enderecoRepository;
//
//	@Autowired
//	private ProcedimentoRepository procedimentoRepository;
//
//	@Autowired
//	private AtendimentoRepository atendimentoRepository;
//	@Autowired
//	private ComorbidadeRepository comorbidadeRepository;
//	@Autowired
//	private HabitoRepository habitoRepository;
//	
//	
//	
//
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//
//		String cnes = "2380633";
//		String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS21_241360.xml";
//
//		Profissional profissional = new Profissional();
////
////		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////
////		profissional.setNome("CARLOS AUGUSTO SILVA");
////		profissional.setCns("898002940850595");
////		profissional.setCpf("47748001049");
////		profissional.setDataNascimento("20-06-1994");
////		profissional.setSexo("M");
////		profissional.setNomeAbrev("CARLOS AUGUSTO");
////		profissional.setTipoProfissional(TipoProfissional.MEDICO);
////		
////		profissionalService.save(profissional);
////		
////		Cidadao cidadaoMariana = new Cidadao();
////		
////		cidadaoMariana.setCns("110525916150007"); 
////		cidadaoMariana.setCodigoNacionalidade(1); 
////		cidadaoMariana.setCodigoRaca(CodigoRaca.PARDA);
////		cidadaoMariana.setDataNascimento(LocalDate.parse("1994-06-20"));
////		cidadaoMariana.setCpf("09814354406");
////		cidadaoMariana.setEmail("MARIANA.MARA@LIVE.COM");
////		cidadaoMariana.setNome("MARIANA MARA ROCHA DE QUEIROZ");
////		cidadaoMariana.setNomeMae("MARIA DE LOURDES ROCHA DE QUEIROZ");
////		cidadaoMariana.setNomePai("GUILHERME ROCHA DE QUEIROZ");
////		cidadaoMariana.setSexo("F"); 
////		Endereco endereco = new Endereco();
////		endereco.setBairro("centro");
////		endereco.setCep("59945000");
////		Logradouro logradouro = new Logradouro();
////		logradouro.setCodigo(Long.parseLong("12"));
////		logradouro.setDescricao("RUA");
////		logradouroRepository.save(logradouro);
////		endereco.setLogradouro(logradouro);
////		endereco.setNomeLogradouro("CÉSAR ROCHA");
////		endereco.setNumero("90");
////		endereco.setComplemento("casa");
////		Estado estado = new Estado();
////		estado.setCodigo(24);
////		estado.setNome("RIO GRANDE DO NORTE");
////		estado.setSigla("RN");
////		estadoRepository.save(estado);
////		Municipio municipio = new Municipio();
////		municipio.setEstado(estado);
////		municipio.setCodigoIBGE(Long.parseLong("23545"));
////		municipio.setCodigoIBGE7(Long.parseLong("23545"));
////		municipio.setNomeMunicipio("Major Sales");
////		municipio.setNomeMunicipioSiglaUF("Major Sales-RN");
////		municipioRepository.save(municipio);
////		endereco.setMunicipio(municipio);
////		cidadaoMariana.setEndereco(endereco);
////		cidadaoMariana.setMunicipioNascimento(municipio);
////		
////		cidadaoService.save(cidadaoMariana);
////		
////		Atendimento atendimento = new Atendimento();
////		
////		atendimento.setCidadao(cidadaoMariana);
////		atendimento.setCondutaCidadao(CondutaCidadao.ATENDIMENTO);
////		atendimento.setCondutaTipoServico(TipoServico.Triagem);
////		atendimento.setDataEntrada(LocalDateTime.now());
////		atendimento.setProfissionalDestino(profissional);
////		StatusAtendimento statusAtendimento = new StatusAtendimento();
////		statusAtendimento.setStatus(Status.AGUARDANDOATENDIMENTO);
////		statusAtendimento.setUltimaAtualizacao(LocalDateTime.now());
////		
////		atendimento.setStatusAtendimento(statusAtendimento);
////		
////		atendimentoRepository.save(atendimento);
//
////		Optional<Atendimento> optional = atendimentoRepository.findById(Long.parseLong("1"));
////		StatusAtendimento statusAtendimento2 = new StatusAtendimento();
////		statusAtendimento2.setStatus(Status.EMATENDIMENTO);
////		statusAtendimento2.setUltimaAtualizacao(LocalDateTime.now());
////		optional.get().setStatusAtendimento(statusAtendimento2);
////		atendimentoRepository.save(optional.get());
////		
////		Optional<Atendimento> optional2 = atendimentoRepository.findById(Long.parseLong("1"));
////		
////		System.out.println("Última atualizaçao em: " + optional2.get().getStatusAtendimento().getUltimaAtualizacao().toString());
////		
//
//		try {
//			leitorXmlEsus.lerXmlEsus(file, cnes);
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		String urlProcedimentos = System.getProperty("user.dir") + "/SigtapSUS/tb_procedimento.txt";
////
//		String urlRegistros = System.getProperty("user.dir") + "/SigtapSUS/tb_registro.txt";
////
//		String urlCid = System.getProperty("user.dir") + "/SigtapSUS/tb_cid.txt";
////
//		String urlOcupacao = System.getProperty("user.dir") + "/SigtapSUS/tb_ocupacao.txt";
////
//		String urlRelationProced_Registro = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_registro.txt";
//
//		String urlRelationProced_Cid = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_cid.txt";
////
//		String urlRelationProced_Ocupacao = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_ocupacao.txt";
////
//		try {
//			leitorTxtSigtap.lerTxtCid(urlCid);
//			leitorTxtSigtap.lerTxtProcedimentos(urlProcedimentos);
//			// leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
//			// leitorTxtSigtap.lerTxtRegistro(urlRegistros);
//			// leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);
////
//			// leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);
////			leitorTxtSigtap.relacionamentoProcedimento_Ocupacao(urlRelationProced_Ocupacao);
//			// leitorTxtSigtap.relacionamentoProcedimento_Registro(urlRelationProced_Registro);
////
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		Comorbidade has = new Comorbidade();
//		Comorbidade dm = new Comorbidade();
//		Comorbidade avc = new Comorbidade();
//		Comorbidade iam = new Comorbidade();
//		Comorbidade insRenal = new Comorbidade();
//		Comorbidade dpoc = new Comorbidade();
//		Comorbidade asma = new Comorbidade();
//		Comorbidade dlp = new Comorbidade();
//		Comorbidade icc = new Comorbidade();
//		Comorbidade epilepsia = new Comorbidade();
//		Comorbidade caNeoPLasia = new Comorbidade();
//
//		has.setNome("HAS");
//		has.setDescricao("Hispertensão Arterial Sisêmica");
//
//		dm.setNome("DM");
//		dm.setDescricao("Diabete mellitus");
//
//		avc.setNome("AVC");
//		avc.setDescricao("Acidente Vascular Cerebral");
//
//		iam.setNome("IAM");
//		iam.setDescricao("Infarto Agudo do Miocárdio");
//
//		insRenal.setNome("INS. RENAL");
//		insRenal.setDescricao("Insuficiência Renal");
//
//		dpoc.setNome("DPOC");
//		dpoc.setDescricao("Doença Pulmonar Obstrutiva Crônica");
//
//		asma.setNome("ASMA");
//		asma.setDescricao("Asma / Bronquite Asmática");
//
//		dlp.setNome("DLP");
//		dlp.setDescricao("Doenças ou lesões pré-existentes");
//
//		icc.setNome("ICC");
//		icc.setDescricao("Insuficiência Cardiáca");
//
//		epilepsia.setNome("EPILEPSIA");
//		epilepsia.setDescricao("Epilepsia");
//
//		caNeoPLasia.setNome("CA / NEOPLASIA");
//		caNeoPLasia.setDescricao("Câncer / Neoplasia");
//
//		List<Comorbidade> comorbidades = new ArrayList<>();
//
//		comorbidades.add(has);
//		comorbidades.add(dm);
//		comorbidades.add(avc);
//		comorbidades.add(iam);
//		comorbidades.add(dpoc);
//		comorbidades.add(asma);
//		comorbidades.add(dlp);
//		comorbidades.add(icc);
//		comorbidades.add(epilepsia);
//		comorbidades.add(caNeoPLasia);
//
//		comorbidadeRepository.saveAll(comorbidades);
//
//		Habito tabagismo = new Habito();
//		tabagismo.setNome("Tabagismo");
//
//		Habito etilismo = new Habito();
//		etilismo.setNome("Etilismo");
//
//		Habito drogasLicitas = new Habito();
//		drogasLicitas.setNome("Drogas Ilícitas");
//
//		List<Habito> habitos = new ArrayList<>();
//
//		habitos.add(tabagismo);
//
//		habitos.add(etilismo);
//
//		habitos.add(drogasLicitas);
//
//		habitoRepository.saveAll(habitos);
//
//		SalvarEstadosEMunicipios estadosEMunicipios = new SalvarEstadosEMunicipios();
//		SalvarLogradouros logradouros = new SalvarLogradouros();
//
//		estadosEMunicipios.lerCEP_Codigo(cepIbgeRepository);
//		estadosEMunicipios.lerCSVEstados(estadoRepository);
//		estadosEMunicipios.lerCSV(municipioRepository, estadoRepository);
//
//		logradouros.lerCsvLogradouro(logradouroRepository);
//
//	}
//}
