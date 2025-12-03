package com.estudos.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.ecommerce.model.usuario.ListDTO;
import com.estudos.ecommerce.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;

	}

	@GetMapping("list")
	public ResponseEntity<List<ListDTO>> listUsers() {
		List<ListDTO> users = usuarioService.listUsers();

		return ResponseEntity.ok(users);

	}
	

}
