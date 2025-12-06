package com.estudos.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.estudos.ecommerce.model.usuario.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthFilter authFilter;
	
	public SecurityConfig(AuthFilter authFilter) {
		this.authFilter = authFilter;
		
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
												.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
												.requestMatchers(HttpMethod.POST, "/product/register").hasRole("ADMIN")
												.requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
												.requestMatchers(HttpMethod.PATCH, "/user/*").hasRole("USER")
												.requestMatchers(HttpMethod.DELETE, "/user/delete").hasRole("USER")
												.requestMatchers(HttpMethod.PATCH, "/user/*/password").hasRole("USER")
												.requestMatchers(HttpMethod.PATCH, "/user/*/role").hasRole("ADMIN")
												.anyRequest().authenticated())
			.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}

}
