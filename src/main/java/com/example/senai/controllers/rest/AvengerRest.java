package com.example.senai.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.senai.controllers.service.AvengerService;
import com.example.senai.exceptions.AvengersNotFoundExcetion;

@RestController
@RequestMapping("/avengers")
public class AvengerRest {
	
	@Autowired
	private AvengerService avengerService;

	@GetMapping("/list")
	public List<String> listOldAvengers() throws AvengersNotFoundExcetion{
		return avengerService.listOldAvengers();
	}
}
