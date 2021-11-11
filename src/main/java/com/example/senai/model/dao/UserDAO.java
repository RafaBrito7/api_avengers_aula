package com.example.senai.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	private static Map<String, User> db = new HashMap<>();
	
	public UserDAO() {
		User gustavo = new User("gustavo.enndi.m@gmail.com", "123456");
		db.put(gustavo.getEmail(), gustavo);
	}
	
	public User getUser(String email) {
		return db.get(email);
	}
	
}
