package com.ifrn.sisgestaohospitalar.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Ciap2;
import com.ifrn.sisgestaohospitalar.repository.Ciap2Repository;
/**
 * Classe que implementa os Métodos da Interface Ciap2Repository
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */

@Service
public class Ciap2Service {
	
	@Autowired
	private Ciap2Repository repository;
	
	/**
	 * Salva a Ciap2
	 * @param ciap2
	 */
	public void save(Ciap2 ciap2) {
		repository.save(ciap2);
	}
	
	/**
	 * Deleta a Ciap2 a partir do id
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**
	 * Retorna a Ciap2 a partir do id
	 * @param id
	 * @return
	 */
	public Ciap2 findOne(Long id) {
		return repository.getOne(id);
	}
	
	/**
	 * Retona a lista de Ciap2
	 * @return List<Ciap2>
	 */
	public List<Ciap2> listAll(){
		return repository.findAll();
	}
	
	/**
	 * @param keyword
	 * @return List<String>
	 */
	public List<String> search(String keyword){
		return repository.search(keyword);
	}

}
