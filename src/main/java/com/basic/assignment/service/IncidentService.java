package com.basic.assignment.service;


import java.util.List;
import java.util.Optional;

import com.basic.assignment.entity.Incident;

public interface IncidentService {

	Incident createIncident(Incident incident, Long user_id);

	List<Incident> getUserIncidents(Long userId);

	Optional<Incident> getIncidentById(String incidentId);

	Incident updateIncident(Incident existingIncident);

}
