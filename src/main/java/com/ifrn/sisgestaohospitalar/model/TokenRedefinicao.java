package com.ifrn.sisgestaohospitalar.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * A classe <code>TokenRedefinicao</code> representa os objetos do tipo
 * TokenRedefinicao e contém seus atributos e métodos.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Entity
public class TokenRedefinicao {

	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String token;

	@OneToOne(targetEntity = Profissional.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "profissional_id")
	private Profissional profissional;

	private Date dataExpiracao;

	public TokenRedefinicao() {
	}

	public TokenRedefinicao(final String token, final Profissional profissional) {
		super();
		this.token = token;
		this.profissional = profissional;
		this.dataExpiracao = calculateExpiryDate(EXPIRATION);
	}

	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public void updateToken(final String token) {
		this.token = token;
		this.dataExpiracao = calculateExpiryDate(EXPIRATION);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date expiryDate) {
		this.dataExpiracao = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}

	@Override
	public String toString() {
		return "TokenRedefinicao [id=" + id + ", token=" + token + ", profissional=" + profissional + ", dataExpiracao="
				+ dataExpiracao + "]";
	}

}
