package com.basic.assignment.Exception;

public class EmailAlreadyInUseException extends RuntimeException {
	
	public EmailAlreadyInUseException(String email) {
        super("Email is already in use: " + email);
    }
}
