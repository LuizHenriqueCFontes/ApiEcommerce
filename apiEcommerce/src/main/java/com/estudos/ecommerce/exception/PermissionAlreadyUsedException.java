package com.estudos.ecommerce.exception;

public class PermissionAlreadyUsedException extends RuntimeException {
	
	public PermissionAlreadyUsedException(String message) {
		super(message);
		
	}
}
