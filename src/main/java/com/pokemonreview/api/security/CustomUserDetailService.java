package com.pokemonreview.api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pokemonreview.api.repo.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService{
	
	private UserRepo userRepo;

	public CustomUserDetailService(UserRepo userRepo) {
		this.userRepo=userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {

		
		return null;
	}

}
