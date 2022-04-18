package com.ifrn.sisgestaohospitalar.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.cadsus.ConexaoCadsus;
import com.ifrn.sisgestaohospitalar.enums.CodigoRaca;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Endereco;
import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;
import com.ifrn.sisgestaohospitalar.repository.MunicipioRepository;

@Service
public class CidadaoCadsusService {

	private static final String URL_PATH = System.getProperty("user.dir") + "/src/main/resources/requests/";
	private static final String PATH_CONSULTACNS = URL_PATH + "consultaCNS.xml";
	private static final String PATH_CONSULTACPF = URL_PATH + "consultaCPF.xml";
	private static final String PATH_CONSULTANOME_NASCIMENTO = URL_PATH + "consultaNOME_DTNASCIMENTO.xml";
	private static final String PARAMETRO = "[[PARAMETRO]]";
	private static final String PARAMETRO_NOME = "[[PARAMETRO_NOME]]";
	private static final String PARAMETRO_DT_NASCIMENTO = "[[PARAMETRO_DT_NASCIMENTO]]";

	ConexaoCadsus conexaoCadsus = new ConexaoCadsus("CADSUS.CNS.PDQ.PUBLICO", "kUXNmiiii#RDdlOELdoe00966", false);

	@Autowired
	private MunicipioRepository municipioRepository;
	@Autowired
	private LogradouroRepository logradouroRepository;

