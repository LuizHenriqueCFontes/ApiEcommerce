package com.estudos.ecommerce.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudos.ecommerce.exception.EmailCadastradoException;
import com.estudos.ecommerce.exception.UsuarioNaoEncontradoException;
import com.estudos.ecommerce.model.usuario.AuthResponseDTO;
import com.estudos.ecommerce.model.usuario.LoginDTO;
import com.estudos.ecommerce.model.usuario.RegisterDTO;
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
	
	public AuthResponseDTO login(LoginDTO data) {
		
		var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		
		var authentication = authenticationManager.authenticate(authToken);
		
		Usuario usuario = (Usuario) authentication.getPrincipal();

		String token = tokenService.generateToken(usuario);

		AuthResponseDTO response = new AuthResponseDTO(token, data.email());

		return response;
		
	}
	
	public AuthResponseDTO register(RegisterDTO data) {
		String senhaCriptografada = passwordEncoder.encode(data.password());

		if(usuarioRepository.existsByEmail(data.email())) {
			throw new EmailCadastradoException("Email indisponivel");

		}

		Usuario usuario = new Usuario(data.username(), data.email(), senhaCriptografada, UserRole.USER);
		
		String token = tokenService.generateToken(usuario);

		usuarioRepository.save(usuario);

		AuthResponseDTO header = new AuthResponseDTO(token, data.email());
		
		return header;
	}

}
