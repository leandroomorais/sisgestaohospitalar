package com.ifrn.sisgestaohospitalar.utils;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
import com.ifrn.sisgestaohospitalar.model.ImportarXmlEsus;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.Ocupacao;
import com.ifrn.sisgestaohospitalar.model.OrgaoResponsavel;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.Role;
import com.ifrn.sisgestaohospitalar.model.TipoUsuario;
import com.ifrn.sisgestaohospitalar.model.Usuario;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.OcupacaoRepository;
import com.ifrn.sisgestaohospitalar.repository.OrgaoResponsavelRepository;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;
import com.ifrn.sisgestaohospitalar.repository.RoleRepository;
import com.ifrn.sisgestaohospitalar.repository.TipoUsuarioRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;

/**
 * A classe <code>LeitorXmlEsus</code> é um utilitário que contém métodos para a
 * leitura e persistência de dados do arquivo XML do Sistema ESUS
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class LeitorXmlEsus {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	@Autowired
	private OrgaoResponsavelRepository orgaoResponsavelRepository;

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * O método lerXmlEsus ler o arquivo XML ESUS e persiste as informações no Banco
	 * de Dados
	 * 
	 * @param file
	 * @param cnes
	 * @throws JAXBException
	 * @throws ParseException
	 */
	public void lerXmlEsus(InputStream inputStream, String cnes) throws JAXBException, ParseException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ImportarXmlEsus.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ImportarXmlEsus importarXmlEsus = (ImportarXmlEsus) unmarshaller.unmarshal(inputStream);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Profissional profissional : importarXmlEsus.getIdentificacao().getProfissionais()) {
			Profissional profissionalBD = profissionalRepository.findByCpf(profissional.getCpf());
			if (profissionalBD != null) {
				for (Lotacao lotacaoProfissional : profissional.getLotacoes()) {
					if (lotacaoProfissional.getCnes().equals(cnes)) {
						String[] name = profissional.getNome().split(" ");
						String nomeAbrev = name[0].toString() + " " + name[1].toString();
						profissional.setNomeAbrev(nomeAbrev);
						if (profissional.getDataNascimento() != null || !profissional.getDataNascimento().isEmpty()) {
							profissional.setDataNascimento(
									LocalDate.parse(profissional.getDataNascimento(), formatter).toString());
						}
						profissional.setAtivo(true);
						Usuario usuario = new Usuario();
						usuario.setUsername(profissional.getCpf());
						usuario.setPassword(new BCryptPasswordEncoder().encode("sgh" + profissional.getCpf()));
						usuario.setConcatName(profissional.getNomeAbrev());

						if (lotacaoProfissional.getCodigoCBO().startsWith("225")) {
							TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("MEDICO");
							Ocupacao ocupacao = ocupacaoRepository.findByCodigo(lotacaoProfissional.getCodigoCBO());
							if (ocupacao != null) {
								profissional.setNomeOcupacao(ocupacao.getNome());
							}
							usuario.setTipoUsuario(tipoUsuario);
							usuario.setEnabled(profissional.isAtivo());
							Role role = roleRepository.findByNome(tipoUsuario.getNome());
							usuario.getRole().add(role);
						} else if (lotacaoProfissional.getCodigoCBO().equals("223505")) {
							TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("ENFERMEIRO");
							Ocupacao ocupacao = ocupacaoRepository.findByCodigo(lotacaoProfissional.getCodigoCBO());
							if (ocupacao != null) {
								profissional.setNomeOcupacao(ocupacao.getNome());
							}
							usuario.setTipoUsuario(tipoUsuario);
							usuario.setEnabled(profissional.isAtivo());
							Role role = roleRepository.findByNome(tipoUsuario.getNome());
							usuario.getRole().add(role);
						} else if (lotacaoProfissional.getCodigoCBO().equals("322205")) {
							TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("TECNICO");
							Ocupacao ocupacao = ocupacaoRepository.findByCodigo(lotacaoProfissional.getCodigoCBO());
							if (ocupacao != null) {
								profissional.setNomeOcupacao(ocupacao.getNome());
							}
							usuario.setEnabled(profissional.isAtivo());
							usuario.setTipoUsuario(tipoUsuario);
							Role role = roleRepository.findByNome(tipoUsuario.getNome());
							usuario.getRole().add(role);
						} else if (lotacaoProfissional.getCodigoCBO().equals("322230")) {
							TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("AUXILIAR");
							Ocupacao ocupacao = ocupacaoRepository.findByCodigo(lotacaoProfissional.getCodigoCBO());
							if (ocupacao != null) {
								profissional.setNomeOcupacao(ocupacao.getNome());
							}
							usuario.setEnabled(profissional.isAtivo());
							usuario.setTipoUsuario(tipoUsuario);
							Role role = roleRepository.findByNome(tipoUsuario.getNome());
							usuario.getRole().add(role);
						} else if (lotacaoProfissional.getCodigoCBO().equals("123105")
								|| lotacaoProfissional.getCodigoCBO().equals("131205")) {
							TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("ADMINISTRADOR");
							Ocupacao ocupacao = ocupacaoRepository.findByCodigo(lotacaoProfissional.getCodigoCBO());
							if (ocupacao != null) {
								profissional.setNomeOcupacao(ocupacao.getNome());
							}
							usuario.setEnabled(profissional.isAtivo());
							usuario.setTipoUsuario(tipoUsuario);
							Role role = roleRepository.findByNome(tipoUsuario.getNome());
							usuario.getRole().add(role);
						}
						usuarioRepository.saveAndFlush(usuario);
						profissionalRepository.saveAndFlush(profissional);
					}
				}
			}
		}

		for (Estabelecimento estabelecimento : importarXmlEsus.getIdentificacao().getEstabelecimentos()) {
			if (estabelecimento.getCnes().equals(cnes)
					&& estabelecimentoRepository.findByCnes(estabelecimento.getCnes()) == null) {
				estabelecimentoRepository.saveAndFlush(estabelecimento);
			}
			if (estabelecimento.getTipoUnidadeId().equals("68")) {
				OrgaoResponsavel orgaoResponsavel = new OrgaoResponsavel();
				orgaoResponsavel.setSigla(getSigla(estabelecimento.getNome()));
				orgaoResponsavel.setNomeOrgao(getNome(orgaoResponsavel.getSigla(), estabelecimento.getNome()));
				orgaoResponsavel.setCnpj(estabelecimento.getCnpj());
				orgaoResponsavel.setIndicador('M');
				orgaoResponsavelRepository.saveAndFlush(orgaoResponsavel);
			}
		}
	}

	private String getSigla(String nomeEstabelecimento) {
		String sigla[] = nomeEstabelecimento.split(" ");
		String siglaAux = "";
		for (int i = 0; i < sigla.length; i++) {
			if (sigla[i].equals("DE")) {
				sigla[i] = "";
			}
			if (sigla[i].length() != 0) {
				siglaAux += sigla[i].substring(0, 1);
			}
		}
		return siglaAux;
	}

	private String getNome(String sigla, String nomeEstabelecimento) {
		String nome[] = nomeEstabelecimento.split(" ");
		String nomeAux = " ";
		for (int i = 0; i < nome.length; i++) {
			if (nome[i].equals("SECRETARIA") || nome[i].equals("MUNICIPAL") || nome[i].equals("DE")
					|| nome[i].equals("SAUDE")) {
				nome[i] = "";
			}
			if (nome[i].length() != 0) {
				nomeAux += nome[i] + " ";
			}
		}
		return sigla.substring(0, 3) + " DE" + nomeAux;

	}

}
