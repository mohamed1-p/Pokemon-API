package com.pokemonreview.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.RegisterDto;
import com.pokemonreview.api.repo.RoleRepo;
import com.pokemonreview.api.repo.UserRepo;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthenticationManager authManager;
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthController(AuthenticationManager authManager, 
						  UserRepo userRepo,RoleRepo roleRepo,
						  PasswordEncoder passwordEncoder) {
		
		this.authManager = authManager;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto rDto){
		
		
		return null;
	}
	
	
	
	
}
