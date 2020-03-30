package com.ifrn.sisgestaohospitalar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifrn.sisgestaohospitalar.model.Ciap2;

@Repository
public interface Ciap2Repository extends JpaRepository<Ciap2, Long> {

	/**
	 * @param title
	 * @return List<String>
	 */
	public List<String> findByTitle(String title);

	/**
	 * @param keyword
	 * @return List<String>
	 */
	@Query("SELECT title FROM Ciap2 where title like  %:keyword%")
	public List<String> search(@Param("keyword") String keyword);


}
