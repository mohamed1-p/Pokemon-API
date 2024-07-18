package com.pokemonreview.api.controller;


import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.RegisterDto;
import com.pokemonreview.api.models.RolesEntity;
import com.pokemonreview.api.models.UserEntity;
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
		
		if(userRepo.existsByUserName(rDto.getUserName())) {
			return new ResponseEntity<String>("username is taken ",
					HttpStatus.BAD_REQUEST);
		}
		else {
			UserEntity user = new UserEntity();
			user.setUserName(rDto.getUserName());
			user.setPassword(passwordEncoder.encode(rDto.getPassword()));
			
			RolesEntity roles =  roleRepo.findByName("USER").get();
			user.setRoles(Collections.singletonList(roles));
			
			userRepo.save(user);
		}
		
		return new ResponseEntity<String>("user Registered",HttpStatus.OK);
	}
	
	
	
	
}
