package com.ifrn.sisgestaohospitalar;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.ifrn.sisgestaohospitalar.model.Role;
import com.ifrn.sisgestaohospitalar.model.TipoServico;
import com.ifrn.sisgestaohospitalar.model.TipoUsuario;
import com.ifrn.sisgestaohospitalar.model.ViaAdministracao;
import com.ifrn.sisgestaohospitalar.repository.Cep_IbgeRepository;
import com.ifrn.sisgestaohospitalar.repository.EstadoRepository;
import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;
import com.ifrn.sisgestaohospitalar.repository.MunicipioRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.RoleRepository;
import com.ifrn.sisgestaohospitalar.repository.TipoServicoRepository;
import com.ifrn.sisgestaohospitalar.repository.TipoUsuarioRepository;
import com.ifrn.sisgestaohospitalar.repository.ViaAdministracaoRepository;
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

	@Autowired
	private Cep_IbgeRepository cepIbgeRepository;

	@Autowired
	private MunicipioRepository municipioRepository;

	@Autowired
	private LogradouroRepository logradouroRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	@Autowired
	private ViaAdministracaoRepository viaAdministracaoRepository;

	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

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

		//criaRolesETipoUsuario();
		//lerSigtab();
		//lerMedicamentosEFormaFarmaceutica();
		//lerXmlEsus();
		//lerEstadosMunicipios();
		//salvarViaAdministracao();
		//salvarTipoServico();
	}

	public void salvarTipoServico() {
		TipoServico triagem = new TipoServico();
		TipoServico consulta = new TipoServico();
		TipoServico adminMedicamentos = new TipoServico();
		TipoServico curativo = new TipoServico();
		TipoServico inativo = new TipoServico();

		triagem.setNome("Triagem");
		consulta.setNome("Consulta");
		adminMedicamentos.setNome("Admin. de medicamentos");
		curativo.setNome("Curativo");
		inativo.setNome("Inativo");

		tipoServicoRepository.saveAndFlush(triagem);
		tipoServicoRepository.saveAndFlush(consulta);
		tipoServicoRepository.saveAndFlush(adminMedicamentos);
		tipoServicoRepository.saveAndFlush(curativo);
		tipoServicoRepository.saveAndFlush(inativo);
	}

	public void salvarViaAdministracao() {
		ViaAdministracao oral = new ViaAdministracao();
		ViaAdministracao parenteralIntramuscular = new ViaAdministracao();
		ViaAdministracao parenteralIntraVenosa = new ViaAdministracao();
		ViaAdministracao parenteralSubcultanea = new ViaAdministracao();
		ViaAdministracao topica = new ViaAdministracao();

		oral.setNome("Oral");
		oral.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100217")));
		parenteralIntramuscular.setNome("Intramuscular");
		parenteralIntramuscular.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100209")));
		parenteralIntraVenosa.setNome("Endovenosa");
		parenteralIntraVenosa.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100195")));
		parenteralSubcultanea.setNome("Subcutânea");
		parenteralSubcultanea.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100225")));
		topica.setNome("Tópica");
		topica.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100233")));

		viaAdministracaoRepository.saveAndFlush(oral);
		viaAdministracaoRepository.saveAndFlush(parenteralIntramuscular);
		viaAdministracaoRepository.saveAndFlush(parenteralIntraVenosa);
		viaAdministracaoRepository.saveAndFlush(parenteralSubcultanea);
		viaAdministracaoRepository.saveAndFlush(topica);
	}

	public void lerMedicamentosEFormaFarmaceutica() {
		try {
			leitorTXTMedicamentos.lerTXTFormaFarmaceutica(txtFormaFarmaceutica);
			leitorTXTMedicamentos.lerTXTMedicamentos(fileMedicamento);

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
			leitorTxtSigtap.lerTxtRegistro(urlRegistros);
			//leitorTxtSigtap.lerProcedimento_Cid(urlRelationProced_Cid);
			//leitorTxtSigtap.lerProcedimento_Ocupacao(urlRelationProced_Ocupacao);
			//leitorTxtSigtap.relacionamentoProcedimento_Registro(urlRelationProced_Registro);
			//leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
			
			// leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);

			// leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);
			//leitorTxtSigtap.relacionamentoProcedimento_Ocupacao(urlRelationProced_Ocupacao);
			// leitorTxtSigtap.relacionamentoProcedimento_Registro(urlRelationProced_Registro);

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

	public void criaRolesETipoUsuario() {
		TipoUsuario medico = new TipoUsuario();
		TipoUsuario enfermeiro = new TipoUsuario();
		TipoUsuario tecnico = new TipoUsuario();
		TipoUsuario auxiliar = new TipoUsuario();
		TipoUsuario administrador = new TipoUsuario();

		medico.setNome("MEDICO");
		enfermeiro.setNome("ENFERMEIRO");
		tecnico.setNome("TECNICO");
		auxiliar.setNome("AUXILIAR");
		administrador.setNome("ADMINISTRADOR");

		Role roleMedico = new Role();
		Role roleEnfermeiro = new Role();
		Role roleTecnico = new Role();
		Role roleAuxiliar = new Role();
		Role roleAdministrador = new Role();

		roleMedico.setNome(medico.getNome());
		roleEnfermeiro.setNome(enfermeiro.getNome());
		roleTecnico.setNome(tecnico.getNome());
		roleAuxiliar.setNome(auxiliar.getNome());
		roleAdministrador.setNome(administrador.getNome());

		tipoUsuarioRepository.saveAndFlush(medico);
		tipoUsuarioRepository.saveAndFlush(enfermeiro);
		tipoUsuarioRepository.saveAndFlush(tecnico);
		tipoUsuarioRepository.saveAndFlush(auxiliar);
		tipoUsuarioRepository.saveAndFlush(administrador);

		roleRepository.saveAndFlush(roleMedico);
		roleRepository.saveAndFlush(roleEnfermeiro);
		roleRepository.saveAndFlush(roleTecnico);
		roleRepository.saveAndFlush(roleAuxiliar);
		roleRepository.saveAndFlush(roleAdministrador);

	}

}
