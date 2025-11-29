package com.estudos.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.ecommerce.model.usuario.ResponseAuthDTO;
import com.estudos.ecommerce.model.usuario.Usuario;
import com.estudos.ecommerce.model.usuario.UsuarioMapper;
import com.estudos.ecommerce.model.usuario.UsuarioResponseDTO;
import com.estudos.ecommerce.service.AuthService;
import com.estudos.ecommerce.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	private final UsuarioMapper usuarioMapper;
	
	public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;	
	}
	
	@GetMapping("/list-users")
	public ResponseEntity<List<ResponseAuthDTO>> listUsers() {
		
		List<Usuario> users = usuarioService.listUsers();
		
		
		List<ResponseAuthDTO> listDTO = usuarioMapper.listTDO(users);
		
		return ResponseEntity.ok(listDTO);
		
	}
	
	

}
