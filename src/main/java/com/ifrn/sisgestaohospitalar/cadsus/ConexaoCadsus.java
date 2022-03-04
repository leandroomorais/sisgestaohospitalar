package com.ifrn.sisgestaohospitalar.cadsus;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ConexaoCadsus {

	private static final String ENDPOINT_HOMOLOGACAO = "https://servicoshm.saude.gov.br/cadsus/PDQSupplier";
	private static final String ENDPOINT_PRODUCAO = "https://servicoshm.saude.gov.br/cadsus/PDQSupplier";
	private static final String USUARIO_XML = "[[USUARIO]]";
	private static final String SENHA_XML = "[[SENHA]]";

	private String usuario;
	private String senha;
	private String endpoint;

	public ConexaoCadsus(String usuario, String senha, boolean producao) {
		this.usuario = usuario;
		this.senha = senha;
		this.endpoint = producao ? ENDPOINT_PRODUCAO : ENDPOINT_HOMOLOGACAO;
	}

	public String requisicao(String xmlRequisicao) {
		xmlRequisicao = configurarXml(xmlRequisicao);

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(this.endpoint);
			HttpEntity entity = new ByteArrayEntity(xmlRequisicao.getBytes("UTF-8"));
			post.setEntity(entity);
			HttpResponse httpResponse = client.execute(post);
			String result = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(result);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String configurarXml(String xmlRequisicao) {
		xmlRequisicao = xmlRequisicao.replace(USUARIO_XML, this.usuario);
		xmlRequisicao = xmlRequisicao.replace(SENHA_XML, this.senha);
		return xmlRequisicao;
	}
}
