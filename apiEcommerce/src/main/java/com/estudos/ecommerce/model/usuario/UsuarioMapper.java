package com.estudos.ecommerce.model.usuario;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	ResponseAuthDTO toDTO(Usuario usuario);
	
	List<ResponseAuthDTO> listTDO(List<Usuario> usuarios);
	
}
