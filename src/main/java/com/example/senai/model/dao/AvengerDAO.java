package com.example.senai.model.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.senai.model.transport.AvengerDTO;

@Repository
public class AvengerDAO {

	public static List<String> oldAvengers = Arrays.asList("Capitão América", "Hulk", 
			"Thor", "Homem de Ferro","Gavião Arqueiro", "Viúva Negra");
	
	//String realName, String name, Integer age, String superPower, long id
	public List<AvengerDTO> avengers = new ArrayList<>(Arrays.asList(
			new AvengerDTO("Steve Rogers", "Capitão América", 45, "Super Soldado", 1),
			new AvengerDTO("Steve Stranger", "Dr. Estranho", 35, "Mago Supremo", 2),
			new AvengerDTO("Bruce Banner", "Hulk", 40, "Monstro Gamma", 3),
			new AvengerDTO("Tony Stark", "Homem de Ferro", 42, "Rico", 4)));
	
	public static List<String> avengersNames = new ArrayList<>();
	
	public AvengerDAO() {
		avengersNames.addAll(oldAvengers);
	}

	public List<String> listOldAvengers() {
		return oldAvengers;
	}

	public Boolean create(AvengerDTO avenger) {
		return avengersNames.add(avenger.getName());
	}

	public List<String> listAvengersNames() {
		return avengersNames;
	}
	
	public List<AvengerDTO> listAvengers(){
		return avengers;
	}
	
	public Optional<AvengerDTO> findById(Long id) {
		List<AvengerDTO> avengers = this.listAvengers();
		return avengers.stream().filter(avenger -> avenger.getId() == id).findFirst();
	}

	public List<AvengerDTO> getAvengersByFilter(String name) {
		List<AvengerDTO> avengers = this.listAvengers();
		return avengers.stream().filter(avenger -> hasEquals(name, avenger.getRealName()))
				.collect(Collectors.toList());
	}
	
	private boolean hasEquals(String filter, String nameCompost) {
		String[] split = nameCompost.split(" ");
		 if (split[0].equalsIgnoreCase(filter)) {
			return true;
		}
		 return false;
	}
}
