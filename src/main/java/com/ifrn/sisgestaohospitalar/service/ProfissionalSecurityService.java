package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.ProfissionalRepository;


@Service
@Primary
public class ProfissionalSecurityService implements UserDetailsService {
	
	@Autowired
	private ProfissionalRepository profisisonalRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Profissional profissional = profisisonalRepository.findByCpf(username);
		
		if(null == profissional) {
			throw new UsernameNotFoundException("Nome de usuário não encontrado!");
		}
		
		return profissional;
	}

}
