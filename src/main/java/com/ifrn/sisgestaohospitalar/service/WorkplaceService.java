package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Workplace;
import com.ifrn.sisgestaohospitalar.repository.WorkplaceRepository;

/**Classe que implementa os Métodos da Interface WorkplaceRepository
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 *
 */
@Service
public class WorkplaceService {
	
	@Autowired
	private WorkplaceRepository repository;
	
	/**Método que permite recuperar todos os locais de trabalho
	 * @return List<Workplace>*/
	public List<Workplace> findAll(){
		return repository.findAll();
	}
	
	/**Método que permite recuperar o Local de Trabalho a partir do ID
	 * @param id
	 * @return Workplace*/
	public Workplace findOne(Long id) {
		return repository.getOne(id);
	}
	
	/**Método que permite salvar o Local de Trabalho
	 * @param Workplace*/
	public Workplace save(Workplace workplace) {
		return repository.saveAndFlush(workplace);
	}
	
	/**Método que permite deletar o Local de Trabalho a partir do ID
	 * @param id*/
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**Método que permite recuperar o profissional a partir do CBO
	 * @return List<Workplace>*/
	public List<Workplace> findByCodecbo(String codecbo){
		return repository.findByCodecbo(codecbo);
	}

}