	public Cidadao consultaCNS(String parametro) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(PATH_CONSULTACNS));
			String arquivoXML = new String(encoded);
			String requisicao = conexaoCadsus.requisicao(arquivoXML.replace(PARAMETRO, parametro));
			if (requisicao != null) {
				JSONObject jsonObject = XML.toJSONObject(requisicao);
				return jsonParaCidadao(jsonObject);
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cidadao consultaCPF(String parametro) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(PATH_CONSULTACPF));
			String arquivoXML = new String(encoded);
			String requisicao = conexaoCadsus.requisicao(arquivoXML.replace(PARAMETRO, parametro));
			if (requisicao != null) {
				JSONObject jsonObject = XML.toJSONObject(requisicao);
				return jsonParaCidadao(jsonObject);
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cidadao consultaNOMEeDN(String nome, String dataNascimento) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(PATH_CONSULTANOME_NASCIMENTO));
			String arquivoXML = new String(encoded);
			String requisicao = conexaoCadsus.requisicao(
					arquivoXML.replace(PARAMETRO_NOME, nome).replace(PARAMETRO_DT_NASCIMENTO, dataNascimento));
			if (requisicao != null) {
				JSONObject jsonObject = XML.toJSONObject(requisicao);
				return jsonParaCidadao(jsonObject);
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cidadao jsonParaCidadao(JSONObject jsonObject) {

		int value = 0;

		if (jsonObject.has("soap:Envelope")) {
			value = jsonObject.getJSONObject("soap:Envelope").getJSONObject("S:Body").getJSONObject("PRPA_IN201306UV02")
					.getJSONObject("controlActProcess").getJSONObject("queryAck").getJSONObject("resultTotalQuantity")
					.getInt("value");
		} else {
			value = jsonObject.getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("PRPA_IN201306UV02")
					.getJSONObject("controlActProcess").getJSONObject("queryAck").getJSONObject("resultTotalQuantity")
					.getInt("value");
		}
		if (value == 0) {
			return null;
		} else {
			Cidadao cidadao = new Cidadao();

			JSONObject getPaciente = jsonObject.getJSONObject("soap:Envelope").getJSONObject("S:Body")
					.getJSONObject("PRPA_IN201306UV02").getJSONObject("controlActProcess").getJSONObject("subject")
					.getJSONObject("registrationEvent").getJSONObject("subject1").getJSONObject("patient");

			// Verifica NACIONALIDADE
			if (getPaciente.getJSONObject("patientPerson").getJSONObject("birthPlace").getJSONObject("addr")
					.has("country")) {

				cidadao.setCodigoNacionalidade(getPaciente.getJSONObject("patientPerson").getJSONObject("birthPlace")
						.getJSONObject("addr").getInt("country"));
			}
			// Verifica MUNICIPIO DE NASCIMENTO
			if (getPaciente.getJSONObject("patientPerson").getJSONObject("birthPlace").getJSONObject("addr")
					.has("city")) {

				cidadao.setMunicipioNascimento(
						municipioRepository.findBycodigoIBGE(getPaciente.getJSONObject("patientPerson")
								.getJSONObject("birthPlace").getJSONObject("addr").getLong("city")));
			}

			// Verifica CNS e CPF
			for (int i = 0; i < (getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs").length()); i++) {
				if (getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs").getJSONObject(i)
						.optJSONArray("id") != null) {
					for (int j = 0; j < getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs")
							.getJSONObject(i).getJSONArray("id").length(); j++) {
						if (getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs").getJSONObject(i)
								.getJSONArray("id").getJSONObject(0).get("root").equals("2.16.840.1.113883.13.236")
								&& getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs")
										.getJSONObject(i).getJSONArray("id").getJSONObject(1).get("extension")
										.equals("D")) {
							cidadao.setCns(Long.toString(getPaciente.getJSONObject("patientPerson")
									.getJSONArray("asOtherIDs").getJSONObject(i).getJSONArray("id").getJSONObject(0)
									.getLong("extension")));
						}
					}
				}

				if (getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs").getJSONObject(i)
						.optJSONObject("id") != null) {
					if (getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs").getJSONObject(i)
							.optJSONObject("id").getString("root").equals("2.16.840.1.113883.13.237")) {
						if (getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs").getJSONObject(i)
								.optJSONObject("id").optString("extension") != null) {
							cidadao.setCpf(getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs")
									.getJSONObject(i).optJSONObject("id").optString("extension"));
						} else {
							cidadao.setCpf(
									Long.toString(getPaciente.getJSONObject("patientPerson").getJSONArray("asOtherIDs")
											.getJSONObject(i).optJSONObject("id").optLong("extension")));
						}
					}
				}
			}

			// Verifica NOME
			if (getPaciente.getJSONObject("patientPerson").getJSONObject("name") != null) {
				cidadao.setNome(getPaciente.getJSONObject("patientPerson").getJSONObject("name").getString("given"));
			}

			// Verifica TELEFONE

//			if (getPaciente.getJSONObject("patientPerson").optJSONObject("telecom") != null) {
//				var telefone = new Telefone();
//				telefone.setNumeroTelefone(
//						getPaciente.getJSONObject("patientPerson").getJSONObject("telecom").getString("value"));
//				List<Telefone> telefones = new ArrayList<Telefone>();
//				telefones.add(telefone);
//				cidadao.setTelefonesCidadao(telefones);
//			}
//
//			if (getPaciente.getJSONObject("patientPerson").optJSONArray("telecom") != null) {
//				var telefone = new Telefone();
//				List<Telefone> telefones = new ArrayList<Telefone>();
//				telefone.setNumeroTelefone(getPaciente.getJSONObject("patientPerson").getJSONArray("telecom")
//						.getJSONObject(2).getString("value"));
//				cidadao.setTelefonesCidadao(telefones);
//
//			}

			// Verifica nome do PAI e da MÃE
			if (getPaciente.getJSONObject("patientPerson").getJSONArray("personalRelationship") != null) {
				for (int i = 0; i < getPaciente.getJSONObject("patientPerson").getJSONArray("personalRelationship")
						.length(); i++) {
					if (getPaciente.getJSONObject("patientPerson").getJSONArray("personalRelationship").getJSONObject(i)
							.getJSONObject("code").getString("code").equals("PRN")) {
						cidadao.setNomeMae(getPaciente.getJSONObject("patientPerson")
								.getJSONArray("personalRelationship").getJSONObject(i)
								.getJSONObject("relationshipHolder1").getJSONObject("name").getString("given"));
					}

					if (getPaciente.getJSONObject("patientPerson").getJSONArray("personalRelationship").getJSONObject(i)
							.getJSONObject("code").getString("code").equals("NPRN")) {
						cidadao.setNomePai(getPaciente.getJSONObject("patientPerson")
								.getJSONArray("personalRelationship").getJSONObject(i)
								.getJSONObject("relationshipHolder1").getJSONObject("name").getString("given"));
					}
				}
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			// Verifica DATA DE NASCIMENTO
			if (getPaciente.getJSONObject("patientPerson").getJSONObject("birthTime") != null) {
				cidadao.setDataNascimento(LocalDate.parse(
						convertData(
								getPaciente.getJSONObject("patientPerson").getJSONObject("birthTime").getLong("value")),
						formatter));
			}

			// Verifica ENDEREÇO
			if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr") != null) {
				Endereco endereco = new Endereco();
				endereco.setBairro(getPaciente.getJSONObject("patientPerson").getJSONObject("addr")
						.getString("additionalLocator"));
				if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr").has("postalCode")) {
					endereco.setCep(Long.toString(
							getPaciente.getJSONObject("patientPerson").getJSONObject("addr").getLong("postalCode")));
				}

				if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr").has("streetNameType")) {
					if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr")
							.optString("streetNameType") != null) {
						endereco.setLogradouro(logradouroRepository.findByCodigo(Long.parseLong(getPaciente
								.getJSONObject("patientPerson").getJSONObject("addr").optString("streetNameType"))));
					} else {
						endereco.setLogradouro(logradouroRepository.findByCodigo(getPaciente
								.getJSONObject("patientPerson").getJSONObject("addr").optLong("streetNameType")));
					}
				}

				if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr").has("houseNumber")) {
					if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr")
							.optString("houseNumber") != null) {
						endereco.setNumero(getPaciente.getJSONObject("patientPerson").getJSONObject("addr")
								.optString("houseNumber"));
					} else {
						endereco.setNumero(Long.toString(getPaciente.getJSONObject("patientPerson")
								.getJSONObject("addr").optLong("houseNumber")));
					}
				}

				if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr").has("city")) {
					endereco.setMunicipio(municipioRepository.findBycodigoIBGE(
							getPaciente.getJSONObject("patientPerson").getJSONObject("addr").getLong("city")));
				}

				if (getPaciente.getJSONObject("patientPerson").getJSONObject("addr").has("streetName")) {
					endereco.setNomeLogradouro(
							getPaciente.getJSONObject("patientPerson").getJSONObject("addr").getString("streetName"));
				}

				cidadao.setEndereco(endereco);
			}

			// Verifica SEXO
			if (getPaciente.getJSONObject("patientPerson").getJSONObject("administrativeGenderCode") != null) {
				cidadao.setSexo(getPaciente.getJSONObject("patientPerson").getJSONObject("administrativeGenderCode")
						.getString("code"));
			}
			// Verifica códido da RAÇA
			if (getPaciente.getJSONObject("patientPerson").getJSONObject("raceCode") != null) {
				for (CodigoRaca codigoRaca : CodigoRaca.values()) {
					if (codigoRaca.getCodigo() == getPaciente.getJSONObject("patientPerson").getJSONObject("raceCode")
							.optInt("code")) {
						cidadao.setCodigoRaca(codigoRaca);
					}
				}
			}
			return cidadao;

		}

	}

	public String convertData(Long data) {
		String data1 = data.toString();
		String ano = data1.substring(0, 4);
		String mes = data1.substring(4, 6);
		String dia = data1.substring(6, 8);
		return ano + "-" + mes + "-" + dia;
	}

}
