package com.ifrn.sisgestaohospitalar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/index")
				.hasAnyAuthority("ADM", "MED", "ENF", "TEC", "SUPER").antMatchers("/administracao/**")
				.hasAnyAuthority("ADM", "SUPER").antMatchers("/atendimentoMedico/**").hasAnyAuthority("MED", "SUPER")
				.antMatchers("/recepcao/**").hasAnyAuthority("ADM", "ATD", "TEC", "SUPER").antMatchers("/triagem/**")
				.hasAnyAuthority("ENF", "SUPER").antMatchers("/add-medicamento/**")
				.hasAnyAuthority("ENF", "TEC", "SUPER").antMatchers("/")
				.hasAnyAuthority("ADM", "ATD", "MED", "ENF", "TEC", "SUPER").antMatchers("/paciente/**")
				.hasAnyAuthority("ADM", "ATD", "TEC", "SUPER").antMatchers("/profissional/**")
				.hasAnyAuthority("ADM", "TEC", "ENF", "MED", "SUPER").antMatchers("/api/usuario/**").permitAll()
				.antMatchers("/recuperar-senha/**").permitAll()
				.antMatchers("/nova-senha/**").permitAll().antMatchers("/cadastra-senha/**").permitAll()
				.antMatchers("/adicionar-cidadao").permitAll().antMatchers("/salvar-cidadao").permitAll()
				.antMatchers("/adicionar-guia-atendimento").permitAll().antMatchers("/salvar-guia-atendimento").permitAll()
				.antMatchers("/listar-status").permitAll().antMatchers("/editar/**").permitAll()
				.antMatchers("/cidadao-resource/**").permitAll().antMatchers("/pesquisaCns/**").permitAll()
				.antMatchers("/ciap2-resource/***").permitAll()
				.antMatchers("/api/procedimento/**").permitAll().antMatchers("/ciap2/**").permitAll()
				.antMatchers("/api/**").hasAnyAuthority("ADM", "ATD", "TEC", "MED", "SUPER").antMatchers("/email/**")
				.permitAll().antMatchers("http://**").permitAll().antMatchers("https://**").permitAll()
				.antMatchers("/layouts/**").permitAll().antMatchers("/fragments/**").permitAll()
				.antMatchers("/assets/**").permitAll().antMatchers("/img/**").permitAll().antMatchers("/js/**")
				.permitAll().antMatchers("/static/**").permitAll().antMatchers("/resources/**").permitAll()
				.antMatchers("/templates/**").permitAll().antMatchers("/fonts/**").permitAll().antMatchers("/css/**")
				.permitAll().antMatchers("/sass/**").permitAll().antMatchers("/js/**").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/entrar").permitAll().successForwardUrl("/index").and()
				.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/entrar");

		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/img/**", "/js/**", "/core/**",
				"/plugin/**", "/atlantis/**", "/scss/**", "/h2/**");
		web.ignoring().antMatchers("/layouts/", "http::/**", "https::/**");
	}

}
