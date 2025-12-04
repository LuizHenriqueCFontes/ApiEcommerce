package com.estudos.ecommerce.model.usuario;

public record RegisterDTO(String username, String email, String password, UserRole role) {

}
