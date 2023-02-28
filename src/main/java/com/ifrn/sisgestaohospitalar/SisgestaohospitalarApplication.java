package com.ifrn.sisgestaohospitalar;

import com.ifrn.sisgestaohospitalar.model.*;
import com.ifrn.sisgestaohospitalar.repository.*;
import com.ifrn.sisgestaohospitalar.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

@SpringBootApplication
public class SisgestaohospitalarApplication extends SpringBootServletInitializer
		implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	LeitorTXTExames leitorTXTExames;

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
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired
	private CidRepository cidRepository;
	@Autowired
	private ClassificacaoDeRiscoRepository classificacaoDeRiscoRepository;
	public static void main(String[] args) {
		SpringApplication.run(SisgestaohospitalarApplication.class, args);
		System.out.println("Iniciando Projeto");
	}

	String userDir = System.getProperty("user.dir");

	String fileMedicamento = userDir + "/medicamento/medicamentos_rename.txt";
	String fileFormaFarmaceutiva = userDir + "/medicamento/formafarmaceutica.txt";
	String cnes = "2380455";
	String file = userDir + "/uploads/XmlParaESUS21_241380.xml";
	String urlProcedimentos = userDir + "/SigtapSUS/tb_procedimento.txt";
	String urlRegistros = userDir + "/SigtapSUS/tb_registro.txt";
	String urlCid = userDir + "/SigtapSUS/tb_cid.txt";
	String urlOcupacao = userDir + "/SigtapSUS/tb_ocupacao.txt";
	String urlRelationProced_Registro = userDir + "/SigtapSUS/rl_procedimento_registro.txt";
	String urlRelationProced_Cid = userDir + "/SigtapSUS/rl_procedimento_cid.txt";
	String urlRelationProced_Ocupacao = userDir + "/SigtapSUS/rl_procedimento_ocupacao.txt";
	String txtMedicamentos = userDir + "/medicamento/medicamentos_rename.txt";
	String txtFormaFarmaceutica = userDir + "/medicamento/formafarmaceutica.txt";
	String txtGruposExames = userDir + "/exames/gruposExames.txt";
	String txtExamesSimplificado = userDir + "/exames/examesSimplificado.txt";
	String urlDetalhe = userDir + "/SigtapSus/tb_detalhe.txt";
	String urlProcedimentoDetalheRelacionamento = userDir + "/SigtapSus/rl_procedimento_detalhe.txt";
	@Autowired
	private RegistroSigtapRepository registroSigtapRepository;
	@Autowired
	private ProcedimentoCidRepository procedimentoCidRepository;
	@Autowired
	private ProcedimentoOcupacaoRepository procedimentoOcupacaoRepository;
	@Autowired
	private ProcedimentoRegistroSigtapRepository procedimentoRegistroSigtapRepository;
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	@Autowired
	private DetalheRepository detalheRepository;
	@Autowired
	private ProcedimentoDetalheRepository procedimentoDetalheRepository;
	@Autowired
	private ExameRepository exameRepository;
	@Autowired
	private GrupoExameRepository grupoExameRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		//inicializar();

		//criaRolesETipoUsuario();
		lerSigtab();
		//lerMedicamentosEFormaFarmaceutica();
		//lerEstadosMunicipios();
		salvarViaAdministracao();
		salvarTipoServico();
		LerExames();
		leitorTXTExames.atualizaGrupo();
		criaClassificacaoDeRisco();
		criaUserAdmin();
	}

	public void inicializar() {
		String ipDaMaquina = null;

		try {
			ipDaMaquina = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if (!ipDaMaquina.equals(Administracao.getIpmaquina())) {
			System.out.print("Aplicação Encerrada!");
			System.exit(0);
		}

		Scanner input = new Scanner(System.in);

		String usuario;
		String senha;

		System.out.print("Informe as credenciais! \n");
		System.out.print("Usuário: ");
		usuario = input.next();

		if (!usuario.equals(Administracao.getUsuario())) {
			System.out.println("Erro: Usuário inválido! Última tentativa.");
			System.out.print("Digite novamente o Usuário: ");
			usuario = input.next();
			if (!usuario.equals(Administracao.getUsuario())) {
				System.out.print("Aplicação Encerrada!");
				System.exit(0);
			}
		}

		System.out.print("Senha: ");
		senha = input.next();

		if (!senha.equals(Administracao.getSenha())) {
			System.out.println("Erro: Senha inválida! Última tentativa.");
			System.out.print("Digite novamente a Senha: ");
			senha = input.next();
			if (!senha.equals(Administracao.getSenha())) {
				System.out.print("Aplicação Encerrada!");
				System.exit(0);
			}
		}

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

		if(tipoServicoRepository.findAll().isEmpty()){
			tipoServicoRepository.saveAndFlush(triagem);
			tipoServicoRepository.saveAndFlush(consulta);
			tipoServicoRepository.saveAndFlush(adminMedicamentos);
			tipoServicoRepository.saveAndFlush(curativo);
			tipoServicoRepository.saveAndFlush(inativo);
		}


	}

	public void salvarViaAdministracao() {
		ViaAdministracao selecione = new ViaAdministracao();
		ViaAdministracao oral = new ViaAdministracao();
		ViaAdministracao parenteralIntramuscular = new ViaAdministracao();
		ViaAdministracao parenteralIntraVenosa = new ViaAdministracao();
		ViaAdministracao parenteralSubcultanea = new ViaAdministracao();
		ViaAdministracao topica = new ViaAdministracao();

		oral.setNome("Oral");
		oral.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100012")));
		parenteralIntramuscular.setNome("Intramuscular");
		parenteralIntramuscular.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100012")));
		parenteralIntraVenosa.setNome("Endovenosa");
		parenteralIntraVenosa.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100012")));
		parenteralSubcultanea.setNome("Subcutânea");
		parenteralSubcultanea.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100012")));
		topica.setNome("Tópica");
		topica.setProcedimento(procedimentoRepository.getOne(Long.parseLong("0301100012")));
		selecione.setNome("Selecione a via de administração");
		selecione.setProcedimento(null);

		if(viaAdministracaoRepository.findAll().isEmpty()){
			viaAdministracaoRepository.saveAndFlush(oral);
			viaAdministracaoRepository.saveAndFlush(parenteralIntramuscular);
			viaAdministracaoRepository.saveAndFlush(parenteralIntraVenosa);
			viaAdministracaoRepository.saveAndFlush(parenteralSubcultanea);
			viaAdministracaoRepository.saveAndFlush(topica);
			viaAdministracaoRepository.saveAndFlush(selecione);
		}
	}

	public void LerExames() {
		try {
			if(exameRepository.findAll().isEmpty()){
				if(grupoExameRepository.findAll().isEmpty()){
					leitorTXTExames.lerTXTFormaGrupos(txtGruposExames);
				}
				leitorTXTExames.lerTXTExames(txtExamesSimplificado);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void lerSigtab() {
		try {
			// OK
			if(cidRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerTxtCid(urlCid);
			}


			// OK
			if(procedimentoRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerTxtProcedimentos(urlProcedimentos);
			}

			// OK
			if(registroSigtapRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerTxtRegistro(urlRegistros);
			}


			// OK
			if(procedimentoCidRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerProcedimento_Cid(urlRelationProced_Cid);
			}


			// OK
			if(procedimentoOcupacaoRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerProcedimento_Ocupacao(urlRelationProced_Ocupacao);
			}

			// OK
			if(procedimentoRegistroSigtapRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerRelacionamentoProcedimento_Registro(urlRelationProced_Registro);
			}


			// OK
			if(ocupacaoRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
			}


			// OK
			if(detalheRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerTxtDetalhe(urlDetalhe);
			}

			if(procedimentoDetalheRepository.findAll().isEmpty()){
				leitorTxtSigtap.lerRelacionamentoProcedimento_Detalhe(urlProcedimentoDetalheRelacionamento);
			}

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

	public void criaClassificacaoDeRisco() {
		ClassificacaoDeRisco vermelho = new ClassificacaoDeRisco();
		vermelho.setNome("VERMELHO");
		vermelho.setDescricao("Casos de emergência");
		vermelho.setPrioridade(0);

		ClassificacaoDeRisco laranja = new ClassificacaoDeRisco();
		laranja.setNome("LARANJA");
		laranja.setDescricao("Casos muito urgentes");
		laranja.setPrioridade(1);

		ClassificacaoDeRisco amarelo = new ClassificacaoDeRisco();
		amarelo.setNome("AMARELO");
		amarelo.setDescricao("Casos de urgência");
		amarelo.setPrioridade(2);

		ClassificacaoDeRisco verde = new ClassificacaoDeRisco();
		verde.setNome("VERDE");
		verde.setDescricao("Casos pouco urgentes");
		verde.setPrioridade(3);

		ClassificacaoDeRisco azul = new ClassificacaoDeRisco();
		azul.setNome("AZUL");
		azul.setDescricao("Casos não urgentes");
		azul.setPrioridade(4);

		ClassificacaoDeRisco naoInformada = new ClassificacaoDeRisco();
		naoInformada.setNome("Não informada");
		naoInformada.setDescricao("Risco não classificasdo");
		naoInformada.setPrioridade(5);

		if(classificacaoDeRiscoRepository.findAll().isEmpty()){
			classificacaoDeRiscoRepository.save(vermelho);
			classificacaoDeRiscoRepository.save(laranja);
			classificacaoDeRiscoRepository.save(amarelo);
			classificacaoDeRiscoRepository.save(verde);
			classificacaoDeRiscoRepository.save(azul);
			classificacaoDeRiscoRepository.save(naoInformada);
		}
	}

	public void criaUserAdmin() {
		if(usuarioRepository.findByUsername("09814354406") == null) {
			Usuario usuario = new Usuario();
			usuario.setUsername("09814354406");
			usuario.setConcatName("LEANDRO MORAIS");
			usuario.setEnabled(true);
			usuario.setPassword(new BCryptPasswordEncoder().encode("leandro916774"));
			usuario.setTipoUsuario(tipoUsuarioRepository.findByNome("ADMINISTRADOR"));
			usuario.getRole().add(roleRepository.findByNome("ADMINISTRADOR"));
			usuarioRepository.saveAndFlush(usuario);
		}
	}
}