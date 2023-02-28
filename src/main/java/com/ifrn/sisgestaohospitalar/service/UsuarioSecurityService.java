package com.ifrn.sisgestaohospitalar.service;

import com.ifrn.sisgestaohospitalar.model.PasswordResetToken;
import com.ifrn.sisgestaohospitalar.model.Usuario;
import com.ifrn.sisgestaohospitalar.repository.PasswordResetTokenRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UsuarioSecurityService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Nome de usuário não encontrado!");
		}

		return usuario;
	}

	public void gerarTokenRedefinicao(Usuario usuario, String token) {
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, usuario);
		passwordResetTokenRepository.saveAndFlush(passwordResetToken);
	}

	public Usuario authenticathedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && authentication.isAuthenticated()){
			if(authentication.getPrincipal() instanceof Usuario){
				Usuario usuario = (Usuario) authentication.getPrincipal();
				return usuarioRepository.findByUsername(usuario.getUsername());
			}
		}
		return null;
	}

}