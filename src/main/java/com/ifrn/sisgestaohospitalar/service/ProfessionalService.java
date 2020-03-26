package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Professional;
import com.ifrn.sisgestaohospitalar.repository.ProfessionalRepository;

/**Classe que implementa os Métodos da Interface ProfessionalRepository
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 *
 */
@Service
public class ProfessionalService {
	
	@Autowired
	private ProfessionalRepository repository;
	
	/**Método que permite salvar o Profissional*/
	public void save(Professional professional) {
		repository.saveAndFlush(professional);
	}
	
	/**Método que permite recuperar o profissional a partir do ID
	 * @return Professional*/
	public Professional findOne(Long id) {
		return repository.getOne(id);
	}
	
	/**Método que permite deletar o profissional a partir do ID*/
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**Método que permite recuperar a lista de todos os Profissionais
	 * @return List<Professional>*/
	public List<Professional> listAll(){
		return repository.findAll();
	}
	
	/**Método que permite recuperar o profissional a partir do CPF
	 * @return Professional*/
	public Professional findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
	
	/**Método que permite recuperar o profissional a partir do Email
	 * @return Professional*/
	public Professional findByEmail(String email) {
		return repository.findByEmail(email);
	}
	

}
