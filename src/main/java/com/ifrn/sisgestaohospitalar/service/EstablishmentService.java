package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Establishment;
import com.ifrn.sisgestaohospitalar.repository.EstablishmentRepository;

/**Classe que implementa os métodos da Interface EstablishmentRepository
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Service
public class EstablishmentService {
	
	@Autowired
	private EstablishmentRepository repository;
	
	/**Método que permite salvar o Estabelecimento
	 * @param Establishment*/
	public void save(Establishment establishment) {
		repository.saveAndFlush(establishment);
	}
	
	/**Método que permite recuperar o Estabelecimento a partir do Id
	 * @param id
	 * @return Establishment*/
	public Establishment findOne(Long id) {
		return repository.getOne(id);
	}
	
	/**Método que permite deletar o Estabelecimento a partir do Id
	 * @param id*/
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**Método que permite recuperar a lista de Estabelecimentos
	 * @return List<Establishment>*/
	public List<Establishment> listAll(){
		return repository.findAll();
	}
	
	/**Método que permite recuperar o Estabelecimento a partir do Cnes
	 * @param cnes
	 * @return Establishment*/
	public Establishment findByCnes(String cnes) {
		return repository.findByCnes(cnes);
	}

}
