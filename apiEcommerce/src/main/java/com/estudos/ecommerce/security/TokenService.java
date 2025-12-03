package com.estudos.ecommerce.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.estudos.ecommerce.exception.UsuarioNaoEncontradoException;
import com.estudos.ecommerce.model.usuario.Usuario;

@Service
public class TokenService {
	
	private final Algorithm algorithm;
	
	public TokenService(Algorithm algorithm) {
		this.algorithm = algorithm;
		
	}
	
	public String generateToken(Usuario usuario) {
		
		try {

			String roles = usuario.getRole().getRole();

			String token = JWT.create()
							.withIssuer("auth-api")
							.withSubject(usuario.getEmail())
							.withClaim("roles", List.of(roles))
							.withExpiresAt(expirationTime())
							.sign(algorithm);
			
			return token;
			
		}catch(JWTCreationException ex) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado" + ex);
			
		}
	}
	
	private Instant expirationTime() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
		
	}
	
	public String extractSubject(String token) {
		
		try {
			String subject = JWT.require(algorithm)
								.withIssuer("auth-api")
								.build()
								.verify(token)
								.getSubject();
			
			return subject;
			
			
		}catch(JWTVerificationException ex) {
			throw new RuntimeException("Falha ao validar token", ex);
			
		}
		
	}
	
	public boolean validateToken(String token) {
		
		try {
			JWT.require(algorithm)
				.withIssuer("auth-api")
				.build()
				.verify(token);
			
			return true;
			
		}catch(JWTVerificationException ex) {
			throw new RuntimeException("Falha ao validar token", ex);
			
		}
		
	}

}
