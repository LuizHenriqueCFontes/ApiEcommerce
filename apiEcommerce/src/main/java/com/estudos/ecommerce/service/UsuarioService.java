package com.estudos.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudos.ecommerce.exception.UsuarioNaoEncontradoException;
import com.estudos.ecommerce.model.usuario.ListDTO;
import com.estudos.ecommerce.model.usuario.Usuario;
import com.estudos.ecommerce.model.usuario.UsuarioMapper;
import com.estudos.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;
	
	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;

	}
	
	public List<ListDTO> listUsers() {
		List<Usuario> users = usuarioRepository.findAll();

		List<ListDTO> listUsers = usuarioMapper.toListDTO(users);
		
		return listUsers;
		
	}
	
	public Usuario editUser(String id, Usuario usuario) {
		
		Usuario user = usuarioRepository.findById(id)
										.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
		
		if(usuario.getUsername() != null && !usuario.getUsername().isBlank()) {
			user.setUsername(usuario.getUsername());
			
		}
		
		if(usuario.getEmail() != null && !usuario.getEmail().isBlank()) {
			user.setEmail(usuario.getEmail());
			
		}
		
		if(usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
			user.setPassword(usuario.getPassword());
			
		}
		
		if(usuario.getRole() != null) {
			user.setRole(usuario.getRole());
			
		}
		
		return usuarioRepository.save(user);
			
			
			
		
	}
	
	public void delete(String id) {
		if(!usuarioRepository.existsById(id)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
			
		}
		
		usuarioRepository.deleteById(id);
		
	}
	

}
