package com.estudos.ecommerce.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudos.ecommerce.exception.IncorrectPasswordException;
import com.estudos.ecommerce.exception.UsuarioNaoEncontradoException;
import com.estudos.ecommerce.model.usuario.ListDTO;
import com.estudos.ecommerce.model.usuario.UpdateDTO;
import com.estudos.ecommerce.model.usuario.UpdatePasswordDTO;
import com.estudos.ecommerce.model.usuario.Usuario;
import com.estudos.ecommerce.model.usuario.UsuarioMapper;
import com.estudos.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;
	private final PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;
		this.passwordEncoder = passwordEncoder;
		
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
		try {
			usuarioRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
			
		}
		
	}
	
	@Transactional
	public void updatePassword(String id, UpdatePasswordDTO data) {
		Usuario usuario = usuarioRepository.findById(id)
											.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String authenticatedUserId = auth.getName();
		
		boolean isAdmin = auth.getAuthorities().stream()
												.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
		
		if(!passwordEncoder.matches(data.oldPassword(), usuario.getPassword())) {
			throw new IncorrectPasswordException("Senha incorreta");
			
		}
		
		if(data.newPassword() != null && !data.newPassword().isBlank()) {
			String senhaCriptografada = passwordEncoder.encode(data.newPassword());
			
			usuario.setPassword(senhaCriptografada);
			
		}
		
		usuarioRepository.save(usuario);
		
	}
	

}
