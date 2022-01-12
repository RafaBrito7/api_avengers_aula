package com.example.senai.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.senai.model.Avenger;
import com.example.senai.model.transport.AvengerDTO;
import com.example.senai.model.transport.AvengerNameProjection;

@Repository
public interface AvengerRepository extends CrudRepository<Avenger, Long> {
	
	public List<Avenger> findByName(String name);
	
	@Query("SELECT a FROM Avenger a WHERE a.name =:name")
	public List<AvengerDTO> findDTOByName(String name);
	
	@Query(value = "SELECT * FROM avenger", nativeQuery = true)
	public List<AvengerNameProjection> findAllName();

	public List<Avenger> findByBirthdayDateGreaterThan(Date birthdayDate);

}
