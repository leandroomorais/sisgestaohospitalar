package com.ifrn.sisgestaohospitalar.resource;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;
import br.com.jhemarcos.cns.CnsDatasus;
import br.com.jhemarcos.conexao.ConexaoDatasusImpl;

/**
 * A classe Controller <code>CidadaoResource</code> contém métodos para a
 * Requisiçao de Dados ao CADSUS.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Controller
@RequestMapping("/cidadao-resource")
public class CidadaoResource {

	@Autowired
	private CidadaoService cidadaoService;

	/**
	 * Retorna os dados do Cidadão a partir do CNS e da Conexão com o Cadsus
	 * 
	 * @param cns
	 * @return
	 */
	@GetMapping("/cadsusCns")
	public ResponseEntity<String> cadsusCns(@RequestParam("cns") String cns) {
		ConexaoDatasusImpl conexaoDatasusImpl = RequisicaoCadsus.conexaoDatasusImpl();
		CnsDatasus cnsDatasus = RequisicaoCadsus.cnsDatasus(conexaoDatasusImpl);
		String xml = RequisicaoCadsus.respostaXmlCns(cnsDatasus, cns);
		JSONObject jsonObject = XML.toJSONObject(xml);

		int value;

		if (jsonObject.isNull("soap:Envelope")) {
			value = jsonObject.getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("PRPA_IN201306UV02")
					.getJSONObject("controlActProcess").getJSONObject("queryAck").getJSONObject("resultTotalQuantity")
					.getInt("value");
		} else {
			value = jsonObject.getJSONObject("soap:Envelope").getJSONObject("S:Body").getJSONObject("PRPA_IN201306UV02")
					.getJSONObject("controlActProcess").getJSONObject("queryAck").getJSONObject("resultTotalQuantity")
					.getInt("value");
		}

		if (value == 0) {
			return ResponseEntity.badRequest().build();
		} else {
			String retorno = jsonObject.toString();
			return new ResponseEntity<String>(retorno, HttpStatus.OK);
		}

	}

	/**
	 * Retorna os dados do Cidadão a partir do CPF e da conexão com o Cadsus
	 * 
	 * @param cpf
	 * @return
	 */

	@GetMapping("/cadsusCpf")
	public ResponseEntity<String> cadsusCpf(@RequestParam("cpf") String cpf) {
		ConexaoDatasusImpl conexaoDatasusImpl = RequisicaoCadsus.conexaoDatasusImpl();
		CnsDatasus cnsDatasus = RequisicaoCadsus.cnsDatasus(conexaoDatasusImpl);
		String xml = RequisicaoCadsus.respostaXmlCpf(cnsDatasus, cpf);
		JSONObject jsonObject = XML.toJSONObject(xml);
		String retorno = jsonObject.toString();
		return new ResponseEntity<String>(retorno, HttpStatus.OK);
	}

	/**
	 * Retorna os dados do Cidadão cadastrado na Base de Dados Local a partir do CNS
	 * 
	 * @param cns
	 * @return
	 */
	@GetMapping("/localCns")
	public ResponseEntity<Cidadao> localCns(@RequestParam("cns") String cns) {
		Cidadao cidadao = cidadaoService.findByCns(cns);
		if (cidadao == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(cidadao);
	}

	/**
	 * Retorna os dados do Cidadão cadastrado na Base de Dados Local a partir do CPF
	 * 
	 * @param cpf
	 * @return
	 */
	@GetMapping("/localCpf")
	public ResponseEntity<Cidadao> localCpf(@RequestParam("cpf") String cpf) {
		Cidadao cidadao = cidadaoService.findByCpf(cpf);
		if (cidadao == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(cidadao);
	}
}
