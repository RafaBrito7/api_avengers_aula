package com.example.senai.model.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AvengerDAO {

	public static List<String> oldAvengers = Arrays.asList("Capitão América", "Hulk", 
			"Thor", "Homem de Ferro","Gavião Arqueiro", "Viúva Negra");

	public List<String> listOldAvengers() {
		return oldAvengers;
	}
}
