package com.estudos.ecommerce.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudos.ecommerce.exception.EmailCadastradoException;
import com.estudos.ecommerce.model.usuario.UserRole;
import com.estudos.ecommerce.model.usuario.Usuario;
import com.estudos.ecommerce.repository.UsuarioRepository;
import com.estudos.ecommerce.security.TokenService;

@Service
public class AuthService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			TokenService tokenService) {
		
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
		
	}
	
	public String login(String email, String password) {
		var authToken = new UsernamePasswordAuthenticationToken(email, password);
		
		var authentication = authenticationManager.authenticate(authToken);
		
		String token = tokenService.generateToken(authentication.getName());
		
		return token;
		
	}
	
	public String register(String username, String email, String password) {
		String senhaCriptografada = passwordEncoder.encode(password);
		
		if(usuarioRepository.existsByEmail(email)) {
			throw new EmailCadastradoException("Email ja cadastrado");
			
		}
		
		Usuario usuario = new Usuario(username, email, senhaCriptografada, UserRole.USER);
		
		usuarioRepository.save(usuario);
		
		String token = tokenService.generateToken(usuario.getEmail());
		
		return token;
	
		
	}

}
