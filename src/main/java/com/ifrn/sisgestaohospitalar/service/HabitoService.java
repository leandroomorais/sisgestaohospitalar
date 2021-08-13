package com.ifrn.sisgestaohospitalar.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Habito;
import com.ifrn.sisgestaohospitalar.repository.HabitoRepository;
import com.ifrn.sisgestaohospitalar.service.exception.HabitoMesmoNomeJaCadastradoException;

@Service
public class HabitoService {
	
	@Autowired
	private HabitoRepository repository;
	
	public void save(Habito habito) {
		Habito habitoAux = repository.findByNomeIgnoreCaseContaining(habito.getNome());
		if(habitoAux != null) {
			throw new HabitoMesmoNomeJaCadastradoException("Um mesmo hábito já foi cadastrado com esse mesmo nome");
		}
		repository.saveAndFlush(habito);
	}

}
