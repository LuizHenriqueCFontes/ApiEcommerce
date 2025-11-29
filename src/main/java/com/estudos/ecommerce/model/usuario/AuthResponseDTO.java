package com.estudos.ecommerce.model.usuario;

public record AuthResponseDTO(String type, String token, String suject) {
	
	public AuthResponseDTO(String token, String subject) {
		this("Bearer", token, subject);
		
	}

}
