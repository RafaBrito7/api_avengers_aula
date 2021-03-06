package com.example.senai.controllers.service;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.senai.dao.UserDAO;
import com.example.senai.model.User;
import com.example.senai.model.UserSpringSecurity;

@Service
public class UserService implements UserDetailsService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public User getUser(String email) {
		return userDAO.getUser(email);
	}

	public static UserSpringSecurity authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return new UserSpringSecurity((String) authentication.getPrincipal(), null,
					authentication.getAuthorities());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUser(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSpringSecurity(user.getEmail(), user.getPassword(), user.getRoles());
	}
}
