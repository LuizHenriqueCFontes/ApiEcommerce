package com.estudos.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.ecommerce.exception.UsuarioNaoEncontradoException;
import com.estudos.ecommerce.model.usuario.ListDTO;
import com.estudos.ecommerce.model.usuario.UpdateDTO;
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
	
	@Transactional
	public UpdateDTO update(String id, UpdateDTO data) {
		Usuario usuario = usuarioRepository.findById(id)
											.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
		
		if(data.username() != null && !data.username().isBlank()) {
			usuario.setUsername(data.username());
			
		}
		
		if(data.email() != null && !data.email().isBlank()) {
			usuario.setEmail(data.email());
			
		}
		
		usuarioRepository.save(usuario);
		
		UpdateDTO user = usuarioMapper.toUpdateDTO(usuario);
		
		return user;
		
		
	}
	
	@Transactional
	public void delete(String id) {
		if(!usuarioRepository.existsById(id)) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
			
		}
		
		usuarioRepository.deleteById(id);
		
	}
	

}
