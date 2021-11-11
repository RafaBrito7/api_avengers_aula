package com.example.senai.controllers.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.senai.model.dao.User;
import com.example.senai.model.dao.UserDAO;
import com.example.senai.model.dao.UserSpringSecurity;

@Service
public class UserService implements UserDetailsService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUser(String email) {
		return userDAO.getUser(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUser(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSpringSecurity(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
}
