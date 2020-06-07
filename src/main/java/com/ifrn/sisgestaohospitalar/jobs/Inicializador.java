package com.ifrn.sisgestaohospitalar.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Role;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import com.ifrn.sisgestaohospitalar.service.RoleService;
import com.ifrn.sisgestaohospitalar.utils.LeitorTxtSigtap;
import com.ifrn.sisgestaohospitalar.utils.LeitorXlsCiap2;
import com.ifrn.sisgestaohospitalar.utils.LeitorXmlEsus;

import jxl.read.biff.BiffException;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private LeitorXmlEsus leitorXmlEsus;

	@Autowired
	private LeitorTxtSigtap leitorTxtSigtap;

	@Autowired
	private LeitorXlsCiap2 leitorXlsCiap2;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CidadaoService cidadaoService;

	@SuppressWarnings("deprecation")
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {


		Role role = new Role();
		role.setNome("ADM");
		roleService.save(role);

		Role rolesuper = new Role();
		rolesuper.setNome("SUPER");
		roleService.save(rolesuper);

		String cnes = "2380633";
		String file = System.getProperty("user.dir") + "/uploads/XmlParaESUS21_241360.xml";

		Cidadao cidadao = new Cidadao();
		
		String datanascimento;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		datanascimento = "1994/06/20";
		
		Date data;

		cidadao.setBairro("Centro");
		cidadao.setCep("59945000");
		cidadao.setCns("000000000000000");
		cidadao.setCodigoetnia(0);
		cidadao.setCodigoibgemunicipio("123");
		cidadao.setCodigologradouro(1);
		cidadao.setCodigonacionalidade(1);
		cidadao.setCodigoraca(03);
		try {
			cidadao.setDatanascimento(data = dateFormat.parse(datanascimento));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cidadao.setComplementoendereco("Casa");
		cidadao.setCpf("09814354406");
		cidadao.setEmail("leandromorais.dev@outlook.com");
		cidadao.setEndereco("César Rocha");
		cidadao.setNome("FRANCISCO LEANDRO DE MORAIS PINTO");
		cidadao.setNomemae("MARIA LUCIA DE MORAIS PINTO");
		cidadao.setNomepai("CÍCERO PINTO");
		cidadao.setNumeroendereco("99");
		cidadao.setSexo("M");
		cidadao.setTelefone("84981561303");
		cidadao.setNomemunicipio("MAJOR SALES");

		cidadaoService.save(cidadao);

		try {
			leitorXmlEsus.lerXmlEsus(file, cnes);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String urlProcedimentos = System.getProperty("user.dir") + "/SigtapSUS/tb_procedimento.txt";

		String urlRegistros = System.getProperty("user.dir") + "/SigtapSUS/tb_registro.txt";

		String urlCid = System.getProperty("user.dir") + "/SigtapSUS/tb_cid.txt";

		String urlOcupacao = System.getProperty("user.dir") + "/SigtapSUS/tb_ocupacao.txt";

		String urlRelationProced_Registro = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_registro.txt";

		String urlRelationProced_Cid = System.getProperty("user.dir") + "/SigtapSUS/rl_procedimento_cid.txt";

		String urlRelationProced_Ocupacao = System.getProperty("user.dir")
				+ "/SigtapSUS/rl_procedimento_ocupacaoteste.txt";

		try {
			// leitorTxtSigtap.lerTxtCid(urlCid);
			// leitorTxtSigtap.lerTxtProcedimentos(urlProcedimentos);
			// leitorTxtSigtap.lerTxtOcupacao(urlOcupacao);
			leitorTxtSigtap.lerTxtRegistro(urlRegistros);

			// leitorTxtSigtap.relacionamentoProcedimento_Cid(urlRelationProced_Cid);
			// leitorTxtSigtap.relacionamentoProcedimento_Ocupacao(urlRelationProced_Ocupacao);
			// leitorTxtSigtap.relacionamentoProcedimento_Registro(urlRelationProced_Registro);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String urlCiap2 = System.getProperty("user.dir") + "/CIAP2/CIAP_CID_revisado_16_8_2016_xls.xls";
		try {
			leitorXlsCiap2.lerCiap2(urlCiap2);
		} catch (BiffException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
