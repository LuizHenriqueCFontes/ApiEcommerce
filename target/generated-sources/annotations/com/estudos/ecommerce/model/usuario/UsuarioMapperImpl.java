package com.estudos.ecommerce.model.usuario;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-29T12:50:39-0300",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public ResponseAuthDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String type = null;
        String subject = null;
        String token = null;

        ResponseAuthDTO responseAuthDTO = new ResponseAuthDTO( type, subject, token );

        return responseAuthDTO;
    }

    @Override
    public List<ResponseAuthDTO> listTDO(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<ResponseAuthDTO> list = new ArrayList<ResponseAuthDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toDTO( usuario ) );
        }

        return list;
    }
}
