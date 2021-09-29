package com.ifrn.sisgestaohospitalar.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Estabelecimento;
import com.ifrn.sisgestaohospitalar.model.ImportarXmlEsus;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.Role;
import com.ifrn.sisgestaohospitalar.model.TipoUsuario;
import com.ifrn.sisgestaohospitalar.model.Usuario;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;
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
	public void lerXmlEsus(String file, String cnes) throws JAXBException, ParseException {

		JAXBContext jaxbContext = JAXBContext.newInstance(ImportarXmlEsus.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		ImportarXmlEsus importarXmlEsus = (ImportarXmlEsus) unmarshaller.unmarshal(new File(file));

		for (Profissional profissional : importarXmlEsus.getIdentificacao().getProfissionais()) {
			for (Lotacao lotacaoProfissional : profissional.getLotacoes()) {
				if (lotacaoProfissional.getCnes().equals(cnes)
						&& profissionalRepository.findByCpf(profissional.getCpf()) == null) {
					String[] name = profissional.getNome().split(" ");
					String nomeAbrev = name[0].toString() + " " + name[1].toString();
					profissional.setNomeAbrev(nomeAbrev);
					profissional.setAtivo(true);
					Usuario usuario = new Usuario();
					usuario.setUsername(profissional.getCpf());
					usuario.setPassword(new BCryptPasswordEncoder().encode("sgh" + profissional.getCpf()));
					usuario.setConcatName(profissional.getNomeAbrev());

					if (lotacaoProfissional.getCodigoCBO().equals("225125")) {
						TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("MEDICO");
						usuario.setTipoUsuario(tipoUsuario);
						usuario.setEnabled(profissional.isAtivo());
						Role role = roleRepository.findByNome(tipoUsuario.getNome());
						usuario.getRole().add(role);
					} else if (lotacaoProfissional.getCodigoCBO().equals("223505")) {
						TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("ENFERMEIRO");
						usuario.setTipoUsuario(tipoUsuario);
						usuario.setEnabled(profissional.isAtivo());
						Role role = roleRepository.findByNome(tipoUsuario.getNome());
						usuario.getRole().add(role);
					} else if (lotacaoProfissional.getCodigoCBO().equals("322205")) {
						TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("TECNICO");
						usuario.setEnabled(profissional.isAtivo());
						usuario.setTipoUsuario(tipoUsuario);
						Role role = roleRepository.findByNome(tipoUsuario.getNome());
						usuario.getRole().add(role);
					} else if (lotacaoProfissional.getCodigoCBO().equals("322230")) {
						TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("AUXILIAR");
						usuario.setEnabled(profissional.isAtivo());
						usuario.setTipoUsuario(tipoUsuario);
						Role role = roleRepository.findByNome(tipoUsuario.getNome());
						usuario.getRole().add(role);
					} else if (lotacaoProfissional.getCodigoCBO().equals("123105")) {
						TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome("ADMINISTRADOR");
						usuario.setEnabled(profissional.isAtivo());
						usuario.setTipoUsuario(tipoUsuario);
						Role role = roleRepository.findByNome(tipoUsuario.getNome());
						usuario.getRole().add(role);
					}

					usuarioRepository.saveAndFlush(usuario);

				}

				profissionalRepository.saveAndFlush(profissional);
			}
		}

		for (Estabelecimento estabelecimento : importarXmlEsus.getIdentificacao().getEstabelecimentos()) {
			if (estabelecimento.getCnes().equals(cnes)
					&& estabelecimentoRepository.findByCnes(estabelecimento.getCnes()) == null) {
				estabelecimentoRepository.saveAndFlush(estabelecimento);
			}
		}
	}

}
