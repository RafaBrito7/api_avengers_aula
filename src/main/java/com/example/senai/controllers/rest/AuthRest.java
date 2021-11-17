package com.example.senai.controllers.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.senai.controllers.service.UserService;
import com.example.senai.model.dao.UserSpringSecurity;
import com.example.senai.model.transport.JwtDTO;
import com.example.senai.util.JWTUtil;
import com.google.gson.Gson;

@RestController
@RequestMapping("/auth")
public class AuthRest {
	
	private JWTUtil jwtUtil;
	
	public AuthRest(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws IOException{
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		JwtDTO generateToken = jwtUtil.generateToken(userSpringSecurity.getUsername());
		response.addHeader("Authorization", generateToken.getFullToken());
		response.getWriter().append(new Gson().toJson(generateToken));
		return ResponseEntity.noContent().build();
	}

}
