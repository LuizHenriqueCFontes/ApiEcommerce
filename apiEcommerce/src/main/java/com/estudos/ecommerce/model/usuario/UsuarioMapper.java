package com.estudos.ecommerce.model.usuario;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	AuthResponseDTO toAuthDTO(Usuario usuario);
	
	ListDTO toListDTO(Usuario usuario);

	List<AuthResponseDTO> toAuthDTO(List<Usuario> usuarios);

	List<ListDTO> toListDTO(List<Usuario> usuarios);
	
}
