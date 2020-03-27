package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.CitizenUser;
import com.ifrn.sisgestaohospitalar.repository.CitizenUserRepository;

/**Classe que implementa os métodos da Interface CitizenUserRepository
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Service
public class CitizenUserService {
	
	@Autowired
	private CitizenUserRepository repository;
	
	/**Método que permite salvar o CidadãoUsuário
	 * @param CitizenUser*/
	public void save(CitizenUser citizenUser) {
		repository.saveAndFlush(citizenUser);
	}
	
	/**Método que permite recuperar o CidadãoUsuário a partir do ID
	 * @param id
	 * @return CitizenUser*/
	public CitizenUser findOne(Long id) {
		return repository.getOne(id);
	}
	
	/**Método que permite deletar o CidadãoUsuário a partir do ID
	 * @param id */
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**Método que permite recuperar o CidadãoUsuário a partir do Cartão de Saúde 
	 * @param healthcardnumber
	 * @return CitizenUser*/
	public CitizenUser findByHealthcardnumber(String healthcardnumber) {
		return repository.findByHealthcardnumber(healthcardnumber);
	}

}
