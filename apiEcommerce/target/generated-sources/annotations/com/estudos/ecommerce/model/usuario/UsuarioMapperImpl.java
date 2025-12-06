package com.estudos.ecommerce.model.usuario;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-05T21:17:23-0300",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public AuthResponseDTO toAuthDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String type = null;
        String token = null;
        String subject = null;

        AuthResponseDTO authResponseDTO = new AuthResponseDTO( type, token, subject );

        return authResponseDTO;
    }

    @Override
    public ListDTO toListDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String username = null;
        String email = null;

        username = usuario.getUsername();
        email = usuario.getEmail();

        ListDTO listDTO = new ListDTO( username, email );

        return listDTO;
    }

    @Override
    public UpdateDTO toUpdateDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String username = null;
        String email = null;

        username = usuario.getUsername();
        email = usuario.getEmail();

        UpdateDTO updateDTO = new UpdateDTO( username, email );

        return updateDTO;
    }

    @Override
    public List<AuthResponseDTO> toAuthDTO(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<AuthResponseDTO> list = new ArrayList<AuthResponseDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toAuthDTO( usuario ) );
        }

        return list;
    }

    @Override
    public List<ListDTO> toListDTO(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<ListDTO> list = new ArrayList<ListDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toListDTO( usuario ) );
        }

        return list;
    }

    @Override
    public List<UpdateDTO> toUpdateDTO(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UpdateDTO> list = new ArrayList<UpdateDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toUpdateDTO( usuario ) );
        }

        return list;
    }
}
