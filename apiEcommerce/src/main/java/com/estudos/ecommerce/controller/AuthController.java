package com.estudos.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.ecommerce.model.usuario.AuthResponseDTO;
import com.estudos.ecommerce.model.usuario.LoginDTO;
import com.estudos.ecommerce.model.usuario.RegisterDTO;
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
	public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDTO data) {
		AuthResponseDTO response = authService.login(data);

		return ResponseEntity.ok(response);

	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterDTO data) {	
		AuthResponseDTO response = authService.register(data);

		return ResponseEntity.ok(response);

	}
	
	
	


}
