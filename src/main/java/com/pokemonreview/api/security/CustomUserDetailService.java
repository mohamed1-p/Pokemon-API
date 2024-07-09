package com.pokemonreview.api.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pokemonreview.api.models.RolesEntity;
import com.pokemonreview.api.models.UserEntity;
import com.pokemonreview.api.repo.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService{
	
	private UserRepo userRepo;

	@Autowired
	public CustomUserDetailService(UserRepo userRepo) {
		this.userRepo=userRepo;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {

		UserEntity user =  userRepo.findByUsername(username).
				orElseThrow(() -> new UsernameNotFoundException(
						"user not found"));
		
		return new User(user.getUserName(),user.getPassword(),
				mapRoleToAuthorities(user.getRoles()));
	}
	
	 private Collection<GrantedAuthority> mapRoleToAuthorities(
			 				List<RolesEntity> roles){
		 
		 return roles.stream().map(role -> new SimpleGrantedAuthority(
				 role.getName())).collect(Collectors.toList());
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
