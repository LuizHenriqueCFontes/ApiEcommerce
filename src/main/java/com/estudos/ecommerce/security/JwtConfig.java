package com.estudos.ecommerce.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class JwtConfig {
	
	@Value("${api.security.token}")
	String secret;
	
	@Bean
	Algorithm jwtAlgorithm() {
		return Algorithm.HMAC256(secret);
		
	}
	

}
