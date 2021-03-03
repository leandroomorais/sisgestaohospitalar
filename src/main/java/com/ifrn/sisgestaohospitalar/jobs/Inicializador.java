package com.ifrn.sisgestaohospitalar.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.Role;
import com.ifrn.sisgestaohospitalar.repository.Cep_IbgeRepository;
import com.ifrn.sisgestaohospitalar.repository.EstadoRepository;
import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;
import com.ifrn.sisgestaohospitalar.repository.MunicipioRepository;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.GuiaAtendimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.service.RoleService;
import com.ifrn.sisgestaohospitalar.utils.LeitorTxtSigtap;
import com.ifrn.sisgestaohospitalar.utils.LeitorXmlEsus;
import com.ifrn.sisgestaohospitalar.utils.SalvarEstadosEMunicipios;
import com.ifrn.sisgestaohospitalar.utils.SalvarLogradouros;

import jxl.read.biff.BiffException;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private LeitorXmlEsus leitorXmlEsus;

	@Autowired
	private LeitorTxtSigtap leitorTxtSigtap;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CidadaoService cidadaoService;
	
	@Autowired
	private ProfissionalService profissionalService;

	
	@Autowired
	private GuiaAtendimentoService guiaAtendimentoService;
	
	@Autowired
	private Cep_IbgeRepository cepIbgeRepository;
	
	@Autowired
	private MunicipioRepository municipioRepository;
	
	@Autowired
	private LogradouroRepository logradouroRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		


//		Role role = new Role();
//		role.setNome("ADM");
//		roleService.save(role);
//		
//		Role roleTec = new Role();
//		roleTec.setNome("TEC");
//		roleService.save(roleTec);

//		Role roleEnf = new Role();
//		roleEnf.setNome("ENF");
//		roleService.save(roleEnf);
		
//		Role roleMed = new Role();
//		roleMed.setNome("MED");
//		roleService.save(roleMed);

//		Role rolesuper = new Role();
//		rolesuper.setNome("SUPER");
//		roleService.save(rolesuper);
		
		String cnes = "2380633";
		String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS21_241360.xml";

//		Profissional profissional = new Profissional();
//		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		profissional.setNome("CARLOS AUGUSTO SILVA");
//		profissional.setCns("781274801470008");
//		profissional.setCpf("47748001049");
//		profissional.setDatanascimento("20-06-1994");
//		profissional.setSexo("M");
//		profissional.setFirstname("CARLOS AUGUSTO");
//		profissional.getRole().add(role);
//		profissional.setPassword(encoder.encode("sgh47748001049"));
		
		//profissionalService.save(profissional);

//		Cidadao cidadaoMariana = new Cidadao();
//		Cidadao cidadaoLucia = new Cidadao();
//		Cidadao cidadaoLucijane = new Cidadao();
//		Cidadao cidadaoLuana = new Cidadao();
//		Cidadao cidadaoIsabel = new Cidadao();
//		Cidadao cidadaoLuciano = new Cidadao();
//		Cidadao cidadaoLuciana = new Cidadao();
//		Cidadao cidadaoDjaneidy = new Cidadao();
//		
//
//		cidadaoMariana.setBairro("CENTRO");
//		cidadaoMariana.setCep("59945000");
//		cidadaoMariana.setCns("110525916150007");
//		cidadaoMariana.setCodigoetnia(0);
//		cidadaoMariana.setCodigoibgemunicipio("2407252");
//		cidadaoMariana.setCodigologradouro(81);
//		cidadaoMariana.setCodigonacionalidade(1);
//		cidadaoMariana.setCodigoraca(03);
//		cidadaoMariana.setDatanascimento(LocalDate.parse("1993-11-21"));
//		cidadaoMariana.setComplementoendereco("CASA");
//		cidadaoMariana.setCpf("00000000000");
//		cidadaoMariana.setEmail("MARIANA.MARA@LIVE.COM");
//		cidadaoMariana.setEndereco("CESAR  ROCHA");
//		cidadaoMariana.setNome("MARIANA MARA ROCHA DE QUEIROZ");
//		cidadaoMariana.setNomemae("MARIA DE LOURDES ROCHA DE QUEIROZ");
//		cidadaoMariana.setNomepai("GUILHERME ROCHA DE QUEIROZ");
//		cidadaoMariana.setNumeroendereco("99");
//		cidadaoMariana.setSexo("F");
//		cidadaoMariana.setTelefone("84981561303");
//		cidadaoMariana.setNomemunicipio("MAJOR SALES");
		
