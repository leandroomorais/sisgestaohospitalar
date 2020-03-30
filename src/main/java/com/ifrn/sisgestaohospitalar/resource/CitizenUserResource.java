package com.ifrn.sisgestaohospitalar.resource;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifrn.sisgestaohospitalar.model.CitizenUser;
import com.ifrn.sisgestaohospitalar.service.CitizenUserService;
import com.ifrn.sisgestaohospitalar.utils.RequestAPI;

import br.com.jhemarcos.cns.CnsDatasus;
import br.com.jhemarcos.conexao.ConexaoDatasusImpl;

@Controller
@RequestMapping("/resource/citizenuser")
public class CitizenUserResource {

	@Autowired
	private CitizenUserService citizenUserService;

	@GetMapping(path = "/datasus")
	public ResponseEntity<String> searchCns(@RequestParam("cns") String cns) {

		ConexaoDatasusImpl conexaoDatasusImpl = RequestAPI.conexaoDatasusImpl();

		CnsDatasus cnsDatasus = RequestAPI.cnsDatasus(conexaoDatasusImpl);

		String xml = RequestAPI.respostaXmlCns(cnsDatasus, cns);

		JSONObject object = XML.toJSONObject(xml);

		String retorno = object.toString();

		return new ResponseEntity<String>(retorno, HttpStatus.OK);
	}
	
	@GetMapping(value="/findbycns/{numeroCns}")
	public ResponseEntity<CitizenUser> findByCns(@PathVariable("numeroCns") String numeroCns){
		
		CitizenUser citizenUser = citizenUserService.findByHealthcardnumber(numeroCns);
	
		if(citizenUser == null){
			return ResponseEntity.notFound().build();		
		}
		
		CitizenUser citizen = new CitizenUser();
		
		citizen.setId(citizenUser.getId());
		citizen.setName(citizenUser.getName());
		citizen.setMothername(citizenUser.getHealthcardnumber());
		citizen.setFathername(citizenUser.getFathername());
		citizen.setHealthcardnumber(citizenUser.getHealthcardnumber());
		citizen.setBirth(citizenUser.getBirth());
		citizen.setAddress(citizenUser.getAddress());
		citizen.setAddresscomplement(citizenUser.getAddresscomplement());
		citizen.setAddressneighborhood(citizenUser.getAddressneighborhood());
		citizen.setPlacecode(citizenUser.getPlacecode());
		citizen.setPostalcode(citizenUser.getPostalcode());
		citizen.setIbgecitycode(citizenUser.getIbgecitycode());
		citizen.setEmail(citizenUser.getEmail());
		citizen.setNationalitycode(citizenUser.getNationalitycode());
		citizen.setEthnicitycode(citizenUser.getEthnicitycode());
		
		return ResponseEntity.ok(citizen);
	}
	
	

}
