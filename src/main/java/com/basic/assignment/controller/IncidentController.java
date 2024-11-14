package com.basic.assignment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basic.assignment.Exception.ApiResponseException;
import com.basic.assignment.Exception.IncidentNotFoundException;
import com.basic.assignment.Exception.UserNotFoundException;
import com.basic.assignment.entity.Incident;
import com.basic.assignment.service.IncidentService;

@RestController
@RequestMapping("/api/incident")
public class IncidentController {

	@Autowired
	private IncidentService incidentService;

	@GetMapping("/main")
	public String health_check() {
		return "working fine main incident!!!!!!";
	}

	@PostMapping("/add-incident/{user_id}")
	public ResponseEntity<?> createIncident(@RequestBody Incident incident, @PathVariable Long user_id) {

		try {
			return ResponseEntity.ok(incidentService.createIncident(incident, user_id));
		} catch (UserNotFoundException ex) {
			ApiResponseException responseException = new ApiResponseException(400, ex.getMessage());
			return ResponseEntity.badRequest().body(responseException);
		}
	}

	@GetMapping("/getAllIncident/{userId}")
	public ResponseEntity<?> getUserIncidents(@PathVariable Long userId) {

		try {
			return ResponseEntity.ok(incidentService.getUserIncidents(userId));
		} catch (UserNotFoundException ex) {
			ApiResponseException responseException = new ApiResponseException(400, ex.getMessage());
			return ResponseEntity.badRequest().body(responseException);
		}
	}

	@GetMapping("/{incidentId}")
	public ResponseEntity<?> getIncidentById(@PathVariable String incidentId) {

		try {
			Optional<Incident> incident = incidentService.getIncidentById(incidentId);
			return incident.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		} catch (IncidentNotFoundException ex) {
			ApiResponseException responseException = new ApiResponseException(400, ex.getMessage());
			return ResponseEntity.badRequest().body(responseException);
		}
	}

	// Endpoint to update an existing incident
	@PutMapping("/update/{incidentId}")
	public ResponseEntity<?> updateIncidentmehtod(@PathVariable String incidentId,
			@RequestBody Incident updatedIncident, @RequestParam Long userId) {
		// First, check if the incident exists

		try {
			Optional<Incident> existingIncidentOpt = incidentService.getIncidentById(incidentId);

			Incident existingIncident = existingIncidentOpt.get();

			// Check if the incident belongs to the user
			if (existingIncident.getUser().getId().equals(userId)) {
			
			

			// Check if the incident is closed. If it is, don't allow editing.
			if ("closed".equals(existingIncident.getStatus())) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Incident is closed and cannot be
																				// updated
			}

			// Update the incident fields
			if (updatedIncident.getDetails() != null) {
				existingIncident.setDetails(updatedIncident.getDetails());
			}
			if (updatedIncident.getPriority() != null) {
				existingIncident.setPriority(updatedIncident.getPriority());
			}
			if (updatedIncident.getStatus() != null) {
				existingIncident.setStatus(updatedIncident.getStatus());
			}

			// Save the updated incident
			Incident savedIncident = incidentService.updateIncident(existingIncident);
			return ResponseEntity.ok(savedIncident); // Return updated incident
			
		}else {
				 throw new UserNotFoundException("Incident found but user is not found");
			}
		} catch (IncidentNotFoundException ex) {
			ApiResponseException responseException = new ApiResponseException(400, ex.getMessage());
			return ResponseEntity.badRequest().body(responseException);
		}catch (UserNotFoundException ex) {
			ApiResponseException responseException = new ApiResponseException(400, ex.getMessage());
			return ResponseEntity.badRequest().body(responseException);
		}

	}
}