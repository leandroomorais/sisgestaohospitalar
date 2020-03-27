package com.ifrn.sisgestaohospitalar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.EstablishmentAddress;
import com.ifrn.sisgestaohospitalar.repository.EstablishmentAddressRepository;

/**
 * Classe que implementa os métodos da Interface AddressEstablishmentRepository
 * @author Leandro Morais
 * @version 1.0
 * @since Release 02 da Aplicação
 */
@Service
public class EstablishmentAddressService {

	@Autowired
	private EstablishmentAddressRepository repository;

	/**
	 * Salva o Endereço do Estabelecimento
	 * @param AddressEstablishment
	 */
	public void save(EstablishmentAddress establishmentAddress) {
		repository.save(establishmentAddress);
	}

	/**
	 * Retorna o Endereço do Estabelecimento a partir do Id
	 * @param id
	 * @return AddresEstablishment
	 */
	public EstablishmentAddress findOne(Long id) {
		return repository.getOne(id);
	}

	/**
	 * Deleta o Endereço do Estabelecimento a partir do Id
	 * @param id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Retorna a Lista de Endereços do Estabelecimento
	 * @return List<AddressEstablishment>
	 */
	public List<EstablishmentAddress> listAll() {
		return repository.findAll();
	}

}
