package com.example.senai.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	private static Map<String, User> db = new HashMap<>();
	
	public UserDAO() {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		User gustavo = new User("gustavo.enndi.m@gmail.com", pe.encode("123456"));
		db.put(gustavo.getEmail(), gustavo);
	}
	
	public User getUser(String email) {
		return db.get(email);
	}
}
