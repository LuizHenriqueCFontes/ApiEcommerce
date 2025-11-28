package com.estudos.ecommerce.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.ecommerce.model.usuario.LoginDTO;
import com.estudos.ecommerce.repository.UsuarioRepository;
import com.estudos.ecommerce.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO data) {
		String token = authService.login(data.email(), data.password());
		
		return ResponseEntity.ok(ResponseAuthDTO data);
		
		
	}
	


}
