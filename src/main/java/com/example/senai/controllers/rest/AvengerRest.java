package com.example.senai.controllers.rest;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.senai.controllers.service.AvengerService;
import com.example.senai.exceptions.AvengersNotFoundExcetion;
import com.example.senai.model.transport.AvengerDTO;

@RestController
@RequestMapping("/avengers")
public class AvengerRest {

	private AvengerService avengerService;

	public AvengerRest(AvengerService avengerService) {
		this.avengerService = avengerService;
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/list")
	public List<String> listOldAvengers() throws AvengersNotFoundExcetion {
		return avengerService.listOldAvengers();
	}

	@GetMapping("/list-all")
	public List<String> listAvengers() throws AvengersNotFoundExcetion, SQLException {
		return avengerService.listAvengers();
	}

	@GetMapping("/{id}")
	public AvengerDTO getById(@PathVariable("id") long id) throws AvengersNotFoundExcetion, SQLException {
		return avengerService.getById(id);
	}

	@PostMapping("/create")
	public ResponseEntity<AvengerDTO> createNewAvenger(@RequestBody AvengerDTO avenger) throws SQLException {
		AvengerDTO avengerCreated = this.avengerService.create(avenger);
		if (avengerCreated == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(avengerCreated);
	}

	@GetMapping("/filter")
	public List<AvengerDTO> getAvengersByFilter(@RequestParam("name") String name)
			throws AvengersNotFoundExcetion, SQLException {
		return avengerService.getAvengersByFilter(name);
	}
}
