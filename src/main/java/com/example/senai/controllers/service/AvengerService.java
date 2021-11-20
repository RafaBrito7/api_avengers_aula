package com.example.senai.controllers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.senai.exceptions.AvengersNotFoundExcetion;
import com.example.senai.model.dao.AvengerDAO;
import com.example.senai.model.transport.AvengerDTO;

@Service
public class AvengerService {
	
	private AvengerDAO avengerDAO;
	
	public AvengerService(AvengerDAO avengerDAO) {
		this.avengerDAO = avengerDAO;
	}

	public List<String> listOldAvengers() throws AvengersNotFoundExcetion{
		List<String> listOldAvengers = this.avengerDAO.listOldAvengers();
		
		if (listOldAvengers.isEmpty()) {
			System.out.println("Não foram encontrados Vingadores no Banco!");
			throw new AvengersNotFoundExcetion();
		}
		return listOldAvengers;
	}

	public Boolean create(AvengerDTO avenger) {
		if (avenger == null) {
			throw new IllegalArgumentException("O Avenger está nulo!");
		}
		return this.avengerDAO.create(avenger);
	}

	public List<String> listAvengers() {
		return this.avengerDAO.listAvengersNames();
	}

	public AvengerDTO getById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("O Id não pode ser Nulo!");
		}
		Optional<AvengerDTO> avenger = avengerDAO.findById(id);
		
		if (avenger.isPresent()) {
			return avenger.get();
		}
		return null;
	}

	public List<AvengerDTO> getAvengersByFilter(String name) {
		if (name == null || name.isEmpty()) { 
			throw new IllegalArgumentException("O nome não pode ser Vazio!");
		}
		
		List<AvengerDTO> avengersFilted = new ArrayList<>();
		avengersFilted = this.avengerDAO.getAvengersByFilter(name);
		if (!avengersFilted.isEmpty()) {
			return avengersFilted;
		}
		
		return avengersFilted;
	}
}
