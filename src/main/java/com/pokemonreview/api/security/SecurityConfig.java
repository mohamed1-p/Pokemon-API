package com.pokemonreview.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private CustomUserDetailService userDetailService;
	
	@Autowired
	public SecurityConfig(CustomUserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}


	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http)
			throws Exception{
		http
		//Cross-Site Request Forgery (CSRF), also known as one-click 
		//attack or session riding,
		//is a type of malicious exploit targeting web applications.
		.csrf().disable()
		.authorizeHttpRequests((request)-> request.requestMatchers(
				"/api/auth/**")
		.permitAll()
		.anyRequest().authenticated())
		.httpBasic();
		return http.build();
	}
	
	
	@Bean
	 UserDetailsService users() {
		UserDetails admin = User.builder()
				.username("admin")
				.password("password")
				.roles("ADMIN")
				.build();
		
		UserDetails user = User.builder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin,user);
		
	}
	
	@Bean
	 AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiuration)
	throws Exception{
		
		return authenticationConfiuration.getAuthenticationManager();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
	
	
	

}
