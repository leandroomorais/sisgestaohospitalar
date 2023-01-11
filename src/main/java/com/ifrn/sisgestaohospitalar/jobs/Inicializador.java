package com.ifrn.sisgestaohospitalar.jobs;

import com.ifrn.sisgestaohospitalar.repository.*;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private LeitorXmlEsus leitorXmlEsus;

    @Autowired
    private LeitorTxtSigtap leitorTxtSigtap;

    @Autowired
    private LeitorTXTMedicamentos leitorTXTMedicamentos;

    @Autowired
    private CidadaoService cidadaoService;

    @Autowired
    private ProfissionalService profissionalService;

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

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private CidRepository cidRepository;

    @Autowired
    private OcupacaoRepository ocupacaoRepository;

    @Autowired
    private RegistroSigtapRepository registroSigtapRepository;




    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        String cnes = "2380455";
        String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS30_241380.xml";

        String fileMedicamento = System.getProperty("user.dir") + "/medicamento/medicamentos_rename.txt";
        String fileFormaFarmaceutica = System.getProperty("user.dir") + "/medicamento/formafarmaceutica.txt";

//        Profissional profissional = new Profissional();
//        TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("ADMINISTRADOR");
//        Usuario usuario = new Usuario();
//        usuario.setTipoUsuario(tipoUsuario);
//        usuario.
//
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		profissional.setNome("FRANCISCO LEANDRO DE MORAIS PINTO");
//		profissional.setCns("723686249990001");
//		profissional.setCpf("09814354406");
//		profissional.setDataNascimento("20-06-1994");
//		profissional.setSexo("M");
//		profissional.setNomeAbrev("LEANDRO MORAIS");
//		profissional.setTipoProfissional(TipoProfissional.ADMINISTRADOR);
//        profissional.setAtivo(true);
////
//		profissionalService.save(profissional);
//
//		Cidadao cidadaoMariana = new Cidadao();
//
//		cidadaoMariana.setCns("110525916150007");
//		cidadaoMariana.setCodigoNacionalidade(1);
//		cidadaoMariana.setCodigoRaca(CodigoRaca.PARDA);
//		cidadaoMariana.setDataNascimento(LocalDate.parse("1994-06-20"));
//		cidadaoMariana.setCpf("09814354406");
//		cidadaoMariana.setEmail("MARIANA.MARA@LIVE.COM");
//		cidadaoMariana.setNome("MARIANA MARA ROCHA DE QUEIROZ");
//		cidadaoMariana.setNomeMae("MARIA DE LOURDES ROCHA DE QUEIROZ");
//		cidadaoMariana.setNomePai("GUILHERME ROCHA DE QUEIROZ");
//		cidadaoMariana.setSexo("F");
//		Endereco endereco = new Endereco();
//		endereco.setBairro("centro");
//		endereco.setCep("59945000");
//		Logradouro logradouro = new Logradouro();
//		logradouro.setCodigo(Long.parseLong("12"));
//		logradouro.setDescricao("RUA");
//		logradouroRepository.save(logradouro);
//		endereco.setLogradouro(logradouro);
//		endereco.setNomeLogradouro("CÉSAR ROCHA");
//		endereco.setNumero("90");
//		endereco.setComplemento("casa");
//		Estado estado = new Estado();
//		estado.setCodigo(24);
//		estado.setNome("RIO GRANDE DO NORTE");
//		estado.setSigla("RN");
//		estadoRepository.save(estado);
//		Municipio municipio = new Municipio();
//		municipio.setEstado(estado);
//		municipio.setCodigoIBGE(Long.parseLong("23545"));
//		municipio.setCodigoIBGE7(Long.parseLong("23545"));
//		municipio.setNomeMunicipio("Major Sales");
//		municipio.setNomeMunicipioSiglaUF("Major Sales-RN");
//		municipioRepository.save(municipio);
//		endereco.setMunicipio(municipio);
//		cidadaoMariana.setEndereco(endereco);
//		cidadaoMariana.setMunicipioNascimento(municipio);
//
//		cidadaoService.save(cidadaoMariana);
//
//		Atendimento atendimento = new Atendimento();
//
//		atendimento.setCidadao(cidadaoMariana);
//		atendimento.setCondutaCidadao(CondutaCidadao.ATENDIMENTO);
//		atendimento.setCondutaTipoServico(TipoServico.Triagem);
//		atendimento.setDataEntrada(LocalDateTime.now());
//		atendimento.setProfissionalDestino(profissional);
//		StatusAtendimento statusAtendimento = new StatusAtendimento();
//		statusAtendimento.setStatus(Status.AGUARDANDOATENDIMENTO);
//		statusAtendimento.setUltimaAtualizacao(LocalDateTime.now());
//
//		atendimento.setStatusAtendimento(statusAtendimento);
//
//		atendimentoRepository.save(atendimento);
//
//		Optional<Atendimento> optional = atendimentoRepository.findById(Long.parseLong("1"));
//		StatusAtendimento statusAtendimento2 = new StatusAtendimento();
//		statusAtendimento2.setStatus(Status.EMATENDIMENTO);
//		statusAtendimento2.setUltimaAtualizacao(LocalDateTime.now());
//		optional.get().setStatusAtendimento(statusAtendimento2);
//		atendimentoRepository.save(optional.get());
//
//		Optional<Atendimento> optional2 = atendimentoRepository.findById(Long.parseLong("1"));
//
//		System.out.println("Última atualizaçao em: " + optional2.get().getStatusAtendimento().getUltimaAtualizacao().toString());
//
//
//        try {
//            leitorXmlEsus.lerXmlEsus(file, cnes);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        String urlProcedimentos = System.getProperty("user.dir") + "/SigtapSUS/tb_procedimento.txt";

        String urlRegistros = System.getProperty("user.dir") + "/SigtapSUS/tb_registro.txt";

        String urlCid = System.getProperty("user.dir") + "/SigtapSUS/tb_cid.txt";

        String urlOcupacao = System.getProperty("user.dir") + "/SigtapSUS/tb_ocupacao.txt";

        String urlRelationProced_Registro = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_registro.txt";

        String urlRelationProced_Cid = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_cid.txt";

        String urlRelationProced_Ocupacao = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_ocupacao.txt";

        String txtMedicamentos = System.getProperty("user.dir") + "/medicamento/medicamentos_rename.txt";

        String txtFormaFarmaceutica = System.getProperty("user.dir") + "/medicamento/formafarmaceutica.txt";

        try {

            if(cidRepository.findAll().isEmpty()){
                leitorTxtSigtap.lerTxtCid(urlCid);
            }
            if(procedimentoRepository.findAll().isEmpty()){
                leitorTxtSigtap.lerTxtProcedimentos(urlProcedimentos);
            }
            if(ocupacaoRepository.findAll().isEmpty()){
                leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
            }
            if(registroSigtapRepository.findAll().isEmpty()){
                leitorTxtSigtap.lerTxtRegistro(urlRegistros);
            }
            //leitorTXTMedicamentos.lerTXTMedicamentos(txtFormaFarmaceutica);
            //leitorTXTMedicamentos.lerTXTMedicamentos(txtMedicamentos);
            leitorTxtSigtap.lerProcedimento_Cid(urlRelationProced_Cid);
            leitorTxtSigtap.lerProcedimento_Ocupacao(urlRelationProced_Ocupacao);
            leitorTxtSigtap.lerRelacionamentoProcedimento_Registro(urlRelationProced_Registro);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Habito tabagismo = new Habito();
//        tabagismo.setNome("Tabagismo");
//        Habito etilismo = new Habito();
//        etilismo.setNome("Etilismo");
//        Habito drogasLicitas = new Habito();
//        drogasLicitas.setNome("Drogas Ilícitas");
//        List<Habito> habitos = new ArrayList<>();
//        habitos.add(tabagismo);
//        habitos.add(etilismo);
//        habitos.add(drogasLicitas);
//        habitoRepository.saveAll(habitos);

        SalvarEstadosEMunicipios estadosEMunicipios = new SalvarEstadosEMunicipios();
        SalvarLogradouros logradouros = new SalvarLogradouros();
        estadosEMunicipios.lerCEP_Codigo(cepIbgeRepository);
        estadosEMunicipios.lerCSVEstados(estadoRepository);
        estadosEMunicipios.lerCSV(municipioRepository, estadoRepository);
        logradouros.lerCsvLogradouro(logradouroRepository);

    }
}
