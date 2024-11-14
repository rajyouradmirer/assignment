package com.basic.assignment.Exception;

public class IncidentNotFoundException extends RuntimeException {
	
	public IncidentNotFoundException(String email) {
        super("Incident not found with id: " + email);
    }
}
