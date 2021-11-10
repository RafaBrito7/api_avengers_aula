package com.example.senai.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.senai.exceptions.AvengersNotFoundExcetion;
import com.example.senai.model.dao.AvengerDAO;

@Service
public class AvengerService {
	
	@Autowired
	private AvengerDAO avengerDAO;

	public List<String> listOldAvengers() throws AvengersNotFoundExcetion{
		List<String> listOldAvengers = this.avengerDAO.listOldAvengers();
		
		if (listOldAvengers.isEmpty()) {
			System.out.println("Não foram encontrados Vingadores no Banco!");
			throw new AvengersNotFoundExcetion();
		}
		return listOldAvengers;
	}
}
