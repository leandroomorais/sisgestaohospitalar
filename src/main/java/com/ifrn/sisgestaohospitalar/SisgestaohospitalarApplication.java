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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	@Autowired
	private ResourceLoader resourceLoader;

	private static final String SIGTAP_DIR = "classpath:sigtapsus/";

	private static final String MEDICAMENTOS_DIR = "classpath:medicamento/";

	private static final String EXAME_DIR = "classpath:exames/";

	private static final String FILE_MEDICAMENTO = "medicamentos_rename.txt";
	private static final String FILE_FORMA_FARMACEUTIVA = "formafarmaceutica.txt";
	private static final String CNES = "2380455";
	//String file = userDir + "/uploads/XmlParaESUS21_241380.xml";
	private static final String URL_PROCEDIMENTOS = "tb_procedimento.txt";
	private static final String URL_REGISTROS = "tb_registro.txt";
	private static final String URL_CID = "tb_cid.txt";
	private static final String URL_OCUPACAO = "tb_ocupacao.txt";
	private static final String URL_RELATION_PROCED_REGISTRO = "rl_procedimento_registro.txt";
	private static final String URL_RELATION_PROCED_CID = "rl_procedimento_cid.txt";
	private static final String URL_RELATION_PROCED_OCUPACAO ="rl_procedimento_ocupacao.txt";

	private static final String TXT_MEDICAMENTOS = "medicamentos_rename.txt";
	private static final String TXT_FORMA_FARMACEUTICA = "formafarmaceutica.txt";
	private static final String TXT_GRUPOS_EXAMES = "gruposExames.txt";
	private static final String TXT_EXAMES_SIMPLIFICADO = "examesSimplificado.txt";
	private static final String URL_DETALHE = "tb_detalhe.txt";
	private static final String URL_PROCEDIMENTO_DETALHE_RELACIONAMENTO = "rl_procedimento_detalhe.txt";
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
					Resource resource = resourceLoader.getResource(EXAME_DIR.concat(TXT_GRUPOS_EXAMES));
					leitorTXTExames.lerTXTFormaGrupos(getPath(resource));
				}
				Resource resource = resourceLoader.getResource(EXAME_DIR.concat(TXT_EXAMES_SIMPLIFICADO));
				leitorTXTExames.lerTXTExames(TXT_EXAMES_SIMPLIFICADO);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void lerMedicamentosEFormaFarmaceutica() {
		try {
			leitorTXTMedicamentos.lerTXTFormaFarmaceutica(TXT_FORMA_FARMACEUTICA);
			leitorTXTMedicamentos.lerTXTMedicamentos(FILE_MEDICAMENTO);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lerSigtab() {
		try {
			// OK
			if(cidRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_CID));
				leitorTxtSigtap.lerTxtCid(getPath(resource));
			}

			// OK
			if(procedimentoRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_PROCEDIMENTOS));
				leitorTxtSigtap.lerTxtProcedimentos(getPath(resource));
			}

			// OK
			if(registroSigtapRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_REGISTROS));
				leitorTxtSigtap.lerTxtRegistro(getPath(resource));
			}


			// OK
			if(procedimentoCidRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_RELATION_PROCED_CID));
				leitorTxtSigtap.lerProcedimento_Cid(getPath(resource));
			}


			// OK
			if(procedimentoOcupacaoRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_RELATION_PROCED_OCUPACAO));
				leitorTxtSigtap.lerProcedimento_Ocupacao(getPath(resource));
			}

			// OK
			if(procedimentoRegistroSigtapRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_RELATION_PROCED_REGISTRO));
				leitorTxtSigtap.lerRelacionamentoProcedimento_Registro(getPath(resource));
			}


			// OK
			if(ocupacaoRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_OCUPACAO));
				leitorTxtSigtap.lerTxtOcupacao(getPath(resource));
			}


			// OK
			if(detalheRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_DETALHE));
				leitorTxtSigtap.lerTxtDetalhe(getPath(resource));
			}

			if(procedimentoDetalheRepository.findAll().isEmpty()){
				Resource resource = resourceLoader.getResource(SIGTAP_DIR.concat(URL_PROCEDIMENTO_DETALHE_RELACIONAMENTO));
				leitorTxtSigtap.lerRelacionamentoProcedimento_Detalhe(getPath(resource));
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

	public String getPath(Resource resource) throws IOException {
		Path path = Paths.get(resource.getURI());
		String filePath = path.toString();
		return filePath;
	}
}