//		cidadaoLucia.setBairro("CENTRO");
//		cidadaoLucia.setCep("59945000");
//		cidadaoLucia.setCns("187458243740005");
//		cidadaoLucia.setCodigoetnia(0);
//		cidadaoLucia.setCodigoibgemunicipio("2407252");
//		cidadaoLucia.setCodigologradouro(81);
//		cidadaoLucia.setCodigonacionalidade(1);
//		cidadaoLucia.setCodigoraca(03);
//		cidadaoLucia.setDatanascimento(LocalDate.parse("1985-11-07"));
//		cidadaoLucia.setComplementoendereco("CASA");
//		cidadaoLucia.setCpf("11111111111");
//		cidadaoLucia.setEmail("");
//		cidadaoLucia.setEndereco("BEIJAMIM FRANCO");
//		cidadaoLucia.setNome("MARIA LUCIA DE MORAIS PINTO");
//		cidadaoLucia.setNomemae("MARIA ISABEL CONCEICAO MORAIS");
//		cidadaoLucia.setNomepai("ANTONIO MAXIMIANO CHAGAS");
//		cidadaoLucia.setNumeroendereco("90");
//		cidadaoLucia.setSexo("F");
//		cidadaoLucia.setTelefone("");
//		cidadaoLucia.setNomemunicipio("MAJOR SALES");
//		
//		cidadaoLuana.setBairro("CENTRO");
//		cidadaoLuana.setCep("59945000");
//		cidadaoLuana.setCns("277821448980007");
//		cidadaoLuana.setCodigoetnia(0);
//		cidadaoLuana.setCodigoibgemunicipio("2407252");
//		cidadaoLuana.setCodigologradouro(81);
//		cidadaoLuana.setCodigonacionalidade(1);
//		cidadaoLuana.setCodigoraca(03);
//		cidadaoLuana.setDatanascimento(LocalDate.parse("1985-11-07"));
//		cidadaoLuana.setComplementoendereco("CASA");
//		cidadaoLuana.setCpf("22222222222");
//		cidadaoLuana.setEmail("");
//		cidadaoLuana.setEndereco("CESAR ROCHA");
//		cidadaoLuana.setNome("MARIA LUANA ROCHA DE QUEIROZ");
//		cidadaoLuana.setNomemae("MARIA DE LOURDES ROCHA DE QUEIROZ");
//		cidadaoLuana.setNomepai("GUILHERME ROCHA DE QUEIROZ");
//		cidadaoLuana.setNumeroendereco("99");
//		cidadaoLuana.setSexo("F");
//		cidadaoLuana.setTelefone("");
//		cidadaoLuana.setNomemunicipio("MAJOR SALES");
//		
//		cidadaoLuciano.setBairro("CENTRO");
//		cidadaoLuciano.setCep("59945000");
//		cidadaoLuciano.setCns("138569771740007");
//		cidadaoLuciano.setCodigoetnia(0);
//		cidadaoLuciano.setCodigoibgemunicipio("2407252");
//		cidadaoLuciano.setCodigologradouro(81);
//		cidadaoLuciano.setCodigonacionalidade(1);
//		cidadaoLuciano.setCodigoraca(03);
//		cidadaoLuciano.setDatanascimento(LocalDate.parse("1985-11-07"));
//		cidadaoLuciano.setComplementoendereco("CASA");
//		cidadaoLuciano.setCpf("33333333333");
//		cidadaoLuciano.setEmail("");
//		cidadaoLuciano.setEndereco("BELA VISTA");
//		cidadaoLuciano.setNome("JOSÉ LUCIANO DA SILVA");
//		cidadaoLuciano.setNomemae("MARIA FRANCISCA DE ALBUQUERQUE");
//		cidadaoLuciano.setNomepai("SEM INFORMAÇÕES");
//		cidadaoLuciano.setNumeroendereco("81");
//		cidadaoLuciano.setSexo("M");
//		cidadaoLuciano.setTelefone("84981561303");
//		cidadaoLuciano.setNomemunicipio("MAJOR SALES");
//		
//		cidadaoDjaneidy.setBairro("CENTRO");
//		cidadaoDjaneidy.setCep("59945000");
//		cidadaoDjaneidy.setCns("744456497940009");
//		cidadaoDjaneidy.setCodigoetnia(0);
//		cidadaoDjaneidy.setCodigoibgemunicipio("2407252");
//		cidadaoDjaneidy.setCodigologradouro(81);
//		cidadaoDjaneidy.setCodigonacionalidade(1);
//		cidadaoDjaneidy.setCodigoraca(03);
//		cidadaoDjaneidy.setDatanascimento(LocalDate.parse("1985-11-07"));
//		cidadaoDjaneidy.setComplementoendereco("CASA");
//		cidadaoDjaneidy.setCpf("44444444444");
//		cidadaoDjaneidy.setEmail("");
//		cidadaoDjaneidy.setEndereco("BELA VISTA");
//		cidadaoDjaneidy.setNome("DJANEIDY PINTO DE MORAIS GOMES");
//		cidadaoDjaneidy.setNomemae("MARIA FRANCISCA DE ALBUQUERQUE");
//		cidadaoDjaneidy.setNomepai("CICERO PINTO");
//		cidadaoDjaneidy.setNumeroendereco("81");
//		cidadaoDjaneidy.setSexo("F");
//		cidadaoDjaneidy.setTelefone("84981561303");
//		cidadaoDjaneidy.setNomemunicipio("MAJOR SALES");
//
//		cidadaoService.save(cidadaoMariana);
//		cidadaoService.save(cidadaoLuciano);
//		cidadaoService.save(cidadaoLucia);
//		cidadaoService.save(cidadaoLuana);
//		cidadaoService.save(cidadaoDjaneidy);
//
//		try {
//			leitorXmlEsus.lerXmlEsus(file, cnes);
//		 } catch (JAXBException e) {
//			 //TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			 //TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String urlProcedimentos = System.getProperty("user.dir") + "/SigtapSUS/tb_procedimento.txt";

		String urlRegistros = System.getProperty("user.dir") + "/SigtapSUS/tb_registro.txt";

		String urlCid = System.getProperty("user.dir") + "/SigtapSUS/tb_cid.txt";

		String urlOcupacao = System.getProperty("user.dir") + "/SigtapSUS/tb_ocupacao.txt";

		String urlRelationProced_Registro = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_registro.txt";

		String urlRelationProced_Cid = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_cid.txt";

		String urlRelationProced_Ocupacao = System.getProperty("user.dir")
				+ "/SigtapSUS/rl_procedimento_ocupacao.txt";

//		try {
//			leitorTxtSigtap.lerTxtCid(urlCid);
//			leitorTxtSigtap.lerTxtProcedimentos(urlProcedimentos);
//			leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
//			leitorTxtSigtap.lerTxtRegistro(urlRegistros);

//			// leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);
//			//leitorTxtSigtap.relacionamentoProcedimento_Ocupacao(urlRelationProced_Ocupacao);
//			leitorTxtSigtap.relacionamentoProcedimento_Registro(urlRelationProced_Registro);
//
//		} catch (IOException e) {
		// TODO Auto-generated catch block
			//e.printStackTrace();
//		}
		
//		SalvarEstadosEMunicipios estadosEMunicipios = new SalvarEstadosEMunicipios();
//		SalvarLogradouros logradouros = new SalvarLogradouros();
//		
//		estadosEMunicipios.lerCEP_Codigo(cepIbgeRepository);
//		estadosEMunicipios.lerCSVEstados(estadoRepository);
//		estadosEMunicipios.lerCSV(municipioRepository, estadoRepository );
//		
//		logradouros.lerCsvLogradouro(logradouroRepository);
		
	}
}
