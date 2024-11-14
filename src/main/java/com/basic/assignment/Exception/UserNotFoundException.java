package com.basic.assignment.Exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String email) {
        super("User is not found: " + email);
    }
}
