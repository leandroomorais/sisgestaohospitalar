package com.ifrn.sisgestaohospitalar;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.ifrn.sisgestaohospitalar.repository.AtendimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.Cep_IbgeRepository;
import com.ifrn.sisgestaohospitalar.repository.EnderecoRepository;
import com.ifrn.sisgestaohospitalar.repository.EstadoRepository;
import com.ifrn.sisgestaohospitalar.repository.FormaFarmaceuticaRepository;
import com.ifrn.sisgestaohospitalar.repository.HabitoRepository;
import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;
import com.ifrn.sisgestaohospitalar.repository.MedicamentoRepository;
import com.ifrn.sisgestaohospitalar.repository.MunicipioRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.utils.LeitorTXTMedicamentos;
import com.ifrn.sisgestaohospitalar.utils.LeitorTxtSigtap;
import com.ifrn.sisgestaohospitalar.utils.LeitorXmlEsus;
import com.ifrn.sisgestaohospitalar.utils.SalvarEstadosEMunicipios;
import com.ifrn.sisgestaohospitalar.utils.SalvarLogradouros;

@SpringBootApplication
public class SisgestaohospitalarApplication implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	LeitorTXTMedicamentos leitorTXTMedicamentos;

	@Autowired
	private LeitorXmlEsus leitorXmlEsus;

	@Autowired
	private LeitorTxtSigtap leitorTxtSigtap;

	// @Autowired
	// private RoleService roleService;

	@Autowired
	private CidadaoService cidadaoService;

	@Autowired
	private ProfissionalService profissionalService;

	// @Autowired
	// private GuiaAtendimentoService guiaAtendimentoService;

	@Autowired
	private Cep_IbgeRepository cepIbgeRepository;

	@Autowired
	private MunicipioRepository municipioRepository;

	@Autowired
	private LogradouroRepository logradouroRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private HabitoRepository habitoRepository;

	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private FormaFarmaceuticaRepository formaFarmaceuticaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SisgestaohospitalarApplication.class, args);

		System.out.println("Iniciando Projeto");
	}

	String fileMedicamento = System.getProperty("user.dir") + "/medicamento/medicamentos_rename.txt";
	String fileFormaFarmaceutiva = System.getProperty("user.dir") + "/medicamento/formafarmaceutica.txt";
	String cnes = "2380633";
	String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS21_241360.xml";
	String urlProcedimentos = System.getProperty("user.dir") + "/SigtapSUS/tb_procedimento.txt";
	String urlRegistros = System.getProperty("user.dir") + "/SigtapSUS/tb_registro.txt";
	String urlCid = System.getProperty("user.dir") + "/SigtapSUS/tb_cid.txt";
	String urlOcupacao = System.getProperty("user.dir") + "/SigtapSUS/tb_ocupacao.txt";
	String urlRelationProced_Registro = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_registro.txt";
	String urlRelationProced_Cid = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_cid.txt";
	String urlRelationProced_Ocupacao = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_ocupacao.txt";
	String txtMedicamentos = System.getProperty("user.dir") + "/medicamento/medicamentos_rename.txt";
	String txtFormaFarmaceutica = System.getProperty("user.dir") + "/medicamento/formafarmaceutica.txt";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//lerSigtab();
		//lerMedicamentosEFormaFarmaceutica();
		//lerXmlEsus();
		//lerEstadosMunicipios();
		

	}

	public void lerMedicamentosEFormaFarmaceutica() {
		try {
			leitorTXTMedicamentos.lerTXTMedicamentos(fileMedicamento);
			leitorTXTMedicamentos.lerTXTFormaFarmaceutica(txtFormaFarmaceutica);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lerXmlEsus() {
		try {
			leitorXmlEsus.lerXmlEsus(file, cnes);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void lerSigtab() {
		try {
			 leitorTxtSigtap.lerTxtCid(urlCid);
			 leitorTxtSigtap.lerTxtProcedimentos(urlProcedimentos);
			 leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
			 leitorTxtSigtap.lerTxtRegistro(urlRegistros);
			 //leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);

			 //leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);
			 //leitorTxtSigtap.relacionamentoProcedimento_Ocupacao(urlRelationProced_Ocupacao);
			 //leitorTxtSigtap.relacionamentoProcedimento_Registro(urlRelationProced_Registro);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void lerEstadosMunicipios() {
		SalvarEstadosEMunicipios estadosEMunicipios = new SalvarEstadosEMunicipios();
		SalvarLogradouros logradouros = new SalvarLogradouros();

		estadosEMunicipios.lerCEP_Codigo(cepIbgeRepository);
		estadosEMunicipios.lerCSVEstados(estadoRepository);
		estadosEMunicipios.lerCSV(municipioRepository, estadoRepository);

		logradouros.lerCsvLogradouro(logradouroRepository);
	}

}
