package com.estudos.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.ecommerce.model.usuario.ListDTO;
import com.estudos.ecommerce.model.usuario.UpdateDTO;
import com.estudos.ecommerce.model.usuario.UpdatePasswordDTO;
import com.estudos.ecommerce.model.usuario.UpdateRoleDTO;
import com.estudos.ecommerce.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;

	}

	@GetMapping
	public ResponseEntity<List<ListDTO>> listUsers(@RequestParam(required = false) String username) {
		List<ListDTO> users = usuarioService.listUsers(username);

		return ResponseEntity.ok(users);

	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<UpdateDTO> update(@PathVariable String id, @RequestBody @Valid UpdateDTO data){
		UpdateDTO user = usuarioService.update(id, data);
		
		return ResponseEntity.ok(user);
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		usuarioService.delete(id);

		return ResponseEntity.noContent().build();

	}
	
	@PatchMapping("/{id}/password")
	public ResponseEntity<Void> updatePassword(@PathVariable String id, @Valid @RequestBody UpdatePasswordDTO data) {
		usuarioService.updatePassword(id, data);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PatchMapping("/{id}/role")
	public ResponseEntity<Void> updateRole(@PathVariable String id, @RequestBody @Valid UpdateRoleDTO data) {
		usuarioService.updateRole(id, data);
		
		return ResponseEntity.noContent().build();
		
	}
	

}
